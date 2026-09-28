package model.cartas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import model.enums.Naipe;

/**
 * Gerenciador principal do baralho e da mão do jogador.
 * Responsável por inicializar o baralho de 52 cartas, sortear cartas aleatórias,
 * reabastecer a mão, ordenar por Naipe ou Valor e gerenciar descartes.
 */
public class DeckCarta {
    /* ------------------ VARIÁVEIS ------------------ */

    private ArrayList<Carta> maoJogador = new ArrayList<>();
    private int tamanhoMao = 8;
    private int qtdMao = 3;             // Quantidade de jogadas restantes na rodada
    private int qtdMaoDescarte = 3;      // Quantidade de descartes restantes na rodada
    private String tipoDeOrganizacao = "OrdemDeValor";
    
    private final static Naipe[] listaNaipes = Naipe.values();
    private final static ArrayList<String> listaOrdemDeValor = new ArrayList<>(
            List.of("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"));
            
    private static HashMap<Naipe, ArrayList<Carta>> baralho = criarBaralho();
    private static HashMap<Naipe, ArrayList<Carta>> baralhoJogo = baralho;
    private final static Random random = new Random();

    /* ------------------ CONSTRUTORES ------------------ */

    public DeckCarta() {
        this.maoJogador = colocarCartasInicial(tamanhoMao);
        DeckCarta.verificarOrdenacao(this.tipoDeOrganizacao, this.maoJogador);
    }

    public DeckCarta(String tipoDeOrganizacao) {
        this.tipoDeOrganizacao = tipoDeOrganizacao;
        this.maoJogador = colocarCartasInicial(tamanhoMao);
        DeckCarta.verificarOrdenacao(this.tipoDeOrganizacao, this.maoJogador);
    }

    public DeckCarta(HashMap<Naipe, ArrayList<Carta>> baralhoNovo) {
        baralhoJogo = baralhoNovo;
        this.maoJogador = colocarCartasInicial(tamanhoMao);
        DeckCarta.verificarOrdenacao(this.tipoDeOrganizacao, this.maoJogador);
    }

    /* ------------------ MÉTODOS ------------------ */

    /**
     * Sorteia a mão inicial preenchendo até o tamanho padrão.
     */
    public ArrayList<Carta> colocarCartasInicial(int tamanhoMao) {
        ArrayList<Carta> auxMaoJogador = new ArrayList<>();
        for (int i = 0; i < tamanhoMao; i++) {
            auxMaoJogador.add(gerarCartaAleatoria());
        }
        return auxMaoJogador;
    }

    /**
     * Repõe cartas na mão do jogador até atingir o tamanho máximo configurado.
     */
    public void adicionarCartas(ArrayList<Carta> maoJogador) {
        int qtdParaAdicionar = Math.abs(maoJogador.size() - this.tamanhoMao);
        for (int i = 0; i < qtdParaAdicionar; i++) {
            maoJogador.add(DeckCarta.gerarCartaAleatoria());
        }
        DeckCarta.verificarOrdenacao(this.tipoDeOrganizacao, simpGetMaoJogador());
    }

    private ArrayList<Carta> simpGetMaoJogador() {
        return this.maoJogador;
    }

    /**
     * Remove da mão do jogador as cartas nas posições indicadas pelo vetor de índices.
     * Ordena a seleção de trás para frente para evitar estouro de índice (IndexOutOfBounds).
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

        DeckCarta.verificarOrdenacao(this.tipoDeOrganizacao, this.maoJogador);
        DeckCarta.verificarOrdenacao(this.tipoDeOrganizacao, cartasJogadas);
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
     * Ordena as cartas em ordem decrescente com base no Valor da carta (Bubble Sort).
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
        return Carta.getValorOrdemDeValor(this.maoJogador);
    }

    /**
     * Sorteia e remove do baralho do jogo uma carta aleatória.
     */
    public static Carta gerarCartaAleatoria() {
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
     * Reseta as contagens de mãos, descartes e reinicializa o baralho completo antes do início de um novo jogo.
     */
    public void resetarPreJogo() {
        this.maoJogador = colocarCartasInicial(tamanhoMao);
        baralho = criarBaralho();
        this.qtdMao = 3;
        this.qtdMaoDescarte = 3;
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

    public int getTamanhoMao() {
        return tamanhoMao;
    }

    public void setTamanhoMao(int tamanhoMao) {
        this.tamanhoMao = tamanhoMao;
    }

    public int getQtdMao() {
        return qtdMao;
    }

    public void setQtdMao(int qtdMao) {
        this.qtdMao = qtdMao;
    }

    public int getQtdMaoDescarte() {
        return qtdMaoDescarte;
    }

    public void setQtdMaoDescarte(int qtdMaoDescarte) {
        this.qtdMaoDescarte = qtdMaoDescarte;
    }

    public String getTipoDeOrganizacao() {
        return tipoDeOrganizacao;
    }

    public void setTipoDeOrganizacao(String tipoDeOrganizacao) {
        this.tipoDeOrganizacao = tipoDeOrganizacao;
    }

    public static Naipe[] getListanaipes() {
        return listaNaipes;
    }

    public static ArrayList<String> getListaordemdevalor() {
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