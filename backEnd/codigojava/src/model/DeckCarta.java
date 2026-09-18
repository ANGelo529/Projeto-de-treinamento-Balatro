package backEnd.codigojava.src.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class DeckCarta {
    /* ------------------ VARIÁVEIS ------------------ */

    private ArrayList<Carta> maoJogador = new ArrayList<>();
    private int tamanhoMao = 8;
    private int qtdMao = 3;
    private int qtdMaoDescarte = 3;
    private String tipoDeOrganizacao = "OrdemDeValor";
    private final static ArrayList<String> listaNaipes = new ArrayList<>(List.of("Espadas", "Copas", "Paus", "Ouros"));
    private final static ArrayList<String> listaOrdemDeValor = new ArrayList<>(
            List.of("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",
                    "A"));
    private static HashMap<String, ArrayList<Carta>> baralho = criarBaralho();
    private static HashMap<String, ArrayList<Carta>> auxBaralho = baralho;
    private final static Random random = new Random();

    /* ------------------ CONSTRUTOR ------------------ */

    public DeckCarta() {
        this.maoJogador = colocarCartasInicial(tamanhoMao);
        DeckCarta.verificarOrdenacao(this.tipoDeOrganizacao, this.maoJogador);
    }

    public DeckCarta(String tipoDeOrganizacao) {
        this.tipoDeOrganizacao = tipoDeOrganizacao;
        this.maoJogador = colocarCartasInicial(tamanhoMao);
        DeckCarta.verificarOrdenacao(this.tipoDeOrganizacao, this.maoJogador);
    }

    public DeckCarta(HashMap<String, ArrayList<Carta>> baralhoNovo) {
        auxBaralho = baralhoNovo;
        this.maoJogador = colocarCartasInicial(tamanhoMao);
        DeckCarta.verificarOrdenacao(this.tipoDeOrganizacao, this.maoJogador);
    }

    /* ------------------ MéTODOS ------------------ */

    public ArrayList<Carta> colocarCartasInicial(int tamanhoMao) {
        ArrayList<Carta> auxMaoJogador = new ArrayList<>();
        for (int i = 0; i < tamanhoMao; i++) {
            auxMaoJogador.add(gerarCartaAleatoria());
        }

        return auxMaoJogador;
    }

    public void adicionarCartas(ArrayList<Carta> maoJogador) {
        int qtdParaAdicionar = Math.abs(maoJogador.size() - this.tamanhoMao);
        for (int i = 0; i < qtdParaAdicionar; i++) {
            maoJogador.add(DeckCarta.gerarCartaAleatoria());
        }

        DeckCarta.verificarOrdenacao(this.tipoDeOrganizacao, this.maoJogador);
    }

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

    public static void verificarOrdenacao(String tipoDeOrdenacao, ArrayList<Carta> maoJogador) {
        if (tipoDeOrdenacao.equals("OrdemDeValor")) {
            organizarPorClasse(maoJogador);
        }

        if (tipoDeOrdenacao.equals("Naipe")) {
            organizarPorNaipe(maoJogador);
        }
    }

    public static void organizarPorNaipe(ArrayList<Carta> maoJogador) {
        for (int i = 0; i < maoJogador.size(); i++) {
            for (int j = 0; j < maoJogador.size() - 1 - i; j++) {

                int ordemDeValorAtual = listaNaipes.indexOf(maoJogador.get(j).getNaipe());
                int ordemDeValorPosterior = listaNaipes.indexOf(maoJogador.get(j + 1).getNaipe());

                if (ordemDeValorAtual > ordemDeValorPosterior) {
                    Carta aux = maoJogador.get(j);
                    maoJogador.set(j, maoJogador.get(j + 1));
                    maoJogador.set(j + 1, aux);
                }
            }
        }
    }

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
        int[] valoresMaoPoker = Carta.getValorOrdemDeValor(this.maoJogador);
        return valoresMaoPoker;
    }

    public static Carta gerarCartaAleatoria() {
        String naipeSorteado = listaNaipes.get(random.nextInt(listaNaipes.size()));
        ArrayList<Carta> cartasNaipeSorteado = auxBaralho.get(naipeSorteado);

        while (cartasNaipeSorteado.isEmpty()) {
            naipeSorteado = listaNaipes.get(random.nextInt(listaNaipes.size()));
            cartasNaipeSorteado = auxBaralho.get(naipeSorteado);
        }

        int indexSorteado = random.nextInt(cartasNaipeSorteado.size());
        Carta cartaSorteada = cartasNaipeSorteado.remove(indexSorteado);

        return cartaSorteada;
    }

    public void resetarPreJogo() {
        this.maoJogador = colocarCartasInicial(tamanhoMao);
        baralho = criarBaralho();
        this.qtdMao = 3;
        this.qtdMaoDescarte = 3;
    }

    public static HashMap<String, ArrayList<Carta>> criarBaralho() {
        HashMap<String, ArrayList<Carta>> baralhoAuxiliar = new HashMap<>();
        for (String str : listaNaipes) {
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

    public static ArrayList<String> getListanaipes() {
        return listaNaipes;
    }

    public static ArrayList<String> getListaordemdevalor() {
        return listaOrdemDeValor;
    }

    public HashMap<String, ArrayList<Carta>> getBaralho() {
        return baralho;
    }

    public void setBaralho(HashMap<String, ArrayList<Carta>> baralho) {
        DeckCarta.baralho = baralho;
    }

    public static HashMap<String, ArrayList<Carta>> getAuxBaralho() {
        return auxBaralho;
    }

    public static void setAuxBaralho(HashMap<String, ArrayList<Carta>> auxBaralho) {
        DeckCarta.auxBaralho = auxBaralho;
    }

}
