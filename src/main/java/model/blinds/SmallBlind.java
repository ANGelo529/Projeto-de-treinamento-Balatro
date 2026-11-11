package model.blinds;

public class SmallBlind extends Blind {
    // Classe que inicializa a maneira padrão de um Small Blind
    SmallBlind(int anteAtual) {
        super(
        "Small Blind", // nome 
        3, // dinheiro ao ganhar
        (long) (Ante.valoresBase[anteAtual] * 1.0), // valor Blind calculado baseado no anteAtual
        1.0, // multiplicador Valor Base
        0); // ante mínimo aparição

    }
}
