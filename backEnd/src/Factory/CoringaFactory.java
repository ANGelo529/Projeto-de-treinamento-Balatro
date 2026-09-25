package backEnd.src.Factory;

import backEnd.src.model.coringas.CatalogoCoringa;
import backEnd.src.model.coringas.Coringa;
import backEnd.src.model.enums.Gatilho;

public class CoringaFactory {

    private static Coringa criarCoringa(CatalogoCoringa coringaAux){
        return new Coringa(coringaAux.getIdJoker(), coringaAux.getNome(), coringaAux.getRaridade(), coringaAux.getPreco(), coringaAux.getEfeitoCoringa());
    }

    private static Coringa criarCoringaTempoDeAtivacao(CatalogoCoringa coringa) {
        return new Coringa(coringa.getIdJoker(), coringa.getNome(), coringa.getRaridade(), coringa.getTempoDeAtivicao(), coringa.getPreco(),
                coringa.getEfeitoCoringa());
    }

    public static Coringa getCoringaPorId(int id) {
        
        for (CatalogoCoringa coringa : CatalogoCoringa.values()) {
            if (coringa.getIdJoker() == id) {
                if (coringa.getTempoDeAtivicao().equals(Gatilho.PORCARTA)) {
                    return criarCoringa(coringa);
                } else {
                    return criarCoringaTempoDeAtivacao(coringa);
                }
            }

        }
        
        throw new IllegalArgumentException("Coringa não encontrado com ID: " + id);
    }

}
