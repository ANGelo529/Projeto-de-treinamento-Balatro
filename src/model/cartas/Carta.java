package model.cartas;

import java.util.ArrayList;
import model.enums.Naipe;
import model.enums.OrdemDeValor;

/**
 * Representa uma Carta individual do baralho.
 * Possui atributos de valor, naipe e modificadores de estilo de jogo (melhorias, selos, edições).
 */
public class Carta {
    /* ------------------ VARIÁVEIS ------------------ */

    private OrdemDeValor ordemDeValor;       // Enum do valor ("2" até "10", "J", "Q", "K", "A")
    private Naipe naipe;              // Enum do Naipe (Ouros, Copas, Espadas, Paus)
    private String melhoria = "Sem valor";
    private String selo = "Sem valor";
    private String edicao = "Sem valor";
    private String tipoDeCarta = "Numero";

    /* ------------------ CONSTRUTORES ------------------ */

    public Carta(OrdemDeValor ordemDeValor, Naipe naipe) {
        this.ordemDeValor = ordemDeValor;
        this.naipe = naipe;
    }

    public Carta(OrdemDeValor ordemDeValor, Naipe naipe, String tipoDeCarta) {
        this.ordemDeValor = ordemDeValor;
        this.naipe = naipe;
        this.tipoDeCarta = tipoDeCarta;
    }

    /* ------------------ MÉTODOS ------------------ */

    /**
     * Mapeia a representação textual do valor para a posição do índice numérico no vetor (0 a 12).
     * 
     * @param ordemDeValor Caractere/String representando o valor
     * @return Índice inteiro correspondente (J=9, Q=10, K=11, A=12, ou o próprio número)
     */
    public static int descobrirPosOrdemDeValor(OrdemDeValor ordemDeValor) {
        switch (ordemDeValor.getNomeExibicao()) {
            case "Valete": return 9;
            case "Dama": return 10;
            case "Rei": return 11;
            case "Ás": return 12;
            default:  return ordemDeValor.getValorOrdemValor();
        }
    }

    /**
     * Retorna a quantidade numérica real de Fichas fornecida pelo valor nominal da carta.
     * 
     * @param ordemDeValor Enum OrdemDeValor
     * @return Fichas base (Figuras J/Q/K = 10, Ás = 11, Números = valor nominal)
     */
    public static int getValorOrdemDeValor(OrdemDeValor ordemDeValor) {
        return ordemDeValor.getValorOrdemValor();
    }

    /**
     * Retorna um array com o valor numérico em fichas de cada carta de uma lista.
     * 
     * @param maoJogador Lista de cartas a serem convertidas em valores numéricos
     */
    public static int[] getValorOrdemDeValorArray(ArrayList<Carta> maoJogador) {
        int[] vetAux = new int[maoJogador.size()];
        for (int i = 0; i < vetAux.length; i++) {
            vetAux[i] = getValorOrdemDeValor(maoJogador.get(i).getOrdemDeValor());
        }
        return vetAux;
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public OrdemDeValor getOrdemDeValor(){
        return ordemDeValor;
    }
    
    public void setOrdemDeValor(OrdemDeValor ordemDeValor) {
        this.ordemDeValor = ordemDeValor;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    public void setNaipe(Naipe naipe) {
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
}