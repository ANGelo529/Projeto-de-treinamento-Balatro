package backEnd.test;

import java.util.ArrayList;
import java.util.Scanner;
import backEnd.src.model.*;
import backEnd.src.model.blinds.*;
import backEnd.src.model.cartas.*;
import backEnd.src.model.coringas.*;
import backEnd.src.service.*;
import backEnd.src.Factory.*;

public class TesteSaida {
    public static void printReceberCartas() {
    }

    public static void printMostrarPontos() {
    }

    public static void printVerificarOrdenacao() {
    }

    public static void printQueTipoDeJogada() {
    }

    public static void printIniciar() {
    }

    public static void printJogar() {
    }

    public static void printMaoJogador(ArrayList<Carta> maoJogador) {
        System.out.println("Verificação MaoJogador");
        for (int i = 0; i < maoJogador.size(); i++) {
            String naipe = maoJogador.get(i).getNaipe();
            String ordemDeValor = maoJogador.get(i).getOrdemDeValor();
            System.out.printf("Naipe: %s OrdemDeValor: %s %d\n", naipe, ordemDeValor, i);
        }
    }

    public static void printAnte() {
        for (int i = 0; i <= 8; i++) {
            System.out.printf("ANTE %d\n", i);
            Ante.setBlindsAtuais(Ante.atualizarBlind(i));
            for (int j = 0; j < 3; j++) {
                String nome = Ante.getBlindsAtuais()[j].getNome();
                long valorBlind = Ante.getBlindsAtuais()[j].getValorBlind();
                System.out.printf("%s: %d\n", nome, valorBlind);
            }
        }
    }

    public static void printTesteJogo() {
        DeckCarta teste = new DeckCarta("Naipe");
        Integer[] vet = new Integer[5];
        Scanner LER = new Scanner(System.in);
        ArrayList<Carta> cartasJogadas = new ArrayList<>();
        System.out.println("CARTAS ORIGINAIS ");
        TesteSaida.printMaoJogador(teste.getMaoJogador());
        System.out.println("\n\n\n\n\n\n");

        for (int i = 0; i < vet.length; i++) {
            vet[i] = LER.nextInt();
        }

        cartasJogadas = teste.retirarCartas(vet);
        System.out.println("CARTAS RETIRADAS");
        TesteSaida.printMaoJogador(cartasJogadas);
        System.out.println("\n\n\n\n\n\n");
        ArrayList<Coringa> maoCoringa = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            maoCoringa.add(CoringaFactory.getCoringaPorId(i));
        }
        
        ValidadorPoker.calcularJogada(cartasJogadas, maoCoringa);
        teste.adicionarCartas(teste.getMaoJogador());
        TesteSaida.printMaoJogador(teste.getMaoJogador());

        LER.close();
    }
}
