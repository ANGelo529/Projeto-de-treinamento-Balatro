package backEnd.codigojava;

public class Pontos {
    /* ------------------ VARIÁVEIS ------------------ */

    private int ficha = 1;
    private int multi;
    private int nivel;

    /* ------------------ CONSTRUTOR ------------------ */
    
    public Pontos(int ficha, int multi, int nivel) {
        this.ficha = ficha;
        this.multi = multi;
        this.nivel = nivel;
    }

    public Pontos() {
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

}
