package backEnd.codigojava;

public class Blind {
    /* ------------------ VARIÁVEIS ------------------ */

    private String nome;
    private int dinheiroAoGanhar;
    private float multiplicadorValorBase;
    private EfeitoBlind EfeitoBlind;
    
    /* ------------------ CONSTRUTOR ------------------ */

    public Blind(String nome, int dinheiroAoGanhar, float multiplicadorValorBase) {
        this.nome = nome;
        this.dinheiroAoGanhar = dinheiroAoGanhar;
        this.multiplicadorValorBase = multiplicadorValorBase;

    }

    public Blind(){
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
    public float getMultiplicadorValorBase() {
        return multiplicadorValorBase;
    }
    public void setMultiplicadorValorBase(float multiplicadorValorBase) {
        this.multiplicadorValorBase = multiplicadorValorBase;
    }
    public EfeitoBlind getEfeitoBlind() {
        return EfeitoBlind;
    }
    public void setEfeitoBlind(EfeitoBlind efeitoBlind) {
        EfeitoBlind = efeitoBlind;
    }


}
