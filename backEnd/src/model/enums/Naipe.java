package backEnd.src.model.enums;

public enum Naipe {
    ESPADAS("Espadas"),
    COPAS("Copas"),
    PAUS("Paus"),
    OUROS("Ouros");

    private final String nomeExibicao;

    Naipe(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}
