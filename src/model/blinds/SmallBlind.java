package model.blinds;

public class SmallBlind extends Blind {
    // Classe que inicializa a maneira padrão de um Small Blind
    SmallBlind(int anteAtual) {
        String nome = "Small Blind";
        int dinheiroAoGanhar = 3;
        double multiplicadorValorBase = 1.0;
        int anteMinimoAparicao = 0;
        long valorBlind = (long) (Ante.valoresBase[anteAtual] * multiplicadorValorBase);
        super(nome,dinheiroAoGanhar,valorBlind,multiplicadorValorBase,anteMinimoAparicao);

        // String nome, int dinheiroAoGanhar, long valorBlind, int
        // multiplicadorValorBase,
        // int anteMinimoAparicao
    }
}
