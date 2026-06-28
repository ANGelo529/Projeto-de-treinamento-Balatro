from Blind import Blind
from Cartas import Cartas


def receberCartas(lengthMao):
    # Atualmente recebe a mão que o jogador quer jogar e faz algumas verificações para prevenir erros
    # Posteriormente será completamente refeito para o front repassar e limitar essas informações
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

def descartarCartas(cartasEscolhidas,maoJogador,lengthMao):
    # somente retira as cartas e coloca elas de novo diferente do jogar cartas que também soma elas
    maoJogada = Cartas.jogarMao(cartasEscolhidas, maoJogador)
    maoJogador.extend(Cartas.colocarCartas(len(maoJogador), lengthMao))

def jogarCartas(maoJogador, cartasEscolhidas, lengthMao,pontos):
    # Retira, atualiza e calcula as cartas que foram
    maoJogada = Cartas.jogarMao(cartasEscolhidas, maoJogador)
    maoJogador.extend(Cartas.colocarCartas(len(maoJogador), lengthMao))
    Cartas.printerMaoJogador(maoJogada)
    return contarPontos(maoJogada,pontos)


def contarPontos(maoJogada,pontos):
    # Calcula os pontos através de BlindObj(pontos).verificarJogada
    # Mostra os valores recebidos através disso posteriormente somente irá repassar
    # Os dados para o front para ele mostrar esses dados da maneira adequada
    pontos.verificarJogada(maoJogada)
    print("\033[1;92m",end="")
    print(pontos.maoPokerJogada, end=" ")
    print("\033[1;34mFicha:" + str(pontos.fichas), end=" ")
    print("\033[0;31mMulti:" + str(pontos.multiplicador), end="\n")
    pontosTotais = pontos.fichas * pontos.multiplicador
    print("Pontos finais",pontosTotais,"\n\033[0m")
    return pontosTotais

def verificarOrdenacao(maoJogador):
    # Organiza as cartas através do naipe ou do valor dela
    # Atualmente recebe valores do terminal posteriormente irá receber do front
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

def queTipoDeJogada(qtdMao,qtdDescarte,maoJogador,cartasEscolhidas,lengthMao,pontos,pontosTotais):
    # Identifica se o player vai querer descartar ou jogar a mão
    # Posteriormente deve ser substituído por um botão no front que indica isso
    print(f"\033[1;34m Mãos:{qtdMao} \033[1;31m Descarte:{qtdDescarte}\033[0m")
    print("Voce vai descartar algumas cartas?")
    print("Digite \033[1;31m <descarte> \033[0m para descartar e \033[1;34m <jogar> \033[0m para jogar as cartas")
    print("OBS: você só pode descartar ou jogar igual a quantidade de descartes ou de mãos ")
    tipoDeJogada = input()
    while tipoDeJogada.lower() != "jogar" and tipoDeJogada.lower() != "descarte":
        print("Jogada incorreta digite novamente")
        tipoDeJogada = input()

    if tipoDeJogada.lower() == "jogar":
        qtdMao -= 1
        pontosTotais += jogarCartas(maoJogador, cartasEscolhidas, lengthMao, pontos)
    else:
        descartarCartas(cartasEscolhidas, maoJogador, lengthMao)
        qtdDescarte -= 1

    return pontosTotais

def iniciar():
    # Faz aquele começo do jogo para ficar bonito durante os testes posteriormente
    # Irá ter que ser retirado, pois isso é trabalho do front
    pontos = Blind()
    rodada = 0
    ante = {
        "SmallBlind":pontos.verificarBlind(rodada),
        "BigBlind":pontos.verificarBlind(rodada + 1),
        "BossBlind":pontos.verificarBlind(rodada + 2)}
    print("digite <jogar> para começar a jogar")
    lengthMao = 8
    maoJogador = Cartas.colocarCartas(0, lengthMao)
    Cartas.organizarPorOrdemDeValor(maoJogador)
    inicio = input().lower()
    return inicio, lengthMao, maoJogador,pontos,rodada,ante

def jogando(maoJogador,lengthMao,pontos,rodada):
    # Simplificação de alguns metódos para deixar o main bonito
    pontosTotais = 0
    resultado = True
    qtdMao = 3
    qtdDescarte = 3
    while True:
        # Verifica se ele não conseguiu ganhar no caso quando ele não pode jogar mais
        if qtdMao <= 0:
            print("Voce perdeu")
            resultado = False
            return resultado

        # Métodos que mostra as cartas, ordena elas, vê quais serão jogadas e no final joga ou descarta elas
        Cartas.printerMaoJogador(maoJogador)
        verificarOrdenacao(maoJogador)
        cartasEscolhidas = receberCartas(lengthMao)
        pontosTotais += queTipoDeJogada(qtdMao, qtdDescarte,maoJogador,cartasEscolhidas,lengthMao,pontos,pontosTotais)

        # Verifica se o ponto daquela rodada passou o necessário para ganhar o blind
        if pontosTotais >= pontos.verificarBlind(rodada):
            print("Voce ganhou esse Blind")
            resultado = True
            return resultado

def mostrandoBlinds(pontos,ante):
    # Uns prints de como deveria ser o front antes de começar o jogo
    for blind,valor in ante.items():
        print(blind,": ",valor)


def mostrandoLoja():
    # Métod0 com a implementação da classe loja
    a=0

def main():
    inicio, lengthMao, maoJogador,pontos,rodada,ante = iniciar()

    while inicio == "jogar":
        mostrandoBlinds(pontos,ante)
        resultado = jogando(maoJogador,lengthMao,pontos,rodada)
        if not resultado:
            break
        mostrandoLoja()
        pontos.atualizarBlinds(rodada,ante)
        rodada += 1


if __name__ == "__main__":
    main()
