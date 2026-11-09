from dataclasses import dataclass,field
from Cartas import Cartas
@dataclass
class Dados:

    # --- Informações gerais---
    rodada: int = 0
    lengthMao: int = 8
    maoJogador: list = field(default_factory=lambda:Cartas.colocarCartas(0,8)) # 8 é o tamanho da mão
    ante: dict = field(default_factory=lambda:{"SmallBlind":300, "BigBlind":450, "BossBlind":600})
    tipoOrganizacao:str = "ordemDeValor"
    valorBase:list = field(default_factory=lambda: [300, 800, 2000, 5000, 11000, 20000, 35000, 50000])

    # --- Informações baralho
    baralho:list = field(default_factory=lambda:[[1 + i for i in range(13)] for _ in range(4)])
    temCartasBaralho:list = field(default_factory=lambda:[[True for _ in range(13)] for _ in range(4)])

    # --- Rodada Atual ---
    qtdMao :int = 3
    qtdMaoDescarte: int = 3
    pontosTotais: int = 0

    # --- Mão de póquer Atual ---
    fichas: int  = 0
    multiplicador: int = 0
    maoPokerJogada:str = ""

    # --- Níveis das Mãos ---
    valorFicha: dict = field(default_factory=lambda: Dados.setValoresFicha())
    valorMulti: dict = field(default_factory=lambda: Dados.setValoresMulti())

    def resetValores(self):
        """Reset dos valores após o fim da rodada"""
        self.qtdMao = 3
        self.qtdMaoDescarte = 3
        self.pontosTotais  = 0
        self.fichas: int = 0
        self.multiplicador: int = 0
        self.maoPokerJogada: str = ""
        self.temCartasBaralho = [[True for _ in range(13)] for _ in range(4)]

    @staticmethod
    def setValoresFicha():
        return {"cartaAlta": 5, "par": 10,"doisPares": 20, "trinca": 30, "sequencia": 30, "flush": 35, "fullHouse": 40,
                "quadra": 60, "straightFlush": 100}

    @staticmethod
    def setValoresMulti():
        return {"cartaAlta": 1,"par": 2,"doisPares":2,"trinca":3,"sequencia":4,"flush":4,"fullHouse":4,"quadra":7,"straightFlush":8}
