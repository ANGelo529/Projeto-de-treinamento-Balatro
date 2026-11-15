package jdbc;

import java.util.ArrayList;
import java.util.HashMap;

public class DeckCartaDao {
    
    public void inserirBaralho(){
        String sql = "INSERT INTO game_state (id_usuario,tamanho_mao,qtd_mao,qtd_descarte,pontos_atuais,ante_atual) VALUES (?,?,?);";
    }
}
