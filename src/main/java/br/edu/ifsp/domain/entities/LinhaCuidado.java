package br.edu.ifsp.domain.entities;

import java.util.List;

public class LinhaCuidado {

    private int id;
    private String descricao;

    public LinhaCuidado(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
