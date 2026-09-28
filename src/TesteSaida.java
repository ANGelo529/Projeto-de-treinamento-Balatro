import java.util.ArrayList;
import java.util.Scanner;
import model.blinds.*;
import model.cartas.*;
import model.coringas.*;
import service.*;
import Factory.*;

/**
 * Classe utilitária e de teste para simulação e impressão dos fluxos do jogo no console.
 * Utilizada para validar o sorteio de cartas, progressão de Ante/Blinds e execução das jogadas.
 */
public class TesteSaida {
    
    /* Métodos de impressão vazios reservados para saídas do fluxo de interface de terminal */
    public static void printReceberCartas() {}
    public static void printMostrarPontos() {}
    public static void printVerificarOrdenacao() {}
    public static void printQueTipoDeJogada() {}
    public static void printIniciar() {}
    public static void printJogar() {}

    /**
     * Exibe no console a lista atual de cartas contidas na mão do jogador,
     * incluindo seus naipes, valores e os índices de posição.
     * 
     * @param maoJogador Lista de objetos {@link Carta} atualmente na mão do jogador
     */
    public static void printMaoJogador(ArrayList<Carta> maoJogador) {
        System.out.println("Verificação MaoJogador");
        for (int i = 0; i < maoJogador.size(); i++) {
            String naipe = maoJogador.get(i).getNaipe().getNomeExibicao();
            String ordemDeValor = maoJogador.get(i).getOrdemDeValor();
            System.out.printf("Naipe: %s OrdemDeValor: %s %d\n", naipe, ordemDeValor, i);
        }
    }

    /**
     * Simula a progressão de 0 a 8 do sistema de Ante (fases do jogo),
     * imprimindo os valores de Small Blind, Big Blind e Boss Blind para cada nível.
     */
    public static void printAnte() {
        for (int i = 0; i <= 8; i++) {
            System.out.printf("ANTE %d\n", i);
            Ante.setBlindsAtuais(Ante.atualizarBlind(i)); // Atualiza os Blinds com base na rodada atual
            for (int j = 0; j < 3; j++) {
                String nome = Ante.getBlindsAtuais()[j].getNome();
                long valorBlind = Ante.getBlindsAtuais()[j].getValorBlind();
                System.out.printf("%s: %d\n", nome, valorBlind);
            }
        }
    }

    /**
     * Realiza um teste interativo do ciclo principal de uma jogada via entrada do console:
     * 1. Gera o baralho/mão inicial do jogador;
     * 2. Lê os índices das 5 cartas a serem jogadas;
     * 3. Retira essas cartas do baralho;
     * 4. Associa Coringas de teste e calcula os pontos via {@link ValidadorPoker};
     * 5. Repõe a mão do jogador.
     */
    public static void printTesteJogo() {
        DeckCarta teste = new DeckCarta("Naipe");
        Integer[] vet = new Integer[5]; // Índices das cartas selecionadas
        Scanner LER = new Scanner(System.in);
        ArrayList<Carta> cartasJogadas = new ArrayList<>();

        System.out.println("CARTAS ORIGINAIS ");
        TesteSaida.printMaoJogador(teste.getMaoJogador());
        System.out.println("\n\n\n\n\n\n");

        // Entrada das posições das cartas a jogar
        for (int i = 0; i < vet.length; i++) {
            vet[i] = LER.nextInt();
        }

        // Retira as cartas escolhidas pelo jogador
        cartasJogadas = teste.retirarCartas(vet);
        System.out.println("CARTAS RETIRADAS");
        TesteSaida.printMaoJogador(cartasJogadas);
        System.out.println("\n\n\n\n\n\n");

        // Cria uma mão de coringas padrão para aplicar na jogada
        ArrayList<Coringa> maoCoringa = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            maoCoringa.add(CoringaFactory.getCoringaPorId(i));
        }
        
        // Avalia e calcula os pontos
        ValidadorPoker.calcularJogada(cartasJogadas, maoCoringa);
        
        // Reabastece a mão do jogador até o limite padrão
        teste.adicionarCartas(teste.getMaoJogador());
        TesteSaida.printMaoJogador(teste.getMaoJogador());

        LER.close();
    }
}