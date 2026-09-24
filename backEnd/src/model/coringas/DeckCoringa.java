package backEnd.src.model.coringas;

import backEnd.src.Factory.CoringaFactory;
import backEnd.src.model.cartas.Carta;
import backEnd.src.model.contextos.*;
import backEnd.src.model.enums.Gatilho;

import java.util.ArrayList;

public class DeckCoringa {
    private ArrayList<Coringa> maoCoringa = new ArrayList<>();


    public static void verificarCoringa(ContextoMao contextoMao, ArrayList<Coringa> maoCoringa) {
        ArrayList<Carta> cartasJogadas = contextoMao.getCartasJogadas();

        verificarCoringasPorCarta(contextoMao, cartasJogadas, maoCoringa);
        verificarCoringasFinalRound(contextoMao, maoCoringa);

    }

    private static void verificarCoringasPorCarta(ContextoMao contextoMao, ArrayList<Carta> cartasJogadas,
            ArrayList<Coringa> maoCoringa) {
        Gatilho tempoDeAtivicao;
        for (int i = 0; i < cartasJogadas.size(); i++) {
            for (int j = 0; j < maoCoringa.size(); j++) {
                tempoDeAtivicao = maoCoringa.get(j).getTempoDeAtivicao();
                if (maoCoringa.get(j) != null && tempoDeAtivicao.equals("Agora")) {
                    contextoMao.setCartaAuxiliar(cartasJogadas.get(i));
                    contextoMao = maoCoringa.get(j).efeitoCoringa(contextoMao);
                }
            }
        }
    }

    private static void verificarCoringasFinalRound(ContextoMao contextoMao,
            ArrayList<Coringa> maoCoringa) {
        Gatilho tempoDeAtivicao;
        for (int i = 0; i < maoCoringa.size(); i++) {
            tempoDeAtivicao = maoCoringa.get(i).getTempoDeAtivicao();
            if (maoCoringa.get(i) != null && tempoDeAtivicao.equals("Final")) {
                contextoMao = maoCoringa.get(i).efeitoCoringa(contextoMao);
            }
        }
    }

    public ArrayList<Coringa> getMaoCoringa() {
        return maoCoringa;
    }

    public void setMaoCoringa(ArrayList<Coringa> maoCoringa) {
        this.maoCoringa = maoCoringa;
    }
}