package model.blinds;

import factory.BlindFactory;
import model.efeitos.EfeitoBlind;

public class BossBlind extends Blind {
    // TODO: colocar para pegar um BossBlind random do factory e inicializar tudo aq
    private EfeitoBlind efeitoBlind;
    private int idIngame;

    public BossBlind(int anteAtual) {
        double multiplicadorValorBase = 2.0;
        long valorBlind = (long) (Ante.valoresBase[anteAtual] * multiplicadorValorBase);
    }

    public BossBlind(String nome, int dinheiroAoGanhar, double multiplicadorValorBase, int anteMinimoAparicao,
            EfeitoBlind efeitoBlind) {
        super(nome, dinheiroAoGanhar, multiplicadorValorBase, anteMinimoAparicao);
        this.efeitoBlind = efeitoBlind;
    }

    public EfeitoBlind getEfeitoBlind() {
        return efeitoBlind;
    }

    public void setEfeitoBlind(EfeitoBlind efeitoBlind) {
        this.efeitoBlind = efeitoBlind;
    }

    public int getIdIngame() {
        return idIngame;
    }

    public void setIdIngame(int idIngame) {
        this.idIngame = idIngame;
    }
}
