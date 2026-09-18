package backEnd.codigojava;

import java.util.ArrayList;

public class DeckCoringa {
    private ArrayList<Coringa> maoCoringa = new ArrayList<>();

    public static void verificarCoringa(ContextoMao contextoMao, ArrayList<Coringa> maoCoringa) {
        ArrayList<Carta> cartasJogadas = contextoMao.getCartasJogadas();
        String tempoDeAtivicao;
        for (int i = 0; i < cartasJogadas.size(); i++) {
            for (int j = 0; j < maoCoringa.size(); j++) {
                tempoDeAtivicao = maoCoringa.get(i).getTempoDeAtivicao();
                if (maoCoringa.get(i) != null && tempoDeAtivicao.equals("Agora")) {
                    contextoMao.setCartaAuxiliar(cartasJogadas.get(i));
                    contextoMao = maoCoringa.get(j).realizarEfeitoCoringa(contextoMao);
                }
            }
        }

        for (int i = 0; i < maoCoringa.size(); i++) {
            tempoDeAtivicao = maoCoringa.get(i).getTempoDeAtivicao();
            if (maoCoringa.get(i) != null && tempoDeAtivicao.equals("Final")) {
                contextoMao = maoCoringa.get(i).realizarEfeitoCoringa(contextoMao);
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