package backEnd.src.model.contextos;

import java.util.ArrayList;

import backEnd.src.model.cartas.Carta;

public class ContextoMao {
        /* ------------------ VARIÁVEIS ------------------ */

        private String tipoMao;
        private ArrayList<Carta> cartasJogadas;
        private Carta cartaAuxiliar;
        private Ponto pontos = new Ponto();
        private int fichasAtuais;
        private int multAtual;

        /* ------------------ CONSTRUTOR ------------------ */

        public ContextoMao(ArrayList<Carta> cartasJogadas, Ponto pontos) {
                this.tipoMao = pontos.getNomeMaoJogada();
                this.cartasJogadas = cartasJogadas;
                this.pontos = pontos;
                this.fichasAtuais = pontos.getFicha();
                this.multAtual = pontos.getMulti();
        }

        public ContextoMao(int fichasAtuais, int multAtual) {
                this.fichasAtuais = fichasAtuais;
                this.multAtual = multAtual;
        }

        public ContextoMao() {
        }

        /* ------------------ GETTERS & SETTERS ------------------ */

        public String getTipoMao() {
                return tipoMao;
        }

        public void setTipoMao(String tipoMao) {
                this.tipoMao = tipoMao;
        }

        public ArrayList<Carta> getCartasJogadas() {
                return cartasJogadas;
        }

        public void setCartasJogadas(ArrayList<Carta> cartasJogadas) {
                this.cartasJogadas = cartasJogadas;
        }

        public Carta getCartaAuxiliar() {
                return cartaAuxiliar;
        }

        public void setCartaAuxiliar(Carta cartaAuxiliar) {
                this.cartaAuxiliar = cartaAuxiliar;
        }

        public Ponto getPontos() {
                return pontos;
        }

        public void setPontos(Ponto pontos) {
                this.pontos = pontos;
        }

        public int getFichasAtuais() {
                return fichasAtuais;
        }

        public void setFichasAtuais(int fichasAtuais) {
                this.fichasAtuais = fichasAtuais;
        }

        public int getMultAtual() {
                return multAtual;
        }

        public void setMultAtual(int multAtual) {
                this.multAtual = multAtual;
        }
}
