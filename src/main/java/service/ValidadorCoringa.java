package service;

import java.util.ArrayList;

import model.cartas.Carta;
import model.contextos.ContextoMao;
import model.coringas.Coringa;
import model.enums.Gatilho;

public class ValidadorCoringa {
    /*
     * TODO: terminar implementação
     */
    public static void verificarCoringasPassivos(ContextoMao contextoMao) {
    }

    /**
     * Executa um loop aninhado: para cada carta jogada, percorre todos os coringas
     * com gatilho PORCARTA.
     */
    public static void verificarCoringasPorCarta(ContextoMao contextoMao, ArrayList<Carta> cartasJogadas,
            ArrayList<Coringa> maoCoringa) {
        Gatilho tempoDeAtivicao;
        for (int i = 0; i < cartasJogadas.size(); i++) {
            for (int j = 0; j < maoCoringa.size(); j++) {
                tempoDeAtivicao = maoCoringa.get(j).getTempoDeAtivicao();
                if (maoCoringa.get(j) != null && tempoDeAtivicao.equals(Gatilho.PORCARTA)) {
                    // Define a carta corrente que será analisada pelas lambdas em EfeitoCoringa
                    contextoMao.setCoringaAtual(maoCoringa.get(j));
                    contextoMao.setCartaAuxiliar(cartasJogadas.get(i));
                    contextoMao = maoCoringa.get(j).efeitoCoringa(contextoMao);
                }
            }
        }
        contextoMao.setCartaAuxiliar(null);

    }

    /**
     * Executa os coringas que reagem apenas no final do cálculo do round
     * (Gatilho.FINALMAO).
     * 
     * ATENÇÃO (BUG DE ESTADO RESIDUAL): O objeto 'contextoMao' mantém a
     * 'cartaAuxiliar'
     * referente à última carta iterada no método anterior. Se algum coringa de
     * FINALMAO
     * tentar acessar 'getCartaAuxiliar()', estará avaliando uma carta residual em
     * vez do contexto global.
     */
    public static void verificarCoringasFinalRound(ContextoMao contextoMao,
            ArrayList<Coringa> maoCoringa) {
        Gatilho tempoDeAtivicao;
        for (int i = 0; i < maoCoringa.size(); i++) {
            tempoDeAtivicao = maoCoringa.get(i).getTempoDeAtivicao();
            if (maoCoringa.get(i) != null && tempoDeAtivicao.equals(Gatilho.FINALMAO)) {
                contextoMao = maoCoringa.get(i).efeitoCoringa(contextoMao);
            }
        }
    }
}
