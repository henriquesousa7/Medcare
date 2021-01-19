package br.edu.ifsp.domain.entities;

import java.util.List;
import java.util.Objects;

public class Acao {

    private Integer id;
    private String nome;
    private String descricao;
    private Docente responsavel;

    public Acao(Integer id, String nome, String descricao, Docente responsavel) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.responsavel = responsavel;
    }

    public Acao(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Acao(){}

    public void setId(Integer id) {
        this.id = id;
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

    public Docente getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Docente responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acao acao = (Acao) o;
        return Objects.equals(id, acao.id) &&
                Objects.equals(nome, acao.nome) &&
                Objects.equals(descricao, acao.descricao) &&
                Objects.equals(responsavel, acao.responsavel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, responsavel);
    }
}
