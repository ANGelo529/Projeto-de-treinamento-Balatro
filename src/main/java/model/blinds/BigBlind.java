package model.blinds;

public class BigBlind extends Blind{
    // Classe que inicializa a maneira padrão de um Big Blind
    BigBlind(int anteAtual){
super(
            "Big Blind",                                  // nome
            5,                                            // dinheiroAoGanhar
            (long) (Ante.valoresBase[anteAtual] * 1.5),   // valorBlind calculado inline
            1.5,                                          // multiplicadorValorBase
            0                                             // anteMinimoAparicao
        );
    }
}
