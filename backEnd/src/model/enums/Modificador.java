package backEnd.src.model.enums;

public enum Modificador {
    CORG_BASE("Base"),
    CORG_FOIL("Foil"),
    CORG_HOLO("Holográfico"),
    CORG_POLY("Policromático"),
    CORG_NEGA("Negativo");

    private final String nomeExibicao;

    Modificador(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}
