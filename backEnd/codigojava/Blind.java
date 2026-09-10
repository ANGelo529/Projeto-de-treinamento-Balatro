package backEnd.codigojava;

public class Blind {
    /* ------------------ VARIÁVEIS ------------------ */

    private String nome;
    private int dinheiroAoGanhar;
    private int valorBlind;
    private float multiplicadorValorBase;
    private int anteMinimoAparicao = 0;
    private EfeitoBlind EfeitoBlind;

    /* ------------------ CONSTRUTOR ------------------ */

    public Blind(String nome, int dinheiroAoGanhar, float multiplicadorValorBase) {
        this.nome = nome;
        this.dinheiroAoGanhar = dinheiroAoGanhar;
        this.multiplicadorValorBase = multiplicadorValorBase;
    }

    public Blind() {
    }

    public static Blind gerarBlindAleatorio() {
        return new Blind();
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDinheiroAoGanhar() {
        return dinheiroAoGanhar;
    }

    public void setDinheiroAoGanhar(int dinheiroAoGanhar) {
        this.dinheiroAoGanhar = dinheiroAoGanhar;
    }

    public int getValorBlind() {
        return valorBlind;
    }

    public void setValorBlind(int valorBlind) {
        this.valorBlind = valorBlind;
    }

    public float getMultiplicadorValorBase() {
        return multiplicadorValorBase;
    }

    public void setMultiplicadorValorBase(float multiplicadorValorBase) {
        this.multiplicadorValorBase = multiplicadorValorBase;
    }

    public int getAnteMinimoAparicao() {
        return anteMinimoAparicao;
    }

    public void setAnteMinimoAparicao(int anteMinimoAparicao) {
        this.anteMinimoAparicao = anteMinimoAparicao;
    }

    public EfeitoBlind getEfeitoBlind() {
        return EfeitoBlind;
    }

    public void setEfeitoBlind(EfeitoBlind efeitoBlind) {
        EfeitoBlind = efeitoBlind;
    }

}
