package model.blinds;

import model.efeitos.EfeitoBlind;
import model.efeitos.EfeitoCoringa;
import model.enums.Gatilho;
import model.enums.Raridade;

public enum CatalogoBlind {
    BANANA(0, "", 2.0, 2,EfeitoBlind.dividirMult());

    private final int idBlind;
    private final String nome;
    private final double multiplicadorValorBase;
    private final int anteMinimoAparicao;
    private final EfeitoBlind efeitoBlind;

    private CatalogoBlind(int idBlind, String nome, double multiplicadorValorBase, int anteMinimoAparicao,
            EfeitoBlind efeitoBlind) {
        this.idBlind = idBlind;
        this.nome = nome;
        this.multiplicadorValorBase = multiplicadorValorBase;
        this.anteMinimoAparicao = anteMinimoAparicao;
        this.efeitoBlind = efeitoBlind;
    }

}
