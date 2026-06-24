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
                    maoPlayer[j], maoPlayer[j + 1] = maoPlayer[j + 1], maoPlayer[j]

    @staticmethod
    def organizarPorNaipe(maoPlayer):
    # Organiza a mão do jogador segundo o naipe
        tamanho = len(maoPlayer)

        # Utilizei bubble sort pelo tamanho da mão ser pequeno
        # E o bubble ser de fácil aplicação
        for i in range(tamanho):
            for j in range(tamanho - 1):
                if maoPlayer[j].naipe < maoPlayer[j + 1].naipe:
                    maoPlayer[j], maoPlayer[j + 1] = maoPlayer[j + 1], maoPlayer[j]

    @staticmethod
    def printerMaoJogador(maoPlayer):
        nomes_naipes = ["Paus", "Ouros", "Espadas", "Copas"]
        nomes_valores = {1: "Ás", 11: "Valete", 12: "Dama", 13: "Rei"}

        for i, carta in enumerate(maoPlayer):
            valor_formatado = nomes_valores.get(carta.ordemDeValor, str(carta.ordemDeValor))
            naipe_formatado = nomes_naipes[carta.naipe - 1]
            match carta.naipe:
                case 1:
                    print(f"[Carta {i + 1}]\033[32m {valor_formatado:<6} | de {naipe_formatado}\033[0m")
                case 2:
                    print(f"[Carta {i + 1}]\033[33m {valor_formatado:<6} | de {naipe_formatado}\033[0m")
                case 3:
                    print(f"[Carta {i + 1}]\033[36m {valor_formatado:<6} | de {naipe_formatado}\033[0m")
                case 4:
                    print(f"[Carta {i + 1}]\033[31m {valor_formatado:<6} | de {naipe_formatado}\033[0m")
        print()


    @staticmethod
    def printerCartasJogadas():
        print()
        printNaipes = ["paus","ouros","espadas","copas"]
        for index,i in enumerate(Cartas.temCartasBaralho):
            print(printNaipes[index] + "\t",end="")
            print(*i)
        print()


    @classmethod
    def colocarCartas(cls,tamanhoMao, tamanhoDesejado):
        #cls igual indicação da classe desse metodo
        maoPlayer = []
        for _ in range(abs(tamanhoDesejado - tamanhoMao)):
            novaCarta = cls(random.randint(1, 13), random.randint(1, 4))
            maoPlayer.append(novaCarta)
        return maoPlayer

    @staticmethod
    def jogarMao(posicaoJogadas,maoPlayer):
        auxMaoJogada = [maoPlayer[i] for i in posicaoJogadas]
        posicaoJogadas.sort()
        for i in range(len(posicaoJogadas)-1,-1,-1):
            del maoPlayer[posicaoJogadas[i]]

        return auxMaoJogada

    # Impressão formatada do objeto 
    def __str__(self):
        nomes_naipes = ["Paus", "Ouros", "Espadas", "Copas"]
        nomes_valores = {1: "Ás", 11: "Valete", 12: "Dama", 13: "Rei"}
        valor = nomes_valores.get(self.ordemDeValor, str(self.ordemDeValor))
        naipe = nomes_naipes[self.naipe - 1]
        return f"{valor} de {naipe}"
