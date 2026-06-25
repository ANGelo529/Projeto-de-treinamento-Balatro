from Blind import Blind
from Cartas import Cartas


def receberCartas(lengthMao):
    jogada = list
    funcionouJogada = True
    deuErro = True
    while funcionouJogada:
        print("para jogar as cartas digite o numero das cartas que você deseja jogar")
        jogada = list(map(int, input().split()))
        if len(jogada) > 5:
            print("Voce só pode jogar 5 cartas")
            print("Jogue novamente")
            continue
        for i in jogada:
            if i > lengthMao:
                print("Voce não possui essa quantidade de cartas")
                print("Jogue novamente")
                deuErro = False
                break
        if deuErro:
            funcionouJogada = False

    for i in range(len(jogada)):
        jogada[i] -= 1
    return jogada


def jogarCartas(maoJogador, cartasEscolhidas, lengthMao,pontos):
    maoJogada = Cartas.jogarMao(cartasEscolhidas, maoJogador)
    maoJogador.extend(Cartas.colocarCartas(len(maoJogador), lengthMao))
    Cartas.printerMaoJogador(maoJogada)
    mostrarPontos(maoJogada,pontos)


def mostrarPontos(maoJogada,pontos):
    pontos.verificarJogada(maoJogada)
    print(pontos.maoPokerJogada, end=" ")
    print("\033[1;34mFicha:" + str(pontos.fichas), end=" ")
    print("\033[0;31mMulti:" + str(pontos.multiplicador), end="\n")
    pontosTotais = pontos.fichas * pontos.multiplicador
    print("Pontos finais",pontosTotais)


def verificarOrdenacao(maoJogador):
    print("caso queira ordenar a mão digite <ordenar>")
    vaiOrdenar = input().lower()
    if vaiOrdenar == "ordenar":
        print("digite <classe> para ordenar pelo valor das cartas ou digite <naipe> para ordenar por naipe")
        ordenar = input()
        ordenar.lower()

        if ordenar == "classe":
            Cartas.organizarPorOrdemDeValor(maoJogador)
            Cartas.printerMaoJogador(maoJogador)
        elif ordenar == "naipe":
            Cartas.organizarPorNaipe(maoJogador)
            Cartas.printerMaoJogador(maoJogador)
        else:
            Cartas.printerMaoJogador(maoJogador)
    else:
        Cartas.printerMaoJogador(maoJogador)


def iniciar():
    print("digite <jogar> para começar a jogar")
    lengthMao = 8
    maoJogador = Cartas.colocarCartas(0, lengthMao)
    Cartas.organizarPorOrdemDeValor(maoJogador)
    inicio = input().lower()
    print("você jogará como <user> ou <moderador>")
    usuario = input().lower()
    return inicio, usuario, lengthMao, maoJogador


def main():
    inicio, usuario, lengthMao, maoJogador = iniciar()
    pontos = Blind()
    while inicio == "jogar":

        if usuario == "moderador":
            Cartas.printerCartasJogadas()

        Cartas.printerMaoJogador(maoJogador)
        verificarOrdenacao(maoJogador)
        cartasEscolhidas = receberCartas(lengthMao)
        jogarCartas(maoJogador, cartasEscolhidas, lengthMao,pontos)


if __name__ == "__main__":
    main()
