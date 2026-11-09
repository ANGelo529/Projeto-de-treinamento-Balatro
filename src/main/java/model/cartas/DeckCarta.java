package model.cartas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import main.java.model.GameState;
import model.enums.Naipe;
import model.enums.OrdemDeValor;

/**
 * Gerenciador principal do baralho e da mão do jogador.
 * Responsável por inicializar o baralho de 52 cartas, sortear cartas
 * aleatórias,
 * reabastecer a mão, ordenar por Naipe ou Valor e gerenciar descartes.
 */
public class DeckCarta {
    /* ------------------ VARIÁVEIS ------------------ */

    private ArrayList<Carta> maoJogador = new ArrayList<>();
    private final static Naipe[] listaNaipes = Naipe.values();
    private final static ArrayList<OrdemDeValor> listaOrdemDeValor = new ArrayList<>(List.of(OrdemDeValor.values()));
    private static HashMap<Naipe, ArrayList<Carta>> baralho;
    private static HashMap<Naipe, ArrayList<Carta>> baralhoJogo;


    /* ------------------ CONSTRUTORES ------------------ */

    public DeckCarta(int tamanhoMao) {
        this.maoJogador = colocarCartasInicial();
        DeckCarta.verificarOrdenacao(GameState.getTipoDeOrganizacao(), this.maoJogador);
    }

    public DeckCarta(String tipoDeOrganizacao) {
        baralho = criarBaralho();
        baralhoJogo = criarBaralho();
        this.maoJogador = colocarCartasInicial();
        DeckCarta.verificarOrdenacao(GameState.getTipoDeOrganizacao(), this.maoJogador);
    }

    public DeckCarta(HashMap<Naipe, ArrayList<Carta>> baralhoNovo) {
        baralhoJogo = baralhoNovo;
        this.maoJogador = colocarCartasInicial();
        DeckCarta.verificarOrdenacao(GameState.getTipoDeOrganizacao(), this.maoJogador);
    }

    /* ------------------ MÉTODOS ------------------ */

    /**
     * Sorteia a mão inicial preenchendo até o tamanho padrão.
     */
    public ArrayList<Carta> colocarCartasInicial() {
        ArrayList<Carta> auxMaoJogador = new ArrayList<>();
        for (int i = 0; i < GameState.getTamanhoMao(); i++) {
            auxMaoJogador.add(gerarCartaAleatoria());
        }
        return auxMaoJogador;
    }

    /**
     * Repõe cartas na mão do jogador até atingir o tamanho máximo configurado.
     */
    public void adicionarCartas(ArrayList<Carta> maoJogador) {
        int qtdParaAdicionar = Math.abs(maoJogador.size() - GameState.getTamanhoMao());
        for (int i = 0; i < qtdParaAdicionar; i++) {
            maoJogador.add(DeckCarta.gerarCartaAleatoria());
        }
        DeckCarta.verificarOrdenacao(GameState.getTipoDeOrganizacao(), simpGetMaoJogador());
    }

    private ArrayList<Carta> simpGetMaoJogador() {
        return this.maoJogador;
    }

    /**
     * Remove da mão do jogador as cartas nas posições indicadas pelo vetor de
     * índices.
     * Ordena a seleção de trás para frente para evitar estouro de índice
     * (IndexOutOfBounds).
     * 
     * @param posMaoJogadas Array com os índices das cartas a remover
     * @return Lista das cartas removidas/jogadas
     */
    public ArrayList<Carta> retirarCartas(Integer[] posMaoJogadas) {
        Arrays.sort(posMaoJogadas, Collections.reverseOrder());
        ArrayList<Carta> cartasJogadas = new ArrayList<>();
        for (int i = 0; i < posMaoJogadas.length; i++) {
            int indice = (int) (posMaoJogadas[i]);
            cartasJogadas.add(this.maoJogador.remove(indice));
        }

        DeckCarta.verificarOrdenacao(GameState.getTipoDeOrganizacao(), this.maoJogador);
        DeckCarta.verificarOrdenacao(GameState.getTipoDeOrganizacao(), cartasJogadas);
        return cartasJogadas;
    }

    /**
     * Aplica o algoritmo de ordenação selecionado sobre a lista de cartas passada.
     */
    public static void verificarOrdenacao(String tipoDeOrdenacao, ArrayList<Carta> maoJogador) {
        if (tipoDeOrdenacao.equals("OrdemDeValor")) {
            organizarPorClasse(maoJogador);
        }
        if (tipoDeOrdenacao.equals("Naipe")) {
            organizarPorNaipe(maoJogador);
        }
    }

