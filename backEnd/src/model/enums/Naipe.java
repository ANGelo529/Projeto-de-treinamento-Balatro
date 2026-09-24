package backEnd.src.model.enums;

public enum Naipe {
    OUROS("Ouros"),
    ESPADAS("Espadas"),
    COPAS("Copas"),
    PAUS("Paus");

    private final String nomeExibicao;

    Naipe(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}
