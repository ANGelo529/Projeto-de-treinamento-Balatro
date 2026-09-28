package service;

import java.util.ArrayList;
import java.util.HashMap;

import model.cartas.*;
import model.contextos.*;
import model.coringas.*;
import model.enums.MaoPoker;

/**
 * Classe responsável por identificar, validar e calcular a pontuação das mãos de Poker
 * com base na combinação de cartas jogadas e na aplicação dos efeitos de Coringas.
 */
public class ValidadorPoker {
    /* ------------------ VARIÁVEIS ------------------ */

    /** Tabela hash estática contendo o mapeamento de cada tipo de mão para sua pontuação base (Fichas e Multiplicador) */
    private static HashMap<MaoPoker, Ponto> listaMaosPoker = setListaMaosPoker();

    /* ------------------ MÉTODOS ------------------ */

    /**
     * Calcula o resultado de uma jogada, contando a frequência de naipes e valores,
     * determinando a melhor mão de poker pontuada e acionando a verificação dos coringas.
     * 
     * @param maoJogada  Lista de cartas selecionadas pelo jogador para a jogada[cite: 1]
     * @param maoCoringa Lista de coringas ativos/equipados no momento[cite: 1]
     */
    public static void calcularJogada(ArrayList<Carta> maoJogada, ArrayList<Coringa> maoCoringa) {
        int[] qtdNaipes = new int[4];         // Contador para os 4 naipes (Ouros, Copas, Espadas, Paus)
        int[] qtdOrdemDeValor = new int[13];   // Contador para as 13 ordens de valor (Ás até Rei)

        // Preenche os vetores de frequência com base nas cartas da mão
        for (int i = 0; i < maoJogada.size(); i++) {
            int naipe = maoJogada.get(i).getNaipe().ordinal();
            int ordemDeValor = Carta.descobrirPosOrdemDeValor(maoJogada.get(i).getOrdemDeValor());
            qtdNaipes[naipe]++;
            qtdOrdemDeValor[ordemDeValor]++;
        }
        
        // Avalia qual é a combinação da mão e retorna seus pontos base[cite: 1]
        Ponto pontosMaoJogada = verificarJogada(qtdOrdemDeValor, qtdNaipes);
        
        // Cria o contexto da jogada e processa as alterações dos Coringas[cite: 1]
        ContextoMao contextoMao = new ContextoMao(maoJogada, pontosMaoJogada);
        DeckCoringa.verificarCoringa(contextoMao, maoCoringa);
    }

    /**
     * Avalia o vetor de frequências seguindo a hierarquia do Poker (da mão mais alta para a mais baixa).
     * 
     * @param qtdOrdemDeValor Vetor com a contagem de cartas por valor[cite: 1]
     * @param qtdNaipe        Vetor com a contagem de cartas por naipe[cite: 1]
     * @return Objeto {@link Ponto} com Fichas, Multiplicador e o Enum da mão identificada[cite: 1]
     */
    public static Ponto verificarJogada(int[] qtdOrdemDeValor, int[] qtdNaipe) {
        // Mapeamento booleano ordenado pela hierarquia tradicional de mãos de poker[cite: 1]
        boolean[] jogadasVerificadas = {
                validarStraightFlush(qtdOrdemDeValor, qtdNaipe),
                validarQuadra(qtdOrdemDeValor),
                validarFullHouse(qtdOrdemDeValor),
                validarFlush(qtdNaipe),
                validarSequencia(qtdOrdemDeValor),
                validarTrinca(qtdOrdemDeValor),
                validarDoisPares(qtdOrdemDeValor),
                validarPar(qtdOrdemDeValor),
                validarCartaAlta(qtdOrdemDeValor)
        };

        // Retorna a primeira combinação válida encontrada na hierarquia
        for (int i = 0; i < jogadasVerificadas.length; i++) {
            if (jogadasVerificadas[i]) {
                MaoPoker nomeMaoJogada = MaoPoker.values()[i];
                System.out.println(nomeMaoJogada.getNomeExibicao());
                int ficha = listaMaosPoker.get(nomeMaoJogada).getFicha();
                int multi = listaMaosPoker.get(nomeMaoJogada).getMulti();
                return new Ponto(ficha, multi, nomeMaoJogada);
            }
        }

        return new Ponto();
    }

