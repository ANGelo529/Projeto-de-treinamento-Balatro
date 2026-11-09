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
    VALETE("Valete", "J", 10),
    DAMA("Dama", "Q", 10),
    REI("Rei", "K", 10),
    AS("Ás", "A", 11);

    private final String nomeExibicao;
    private final String nomeSimplificado;
    private final int valorOrdemValor;

    OrdemDeValor(String nomeExibicao, String nomeSimplificado, int valorOrdemValor) {
        this.nomeExibicao = nomeExibicao;
        this.valorOrdemValor = valorOrdemValor;
        this.nomeSimplificado = nomeSimplificado;
    }

    OrdemDeValor(String nomeExibicao, int valorOrdemValor) {
        this.nomeExibicao = nomeExibicao;
        this.valorOrdemValor = valorOrdemValor;
        this.nomeSimplificado = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public String getNomeSimplificado() {
        return nomeSimplificado;
    }

    public int getValorOrdemValor() {
        return valorOrdemValor;
    }
}
