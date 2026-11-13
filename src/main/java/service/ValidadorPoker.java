package service;

import java.util.ArrayList;
import java.util.HashMap;

import model.cartas.Carta;
import model.contextos.ContextoMao;
import model.contextos.PokerMao;
import model.enums.MaoPoker;
import model.enums.OrdemDeValor;

/**
 * Classe responsável por identificar, validar e calcular a pontuação das mãos
 * de Poker
 * com base na combinação de cartas jogadas e na aplicação dos efeitos de
 * Coringas.
 */
public class ValidadorPoker {
    /* ------------------ VARIÁVEIS ------------------ */

    /**
     * Tabela hash estática contendo o mapeamento de cada tipo de mão para sua
     * pontuação base (Fichas e Multiplicador)
     */
    private static HashMap<MaoPoker, PokerMao> listaMaosPoker = startListaMaosPoker();
    private static int tamSeqMaoPoker = 5;
    private final static OrdemDeValor[] ordemDeValores = OrdemDeValor.values();
    /* ------------------ MÉTODOS ------------------ */

    /**
     * Calcula o resultado de uma jogada, contando a frequência de naipes e valores,
     * determinando a melhor mão de poker pontuada e acionando a verificação dos
     * coringas.
     * 
     * @param maoJogada  Lista de cartas selecionadas pelo jogador para a
     *                   jogada[cite: 1]
     * @param maoCoringa Lista de coringas ativos/equipados no momento[cite: 1]
     */
    public static PokerMao calcularJogada(ContextoMao ctx) {
        int[] qtdNaipes = new int[4]; // Contador para os 4 naipes (Ouros, Copas, Espadas, Paus)
        int[] qtdOrdemDeValor = new int[13]; // Contador para as 13 ordens de valor (Ás até Rei)
        ArrayList<Carta> maoJogada = ctx.getCartasJogadas();

        // Preenche os vetores de frequência com base nas cartas da mão
        for (int i = 0; i < maoJogada.size(); i++) {
            int naipe = maoJogada.get(i).getNaipe().ordinal();
            int ordemDeValor = Carta.descobrirPosOrdemDeValor(maoJogada.get(i).getOrdemDeValor());
            qtdNaipes[naipe]++;
            qtdOrdemDeValor[ordemDeValor]++;
        }

        // Avalia qual é a combinação da mão e retorna seus pontos base[cite: 1]
        return verificarJogada(qtdOrdemDeValor, qtdNaipes, ctx);

    }

    /**
     * Avalia o vetor de frequências seguindo a hierarquia do Poker (da mão mais
     * alta para a mais baixa).
     * 
     * @param qtdOrdemDeValor Vetor com a contagem de cartas por valor[cite: 1]
     * @param qtdNaipe        Vetor com a contagem de cartas por naipe[cite: 1]
     * @return Objeto {@link Ponto} com Fichas, Multiplicador e o Enum da mão
     *         identificada[cite: 1]
     */
    public static PokerMao verificarJogada(int[] qtdOrdemDeValor, int[] qtdNaipe, ContextoMao ctx) {
        // Mapeamento booleano ordenado pela hierarquia tradicional de mãos de
        // poker[cite: 1]
        boolean[] jogadasVerificadas = {
                validarStraightFlush(qtdOrdemDeValor, qtdNaipe, ctx, false),
                validarQuadra(qtdOrdemDeValor, ctx, false),
                validarFullHouse(qtdOrdemDeValor, ctx, false),
                validarFlush(qtdNaipe, ctx, false),
                validarSequencia(qtdOrdemDeValor, ctx, false),
                validarTrinca(qtdOrdemDeValor, ctx, false),
                validarDoisPares(qtdOrdemDeValor, ctx, false),
                validarPar(qtdOrdemDeValor, ctx, false),
                validarCartaAlta(qtdOrdemDeValor, ctx, false)
        };

        adicionarFichas(jogadasVerificadas,qtdOrdemDeValor,qtdNaipe,ctx);
        // Retorna a primeira combinação válida encontrada na hierarquia
        for (int i = 0; i < jogadasVerificadas.length; i++) {
            if (jogadasVerificadas[i]) {
                MaoPoker nomeMaoJogada = MaoPoker.values()[i];
                System.out.println(nomeMaoJogada.getNomeExibicao());
                long ficha = listaMaosPoker.get(nomeMaoJogada).getFicha();
                long multi = listaMaosPoker.get(nomeMaoJogada).getMulti();
                int nivel = listaMaosPoker.get(nomeMaoJogada).getNivel();
                return new PokerMao(ficha + ctx.getFichas(), multi, nivel, nomeMaoJogada);
            }
        }

        return new PokerMao();
    }

