package backEnd.codigojava;

import java.util.ArrayList;

public class DeckCoringa {
    private ArrayList<Coringa> maoCoringa = new ArrayList<>();

    public static void verificarCoringa(ContextoMao contextoMao, ArrayList<Coringa> maoCoringa) {
        for (int i = 0; i < maoCoringa.size(); i++) {
            maoCoringa.get(i).getEfeitoCoringa().aplicarEfeito(contextoMao);
        }
    }

    public ArrayList<Coringa> getMaoCoringa() {
        return maoCoringa;
    }

    public void setMaoCoringa(ArrayList<Coringa> maoCoringa) {
        this.maoCoringa = maoCoringa;
    }
}