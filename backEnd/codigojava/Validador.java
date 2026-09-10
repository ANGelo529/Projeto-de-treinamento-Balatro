package backEnd.codigojava;

import java.util.ArrayList;
import java.util.HashMap;

public class Validador {
    /* ------------------ VARIÁVEIS ------------------ */
    
    private static HashMap<String, Pontos> listaMaosPoker = setListaMaosPoker();


    /* ------------------ MéTODOS ------------------ */

    public static void calcularJogada() {
    }

    public static void verificarJogada(int[] qtdOrdemDeValor, int[] qtdNaipe) {
        boolean[] jogadasVerificadas = {
                validarStraightFlush(qtdOrdemDeValor, qtdNaipe),
                validarQuadra(qtdOrdemDeValor),
                validarFullHouse(qtdOrdemDeValor),
                validarFlush(qtdNaipe),
                validarSequencia(qtdOrdemDeValor),
                validarTrinca(qtdOrdemDeValor),
                validarDoisPares(qtdOrdemDeValor),
                validarPar(qtdOrdemDeValor),
                validarCartaAlta(qtdOrdemDeValor)
        };

        String[] nomesMaospoker = {
                "StraightFlush",
                "Quadra",
                "FullHouse",
                "Flush",
                "Sequencia",
                "Trinca",
                "DoisPares",
                "Par",
                "CartaAlta"
        };

        for (int i = 0; i < jogadasVerificadas.length; i++) {
            if (jogadasVerificadas[i]) {
                listaMaosPoker.get(nomesMaospoker[i]).uparMao();
                break;
            }
        }

    }

    public static boolean validarStraightFlush(int[] qtdOrdemDeValor, int[] qtdNaipe) {
        if (validarSequencia(qtdOrdemDeValor) && validarFlush(qtdNaipe)) {
            return true;
        }

        return false;
    }

    public static boolean validarQuadra(int[] qtdOrdemDeValor) {
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] >= 4) {
                return true;
            }
        }

        return false;
    }

    public static boolean validarFullHouse(int[] qtdOrdemDeValor) {
        boolean temTrinca = false;
        boolean temPar = false;

        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 3) {
                temTrinca = true;
            }

            if (qtdOrdemDeValor[i] == 2) {
                temPar = true;
            }

            if (temTrinca && temPar) {
                return true;
            }
        }

        return false;
    }

    public static boolean validarFlush(int[] qtdNaipe) {
        for (int i = 0; i < qtdNaipe.length; i++) {
            if (qtdNaipe[i] == 5) {
                return true;
            }
        }

        return false;
    }

    public static boolean validarSequencia(int[] qtdOrdemDeValor) {

        int qtdValidos = 1;

        for (int i = 0; i < qtdOrdemDeValor.length + 4; i++) {

            if (qtdOrdemDeValor[i % qtdOrdemDeValor.length] > 0) {
                qtdValidos++;

                if (qtdValidos == 5) {
                    return true;
                }

            } else {
                qtdValidos = 0;
            }
        }

        return false;
    }

    public static boolean validarTrinca(int[] qtdOrdemDeValor) {
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 3) {
                return true;
            }
        }

        return false;
    }

    public static boolean validarDoisPares(int[] qtdOrdemDeValor) {
        int qtdPares = 0;
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 2) {
                qtdPares++;
            }
        }

        if (qtdPares >= 2) {
            return true;
        }

        return false;
    }

    public static boolean validarPar(int[] qtdOrdemDeValor) {
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 2) {
                return true;
            }
        }

        return false;
    }

    public static boolean validarCartaAlta(int[] qtdOrdemDeValor) {
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 1) {
                return true;
            }
        }

        return false;
    }

    public static HashMap<String, Pontos> getListaMaosPoker() {
        return listaMaosPoker;
    }

    public static void setListaMaosPoker(HashMap<String, Pontos> listaMaosPoker) {
        Validador.listaMaosPoker = listaMaosPoker;
    }

    public static HashMap<String, Pontos> setListaMaosPoker() {
        HashMap<String, Pontos> aux = new HashMap<>();
        String[] nomesMaospoker = { "StraightFlush", "Quadra", "FullHouse", "Flush", "Sequencia", "Trinca", "DoisPares",
                "Par", "CartaAlta" };
        int[] fichas = { 5, 10, 20, 30, 30, 35, 40, 60, 100 };
        int[] multi = { 1, 2, 2, 3, 4, 4, 4, 7, 8 };

        for (int i = 0; i < 9; i++) {
            aux.put(nomesMaospoker[i], new Pontos(fichas[i], multi[i], 1));
        }

        return aux;
    }

}
