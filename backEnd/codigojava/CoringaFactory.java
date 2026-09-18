package backEnd.codigojava;

import java.util.HashMap;

public class CoringaFactory {
    private static final HashMap<Integer, Coringa> catalagoCoringas = new HashMap<>();


    static {
                /* ------------------ CORINGAS COMUNS ------------------ */
        addCoringa(1, criarCoringaGanancioso());
        addCoringa(2, criarCoringaRobusto());
        addCoringa(3, criarCoringaIrritado());
        addCoringa(4, criarCoringaGuloso());
        addCoringa(5, criarCoringaPadrao());
        addCoringa(6, criarCoringaAlegre());
        addCoringa(1, criarCoringaGanancioso());
        addCoringa(2, criarCoringaRobusto());
        addCoringa(3, criarCoringaIrritado());
        addCoringa(4, criarCoringaGuloso());
        addCoringa(5, criarCoringaPadrao());
        addCoringa(6, criarCoringaAlegre());

        /* ------------------ CORINGAS INCOMUNS ------------------ */
        addCoringa(1, criarCoringaGanancioso());
        addCoringa(2, criarCoringaRobusto());
        addCoringa(3, criarCoringaIrritado());
        addCoringa(4, criarCoringaGuloso());
        addCoringa(5, criarCoringaPadrao());
        addCoringa(6, criarCoringaAlegre());
        addCoringa(1, criarCoringaGanancioso());
        addCoringa(2, criarCoringaRobusto());
        addCoringa(3, criarCoringaIrritado());
        addCoringa(4, criarCoringaGuloso());
        addCoringa(5, criarCoringaPadrao());
        addCoringa(6, criarCoringaAlegre());

        /* ------------------ CORINGAS RAROS ------------------ */
        addCoringa(1, criarCoringaGanancioso());
        addCoringa(2, criarCoringaRobusto());
        addCoringa(3, criarCoringaIrritado());
        addCoringa(4, criarCoringaGuloso());
        addCoringa(5, criarCoringaPadrao());
        addCoringa(6, criarCoringaAlegre());
        addCoringa(1, criarCoringaGanancioso());
        addCoringa(2, criarCoringaRobusto());
        addCoringa(3, criarCoringaIrritado());
        addCoringa(4, criarCoringaGuloso());
        addCoringa(5, criarCoringaPadrao());
        addCoringa(6, criarCoringaAlegre());

        /* ------------------ CORINGAS LENDÁRIOS ------------------ */
        addCoringa(1, criarCoringaGanancioso());
        addCoringa(2, criarCoringaRobusto());
        addCoringa(3, criarCoringaIrritado());
        addCoringa(4, criarCoringaGuloso());
        addCoringa(5, criarCoringaPadrao());
    }

    // private static void addAllCoringas() {


    // }

    private static Coringa criarCoringaGanancioso() {
        return new Coringa(1, "Coringa Ganancioso", "Comum", 5, contextoMao -> {
            if (contextoMao.getCartaAuxiliar().getNaipe().equals("Ouros")) {
                contextoMao.setMultAtual(contextoMao.getMultAtual() + 4);
            }
            return contextoMao;
        });
    }

    private static Coringa criarCoringaRobusto() {
        return new Coringa(2, "Coringa Robusto", "Comum", 5, contextoMao -> {
            if (contextoMao.getCartaAuxiliar().getNaipe().equals("Copas")) {
                contextoMao.setMultAtual(contextoMao.getMultAtual() + 4);
            }
            return contextoMao;
        });
    }

    private static Coringa criarCoringaIrritado() {
        return new Coringa(3, "Coringa Irritado", "Comum", 5, contextoMao -> {
            if (contextoMao.getCartaAuxiliar().getNaipe().equals("Espadas")) {
                contextoMao.setMultAtual(contextoMao.getMultAtual() + 4);
            }
            return contextoMao;
        });
    }

    private static Coringa criarCoringaGuloso() {
        return new Coringa(4, "Coringa Guloso", "Comum", 5, contextoMao -> {
            if (contextoMao.getCartaAuxiliar().getNaipe().equals("Paus")) {
                contextoMao.setMultAtual(contextoMao.getMultAtual() + 4);
            }
            return contextoMao;
        });
    }

    private static Coringa criarCoringaPadrao() {
        return new Coringa(5, "Coringa Padrão", "Comum", 5, contextoMao -> {
            contextoMao.setMultAtual(contextoMao.getMultAtual() + 1);
            return contextoMao;
        });
    }

    private static Coringa criarCoringaAlegre() {
        return new Coringa(6, "Coringa Guloso", "Comum", 5, contextoMao -> {
            if (contextoMao.getCartaAuxiliar().getTipoDeCarta().equals("DoisPares")) {
                contextoMao.setMultAtual(contextoMao.getMultAtual() + 2);
            }
            return contextoMao;
        });
    }

    private static void addCoringa(int idJoker, Coringa novoCoringa) {
        catalagoCoringas.put(idJoker, novoCoringa);
    }

    public static Coringa getCoringaPorId(int id) {
        Coringa auxCoringa = new Coringa(catalagoCoringas.get(id));
        return auxCoringa;
    }
}
