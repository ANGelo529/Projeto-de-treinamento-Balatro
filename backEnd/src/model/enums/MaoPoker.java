package backEnd.src.model.enums;

public enum MaoPoker {
    STRAIGHTFLUSH("Straight Flush"),
    QUADRA("Quadra"),
    FULLHOUSE("FullHouse"),
    FLUSH("Flush"),
    SEQUENCIA("Sequência"),
    TRINCA("Trinca"),
    DOISPARES("Dois pares"),
    PAR("Par"),
    CARTAALTA("Carta Alta");

    private final String nomeExibicao;

    MaoPoker(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}
