package backEnd.codigojava;

import java.util.HashMap;

public class Ante {
    /* ------------------ VARIÁVEIS ------------------ */

    private static int numAnteAtual = 1;
    private static Blind[] blindsAtuais = atualizarBlind(numAnteAtual, new Blind[3]);
    private static final int[] valoresBase = { 100, 300, 800, 2000, 5000, 11000, 20000, 35000, 50000 };

    /* ------------------ MéTODOS ------------------ */

    public static Blind[] atualizarBlind(int anteAtual, Blind[] blind) {
        blind[0].setNome("Small Blind");
        blind[1].setNome("Big Blind");
        blind[0].setValorBlind(valoresBase[numAnteAtual]);
        blind[1].setValorBlind((int) (valoresBase[numAnteAtual] * 1.5));

        while (true) {
            Blind aux = Blind.gerarBlindAleatorio();
            if (aux.getAnteMinimoAparicao() <= numAnteAtual) {
                blind[2] = aux;
                blind[2].setValorBlind((int) (valoresBase[numAnteAtual] * blind[2].getMultiplicadorValorBase()));
                break;
            }
        }

        return blind;
    }

    /* ------------------ GETTERS & SETTERS ------------------ */
    
    public static int getNumAnteAtual() {
        return numAnteAtual;
    }

    public static void setNumAnteAtual(int numAnteAtual) {
        Ante.numAnteAtual = numAnteAtual;
    }

    public static Blind[] getBlindsAtuais() {
        return blindsAtuais;
    }

    public static void setBlindsAtuais(Blind[] blindsAtuais) {
        Ante.blindsAtuais = blindsAtuais;
    }
}
