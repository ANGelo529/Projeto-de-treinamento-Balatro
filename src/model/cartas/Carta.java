package model.cartas;

import java.util.ArrayList;
import model.enums.Naipe;

/**
 * Representa uma Carta individual do baralho.
 * Possui atributos de valor, naipe e modificadores de estilo de jogo (melhorias, selos, edições).
 */
public class Carta {
    /* ------------------ VARIÁVEIS ------------------ */

    private String ordemDeValor;       // Representação textual do valor ("2" até "10", "J", "Q", "K", "A")
    private Naipe naipe;              // Enum do Naipe (Ouros, Copas, Espadas, Paus)
    private String melhoria = "Sem valor";
    private String selo = "Sem valor";
    private String edicao = "Sem valor";
    private String tipoDeCarta = "Numero";

    /* ------------------ CONSTRUTORES ------------------ */

    public Carta(String ordemDeValor, Naipe naipe) {
        this.ordemDeValor = ordemDeValor;
        this.naipe = naipe;
    }

    public Carta(String ordemDeValor, Naipe naipe, String tipoDeCarta) {
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
    public static int descobrirPosOrdemDeValor(String ordemDeValor) {
        switch (ordemDeValor) {
            case "J": return 9;
            case "Q": return 10;
            case "K": return 11;
            case "A": return 12;
            default:  return Integer.parseInt(ordemDeValor);
        }
    }

    /**
     * Retorna a quantidade numérica real de Fichas fornecida pelo valor nominal da carta.
     * 
     * @param ordemDeValor Caractere/String do valor
     * @return Fichas base (Figuras J/Q/K = 10, Ás = 11, Números = valor nominal)
     */
    public static int getValorOrdemDeValor(String ordemDeValor) {
        if (ordemDeValor.equals("J") || ordemDeValor.equals("Q") || ordemDeValor.equals("K")) {
            return 10;
        } else if (ordemDeValor.equals("A")) {
            return 11;
        } else {
            return Integer.parseInt(ordemDeValor);
        }
    }

    /**
     * Retorna um array com o valor numérico em fichas de cada carta de uma lista.
     * 
     * @param maoJogador Lista de cartas a serem convertidas em valores numéricos
     */
    public static int[] getValorOrdemDeValor(ArrayList<Carta> maoJogador) {
        int[] vetAux = new int[maoJogador.size()];
        for (int i = 0; i < vetAux.length; i++) {
            String val = maoJogador.get(i).getOrdemDeValor();
            if (val.equals("J") || val.equals("Q") || val.equals("K")) {
                vetAux[i] = 10;
            } else if (val.equals("A")) {
                vetAux[i] = 11;
            } else {
                vetAux[i] = Integer.parseInt(val);
            }
        }
        return vetAux;
    }

    /* ------------------ GETTERS & SETTERS ------------------ */

    public String getOrdemDeValor() {
        return ordemDeValor;
    }

    public void setOrdemDeValor(String ordemDeValor) {
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