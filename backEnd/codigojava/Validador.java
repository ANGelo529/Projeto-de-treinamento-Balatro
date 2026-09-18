package backEnd.codigojava;

import java.util.ArrayList;
import java.util.HashMap;

public class Validador {
    /* ------------------ VARIÁVEIS ------------------ */

    private static HashMap<String, Ponto> listaMaosPoker = setListaMaosPoker();

    /* ------------------ MéTODOS ------------------ */

    public static void calcularJogada(ArrayList<Carta> maoJogada, ArrayList<Coringa> maoCoringa) {
        int[] qtdNaipes = new int[4];
        int[] qtdOrdemDeValor = new int[13];

        for (int i = 0; i < maoJogada.size(); i++) {
            int naipe = DeckCarta.getListanaipes().indexOf(maoJogada.get(i).getNaipe());
            int ordemDeValor = Carta.descobrirPosOrdemDeValor(maoJogada.get(i).getOrdemDeValor());
            qtdNaipes[naipe]++;
            qtdOrdemDeValor[ordemDeValor]++;
        }

        Ponto pontosMaoJogada = verificarJogada(qtdOrdemDeValor, qtdNaipes);
        ContextoMao contextoMao = new ContextoMao(maoJogada, pontosMaoJogada);
        DeckCoringa.verificarCoringa(contextoMao, maoCoringa);
    }

    public static Ponto verificarJogada(int[] qtdOrdemDeValor, int[] qtdNaipe) {
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
                System.out.println(nomesMaospoker[i]);
                String nomeMaoJogada = nomesMaospoker[i];
                int ficha = listaMaosPoker.get(nomesMaospoker[i]).getFicha();
                int multi = listaMaosPoker.get(nomesMaospoker[i]).getMulti();
                return new Ponto(ficha, multi, nomeMaoJogada);
            }
        }

        return new Ponto();
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

    public static HashMap<String, Ponto> getListaMaosPoker() {
        return listaMaosPoker;
    }

    public static void setListaMaosPoker(HashMap<String, Ponto> listaMaosPoker) {
        Validador.listaMaosPoker = listaMaosPoker;
    }

    public static HashMap<String, Ponto> setListaMaosPoker() {
        HashMap<String, Ponto> aux = new HashMap<>();
        // String[] nomesMaospoker = { "StraightFlush", "Quadra", "FullHouse", "Flush", "Sequencia", "Trinca", "DoisPares",
        //         "Par", "CartaAlta" };
        String[] nomesMaospoker = { "CartaAlta", "Par", "DoisPares", "Trinca", "Sequencia", "Flush", "FullHouse",
                "Quadra", "StraightFlush" };
        int[] fichas = { 5, 10, 20, 30, 30, 35, 40, 60, 100 };
        int[] multi = { 1, 2, 2, 3, 4, 4, 4, 7, 8 };

        for (int i = 0; i < 9; i++) {
            aux.put(nomesMaospoker[i], new Ponto(fichas[i], multi[i], 1));
        }

        return aux;
    }

}
