DROP DATABASE if exists tcb_BD;
CREATE DATABASE if not EXISTS tcb_BD;
use tcb_BD;

CREATE TABLE usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    jogo_ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4; 

CREATE TABLE game_state (
    id_game_state INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT UNIQUE NOT NULL,
    tamanho_mao INT NOT NULL DEFAULT 8,
    qtd_mao INT NOT NULL DEFAULT 3,
    qtd_descarte INT NOT NULL DEFAULT 3,
    pontos_atuais BIGINT NOT NULL DEFAULT 0,
    ante_atual INT NOT NULL DEFAULT 1,
    pos_blind_atual INT NOT NULL DEFAULT 0,
    limite_coringa INT NOT NULL DEFAULT 5,
    limite_consumivel INT NOT NULL DEFAULT 2,
    tipo_organizacao VARCHAR(20) DEFAULT "OrdemDeValor",
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario) ON DELETE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE carta_instancia (
    id_carta_instancia INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_game_state INT NOT NULL,
    ordem_de_valor VARCHAR(2) NOT NULL,
    naipe varchar(10) NOT NULL,
    melhoria VARCHAR(30),
    selo VARCHAR(30),
    edicao VARCHAR(30),
    tipo_de_carta VARCHAR(30),
    posicao_carta VARCHAR(10),
    CONSTRAINT fk_carta_game_state FOREIGN KEY (id_game_state) REFERENCES game_state (id_game_state)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE coringa_instancia (
    id_coringa INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_game_state INT NOT NULL,
    id_ingame INT NOT NULL,
    nome VARCHAR(20) NOT NULL,
    modificador VARCHAR(20) NOT NULL,
    incremento INT NOT NULL,
    CONSTRAINT fk_coringa_game_state FOREIGN KEY (id_game_state) REFERENCES game_state (id_game_state) ON DELETE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE consumivel_instancia (
    id_consumivel INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_game_state INT NOT NULL,
    id_ingame INT NOT NULL,
    nome VARCHAR(20) NOT NULL,
    CONSTRAINT fk_consumivel_game_state FOREIGN KEY (id_game_state)
        REFERENCES game_state (id_game_state) ON DELETE CASCADE 
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

select * from usuario;
select * from game_state;