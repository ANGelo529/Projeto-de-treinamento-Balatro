package model.blinds;

import Factory.BlindFactory;
import model.Efeitos.EfeitoBlind;

public class BossBlind extends Blind{
    // TODO: colocar para pegar um BossBlind random do factory e inicializar tudo aq
    private EfeitoBlind efeitoBlind;
    public BossBlind(int anteAtual){
        double multiplicadorValorBase = 2.0;
        long valorBlind = (long) (Ante.valoresBase[anteAtual] * multiplicadorValorBase);
    }

    public BossBlind(String nome,int dinheiroAoGanhar, double multiplicadorValorBase, int anteMinimoAparicao,EfeitoBlind efeitoBlind){
        super(nome,dinheiroAoGanhar,multiplicadorValorBase,anteMinimoAparicao);
        this.efeitoBlind = efeitoBlind;
    }
}
