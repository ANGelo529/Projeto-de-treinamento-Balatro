package backEnd.src.model.enums;

public enum MaoPoker {
    CARTAALTA("Carta Alta"),
    PAR("Par"),
    DOISPARES("Dois pares"),
    TRINCA("Trinca"),
    SEQUENCIA("Sequência"),
    FLUSH("Flush"),
    FULLHOUSE("FullHouse"),
    QUADRA("Quadra"),
    STRAIGHTFLUSH("Straight Flush");

    private final String nomeExibicao;

    MaoPoker(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}
