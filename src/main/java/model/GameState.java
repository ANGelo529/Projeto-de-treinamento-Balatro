package model;

import java.util.Random;

import model.blinds.Ante;
import model.cartas.DeckCarta;
import model.coringas.DeckCoringa;

public class GameState {
    /* ------------------ ATRIBUTOS ------------------ */

    private static int idGameState;
    private static int tamanhoMao = 8;
    private static int qtdMao = 3; // Quantidade de jogadas restantes na rodada
    private static int qtdMaoDescarte = 3; // Quantidade de descartes restantes na rodada
    private static int pontosTotaisAtuais;
    private static int seed;
    private static int anteAtual = 0;
    private static String tipoDeOrganizacao = "OrdemDeValor";
    private static Random random;
    private static DeckCarta deckCarta;
    private static DeckCoringa deckCoringa;
    private static Ante ante;

    /* ------------------ MÉTODOS ------------------ */

    public static void iniciarSeed(String seedParaSortear) {
        GameState.setRandom(new Random(seedParaSortear.hashCode()));
    }

    public static void iniciarRodada() {
        DeckCarta.criarBaralhoJogo();
        DeckCarta.adicionarCartas(DeckCarta.getMaoJogador());
        Ante.atualizarBlind(anteAtual);
    }

    /* ------------------ GETTERS & SETTERS ------------------ */
    public static int getIdGameState() {
        return idGameState;
    }

    public static void setIdGameState(int idGameState) {
        GameState.idGameState = idGameState;
    }

    public static int getTamanhoMao() {
        return tamanhoMao;
    }

    public static void setTamanhoMao(int tamanhoMao) {
        GameState.tamanhoMao = tamanhoMao;
    }

    public static int getQtdMao() {
        return qtdMao;
    }

    public static void setQtdMao(int qtdMao) {
        GameState.qtdMao = qtdMao;
    }

    public static int getQtdMaoDescarte() {
        return qtdMaoDescarte;
    }

    public static void setQtdMaoDescarte(int qtdMaoDescarte) {
        GameState.qtdMaoDescarte = qtdMaoDescarte;
    }

    public static int getAnteAtual() {
        return anteAtual;
    }

    public static void setAnteAtual(int anteAtual) {
        GameState.anteAtual = anteAtual;
    }

    public static String getTipoDeOrganizacao() {
        return tipoDeOrganizacao;
    }

    public static void setTipoDeOrganizacao(String tipoDeOrganizacao) {
        GameState.tipoDeOrganizacao = tipoDeOrganizacao;
    }

    public static int getPontosTotaisAtuais() {
        return pontosTotaisAtuais;
    }

    public static void setPontosTotaisAtuais(int pontosTotaisAtuais) {
        GameState.pontosTotaisAtuais = pontosTotaisAtuais;
    }

    public static int getSeed() {
        return seed;
    }

    public static void setSeed(int seed) {
        GameState.seed = seed;
    }

    public static Random getRandom() {
        return random;
    }

    public static void setRandom(Random random) {
        GameState.random = random;
    }

    public static DeckCarta getDeckCarta() {
        return deckCarta;
    }

    public static void setDeckCarta(DeckCarta deckCarta) {
        GameState.deckCarta = deckCarta;
    }

    public static DeckCoringa getDeckCoringa() {
        return deckCoringa;
    }

    public static void setDeckCoringa(DeckCoringa deckCoringa) {
        GameState.deckCoringa = deckCoringa;
    }

    public static Ante getAnte() {
        return ante;
    }

    public static void setAnte(Ante ante) {
        GameState.ante = ante;
    }
}