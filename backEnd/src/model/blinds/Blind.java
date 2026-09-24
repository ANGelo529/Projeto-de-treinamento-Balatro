package backEnd.src.model.blinds;

import backEnd.src.model.Efeitos.EfeitoBlind;

public class Blind {
    /* ------------------ VARIÁVEIS ------------------ */

    private String nome;
    private int dinheiroAoGanhar = 3;
    private long valorBlind;
    private double multiplicadorValorBase;
    private int anteMinimoAparicao = 0;
    private EfeitoBlind efeitoBlind;

    /* ------------------ CONSTRUTOR ------------------ */

    public Blind(String nome, int dinheiroAoGanhar, long valorBlind, int multiplicadorValorBase,
            int anteMinimoAparicao) {
        this.nome = nome;
        this.dinheiroAoGanhar = dinheiroAoGanhar;
        this.valorBlind = valorBlind;
        this.multiplicadorValorBase = multiplicadorValorBase;
        this.anteMinimoAparicao = anteMinimoAparicao;
    }

    public Blind(String nome, int dinheiroAoGanhar, double multiplicadorValorBase, long valorBlind) {
        this.nome = nome;
        this.dinheiroAoGanhar = dinheiroAoGanhar;
        this.multiplicadorValorBase = multiplicadorValorBase;
        this.valorBlind = valorBlind;
    }

    public Blind(String nome, double multiplicadorValorBase, long valorBlind) {
        this.nome = nome;
        this.multiplicadorValorBase = multiplicadorValorBase;
        this.valorBlind = valorBlind;
    }

    public Blind(String nome, int dinheiroAoGanhar, int anteMinimoAparicao, EfeitoBlind efeitoBlind) {
        this.nome = nome;
        this.dinheiroAoGanhar = dinheiroAoGanhar;
        this.anteMinimoAparicao = anteMinimoAparicao;
        this.efeitoBlind = efeitoBlind;
    }

    public Blind() {
    }

    public static Blind gerarBlindAleatorio(int[] valoresBase, int anteAtual) {
        // BlindFactory conjuntoBLind = BlindFactory.getTodosOsBlinds();
        // Blind blind = conjuntoBLind.sortearBlind();
        Blind blind = new Blind();
        blind.setDinheiroAoGanhar(5);
        blind.setMultiplicadorValorBase(2);
        blind.setNome("Boss Blind");
        blind.setValorBlind(valoresBase[anteAtual]);
        return blind;
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

    public long getValorBlind() {
        return valorBlind;
    }

    public void setValorBlind(long valorBlind) {
        this.valorBlind = valorBlind;
    }

    public double getMultiplicadorValorBase() {
        return multiplicadorValorBase;
    }

    public void setMultiplicadorValorBase(double multiplicadorValorBase) {
        this.multiplicadorValorBase = multiplicadorValorBase;
    }

    public int getAnteMinimoAparicao() {
        return anteMinimoAparicao;
    }

    public void setAnteMinimoAparicao(int anteMinimoAparicao) {
        this.anteMinimoAparicao = anteMinimoAparicao;
    }

    public EfeitoBlind getEfeitoBlind() {
        return efeitoBlind;
    }

    public void setEfeitoBlind(EfeitoBlind efeitoBlind) {
        this.efeitoBlind = efeitoBlind;
    }

}
