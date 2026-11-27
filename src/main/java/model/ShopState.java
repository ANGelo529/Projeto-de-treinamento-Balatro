package model;

import java.util.Random;

import factory.CoringaFactory;
import model.coringas.Coringa;

public class ShopState {

    public static void iniciarLoja() {
        Coringa[] coringaSorteados = criarCoringaSorteados();
        // criarPacotesConsumiveis
        // printCoringasSorteados
        // printConsumiveisSorteados
        GameState.controleJogo();
    }

    /*
     * Esse método serve como um simplificador para a criação dos coringas
     */
    private static Coringa[] criarCoringaSorteados() {
        Coringa[] coringaSorteados = new Coringa[5];
        for (int i = 0; i < coringaSorteados.length; i++) {
            coringaSorteados[i] = sortearCoringa();
        }
        return coringaSorteados;
    }

    /*
     * Esse método faz o sorteio considerando probabilides específicas
     * Seguindo a seguinte ordem de probabilidade
     * Coringa Comum (50%)
     * Coringa Incomum (30%)
     * Coringa Raro (19,95%)
     * Coringa Lendário (0,05%)
     */
    private static Coringa sortearCoringa() {
        Random random = GameState.getRandom();
        double valorAleatorio = random.nextDouble();

        // Intervalo 1: 0.0 até 0.50 (Equivale a 50%)
        if (valorAleatorio < 0.50) {
            // Sorteia de 1 a 15 (o limite 16 é exclusivo)
            int numSorteado = random.nextInt(1, 15 + 1);
            Coringa corgAux = CoringaFactory.getCoringaPorId(numSorteado);
            return corgAux;
        }

        // Intervalo 2: 0.50 até 0.80 (Equivale aos 30% do Incomum -> 0.50 + 0.30)
        else if (valorAleatorio < 0.80) {
            // Sorteia de 16 a 30 (o limite 31 é exclusivo)
            int numSorteado = random.nextInt(16, 30 + 1);
            Coringa corgAux = CoringaFactory.getCoringaPorId(numSorteado);
            return corgAux;
        }

        // Intervalo 3: 0.80 até 0.9995 (Equivale aos 19,5% do Raro -> 0.80 + 0.1995)
        else if (valorAleatorio < 0.9995) {
            // Sorteia de 31 a 40 (o limite 41 é exclusivo)
            int numSorteado = random.nextInt(31, 40 + 1);
            Coringa corgAux = CoringaFactory.getCoringaPorId(numSorteado);
            return corgAux;
        }

        // Intervalo 4: 0.995 até 1.0 (Equivale aos 0,05% do Lendário -> 0.995 + 0.005)
        else {
            // Sorteia de 41 a 45 (o limite 46 é exclusivo)
            int numSorteado = random.nextInt(41, 45 + 1);
            Coringa corgAux = CoringaFactory.getCoringaPorId(numSorteado);
            return corgAux;
        }
    }
}
