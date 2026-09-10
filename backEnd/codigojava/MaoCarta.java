package backEnd.codigojava;

import java.util.ArrayList;
import java.util.HashMap;

public class MaoCarta {
    /* ------------------ VARIÁVEIS ------------------ */
    
    private ArrayList<Carta> maoJogador = new ArrayList<>();
    private int tamanhoMao = 8;
    private int qtdMao = 3;
    private int qtdMaoDescarte = 3;
    private String tipoDeOrganizacao = "OrdemDeValor";
    private HashMap<String,ArrayList<Carta>> baralho;

    /* ------------------ CONSTRUTOR ------------------ */

    public MaoCarta() {
        maoJogador = colocarCartasInicial(tamanhoMao);
        baralho = criarBaralho();
        resetarPreJogo();
    }

    public MaoCarta(String tipoDeOrganizacao) {
        this.tipoDeOrganizacao = tipoDeOrganizacao;
        maoJogador = colocarCartasInicial(tamanhoMao);
        baralho = criarBaralho();
        resetarPreJogo();
    }

    /* ------------------ MéTODOS ------------------ */

    public static ArrayList<Carta> colocarCartasInicial(int tamanhoMao) {
        return new ArrayList<>();
    }

    public static ArrayList<Carta> adicionarCartas(int qtdParaAdicionar) {
        return new ArrayList<>();
    }

    public static ArrayList<Carta> retirarCartas() {
        return new ArrayList<>();
    }

    public static ArrayList<Carta> organizarPorNaipe() {
        return new ArrayList<>();
    }

    public static ArrayList<Carta> organizarPorClasse() {
        return new ArrayList<>();
    }

    public static void colocarValorMaoPoker(String maoAlterada) {
    }

    public static boolean verificarExistentes() {
        return true;
    }

    public static int gerarCartaAleatoria() {
        return 0;
    }

    public static void resetarPreJogo() {
    }

    public static HashMap<String,ArrayList<Carta>> criarBaralho() {
        return new HashMap<String,ArrayList<Carta>>();
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public ArrayList<Carta> getMaoJogador() {
        return maoJogador;
    }

    public void setMaoJogador(ArrayList<Carta> maoJogador) {
        this.maoJogador = maoJogador;
    }

    public int getTamanhoMao() {
        return tamanhoMao;
    }

    public void setTamanhoMao(int tamanhoMao) {
        this.tamanhoMao = tamanhoMao;
    }

    public int getQtdMao() {
        return qtdMao;
    }

    public void setQtdMao(int qtdMao) {
        this.qtdMao = qtdMao;
    }

    public int getQtdMaoDescarte() {
        return qtdMaoDescarte;
    }

    public void setQtdMaoDescarte(int qtdMaoDescarte) {
        this.qtdMaoDescarte = qtdMaoDescarte;
    }

    public String getTipoDeOrganizacao() {
        return tipoDeOrganizacao;
    }

    public void setTipoDeOrganizacao(String tipoDeOrganizacao) {
        this.tipoDeOrganizacao = tipoDeOrganizacao;
    }

    public HashMap<String,ArrayList<Carta>> getBaralho() {
        return baralho;
    }

    public void setBaralho(HashMap<String,ArrayList<Carta>> baralho) {
        this.baralho = baralho;
    }

}
