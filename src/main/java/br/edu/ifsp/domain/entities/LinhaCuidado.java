package br.edu.ifsp.domain.entities;

import java.util.List;

public class LinhaCuidado {

    private Integer id;
    private String nome;
    private String descricao;
    private Acao acao;

    public LinhaCuidado(){}

    public LinhaCuidado(Integer id, String nome, String descricao, Acao acao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.acao = acao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return nome;
    }
}
