package model.contextos;

import java.util.ArrayList;
import model.cartas.Carta;
import model.enums.MaoPoker;

/**
 * Classe responsável por representar o contexto de pontuação de uma mão jogada.
 * Atua como um DTO (Data Transfer Object) transportando o estado das cartas, 
 * pontuação atual e carta em processamento.
 * 
 * ATENÇÃO (BUG DE DESSINCRONIZAÇÃO): Esta classe mantém 'fichasAtuais' e 'multAtual'
 * de forma duplicada em relação ao objeto 'pontos' (instância de Ponto). Alterar
 * 'multAtual' nesta classe não atualiza a propriedade correspondente em 'pontos'.
 */
public class ContextoMao {

    /* ------------------ VARIÁVEIS DE ESTADO ------------------ */

    /** Tipo da mão de poker jogada (ex: PAR, TRINCA, FLUSH) */
    private MaoPoker tipoMao;

    /** Lista das cartas selecionadas e jogadas pelo jogador na rodada atual */
    private ArrayList<Carta> cartasJogadas;

    /** Carta individual sendo avaliada no momento pelo efeito de um Coringa */
    private Carta cartaAuxiliar;

    /** Objeto que armazena os valores base de fichas, multiplicadores e nível da mão */
    private Ponto pontos = new Ponto();


    /* ------------------ CONSTRUTORES ------------------ */

    /**
     * Construtor completo a partir de uma lista de cartas e do objeto Ponto.
     * Inicializa os valores locais de fichas e multiplicador com base no Ponto informado.
     * 
     * @param cartasJogadas Lista de cartas jogadas.
     * @param pontos Objeto Ponto com as estatísticas base da mão.
     */
    public ContextoMao(ArrayList<Carta> cartasJogadas, Ponto pontos) {
        this.tipoMao = pontos.getNomeMaoJogada();
        this.cartasJogadas = cartasJogadas;
        this.pontos = pontos;
    }

    /** Construtor padrão sem argumentos. */
    public ContextoMao() {
    }

    /* ------------------ MÉTODOS DE NEGÓCIO ------------------ */

    /**
     * Incrementa o multiplicador atual com um valor específico.
     * 
     * @param valor Quantidade a ser somada ao multiplicador.
     */
    public void adicionarMult(int valor) {
        this.pontos.setMulti(valor + pontos.getMulti());
    }

    /**
     * Incrementa as fichas com um valor específico.
     * @param valor quantidade a ser somada as fichas
     */
    public void adicionarFicha(int valor) {
        this.pontos.setFicha(valor + pontos.getFicha());
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public MaoPoker getTipoMao() {
        return tipoMao;
    }

    public void setTipoMao(MaoPoker tipoMao) {
        this.tipoMao = tipoMao;
    }

    public ArrayList<Carta> getCartasJogadas() {
        return cartasJogadas;
    }

    public void setCartasJogadas(ArrayList<Carta> cartasJogadas) {
        this.cartasJogadas = cartasJogadas;
    }

    public Carta getCartaAuxiliar() {
        return cartaAuxiliar;
    }

    public void setCartaAuxiliar(Carta cartaAuxiliar) {
        this.cartaAuxiliar = cartaAuxiliar;
    }

    public Ponto getPontos() {
        return pontos;
    }

    public void setPontos(Ponto pontos) {
        this.pontos = pontos;
    }

}