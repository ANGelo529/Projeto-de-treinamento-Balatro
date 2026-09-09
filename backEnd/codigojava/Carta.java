package backEnd.codigojava;


public class Carta {
    /* ------------------ VARIÁVEIS ------------------ */

    private int ordemDeValor;
    private String naipe;
    private String melhoria  = "Sem valor";
    private String selo  = "Sem valor";
    private String edicao  = "Sem valor";
    private String tipoDeCarta = "Numero";
    private boolean statusJogada = false;



    /* ------------------ CONSTRUTOR ------------------ */

    public Carta(int ordemDeValor, String naipe) {
        this.ordemDeValor = ordemDeValor;
        this.naipe = naipe;
    }

    public Carta(int ordemDeValor, String naipe, String tipoDeCarta) {
        this.ordemDeValor = ordemDeValor;
        this.naipe = naipe;
        this.tipoDeCarta = tipoDeCarta;
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public int getOrdemDeValor() {
        return ordemDeValor;
    }

    public void setOrdemDeValor(int ordemDeValor) {
        this.ordemDeValor = ordemDeValor;
    }

    public String getNaipe() {
        return naipe;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }

    public String getMelhoria() {
        return melhoria;
    }

    public void setMelhoria(String melhoria) {
        this.melhoria = melhoria;
    }

    public String getSelo() {
        return selo;
    }

    public void setSelo(String selo) {
        this.selo = selo;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getTipoDeCarta() {
        return tipoDeCarta;
    }

    public void setTipoDeCarta(String tipoDeCarta) {
        this.tipoDeCarta = tipoDeCarta;
    }

    public boolean isStatusJogada() {
        return statusJogada;
    }

    public void setStatusJogada(boolean statusJogada) {
        this.statusJogada = statusJogada;
    }
}
