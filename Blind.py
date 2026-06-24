from  Coringa import Coringa


# noinspection SpellCheckingInspection
class Blind:

    fichas = 0;
    multiplicador = 0;
    valorMao = [[0 for _ in range(2)] for _ in range(9)]
    def __init__(self):
        self.colocarValorMaoPoker(-1)

    def colocarValorMaoPoker(self, setFicha):
        match setFicha:
            case -1:
                self.valorMao[0][0], self.valorMao[0][1] = 5, 1  # Carta Alta (High card)
                self.valorMao[1][0], self.valorMao[1][1] = 10, 2  # Par (Pair)
                self.valorMao[2][0], self.valorMao[2][1] = 20, 2  # Dois pares (Two pair)
                self.valorMao[3][0], self.valorMao[3][1] = 30, 3  # Trinca (Three of a kind)
                self.valorMao[4][0], self.valorMao[4][1] = 30, 4  # Sequência (Straight)
                self.valorMao[5][0], self.valorMao[5][1] = 35, 4  # Flush (Flush)
                self.valorMao[6][0], self.valorMao[6][1] = 40, 4  # Full house (Full house)
                self.valorMao[7][0], self.valorMao[7][1] = 60, 7  # Quadra (Four of a kind)
                self.valorMao[8][0], self.valorMao[8][1] = 100, 8  # Straight flush (Straight flush)

            case 1: # Carta alta
                self.fichas = self.valorMao[0][0]
                self.multiplicador = self.valorMao[0][1]

            case 2: # Par
                self.fichas = self.valorMao[1][0]
                self.multiplicador = self.valorMao[0][1]

            case 3: # Dois pares
                self.fichas = self.valorMao[2][0]
                self.multiplicador = self.valorMao[2][1]

            case 4: # Trinca
                self.fichas = self.valorMao[3][0]
                self.multiplicador = self.valorMao[3][1]

            case 5: # Sequência
                self.fichas = self.valorMao[4][0]
                self.multiplicador = self.valorMao[4][1]

            case 6: # Flush
                self.fichas = self.valorMao[5][0]
                self.multiplicador = self.valorMao[5][1]

            case 7: # Full house
                self.fichas = self.valorMao[6][0]
                self.multiplicador = self.valorMao[6][1]

            case 8: # Quadra
                self.fichas = self.valorMao[7][0]
                self.multiplicador = self.valorMao[7][1]

            case 9: # Straight flush
                self.fichas = self.valorMao[8][0]
                self.multiplicador = self.valorMao[8][1]


    def verificarBlind(self, rodada):
        valorBase = [300,800,2000,5000,11000,20000,35000,50000]
        if rodada % 3 == 0:
            # Boss blind
            return valorBase[int(rodada / 3)] * 2
        elif rodada % 3 == 2:
            # Big blind
            return valorBase[int(rodada / 3 )] * 1.5
        elif rodada % 3 == 1:
            # Small blind
            return valorBase[int(rodada / 3)]
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
        qtdMaosPoker = 10
        validadores = (self.validarStraightFlush(maoJogada),
                       self.validarQuadra(),
                       self.validarFullHouse(),
                       self.validarFlush(),
                       self.validarSequencia(qtdOrdemDeValor),
                       self.validarTrinca(qtdOrdemDeValor),
                       self.validarDoisPares(qtdOrdemDeValor),
                       self.validarPar(qtdOrdemDeValor),
                       self.validarCartaAlta(qtdOrdemDeValor))
        for index,i in enumerate(validadores):
            if i:
                self.colocarValorMaoPoker(qtdMaosPoker - index)

    def validarStraightFlush(self,qtdNaipes,qtdOrdemDeValor):
        # INCOMPLETO
        a=0

    def validarQuadra(self,qtdNaipes,qtdOrdemDeValor):
        # INCOMPLETO
        a=0

    def validarFullHouse(self,qtdNaipes,qtdOrdemDeValor):
        # INCOMPLETO
        a=0

    def validarSequencia(self,qtdOrdemDeValor):
        sequenciaValida = True
        qtdValidos = 0
        
        for i in range(len(qtdOrdemDeValor) - 1):
            if qtdOrdemDeValor[i] == 1 and qtdOrdemDeValor[i + 1] == 1:
                qtdValidos+=1
                sequenciaValida = True
            else:
                sequenciaValida = False
                qtdValidos = 0
            if qtdValidos ==  4 and sequenciaValida:
                return True
        return False


    def validarTrinca(self,qtdOrdemDeValor):
        for i in range(len(qtdOrdemDeValor)):
            if qtdOrdemDeValor[i] == 3:
                return True
        return False

    def validarDoisPares(self,qtdOrdemDeValor):
        qtdPares =0
        for i in range(len(qtdOrdemDeValor)):
            if qtdOrdemDeValor[i] == 2:
                qtdPares+= 1
        if qtdPares >= 2:
            return True
        return False

    def validarPar(self,qtdOrdemDeValor):
        for i in range(len(qtdOrdemDeValor)):
            if qtdOrdemDeValor[i] == 2:
                return True
        return False

    def validarCartaAlta(self,qtdOrdemDeValor):
        for i in range(len(qtdOrdemDeValor)):
            if qtdOrdemDeValor[i] == 1:
                return True
        return False