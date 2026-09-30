package model.enums;

import java.util.ArrayList;

public enum Gatilho {
    PORCARTA("Ativa a cada carta"),
    FINALMAO("Ativo no final da mão"),
    FINALRODADA("Ativa no final da rodada"),
    BLINDSELECIONADO("Ativa quando o blind é selecionado"),
    PASSIVO("Seu efeito é passivo");

    private final String nomeExibicao;
    
    Gatilho(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}
