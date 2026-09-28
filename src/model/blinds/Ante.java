package model.blinds;

/**
 * Gerenciador dos estágios de dificuldade do jogo (Ante).
 * Controla os multiplicadores e escalas de pontuação para o Small, Big e Boss Blind.
 */
public class Ante {
    /* ------------------ VARIÁVEIS ------------------ */

    /** Número do Ante/rodada atual */
    private static int numAnteAtual = 1;
    
    /** Array com as 3 metas de pontuação ativas (0 = Small, 1 = Big, 2 = Boss) */
    private static Blind[] blindsAtuais = new Blind[3];
    
    /** Tabela progressiva de pontuação base por nível de Ante */
    private static final int[] valoresBase = { 100, 300, 800, 2000, 5000, 11000, 20000, 35000, 50000 };

    /* ------------------ CONSTRUTORES ------------------ */
    
    public Ante() {
        blindsAtuais = atualizarBlind(numAnteAtual);
    }

    public Ante(int anteAtual) {
        blindsAtuais = atualizarBlind(anteAtual);
    }

    /* ------------------ MÉTODOS ------------------ */

    /**
     * Atualiza e retorna o trio de Blinds para o Ante atual.
     */
    public static Blind[] atualizarBlind() {
        Blind[] blind = new Blind[3];
        blind[0] = getSmallBlind(numAnteAtual);
        blind[1] = getBigBlind(numAnteAtual);
        blind[2] = getBossBlind(numAnteAtual);
        return blind;
    }

    /**
     * Atualiza e retorna o trio de Blinds para um Ante específico fornecido.
     * 
     * @param anteAtual Índice do estágio a ser configurado
     */
    public static Blind[] atualizarBlind(int anteAtual) {
        Blind[] blind = new Blind[3];
        blind[0] = getSmallBlind(anteAtual);
        blind[1] = getBigBlind(anteAtual);
        blind[2] = getBossBlind(anteAtual);
        return blind;
    }

    /**
     * Gera o Boss Blind sorteado validando o limite mínimo de aparição e aplicando multiplicadores.
     */
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

    /**
     * Gera e calcula o valor da meta para o Big Blind (Multiplicador base 1.5x).
     */
    public static Blind getBigBlind(int anteAtual) {
        String nomeBlind = "Big Blind";
        double multiplicadorValorBase = 1.5;
        int valorBlind = (int) (valoresBase[anteAtual] * multiplicadorValorBase);
        Blind auxBlind = new Blind(nomeBlind, multiplicadorValorBase, valorBlind);
        return auxBlind;
    }

    /**
     * Gera e calcula o valor da meta para o Small Blind (Multiplicador base 1.0x).
     */
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