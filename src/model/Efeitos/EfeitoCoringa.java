package model.Efeitos;

import java.util.List;

import model.cartas.Carta;
import model.cartas.DeckCarta;
import model.contextos.ContextoMao;
import model.coringas.Coringa;
import model.coringas.DeckCoringa;
import model.enums.*;
import service.ValidadorPoker;

/**
 * Interface funcional e fábrica de estratégias para os efeitos dos
 * Coringas[cite: 20].
 * Contém métodos estáticos que retornam lambdas com regras de cálculo de
 * multiplicador[cite: 20].
 */
@FunctionalInterface
public interface EfeitoCoringa {

    /**
     * Aplica a lógica do efeito do Coringa no contexto da mão[cite: 20].
     * 
     * @param contextoMao Contexto de pontuação atual[cite: 20].
     * @return Contexto da mão modificado[cite: 20].
     */
    ContextoMao aplicarEfeito(ContextoMao contextoMao);

    /**
     * Adiciona um valor de multiplicador se o naipe da carta auxiliar for igual ao
     * naipe especificado[cite: 20].
     * 
     * @param naipe Naipe exigido para ativar o efeito[cite: 20].
     * @param valor Quantidade de multiplicador a adicionar[cite: 20].
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMultSeNaipe(Naipe naipe, int valor) {
        return ctx -> {
            if (ctx.getCartaAuxiliar().getNaipe().equals(naipe)) {
                ctx.adicionarMult(valor);
            }
            return ctx;
        };
    }

    /**
     * Adiciona o multiplicador padrão (+4) naipe da carta auxiliar
     * corresponder ao informado[cite: 20].
     * 
     * @param naipe Naipe exigido[cite: 20].
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMultSeNaipe(Naipe naipe) {
        return ctx -> {
            if (ctx.getCartaAuxiliar().getNaipe().equals(naipe)) {
                ctx.adicionarMult(4); // Incrementa o valor padrão
            }
            return ctx;
        };
    }

    /**
     * Adiciona multiplicador caso a mão jogada corresponda ao tipo
     * especificado[cite: 20].
     * 
     * @param valor    Quantidade de multiplicador.
     * @param maoPoker Nome da mão de poker exigida.
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMultSeMaoPoker(int valor, MaoPoker maoPoker) {
        return ctx -> {
            if (ctx.getTipoMao() == maoPoker) {
                ctx.adicionarMult(valor);
            }
            return ctx;
        };
    }

    /**
     * Adiciona multiplicador incondicionalmente[cite: 20]..
     * 
     * @param valor Quantidade de multiplicador.
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addMult(int valor) {
        return ctx -> {
            ctx.adicionarMult(valor);
            return ctx;
        };
    }

    /**
     * Adiciona ficha caso a mão jogada corresponda ao tipo
     * especificado[cite: 20].
     * 
     * @param valor    Quantidade de multiplicador.
     * @param maoPoker Nome da mão de poker exigida.
     * @return Instância de EfeitoCoringa executável[cite: 20].
     */
    static EfeitoCoringa addFichaSeMaoPoker(int valor, MaoPoker maoPoker) {
        return ctx -> {
            if (ctx.getTipoMao() == maoPoker) {
                ctx.adicionarMult(valor);
            }
            return ctx;
        };
    }

    static EfeitoCoringa addFichaMenosMao(int ficha, int dimiMao) {
        return ctx -> {
            ctx.adicionarFicha(ficha);
            DeckCarta.setTamanhoMao(DeckCarta.getTamanhoMao() - dimiMao);
            return ctx;
        };
    }

    static EfeitoCoringa addFibNumeros() {
        return ctx -> {
            OrdemDeValor[] fibNumeros = { OrdemDeValor.AS, OrdemDeValor.DOIS, OrdemDeValor.TRES, OrdemDeValor.CINCO,
                    OrdemDeValor.OITO };
            for (OrdemDeValor fibNumerosAtual : fibNumeros) {
                if (ctx.getCartaAuxiliar().getOrdemDeValor() == fibNumerosAtual) {
                    ctx.getPontos().setMulti(ctx.getPontos().getMulti() + 8);
                    return ctx;
                }
            }
            return ctx;
        };
    }

    static EfeitoCoringa alterarTamSeqPoker() {
        int novoTamanho = 4;
        return ctx -> {
            DeckCarta.setTamSeqPoker(novoTamanho);
            return ctx;
        };
    }

    static EfeitoCoringa alterarDescartes() {
        return ctx -> {
            DeckCarta.setQtdMaoDescarte(0);
            DeckCarta.setQtdMao(DeckCarta.getQtdMao() + 3);
            return ctx;
        };
    }

