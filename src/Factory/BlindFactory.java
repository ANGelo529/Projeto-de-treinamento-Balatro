package Factory;

import model.Efeitos.EfeitoBlind;
import model.blinds.BossBlind;
import model.contextos.ContextoMao;
import model.blinds.CatalogoBlind;


/**
 * Fábrica (Factory Pattern) destinada à geração de instâncias de Blinds
 * (Apostas/Chefões) do jogo[cite: 22].
 * 
 * NOTA: Esta classe encontra-se vazia/em desenvolvimento[cite: 22]. Deverá
 * conter no futuro métodos
 * estáticos para instanciar Blinds Pequenas, Blinds Grandes e Boss Blinds com
 * os seus
 * respetivos alvos de pontuação e EfeitosBlind[cite: 19, 22].
 */
public class BlindFactory {
    // TODO: Implementar métodos de criação das Blinds (ex: getBlindPorId ou
    // criarBossBlind)[cite: 22]

    // Método temp pra poder testar alterar dps com a criação do catalagoBlind
    private static BossBlind criarBlind(CatalogoBlind coringaAux) {
        EfeitoBlind efeitoBlind = ctx -> {
            return new ContextoMao();
        };
        return new BossBlind(
                "nome",
                1,
                1.0,
                1,
                efeitoBlind);
    }
}