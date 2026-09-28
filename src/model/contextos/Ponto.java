package model.contextos;

import model.enums.MaoPoker;

/**
 * Representa os valores base de pontuação (Fichas e Multiplicador) associados 
 * a um nível específico de uma mão de poker.
 */
public class Ponto {

    /* ------------------ VARIÁVEIS ------------------ */

    /** Quantidade base de fichas concedidas pela mão */
    private int ficha;

    /** Valor base do multiplicador concedido pela mão */
    private int multi;

    /** Nível atual de evolução da mão de poker (padrão inicia em 1) */
    private int nivel = 1;

    /** Identificador do tipo de mão de poker associada a esta pontuação */
    private MaoPoker nomeMaoJogada;

    /* ------------------ CONSTRUTORES ------------------ */

    /**
     * Construtor para definir ficha, multiplicador e nível da mão.
     * 
     * @param ficha Valor base em fichas.
     * @param multi Valor base do multiplicador.
     * @param nivel Nível inicial.
     */
    public Ponto(int ficha, int multi, int nivel) {
        this.ficha = ficha;
        this.multi = multi;
        this.nivel = nivel;
    }

    /**
     * Construtor para mapear a pontuação a um tipo específico de mão de poker.
     * 
     * @param ficha Valor base em fichas.
     * @param multi Valor base do multiplicador.
     * @param nomeMaoJogada Enum do tipo da mão de poker.
     */
    public Ponto(int ficha, int multi, MaoPoker nomeMaoJogada) {
        this.ficha = ficha;
        this.multi = multi;
        this.nomeMaoJogada = nomeMaoJogada;
    }

    /** Construtor padrão sem parâmetros. */
    public Ponto() {
    }

    /* ------------------ MÉTODOS DE NEGÓCIO ------------------ */

    /**
     * Incrementa o nível da mão e melhora os seus atributos base.
     * Aumenta a ficha e o multiplicador em 50% do seu valor atual (usando divisão inteira truncada).
     */
    public void uparMao() {
        this.nivel++;
        this.ficha += (int) (this.ficha / 2);
        this.multi += (int) (this.multi / 2);
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public int getFicha() {
        return ficha;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

    public int getMulti() {
        return multi;
    }

    public void setMulti(int multi) {
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