    static EfeitoCoringa multiplicarQK() {
        return ctx -> {
            Carta cartaAux = ctx.getCartaAuxiliar();
            if (cartaAux.getOrdemDeValor() == OrdemDeValor.DAMA || cartaAux.getOrdemDeValor() == OrdemDeValor.REI) {
                ctx.getPontos().setMulti(ctx.getPontos().getMulti() * 2);
            }
            return ctx;
        };
    }

    static EfeitoCoringa multiplicarMaoPoker(MaoPoker maoPoker, int multiplicador) {
        return ctx -> {
            if (ctx.getTipoMao() == maoPoker) {
                ctx.getPontos().setMulti(ctx.getPontos().getMulti() * multiplicador);
            }
            return ctx;
        };
    }

    static EfeitoCoringa multiplicarPorNaipe() {
        return ctx -> {
            Naipe[] naipes = Naipe.values();
            Naipe naipeSorteado = naipes[DeckCarta.getRandom().nextInt(4)];
            if (ctx.getCartaAuxiliar().getNaipe() == naipeSorteado) {
                ctx.getPontos().setMulti((int) (ctx.getPontos().getMulti() * 1.5));
            }
            return ctx;
        };
    }

    static EfeitoCoringa multiplicarPorMaoJogada() {
        return ctx -> {
            if (ctx.getCoringaAtual().getIncremento() == 6) {
                ctx.getPontos().setMulti(ctx.getPontos().getMulti() * 4);
                ctx.getCoringaAtual().setIncremento(0);
            } else {
                ctx.getCoringaAtual().setIncremento(ctx.getCoringaAtual().getIncremento() + 1);
            }

            return ctx;
        };
    }

    static EfeitoCoringa uparMaoPoker() {
        return ctx -> {
            int numRandom = DeckCarta.getRandom().nextInt(4);
            // numeros de 0 a 4 para dar a sensação de 1/4 fiz se for um número 0 deu certo
            // a porcentagem
            if (numRandom == 0) {
                ValidadorPoker.setListaMaosPoker(ctx.getTipoMao());
            }
            return ctx;
        };
    }

    static EfeitoCoringa repetirCoringaEsp(int posCoringa) {
        return ctx -> {
            DeckCoringa.getMaoCoringa().get(posCoringa).efeitoCoringa(ctx);
            return ctx;
        };
    }

    static EfeitoCoringa repetirCoringaXNum() {
        return ctx -> {
            List<Coringa> mao = DeckCoringa.getMaoCoringa();
            OrdemDeValor[] repNums = { OrdemDeValor.DOIS, OrdemDeValor.TRES, OrdemDeValor.QUATRO,
                    OrdemDeValor.CINCO };

            for (int i = 0; i < repNums.length; i++) {
                if (ctx.getCartaAuxiliar().getOrdemDeValor() == repNums[i]) {
                    for (int j = 0; j < repNums.length; j++) {
                        if (mao.get(j).getIdJoker() != ctx.getCoringaAtual().getIdJoker()) {
                            mao.get(j).efeitoCoringa(ctx);
                        }
                    }
                }
            }

            return ctx;
        };
    }

    static EfeitoCoringa deletarCorgDir() {
        return ctx -> {
            List<Coringa> mao = DeckCoringa.getMaoCoringa();
            int indexAdaga = 0;
            for (int i = 0; i < mao.size(); i++) {
                if (mao.get(i).getIdJoker() == 27) {
                    indexAdaga = i;
                    break;
                }
            }

            if (indexAdaga + 1 > mao.size()) {
                return ctx;
            }

            Coringa coringaAdaga = mao.get(indexAdaga);
            Coringa coringaADir = mao.get(indexAdaga + 1);
            if (coringaADir != null) {
                ctx.getPontos().setMulti(ctx.getPontos().getMulti() + coringaAdaga.getIncremento());
                coringaAdaga.setIncremento((int) (coringaADir.getPreco() + coringaAdaga.getIncremento()));
                mao.remove(indexAdaga + 1);
            }

            return ctx;
        };
    }

    static EfeitoCoringa deletarCorgRandom() {
        return ctx -> {
            List<Coringa> mao = DeckCoringa.getMaoCoringa();
            int indexCorg = -1;
            for (int i = 0; i < mao.size(); i++) {
                if (mao.get(i).getIdJoker() == 27) {
                    indexCorg = i;
                    break;
                }
            }

            if (mao.size() == 1 && indexCorg == 0) {
                return ctx;
            }

            int numDeletar;
            do {
                numDeletar = DeckCarta.getRandom().nextInt(mao.size());
            } while (numDeletar == indexCorg);

            mao.remove(numDeletar);
            ctx.getCoringaAtual().setIncremento(ctx.getCoringaAtual().getIncremento() + 1);
            return ctx;
        };
    }

    static EfeitoCoringa TODO() {
        return ctx -> {
            return ctx;
        };
    }
}