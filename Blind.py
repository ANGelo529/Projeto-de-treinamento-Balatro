from  Coringa import Coringa

class Blind:
    valorBase = [ 0 for _ in range(8)]
    def __init__(self):
        self.colocarValorBase()

    def colocarValorBase(self):
        valores = [300,800,2000,5000,11000,20000,35000,50000]
        valorBase = valores

    def verificarBlind(self,rodada):
        if rodada % 3 == 0:
            return self.valorBase[int(rodada / 3)] * 2
        elif rodada % 3 == 1:
            return self.valorBase[int(rodada / 3 )]
        else:
            return self.valorBase[int(rodada / 3)] * 1.5

    def verificarJogada(self,maoJogada):
        qtdNaipes = [0 for _ in range(4)]
        qtdOrdemDeValor = [0 for _ in range(13)]
        for index,i in enumerate(maoJogada):
            qtdNaipes[maoJogada.naipe]+= 1
            qtdOrdemDeValor[maoJogada.ordemDeValor] += 1
            Coringa.validacaoCoringas()
        self.verificarMaoPoker(qtdNaipes,qtdOrdemDeValor)

    def verificarMaoPoker(self,qtdNaipes,qtdOrdemDeValor):
        # INCOMPLETO
        a=0