    /**
     * Ordena as cartas em ordem crescente com base no Naipe (Bubble Sort).
     */
    public static void organizarPorNaipe(ArrayList<Carta> maoJogador) {
        for (int i = 0; i < maoJogador.size(); i++) {
            for (int j = 0; j < maoJogador.size() - 1 - i; j++) {
                int ordemDeValorAtual = maoJogador.get(j).getNaipe().ordinal();
                int ordemDeValorPosterior = maoJogador.get(j + 1).getNaipe().ordinal();

                if (ordemDeValorAtual > ordemDeValorPosterior) {
                    Carta aux = maoJogador.get(j);
                    maoJogador.set(j, maoJogador.get(j + 1));
                    maoJogador.set(j + 1, aux);
                }
            }
        }
    }

    /**
     * Ordena as cartas em ordem decrescente com base no Valor da carta (Bubble
     * Sort).
     */
    public static void organizarPorClasse(ArrayList<Carta> maoJogador) {
        for (int i = 0; i < maoJogador.size(); i++) {
            for (int j = 0; j < maoJogador.size() - 1 - i; j++) {
                int ordemDeValorAtual = Carta.getValorOrdemDeValor(maoJogador.get(j).getOrdemDeValor());
                int ordemDeValorPosterior = Carta.getValorOrdemDeValor(maoJogador.get(j + 1).getOrdemDeValor());

                if (ordemDeValorAtual < ordemDeValorPosterior) {
                    Carta aux = maoJogador.get(j);
                    maoJogador.set(j, maoJogador.get(j + 1));
                    maoJogador.set(j + 1, aux);
                }
            }
        }
    }

    public int[] colocarValorMaoPoker() {
        return Carta.getValorOrdemDeValorArray(this.maoJogador);
    }

    /**
     * Sorteia e remove do baralho do jogo uma carta aleatória.
     */
    public static Carta gerarCartaAleatoria() {
        Random random = GameState.getRandom();
        Naipe naipeSorteado = listaNaipes[random.nextInt(listaNaipes.length)];
        ArrayList<Carta> cartasNaipeSorteado = baralhoJogo.get(naipeSorteado);

        while (cartasNaipeSorteado.isEmpty()) {
            naipeSorteado = listaNaipes[random.nextInt(listaNaipes.length)];
            cartasNaipeSorteado = baralhoJogo.get(naipeSorteado);
        }

        int indexSorteado = random.nextInt(cartasNaipeSorteado.size());
        return cartasNaipeSorteado.remove(indexSorteado);
    }



    /**
     * Constrói o HashMap inicial de 52 cartas estruturado por Naipes.
     */
    public static HashMap<Naipe, ArrayList<Carta>> criarBaralho() {
        HashMap<Naipe, ArrayList<Carta>> baralhoAuxiliar = new HashMap<>();
        for (Naipe str : Naipe.values()) {
            ArrayList<Carta> listaCarta = new ArrayList<>();
            for (int i = 0; i < listaOrdemDeValor.size(); i++) {
                listaCarta.add(new Carta(listaOrdemDeValor.get(i), str));
            }
            baralhoAuxiliar.put(str, listaCarta);
        }
        return baralhoAuxiliar;
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public ArrayList<Carta> getMaoJogador() {
        return maoJogador;
    }

    public void setMaoJogador(ArrayList<Carta> maoJogador) {
        this.maoJogador = maoJogador;
    }

    public static Naipe[] getListanaipes() {
        return listaNaipes;
    }

    public static ArrayList<OrdemDeValor> getListaordemdevalor() {
        return listaOrdemDeValor;
    }

    public HashMap<Naipe, ArrayList<Carta>> getBaralho() {
        return baralho;
    }

    public void setBaralho(HashMap<Naipe, ArrayList<Carta>> baralho) {
        DeckCarta.baralho = baralho;
    }

    public static HashMap<Naipe, ArrayList<Carta>> getBaralhoJogo() {
        return baralhoJogo;
    }

    public static void setBaralhoJogo(HashMap<Naipe, ArrayList<Carta>> baralhoJogo) {
        DeckCarta.baralhoJogo = baralhoJogo;
    }

}