    private static void adicionarFichas(boolean[] jogadasVerificadas, int[] qtdOrdemDeValor, int[] qtdNaipe,
            ContextoMao ctx) {
        for (int i = 0; i < jogadasVerificadas.length; i++) {
            if (jogadasVerificadas[i]) {
                switch (i) { // Switch pra pontuar de acordo com a mão segue a ordem de jogadasVerificadas
                    case 0:
                        validarStraightFlush(qtdOrdemDeValor, qtdNaipe, ctx, true);
                        break;
                    case 1:
                        validarQuadra(qtdOrdemDeValor, ctx, true);
                        break;
                    case 2:
                        validarFullHouse(qtdOrdemDeValor, ctx, true);
                        break;
                    case 3:
                        validarFlush(qtdNaipe, ctx, true);
                        break;
                    case 4:
                        validarSequencia(qtdOrdemDeValor, ctx, true);
                        break;
                    case 5:
                        validarTrinca(qtdOrdemDeValor, ctx, true);
                        break;
                    case 6:
                        validarDoisPares(qtdOrdemDeValor, ctx, true);
                        break;
                    case 7:
                        validarPar(qtdOrdemDeValor, ctx, true);
                        break;
                    case 8:
                        validarCartaAlta(qtdOrdemDeValor, ctx, false);
                        break;
                    default:
                        break;
                }
            }

        }
    }

    /**
     * Valida se a mão é um Straight Flush (uma sequência onde todas as cartas
     * possuem o mesmo naipe).
     */
    public static boolean validarStraightFlush(int[] qtdOrdemDeValor, int[] qtdNaipe, ContextoMao ctx,
            boolean calculoFicha) {
        return validarSequencia(qtdOrdemDeValor, ctx, calculoFicha) && validarFlush(qtdNaipe, ctx, calculoFicha);
    }

