package backEnd.codigojava;

import java.util.ArrayList;

public record ContextoMao(
        String tipoMao,
        ArrayList<Carta> cartasJogadas,
        int fichasAtuais,
        int multAtual) {
}
