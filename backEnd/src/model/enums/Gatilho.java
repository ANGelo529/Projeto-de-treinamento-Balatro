package backEnd.src.model.enums;

public enum Gatilho {
    PORCARTA("Ativa a cada carta"),
    FINALMAO("Ativo no final da mão"),
    PASSIVO("Seu efeito é passivo");

    private final String nomeExibicao;

    Gatilho(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}
