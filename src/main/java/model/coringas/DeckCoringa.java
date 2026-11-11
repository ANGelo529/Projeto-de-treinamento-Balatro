package model.coringas;

import model.cartas.Carta;
import model.contextos.*;
import model.enums.Gatilho;
import java.util.ArrayList;
import Factory.CoringaFactory;

/**
 * Gerenciador e executor do conjunto de Coringas equipados pelo jogador.
 */
public class DeckCoringa {
    /* ------------------ ATRIBUTOS ------------------ */

    private static int idDeckCoringa;
    private static int limiteCoringas = 5;
    private static ArrayList<Coringa> maoCoringa = new ArrayList<>();// Lista de Coringas equipados

    /* ------------------ MÉTODOS ------------------ */
    public static void addCoringa(int idCorg) {
        if (DeckCoringa.maoCoringa.size() + 1 > DeckCoringa.limiteCoringas) {
            System.out.println("Não é possível adicionar mais coringas você chegou ao limite de espaços");
        } else {
            maoCoringa.add(CoringaFactory.getCoringaPorId(idCorg));
            System.out.printf("Coringa %s adicionado", maoCoringa.get(maoCoringa.size() - 1).getNome());
        }

    }

    public static void delTodosCoringas() {
        maoCoringa.clear();
        System.out.println("Mao coringa esvaziada");
    }

    /**
     * Ponto de entrada estático para processar e aplicar todos os efeitos de
     * Coringas na mão.
     * TODO: Verificar utilidade desse método provavelmente chamada direta dos métodos auxiliares seja mais útil
     * 
     * @param contextoMao Estado atual do cálculo da mão.
     * @param maoCoringa  Lista dos coringas equipados a serem testados.
     */
    public static void verificarCoringa(ContextoMao contextoMao, ArrayList<Coringa> maoCoringa) {
        ArrayList<Carta> cartasJogadas = contextoMao.getCartasJogadas();

        // 1º Passo: Dispara os coringas que reagem a cada carta pontuada
        // individualmente
        verificarCoringasPorCarta(contextoMao, cartasJogadas, maoCoringa);

        // 2º Passo: Dispara os coringas que reagem ao valor final da mão inteira
        verificarCoringasFinalRound(contextoMao, maoCoringa);
    }

    /*
     * TODO: terminar implementação
     */
    public static void verificarCoringasPassivos(ContextoMao contextoMao) {
    }

    /**
     * Executa um loop aninhado: para cada carta jogada, percorre todos os coringas
     * com gatilho PORCARTA.
     */
    private static void verificarCoringasPorCarta(ContextoMao contextoMao, ArrayList<Carta> cartasJogadas,
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

    public static int getIdDeckCoringa() {
        return idDeckCoringa;
    }

    public static void setIdDeckCoringa(int idDeckCoringa) {
        DeckCoringa.idDeckCoringa = idDeckCoringa;
    }

    public static ArrayList<Coringa> getMaoCoringa() {
        return maoCoringa;
    }

    public static void setMaoCoringa(ArrayList<Coringa> newMaoCoringa) {
        maoCoringa = newMaoCoringa;
    }

}