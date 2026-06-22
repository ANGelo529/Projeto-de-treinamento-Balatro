from  Coringa import Coringa


# noinspection SpellCheckingInspection
class Blind:

    fichas = 0;
    multiplicador = 0;
    valorMao = [[0 for _ in range(9)] for _ in range(2)]
    def __init__(self):
        self.colocarValorMaoPoker(0)

    def colocarValorMaoPoker(self, setFicha):
        if setFicha == 0:
            self.valorMao[0][0], self.valorMao[0][1] =   5, 1 #Carta Alta (High card)
            self.valorMao[1][0], self.valorMao[1][1] =  10, 2 #Par (Pair)
            self.valorMao[2][0], self.valorMao[2][1] =  20, 2 #Dois pares (Two pair)
            self.valorMao[3][0], self.valorMao[3][1] =  30, 3 #Trinca (Three of a kind)
            self.valorMao[4][0], self.valorMao[4][1] =  30, 4 #Sequência (Straight)
            self.valorMao[5][0], self.valorMao[5][1] =  35, 4 #Flush (Flush)
            self.valorMao[6][0], self.valorMao[6][1] =  40, 4 #Full house (Full house)
            self.valorMao[7][0], self.valorMao[7][1] =  60, 7 #Quadra (Four of a kind)
            self.valorMao[8][0], self.valorMao[8][1] = 100, 8 #Straight flush (Straight flush)
        else:
            match setFicha:
                case 1:
                    # Carta alta
                    self.fichas = self.valorMao[0][0]
                    self.multiplicador = self.valorMao[0][1]
                case 2:
                    # Par
                    self.fichas = self.valorMao[1][0]
                    self.multiplicador = self.valorMao[0][1]
                case 3:
                    # Dois pares
                    self.fichas = self.valorMao[2][0]
                    self.multiplicador = self.valorMao[2][1]
                case 4:
                    # Trinca
                    self.fichas = self.valorMao[3][0]
                    self.multiplicador = self.valorMao[3][1]
                case 5:
                    # Sequência
                    self.fichas = self.valorMao[4][0]
                    self.multiplicador = self.valorMao[4][1]
                case 6:
                    # Flush
                    self.fichas = self.valorMao[5][0]
                    self.multiplicador = self.valorMao[5][1]
                case 7:
                    # Full house
                    self.fichas = self.valorMao[6][0]
                    self.multiplicador = self.valorMao[6][1]
                case 8:
                    # Quadra
                    self.fichas = self.valorMao[7][0]
                    self.multiplicador = self.valorMao[7][1]
                case 9:
                    # Straight flush
                    self.fichas = self.valorMao[8][0]
                    self.multiplicador = self.valorMao[8][1]


    def verificarBlind(self, rodada):
        valorBase = [300,800,2000,5000,11000,20000,35000,50000]
        if rodada % 3 == 0:
            return valorBase[int(rodada / 3)] * 2
        elif rodada % 3 == 1:
            return valorBase[int(rodada / 3 )]
        elif rodada % 3 == 2:
            return valorBase[int(rodada / 3)] * 1.5
        return None

    def verificarJogada(self,maoJogada):
        qtdNaipes = [0 for _ in range(4)]
        qtdOrdemDeValor = [0 for _ in range(13)]
        for index,i in enumerate(maoJogada):
            qtdNaipes[maoJogada.naipe]+= 1
            qtdOrdemDeValor[maoJogada.ordemDeValor] += 1
            Coringa.validacaoCoringas()
        self.verificarMaoPoker(qtdNaipes,qtdOrdemDeValor,maoJogada)

    def verificarMaoPoker(self,qtdNaipes,qtdOrdemDeValor,maoJogada):
        # INCOMPLETO
        a =0