    /**
     * Valida se a mão contém uma Quadra (4 cartas do mesmo valor).
     */
    public static boolean validarQuadra(int[] qtdOrdemDeValor, ContextoMao ctx, boolean calculoFicha) {
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] >= 4) {
                if (calculoFicha) {
                    for (int j = 0; j < 4; j++) {
                        ctx.adicionarFicha(ordemDeValores[i].getValorOrdemValor());
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Valida se a mão é um Full House (uma Trinca e um Par simultaneamente).
     */
    public static boolean validarFullHouse(int[] qtdOrdemDeValor, ContextoMao ctx, boolean calculoFicha) {
        boolean temTrinca = false;
        boolean temPar = false;
        int[] posPontuados = new int[2];
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 3) {
                temTrinca = true;
                posPontuados[0] = i;
            }
            if (qtdOrdemDeValor[i] == 2) {
                temPar = true;
                posPontuados[1] = i;
            }
            if (temTrinca && temPar) {
                if (calculoFicha) {
                    for (int j = 0; j < 3; j++) {
                        ctx.adicionarFicha(ordemDeValores[posPontuados[0]].getValorOrdemValor());
                    }
                    for (int j = 0; j < 2; j++) {
                        ctx.adicionarFicha(ordemDeValores[posPontuados[1]].getValorOrdemValor());
                    }
                }

                return true;
            }
        }
        return false;
    }

    /**
     * Valida se a mão é um Flush (tamSeqPoker cartas do mesmo naipe).
     */
    public static boolean validarFlush(int[] qtdNaipe, ContextoMao ctx, boolean calculoFicha) {
        for (int i = 0; i < qtdNaipe.length; i++) {
            if (qtdNaipe[i] == tamSeqMaoPoker) { // trocar esse 5 pela variavel tamSeq
                if (calculoFicha) {
                    for (int j = 0; j < tamSeqMaoPoker; j++) {
                        ctx.adicionarFicha(ordemDeValores[i].getValorOrdemValor());
                    }
                }

                return true;
            }
        }
        return false;
    }

    /**
     * Valida se as cartas formam uma Sequência (tamSeqPoker valores consecutivos).
     */
    public static boolean validarSequencia(int[] qtdOrdemDeValor, ContextoMao ctx, boolean calculoFicha) {
        int qtdValidos = 0;
        int[] pontosPontuados = new int[5];
        // Verifica 5 valores consecutivos no vetor
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] > 0) {
                pontosPontuados[qtdValidos] = ordemDeValores[i].getValorOrdemValor();
                qtdValidos++;
                if (qtdValidos == tamSeqMaoPoker) {
                    if (calculoFicha) {
                        for (int j = 0; j < ctx.getCartasJogadas().size(); j++) {
                            ctx.adicionarFicha(pontosPontuados[j]);
                        }
                    }
                    return true;
                }
            } else {
                pontosPontuados = new int[5];
                qtdValidos = 0; // Reseta a contagem se a sequência for interrompida
            }
        }

        // Caso especial: Sequência A-2-3-4-5 (onde o Ás atua como valor mais baixo)
        if (qtdOrdemDeValor[12] > 0 && qtdOrdemDeValor[0] > 0 && qtdOrdemDeValor[1] > 0
                && qtdOrdemDeValor[2] > 0 && qtdOrdemDeValor[3] > 0) {
            return true;
        }

        return false;
    }

    /**
     * Valida se a mão contém uma Trinca (3 cartas do mesmo valor).
     */
    public static boolean validarTrinca(int[] qtdOrdemDeValor, ContextoMao ctx, boolean calculoFicha) {
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 3) {
                if (calculoFicha) {
                    for (int j = 0; j < 3; j++) {
                        ctx.adicionarFicha(ordemDeValores[i].getValorOrdemValor());
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Valida se a mão possui pelo menos Dois Pares.
     */
    public static boolean validarDoisPares(int[] qtdOrdemDeValor, ContextoMao ctx, boolean calculoFicha) {
        int qtdPares = 0;

        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 2) {
                if (calculoFicha) {
                    for (int j = 0; j < 2; j++) {
                        ctx.adicionarFicha(ordemDeValores[i].getValorOrdemValor());
                    }
                }
                qtdPares++;
            }
        }
        return qtdPares >= 2;
    }

    /**
     * Valida se a mão contém ao menos um Par (2 cartas do mesmo valor).
     */
    public static boolean validarPar(int[] qtdOrdemDeValor, ContextoMao ctx, boolean calculoFicha) {
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 2) {
                if (calculoFicha) {
                    for (int j = 0; j < 2; j++) {
                        ctx.adicionarFicha(ordemDeValores[i].getValorOrdemValor());
                    }
                }

                return true;
            }
        }
        return false;
    }

    /**
     * Valida a presença de Carta Alta (pelo menos uma carta isolada na jogada).
     */
    public static boolean validarCartaAlta(int[] qtdOrdemDeValor, ContextoMao ctx, boolean calculoFicha) {
        int maiorValor = 0;
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 1) {
                maiorValor = ordemDeValores[i].getValorOrdemValor();
            }
        }

        if (calculoFicha) {
            ctx.adicionarFicha(maiorValor);
        }
        return true;
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public static HashMap<MaoPoker, PokerMao> getListaMaosPoker() {
        return listaMaosPoker;
    }

    public static void setListaMaosPoker(HashMap<MaoPoker, PokerMao> listaMaosPoker) {
        ValidadorPoker.listaMaosPoker = listaMaosPoker;
    }

    public static void setListaMaosPoker(MaoPoker maoPoker) {
        int ficha = (int) (ValidadorPoker.listaMaosPoker.get(maoPoker).getFicha() * 1.5);
        int multi = (int) (ValidadorPoker.listaMaosPoker.get(maoPoker).getMulti() * 1.5);
        int nivel = ValidadorPoker.listaMaosPoker.get(maoPoker).getNivel() + 1;
        PokerMao auxPonto = new PokerMao(ficha, multi, nivel, maoPoker);
        ValidadorPoker.listaMaosPoker.put(maoPoker, auxPonto);
    }

    /**
     * Inicializa a tabela padrão de pontuações bases (Fichas e Multiplicadores)
     * para cada mão de Poker.
     * 
     * @return HashMap populado com as pontuações iniciais
     */
    public static HashMap<MaoPoker, PokerMao> startListaMaosPoker() {
        HashMap<MaoPoker, PokerMao> aux = new HashMap<>();
        int[] fichas = { 5, 10, 20, 30, 30, 35, 40, 60, 100 };
        int[] multi = { 1, 2, 2, 3, 4, 4, 4, 7, 8 };
        MaoPoker[] nomesMaoPoker = MaoPoker.values();
        // Mapeia os valores em ordem invertida associando aos enums correspondentes de
        // MaoPoker
        for (int i = fichas.length - 1; i >= 0; i--) {
            aux.put(MaoPoker.values()[fichas.length - 1 - i], new PokerMao(fichas[i], multi[i], 1, nomesMaoPoker[i]));
        }

        return aux;
    }

    public static int getTamSeqMaoPoker() {
        return tamSeqMaoPoker;
    }

    public static void setTamSeqMaoPoker(int tamSeqMaoPoker) {
        ValidadorPoker.tamSeqMaoPoker = tamSeqMaoPoker;
    }
}