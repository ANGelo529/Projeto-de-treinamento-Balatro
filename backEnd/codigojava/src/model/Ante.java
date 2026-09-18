package backEnd.codigojava.src.model;

public class Ante {
    /* ------------------ VARIÁVEIS ------------------ */

    private static int numAnteAtual = 1;
    private static Blind[] blindsAtuais = new Blind[3];
    private static final int[] valoresBase = { 100, 300, 800, 2000, 5000, 11000, 20000, 35000, 50000 };

    /* ------------------ CONSTRUTOR ------------------ */
    
    public Ante() {
        blindsAtuais = atualizarBlind(numAnteAtual);
    }

    public Ante(int anteAtual) {
        blindsAtuais = atualizarBlind(anteAtual);
    }

    /* ------------------ MéTODOS ------------------ */

    public static Blind[] atualizarBlind() {
        Blind[] blind = new Blind[3];
        blind[0] = getSmallBlind(numAnteAtual);
        blind[1] = getBigBlind(numAnteAtual);
        blind[2] = getBossBlind(numAnteAtual);
        return blind;
    }

    public static Blind[] atualizarBlind(int anteAtual) {
        Blind[] blind = new Blind[3];
        blind[0] = getSmallBlind(anteAtual);
        blind[1] = getBigBlind(anteAtual);
        blind[2] = getBossBlind(anteAtual);
        return blind;
    }

    public static Blind getBossBlind(int anteAtual) {
        Blind blind = new Blind();
        while (true) {
            Blind aux = Blind.gerarBlindAleatorio(valoresBase, anteAtual);
            if (aux.getAnteMinimoAparicao() <= anteAtual) {
                blind = aux;
                blind.setValorBlind((int) (valoresBase[anteAtual] * aux.getMultiplicadorValorBase()));
                return blind;
            }
        }
    }

    public static Blind getBigBlind(int anteAtual) {
        String nomeBlind = "Big Blind";
        double multiplicadorValorBase = 1.5;
        int valorBlind = (int) (valoresBase[anteAtual] * multiplicadorValorBase);
        Blind auxBlind = new Blind(nomeBlind, multiplicadorValorBase, valorBlind);
        return auxBlind;
    }

    public static Blind getSmallBlind(int anteAtual) {
        String nomeBlind = "Small Blind";
        double multiplicadorValorBase = 1;
        int valorBlind = (int) (valoresBase[anteAtual] * multiplicadorValorBase);
        Blind auxBlind = new Blind(nomeBlind, multiplicadorValorBase, valorBlind);
        return auxBlind;
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
