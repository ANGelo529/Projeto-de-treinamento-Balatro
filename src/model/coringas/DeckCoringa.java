package model.coringas;

import model.cartas.Carta;
import model.contextos.*;
import model.enums.Gatilho;

import java.util.ArrayList;

/**
 * Gerenciador e executor do conjunto de Coringas equipados pelo jogador.
 */
public class DeckCoringa {

    /** Lista de Coringas atualmente equipados */
    private ArrayList<Coringa> maoCoringa = new ArrayList<>();

    /**
     * Ponto de entrada estático para processar e aplicar todos os efeitos de Coringas na mão.
     * 
     * @param contextoMao Estado atual do cálculo da mão.
     * @param maoCoringa Lista dos coringas equipados a serem testados.
     */
    public static void verificarCoringa(ContextoMao contextoMao, ArrayList<Coringa> maoCoringa) {
        ArrayList<Carta> cartasJogadas = contextoMao.getCartasJogadas();

        // 1º Passo: Dispara os coringas que reagem a cada carta pontuada individualmente
        verificarCoringasPorCarta(contextoMao, cartasJogadas, maoCoringa);

        // 2º Passo: Dispara os coringas que reagem ao valor final da mão inteira
        verificarCoringasFinalRound(contextoMao, maoCoringa);
    }

    /**
     * Executa um loop aninhado: para cada carta jogada, percorre todos os coringas com gatilho PORCARTA.
     */
    private static void verificarCoringasPorCarta(ContextoMao contextoMao, ArrayList<Carta> cartasJogadas,
            ArrayList<Coringa> maoCoringa) {
        Gatilho tempoDeAtivicao;
        for (int i = 0; i < cartasJogadas.size(); i++) {
            for (int j = 0; j < maoCoringa.size(); j++) {
                tempoDeAtivicao = maoCoringa.get(j).getTempoDeAtivicao();
                if (maoCoringa.get(j) != null && tempoDeAtivicao.equals(Gatilho.PORCARTA)) {
                    // Define a carta corrente que será analisada pelas lambdas em EfeitoCoringa
                    contextoMao.setCartaAuxiliar(cartasJogadas.get(i));
                    contextoMao = maoCoringa.get(j).efeitoCoringa(contextoMao);
                }
            }
        }
    }

    /**
     * Executa os coringas que reagem apenas no final do cálculo do round (Gatilho.FINALMAO).
     * 
     * ATENÇÃO (BUG DE ESTADO RESIDUAL): O objeto 'contextoMao' mantém a 'cartaAuxiliar' 
     * referente à última carta iterada no método anterior. Se algum coringa de FINALMAO 
     * tentar acessar 'getCartaAuxiliar()', estará avaliando uma carta residual em vez do contexto global.
     */
    private static void verificarCoringasFinalRound(ContextoMao contextoMao,
            ArrayList<Coringa> maoCoringa) {
        Gatilho tempoDeAtivicao;
        for (int i = 0; i < maoCoringa.size(); i++) {
            tempoDeAtivicao = maoCoringa.get(i).getTempoDeAtivicao();
            if (maoCoringa.get(i) != null && tempoDeAtivicao.equals(Gatilho.FINALMAO)) {
                contextoMao = maoCoringa.get(i).efeitoCoringa(contextoMao);
            }
        }
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public ArrayList<Coringa> getMaoCoringa() {
        return maoCoringa;
    }

    public void setMaoCoringa(ArrayList<Coringa> maoCoringa) {
        this.maoCoringa = maoCoringa;
    }
}