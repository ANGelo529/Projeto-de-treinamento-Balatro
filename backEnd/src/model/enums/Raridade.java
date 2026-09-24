package backEnd.src.model.enums;

public enum Raridade {
    COMUM("Comum"),
    INCOMUM("Incomum"),
    RARO("Raro"),
    LENDARIO("Lendário");

    private final String nomeExibicao;

    Raridade(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}
