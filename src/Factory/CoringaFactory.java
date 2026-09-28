package Factory;

import model.coringas.CatalogoCoringa;
import model.coringas.Coringa;
import model.enums.Gatilho;

/**
 * Classe de fábrica (Factory Pattern) responsável pela criação e instanciação 
 * dos objetos Coringa a partir das definições contidas no CatalogoCoringa[cite: 21].
 */
public class CoringaFactory {

    /**
     * Instancia um Coringa assumindo o tempo de ativação padrão (Gatilho.PORCARTA)[cite: 21].
     * 
     * @param coringaAux Item do catálogo[cite: 21].
     * @return Nova instância de Coringa[cite: 21].
     */
    private static Coringa criarCoringa(CatalogoCoringa coringaAux){
        return new Coringa(
            coringaAux.getIdJoker(), 
            coringaAux.getNome(), 
            coringaAux.getRaridade(), 
            coringaAux.getPreco(), 
            coringaAux.getEfeitoCoringa()
        );
    }

    /**
     * Instancia um Coringa mantendo explicitamente o tempo de ativação definido no catálogo (ex: FINALMAO, PASSIVO)[cite: 21].
     * 
     * @param coringa Item do catálogo[cite: 21].
     * @return Nova instância de Coringa[cite: 21].
     */
    private static Coringa criarCoringaTempoDeAtivacao(CatalogoCoringa coringa) {
        return new Coringa(
            coringa.getIdJoker(), 
            coringa.getNome(), 
            coringa.getRaridade(), 
            coringa.getTempoDeAtivicao(), 
            coringa.getPreco(),
            coringa.getEfeitoCoringa()
        );
    }

    /**
     * Busca um Coringa pelo seu ID no catálogo e retorna uma nova instância configurada[cite: 21].
     * 
     * @param id Identificador único do Coringa[cite: 21].
     * @return Objeto Coringa pronto para uso no jogo[cite: 21].
     * @throws IllegalArgumentException Caso o ID não exista no catálogo[cite: 21].
     */
    public static Coringa getCoringaPorId(int id) {
        for (CatalogoCoringa coringa : CatalogoCoringa.values()) {
            if (coringa.getIdJoker() == id) {
                // Redireciona para o construtor adequado conforme o gatilho de ativação[cite: 21]
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