    /**
     * Valida se a mão é um Straight Flush (uma sequência onde todas as cartas possuem o mesmo naipe).
     */
    public static boolean validarStraightFlush(int[] qtdOrdemDeValor, int[] qtdNaipe) {
        return validarSequencia(qtdOrdemDeValor) && validarFlush(qtdNaipe);
    }

    /**
     * Valida se a mão contém uma Quadra (4 cartas do mesmo valor).
     */
    public static boolean validarQuadra(int[] qtdOrdemDeValor) {
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] >= 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida se a mão é um Full House (uma Trinca e um Par simultaneamente).
     */
    public static boolean validarFullHouse(int[] qtdOrdemDeValor) {
        boolean temTrinca = false;
        boolean temPar = false;

        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 3) {
                temTrinca = true;
            }
            if (qtdOrdemDeValor[i] == 2) {
                temPar = true;
            }
            if (temTrinca && temPar) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida se a mão é um Flush (5 cartas do mesmo naipe).
     */
    public static boolean validarFlush(int[] qtdNaipe) {
        for (int i = 0; i < qtdNaipe.length; i++) {
            if (qtdNaipe[i] == 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida se as cartas formam uma Sequência (5 valores consecutivos).
     */
    public static boolean validarSequencia(int[] qtdOrdemDeValor) {
        int qtdValidos = 0;

        // Verifica 5 valores consecutivos no vetor
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] > 0) {
                qtdValidos++;
                if (qtdValidos == 5) {
                    return true;
                }
            } else {
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
    public static boolean validarTrinca(int[] qtdOrdemDeValor) {
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida se a mão possui pelo menos Dois Pares.
     */
    public static boolean validarDoisPares(int[] qtdOrdemDeValor) {
        int qtdPares = 0;
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 2) {
                qtdPares++;
            }
        }
        return qtdPares >= 2;
    }

    /**
     * Valida se a mão contém ao menos um Par (2 cartas do mesmo valor).
     */
    public static boolean validarPar(int[] qtdOrdemDeValor) {
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida a presença de Carta Alta (pelo menos uma carta isolada na jogada).
     */
    public static boolean validarCartaAlta(int[] qtdOrdemDeValor) {
        for (int i = 0; i < qtdOrdemDeValor.length; i++) {
            if (qtdOrdemDeValor[i] == 1) {
                return true;
            }
        }
        return false;
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public static HashMap<MaoPoker, Ponto> getListaMaosPoker() {
        return listaMaosPoker;
    }

    public static void setListaMaosPoker(HashMap<MaoPoker, Ponto> listaMaosPoker) {
        ValidadorPoker.listaMaosPoker = listaMaosPoker;
    }

    /**
     * Inicializa a tabela padrão de pontuações bases (Fichas e Multiplicadores) para cada mão de Poker.
     * 
     * @return HashMap populado com as pontuações iniciais
     */
    public static HashMap<MaoPoker, Ponto> setListaMaosPoker() {
        HashMap<MaoPoker, Ponto> aux = new HashMap<>();
        int[] fichas = { 5, 10, 20, 30, 30, 35, 40, 60, 100 };
        int[] multi = { 1, 2, 2, 3, 4, 4, 4, 7, 8 };

        // Mapeia os valores em ordem invertida associando aos enums correspondentes de MaoPoker
        for (int i = fichas.length - 1; i >= 0; i--) {
            aux.put(MaoPoker.values()[fichas.length - 1 - i], new Ponto(fichas[i], multi[i], 1));
        }

        return aux;
    }
}