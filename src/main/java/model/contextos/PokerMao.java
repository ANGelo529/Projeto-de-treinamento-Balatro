package model.contextos;

import model.enums.MaoPoker;

/**
 * Representa os valores base de pontuação (Fichas e Multiplicador) associados
 * a um nível específico de uma mão de poker.
 */
public class PokerMao {

    /* ------------------ VARIÁVEIS ------------------ */

    /** Quantidade base de fichas concedidas pela mão */
    private long ficha;

    /** Valor base do multiplicador concedido pela mão */
    private long multi;

    /** Nível atual de evolução da mão de poker (padrão inicia em 1) */
    private int nivel = 1;

    /** Identificador do tipo de mão de poker associada a esta pontuação */
    private MaoPoker nomeMaoJogada;

    /* ------------------ CONSTRUTORES ------------------ */

    /**
     * Construtor para definir ficha, multiplicador e nível da mão.
     * 
     * @param ficha         Valor base em fichas.
     * @param multi         Valor base do multiplicador.
     * @param nivel         Nível inicial.
     * @param nomeMaoJogada nomeMaoJogada
     */
    public PokerMao(long ficha, long multi, int nivel, MaoPoker nomeMaoJogada) {
        this.ficha = ficha;
        this.multi = multi;
        this.nivel = nivel;
        this.nomeMaoJogada = nomeMaoJogada;
    }

    /** Construtor padrão sem parâmetros. */
    public PokerMao() {
    }

    /* ------------------ MÉTODOS DE NEGÓCIO ------------------ */

    /**
     * Incrementa o nível da mão e melhora os seus atributos base.
     * Aumenta a ficha e o multiplicador em 50% do seu valor atual (usando divisão
     * inteira truncada).
     */
    public void uparMao() {
        this.nivel++;
        this.ficha += (int) (this.ficha / 2);
        this.multi += (int) (this.multi / 2);
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public long getFicha() {
        return ficha;
    }

    public void setFicha(long ficha) {
        this.ficha = ficha;
    }

    public long getMulti() {
        return multi;
    }

    public void setMulti(long multi) {
        this.multi = multi;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public MaoPoker getNomeMaoJogada() {
        return nomeMaoJogada;
    }

    public void setNomeMaoJogada(MaoPoker nomeMaoJogada) {
        this.nomeMaoJogada = nomeMaoJogada;
    }
}