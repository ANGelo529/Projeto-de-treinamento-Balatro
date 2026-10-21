package model.blinds;

public class BigBlind extends Blind{
    // Classe que inicializa a maneira padrão de um Big Blind
    BigBlind(int anteAtual){
        String nome = "Big Blind";
        int dinheiroAoGanhar = 5;
        double multiplicadorValorBase = 1.5;
        int anteMinimoAparicao = 0;
        long valorBlind = (long) (Ante.valoresBase[anteAtual] * multiplicadorValorBase);
        super(nome,dinheiroAoGanhar,valorBlind,multiplicadorValorBase,anteMinimoAparicao);
    }
}
