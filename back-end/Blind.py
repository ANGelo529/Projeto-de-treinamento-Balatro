from  Coringa import Coringa

# noinspection SpellCheckingInspection
class Blind:

    fichas = 0;
    multiplicador = 0;
    maoPokerJogada = ""
    valorFicha = {
        "straightFlush": 0,
        "quadra": 0,
        "fullHouse": 0,
        "flush": 0,
        "sequencia": 0,
        "trinca": 0,
        "doisPares": 0,
        "par": 0,
        "cartaAlta": 0
    }
    valorMulti = {
        "straightFlush": 0,
        "quadra": 0,
        "fullHouse": 0,
        "flush": 0,
        "sequencia": 0,
        "trinca": 0,
        "doisPares": 0,
        "par": 0,
        "cartaAlta": 0
    }
    def __init__(self):
        self.colocarValorMaoPoker("inicio")

    def colocarValorMaoPoker(self, setFicha):
        match setFicha:
            case "inicio":
                self.valorFicha["cartaAlta"], self.valorMulti["cartaAlta"] = 5, 1  # Carta Alta (High card)
                self.valorFicha["par"], self.valorMulti["par"] = 10, 2  # Par (Pair)
                self.valorFicha["doisPares"], self.valorMulti["doisPares"] = 20, 2  # Dois pares (Two pair)
                self.valorFicha["trinca"], self.valorMulti["trinca"] = 30, 3  # Trinca (Three of a kind)
                self.valorFicha["sequencia"], self.valorMulti["sequencia"] = 30, 4  # Sequência (Straight)
                self.valorFicha["flush"], self.valorMulti["flush"] = 35, 4  # Flush (Flush)
                self.valorFicha["fullHouse"], self.valorMulti["fullHouse"] = 40, 4  # Full house (Full house)
                self.valorFicha["quadra"], self.valorMulti["quadra"] = 60, 7  # Quadra (Four of a kind)
                self.valorFicha["straightFlush"], self.valorMulti["straightFlush"] = 100, 8  # Straight flush (Straight flush)

            case "cartaAlta": # Carta alta
                self.maoPokerJogada = "carta alta"
                self.fichas = self.valorFicha["cartaAlta"]
                self.multiplicador = self.valorMulti["cartaAlta"]

            case "par": # Par
                self.maoPokerJogada = "par"
                self.fichas = self.valorFicha["par"]
                self.multiplicador = self.valorMulti["par"]

            case "doisPares": # Dois pares
                self.maoPokerJogada = "dois pares"
                self.fichas = self.valorFicha["doisPares"]
                self.multiplicador = self.valorMulti["doisPares"]

            case "trinca": # Trinca
                self.maoPokerJogada = "trinca"
                self.fichas = self.valorFicha["trinca"]
                self.multiplicador = self.valorMulti["trinca"]

            case "sequencia": # Sequência
                self.maoPokerJogada = "sequência"
                self.fichas = self.valorFicha["sequencia"]
                self.multiplicador = self.valorMulti["sequencia"]

            case "flush": # Flush
                self.maoPokerJogada = "flush"
                self.fichas = self.valorFicha["flush"]
                self.multiplicador = self.valorMulti["flush"]

            case "fullHouse": # Full house
                self.maoPokerJogada = "full house"
                self.fichas = self.valorFicha["fullHouse"]
                self.multiplicador = self.valorMulti["fullHouse"]

            case "quadra": # Quadra
                self.maoPokerJogada = "quadra"
                self.fichas = self.valorFicha["quadra"]
                self.multiplicador = self.valorMulti["quadra"]

            case "straightFlush": # Straight flush
                self.maoPokerJogada = "straight flush"
                self.fichas = self.valorFicha["straightFlush"]
                self.multiplicador = self.valorMulti["straightFlush"]


    def verificarBlind(self, rodada):
        valorBase = [300,800,2000,5000,11000,20000,35000,50000]
        if rodada % 3 == 0:
            return valorBase[int(rodada / 3)] * 2 # Boss blind
        elif rodada % 3 == 2:
            return valorBase[int(rodada / 3 )] * 1.5 # Big blind
        elif rodada % 3 == 1:
            return valorBase[int(rodada / 3)] # Small blind

        return None

    def verificarJogada(self,maoJogada):
        qtdNaipes = [0 for _ in range(4)]
        qtdOrdemDeValor = [0 for _ in range(13)]
        for i in maoJogada:
            qtdNaipes[i.naipe - 1] += 1
            qtdOrdemDeValor[i.ordemDeValor - 1] += 1
            #Coringa.validacaoCoringas()

        self.verificarMaoPoker(qtdNaipes,qtdOrdemDeValor)
        self.colocarFichasMao(maoJogada)

    def colocarFichasMao(self,maoJogada):
        for i in maoJogada:
            valor = i.ordemDeValor

            if valor == 1:
                self.fichas += 11
            elif valor > 10:
                self.fichas += 10
            else:
                self.fichas += valor

    def verificarMaoPoker(self, qtdNaipes:list, qtdOrdemDeValor:list):
        qtdMaosPoker = 10
        validadores = {
            "straightFlush": self.validarStraightFlush(qtdNaipes,qtdOrdemDeValor),
            "quadra": self.validarQuadra(qtdOrdemDeValor),
            "fullHouse": self.validarFullHouse(qtdOrdemDeValor),
            "flush": self.validarFlush(qtdNaipes),
            "sequencia": self.validarSequencia(qtdOrdemDeValor),
            "trinca": self.validarTrinca(qtdOrdemDeValor),
            "doisPares": self.validarDoisPares(qtdOrdemDeValor),
            "par": self.validarPar(qtdOrdemDeValor),
            "cartaAlta": self.validarCartaAlta(qtdOrdemDeValor)
        }

        for nomeMao,ehValido in validadores.items():
            if ehValido:
                self.colocarValorMaoPoker(nomeMao)

    @staticmethod
    def validarStraightFlush(qtdNaipes:list,qtdOrdemDeValor:list,limiteFlush=5,limiteSequencia=5,):
        temFlush = Blind.validarFlush(qtdNaipes,limiteFlush)
        temSequencia = Blind.validarSequencia(qtdOrdemDeValor,limiteSequencia)
        if temFlush and temSequencia:
            return True

        return False

    @staticmethod
    def validarQuadra(qtdOrdemDeValor):
        for i in range(len(qtdOrdemDeValor)):
            if qtdOrdemDeValor[i] == 4:
                return True

        return False

    @staticmethod
    def validarFullHouse(qtdOrdemDeValor):
        temTrinca = False
        temPar = False
        for i in range(len(qtdOrdemDeValor)):
            if qtdOrdemDeValor[i] == 3:
                temTrinca = True
            if qtdOrdemDeValor[i] == 2:
                temPar = True

            if temPar and temTrinca:
                return True

        return False

    @staticmethod
    def validarFlush(qtdNaipes,limiteFlush = 5):
        for i in range(len(qtdNaipes)):
            if qtdNaipes[i] >= limiteFlush:
                return True

        return False

    @staticmethod
    def validarSequencia(qtdOrdemDeValor,limiteSequencia = 5):
        sequenciaValida = True
        qtdValidos = 1
        auxQtdOrdemDeValor = qtdOrdemDeValor.copy() + [qtdOrdemDeValor[0]]
        for i in range(len(auxQtdOrdemDeValor) - 1):
            if auxQtdOrdemDeValor[i] == 1 and auxQtdOrdemDeValor[i + 1] == 1:
                qtdValidos+=1
                sequenciaValida = True
            else:
                sequenciaValida = False
                qtdValidos = 1

            if qtdValidos ==  limiteSequencia and sequenciaValida:
                return True

        return False

    @staticmethod
    def validarTrinca(qtdOrdemDeValor):
        for i in range(len(qtdOrdemDeValor)):
            if qtdOrdemDeValor[i] == 3:
                return True

        return False

    @staticmethod
    def validarDoisPares(qtdOrdemDeValor):
        qtdPares =0
        for i in range(len(qtdOrdemDeValor)):
            if qtdOrdemDeValor[i] == 2:
                qtdPares+= 1

        if qtdPares >= 2:
            return True

        return False

    @staticmethod
    def validarPar(qtdOrdemDeValor):
        for i in range(len(qtdOrdemDeValor)):
            if qtdOrdemDeValor[i] == 2:
                return True

        return False

    @staticmethod
    def validarCartaAlta(qtdOrdemDeValor):
        for i in range(len(qtdOrdemDeValor)):
            if qtdOrdemDeValor[i] == 1:
                return True

        return False