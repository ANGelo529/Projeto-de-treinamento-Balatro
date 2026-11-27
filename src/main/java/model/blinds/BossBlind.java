package model.blinds;



public class BossBlind extends Blind {

    public BossBlind(int anteAtual) {
        super(
        "Boss Blind", // nome 
        7, // dinheiro ao ganhar
        (long) (Ante.valoresBase[anteAtual] * 2.5), // valor Blind calculado baseado no anteAtual
        2.5 // multiplicador Valor Base
        );
    }
}
