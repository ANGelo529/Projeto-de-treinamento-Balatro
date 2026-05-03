import random

class Cartas:
    # Documentação do código
    # Colocar as atualizações feitas do código aqui também
    """
        Estilo padrão das Cartas do Balatro
    """

    # Variáveis globais da classe
    baralho = [[1 + i for i in range(13)] for _ in range(4)] # int
    temCartasBaralho = [[True for _ in range(13)] for _ in range(4)] # boolean

    def __init__(self,ordemDeValor, naipe,selos = 0 , aprimoramento = 0, edicao = 0):
        # Valores do baralho (Linhas = naipes, Colunas = ordem de valor das cartas)
        # Os naipes têm a seguinte ordem no baralho ["paus","ouros","espadas","copas"]


        # Coloca os valores(ordem dos valores das cartas e o seu naipe) no objeto
        self.ordemDeValor = ordemDeValor #int
        self.naipe = naipe #int

        while not self.verificarExistentes():
            # Se a carta já foi jogada coloca novos valores até ter uma carta possivel de ser jogada
            self.ordemDeValor = self.gerarRandom(1,13)
            self.naipe = self.gerarRandom(1,4)




    def verificarExistentes(self):
        # Verifica se a carta que foi escolhida no objeto já foi jogada 
        # Como pode haver de ter cartas repetidas é necessário que tenha 
        # Alguma forma de diferenciar cada carta da maneira que está a ser feita
        # É através da posição da carta no baralho

        if Cartas.temCartasBaralho[self.naipe - 1][self.ordemDeValor - 1]:
        # Verifica se a carta na posição ordem e naipe já foi jogada
            # Informa que a posição da carta do objeto no baralho já foi jogada
            Cartas.temCartasBaralho[self.naipe - 1][self.ordemDeValor - 1] = False
            return True
         
        return False

    @staticmethod
    def gerarRandom(inicio,fim):
    # Só é uma simplificação para gerar números aleatórios
        return random.randint(inicio,fim)

    @staticmethod
    def organizarPorOrdemDeValor(maoPlayer):
    # Organiza a mão do jogador segundo a ordem do valor(número da carta)
        tamanho = len(maoPlayer)

        # Utilizei bubble sort pelo tamanho da mão ser pequeno
        # E o bubble ser de fácil aplicação
        for i in range(tamanho):
            for j in range(tamanho - 1 ):
                if maoPlayer[j].ordemDeValor < maoPlayer[j + 1].ordemDeValor:
                    aux = maoPlayer[j + 1].ordemDeValor
                    maoPlayer[j + 1].ordemDeValor = maoPlayer[j].ordemDeValor
                    maoPlayer[j].ordemDeValor = aux

    @staticmethod
    def organizarPorNaipe(maoPlayer):
    # Organiza a mão do jogador segundo o naipe
        tamanho = len(maoPlayer)

        # Utilizei bubble sort pelo tamanho da mão ser pequeno
        # E o bubble ser de fácil aplicação
        for i in range(tamanho):
            for j in range(tamanho - 1):
                if maoPlayer[j].naipe < maoPlayer[j + 1].naipe:
                    aux = maoPlayer[j + 1].ordemDeValor
                    maoPlayer[j + 1].naipe = maoPlayer[j].naipe
                    maoPlayer[j].naipe = aux

    @staticmethod
    def printerMaoJogador(maoPlayer):
        for i,carta in enumerate(maoPlayer):
            print(f"[carta {i + 1}] classe: {carta.ordemDeValor:<5} | naipe: {carta.naipe}")


    @staticmethod
    def printerCartasJogadas():
        print()
        for i in Cartas.temCartasBaralho:
            print(*i)

    @staticmethod
    def colocarCartasInicio(tamanhoMao):
        maoPlayer = []
        for _ in range(tamanhoMao):
            novaCarta = Cartas(random.randint(1, 13), random.randint(1, 4))
            maoPlayer.append(novaCarta)
        return maoPlayer


    # Impressão formatada do objeto 
    def __str__(self):
        return f"{self}"

lengthMao = 8
maoJogador = Cartas.colocarCartasInicio(lengthMao)

Cartas.printerMaoJogador(maoJogador)
Cartas.printerCartasJogadas()

ordenar = int(input())

if ordenar == 1:
    Cartas.organizarPorOrdemDeValor(maoJogador)
elif ordenar == 2:
    Cartas.organizarPorNaipe(maoJogador)

Cartas.printerMaoJogador(maoJogador)
