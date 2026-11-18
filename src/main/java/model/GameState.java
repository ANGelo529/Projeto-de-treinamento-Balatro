package model;

import java.util.Random;

import jdbc.DeckCartaDao;
import jdbc.GameStateDAO;
import jdbc.UsuarioDAO;
import util.TesteSaida;
import model.blinds.Ante;
import model.cartas.DeckCarta;
import model.contextos.ContextoMao;
import model.coringas.DeckCoringa;
import model.usuario.Usuario;
import service.ValidadorPoker;

public class GameState {
    /* ------------------ ATRIBUTOS ------------------ */

    private static int idGameState;
    private static int tamanhoMao = 8;
    private static int qtdMao = 3; // Quantidade de jogadas restantes na rodada
    private static int qtdMaoDescarte = 3; // Quantidade de descartes restantes na rodada
    private static long pontosAtuais = 0;
    private static int seed;
    private static int anteAtual = 1;
    private static int posBlindAtual;
    private static int limiteCoringas = 5;
    private static int limiteConsumiveis = 5;
    private static String tipoDeOrganizacao = "OrdemDeValor";
    private static Random random;
    private static DeckCarta deckCarta;
    private static DeckCoringa deckCoringa;
    private static Ante ante;
    private static Usuario usuario;

    /* ------------------ MÉTODOS ------------------ */

    public static void iniciarSeed(String seedParaSortear) {
        GameState.setRandom(new Random(seedParaSortear.hashCode()));
        // Lembrar de implementar o INSERT da seed aqui
    }

    public static void iniciarRodada() {
        GameStateDAO.receberDados(usuario);
        DeckCarta.criarBaralhoJogo();
        DeckCartaDao.inserirBaralho();
        DeckCarta.adicionarCartas(DeckCarta.getMaoJogador());
        Ante.setBlindsAtuais(Ante.atualizarBlind(anteAtual));
        posBlindAtual = 0;
        DeckCartaDao.updateCartasJogadas();
        UsuarioDAO.updateStatusJogo(usuario,true);
    }

    public static void controleJogo() {
        iniciarRodada();
        TesteSaida.printBlidsAtuais();
        ContextoMao ctx = new ContextoMao();
        while (qtdMao > 0) {
            // Futuramente fazer uma forma de receber/entregar um valor do JavaScript
            // Ou seja se for fazer com front todo print vira um request pro front

            TesteSaida.printBlindAtual();
            // DeckCoringa.verificarCoringaPassivo();
            ctx.setCartasJogadas(DeckCarta.jogarCartas(TesteSaida.pegarPosicoes()));
            TesteSaida.printMaoJogador(ctx.getCartasJogadas());
            ctx.setPontos(ValidadorPoker.calcularJogada(ctx));
            System.out.println("Fichas: " + ctx.getFichas());
            System.out.println("Multi: " + ctx.getMulti());
            // DeckCoringa.verificarCoringaPorCarta()
            if (verificarPontos(ctx)) {
                break;
            }
            DeckCarta.adicionarCartas(DeckCarta.getMaoJogador());
            qtdMao--;
        }

        // TesteSaida.printGanhouBlind();
        avancarAnte();
        resetarValores();
        // ShopState.iniciarLoja();
    }

    private static boolean verificarPontos(ContextoMao ctx) {
        long fichas = ctx.getPontos().getFicha();
        long multi = ctx.getPontos().getMulti();
        long limiteBlind = Ante.getBlindsAtuais()[posBlindAtual].getValorBlind();
        pontosAtuais = fichas * multi;
        if (pontosAtuais >= limiteBlind) {
            return true;
        }
        return false;
    }

    private static void avancarAnte() {
    }

    public static void resetarValores() {
        DeckCarta.getBaralhoJogo().clear();
        DeckCarta.getMaoJogador().clear();
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

    public static int getPosBlindAtual() {
        return posBlindAtual;
    }

    public static void setPosBlindAtual(int posBlindAtual) {
        GameState.posBlindAtual = posBlindAtual;
    }

    public static String getTipoDeOrganizacao() {
        return tipoDeOrganizacao;
    }

    public static int getLimiteCoringas() {
        return limiteCoringas;
    }

    public static void setLimiteCoringas(int limiteCoringas) {
        GameState.limiteCoringas = limiteCoringas;
    }

    public static int getLimiteConsumiveis() {
        return limiteConsumiveis;
    }

    public static void setLimiteConsumiveis(int limiteConsumiveis) {
        GameState.limiteConsumiveis = limiteConsumiveis;
    }

    public static void setTipoDeOrganizacao(String tipoDeOrganizacao) {
        GameState.tipoDeOrganizacao = tipoDeOrganizacao;
    }

    public static long getpontosAtuais() {
        return pontosAtuais;
    }

    public static void setpontosAtuais(long pontosAtuais) {
        GameState.pontosAtuais = pontosAtuais;
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

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        GameState.usuario = usuario;
    }
}