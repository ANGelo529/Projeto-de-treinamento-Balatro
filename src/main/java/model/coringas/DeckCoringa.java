package model.coringas;

import model.GameState;
import model.cartas.Carta;
import model.contextos.*;
import service.ValidadorCoringa;

import java.util.ArrayList;

import factory.CoringaFactory;

/**
 * Gerenciador e executor do conjunto de Coringas equipados pelo jogador.
 */
public class DeckCoringa {
    /* ------------------ ATRIBUTOS ------------------ */

    private static int idDeckCoringa;
    private static ArrayList<Coringa> maoCoringa = new ArrayList<>();// Lista de Coringas equipados

    /* ------------------ MÉTODOS ------------------ */
    public static void addCoringa(int idCorg) {
        if (DeckCoringa.maoCoringa.size() + 1 > GameState.getLimiteCoringas()) {
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
     * TODO: Verificar utilidade desse método provavelmente chamada direta dos
     * métodos auxiliares seja mais útil
     * 
     * @param contextoMao Estado atual do cálculo da mão.
     * @param maoCoringa  Lista dos coringas equipados a serem testados.
     */
    public static void verificarCoringa(ContextoMao contextoMao, ArrayList<Coringa> maoCoringa) {
        ArrayList<Carta> cartasJogadas = contextoMao.getCartasJogadas();

        // 1º Passo: Dispara os coringas que reagem a cada carta pontuada
        // individualmente
        ValidadorCoringa.verificarCoringasPorCarta(contextoMao, cartasJogadas, maoCoringa);

        // 2º Passo: Dispara os coringas que reagem ao valor final da mão inteira
        ValidadorCoringa.verificarCoringasFinalRound(contextoMao, maoCoringa);
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