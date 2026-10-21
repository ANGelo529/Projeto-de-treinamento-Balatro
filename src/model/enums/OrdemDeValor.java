package model.enums;

public enum OrdemDeValor {
    DOIS("Dois", 2),
    TRES("Três", 3),
    QUATRO("Quatro", 4),
    CINCO("Cinco", 5),
    SEIS("Seis", 6),
    SETE("Sete", 7),
    OITO("Oito", 8),
    NOVE("Nove", 9),
    DEZ("Dez", 10),
    VALETE("Valete", 10),
    DAMA("Dama", 10),
    REI("Rei", 10),
    AS("Ás", 11);

    private final String nomeExibicao;
    private final int valorOrdemValor;

    OrdemDeValor(String nomeExibicao, int valorOrdemValor) {
        this.nomeExibicao = nomeExibicao;
        this.valorOrdemValor = valorOrdemValor;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public int getValorOrdemValor() {
        return valorOrdemValor;
    }
}
