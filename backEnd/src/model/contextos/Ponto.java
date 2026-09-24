package backEnd.src.model.contextos;

import backEnd.src.model.enums.MaoPoker;

public class Ponto {
    /* ------------------ VARIÁVEIS ------------------ */

    private int ficha;
    private int multi;
    private int nivel = 1;
    private MaoPoker nomeMaoJogada;

    /* ------------------ CONSTRUTOR ------------------ */

    public Ponto(int ficha, int multi, int nivel) {
        this.ficha = ficha;
        this.multi = multi;
        this.nivel = nivel;
    }

    public Ponto(int ficha, int multi, MaoPoker nomeMaoJogada) {
        this.ficha = ficha;
        this.multi = multi;
        this.nomeMaoJogada = nomeMaoJogada;
    }

    public Ponto() {
    }

    /* ------------------ MéTODOS ------------------ */

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
