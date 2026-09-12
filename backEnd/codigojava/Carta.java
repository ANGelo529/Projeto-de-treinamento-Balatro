package backEnd.codigojava;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Carta {
    /* ------------------ VARIÁVEIS ------------------ */

    private String ordemDeValor;
    private String naipe;
    private String melhoria = "Sem valor";
    private String selo = "Sem valor";
    private String edicao = "Sem valor";
    private String tipoDeCarta = "Numero";

    /* ------------------ CONSTRUTOR ------------------ */

    public Carta(String ordemDeValor, String naipe) {
        this.ordemDeValor = ordemDeValor;
        this.naipe = naipe;
    }

    public Carta(String ordemDeValor, String naipe, String tipoDeCarta) {
        this.ordemDeValor = ordemDeValor;
        this.naipe = naipe;
        this.tipoDeCarta = tipoDeCarta;
    }

    /* ------------------ MéTODOS ------------------ */

    public static int descobrirPosOrdemDeValor(String ordemDeValor) {
        int aux = 0;
        switch (ordemDeValor) {
            case "J":
                aux = 9;
                break;

            case "Q":
                aux = 10;
                break;

            case "K":
                aux = 11;
                break;

            case "A":
                aux = 12;
                break;
            default:
                aux = Integer.valueOf(ordemDeValor);
                break;
        }
        return aux;
    }

    public static int getValorOrdemDeValor(String ordemDeValor) {
        int aux = 0;

        if (ordemDeValor.equals("J") || ordemDeValor.equals("Q") || ordemDeValor.equals("K")) {
            aux = 10;

        } else if (ordemDeValor.equals("A")) {
            aux = 11;

        } else {
            aux = Integer.valueOf(ordemDeValor);
        }

        return aux;
    }

    public static int[] getValorOrdemDeValor(ArrayList<Carta> maoJogador) {
        int[] vetAux = new int[maoJogador.size()];
        for (int i = 0; i < vetAux.length; i++) {
            if (maoJogador.get(i).getOrdemDeValor().equals("J") || maoJogador.get(i).getOrdemDeValor().equals("Q") || maoJogador.get(i).getOrdemDeValor().equals("K")) {
                vetAux[i] = 10;

            } else if (maoJogador.get(i).getOrdemDeValor().equals("A")) {
                vetAux[i] = 11;

            } else {
                vetAux[i] = Integer.valueOf(maoJogador.get(i).getOrdemDeValor());
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

}
