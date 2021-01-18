package br.edu.ifsp.domain.entities;

import java.util.List;
import java.util.Objects;

public class LinhaAcao {

    private Integer id;
    private String nome;
    private String descricao;
    private LinhaCuidado linhaCuidado;
    private Docente responsavel;

    public LinhaAcao(Integer id, String nome, String descricao, LinhaCuidado linhaCuidado, Docente responsavel) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.linhaCuidado = linhaCuidado;
        this.responsavel = responsavel;
    }

    public LinhaAcao(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public LinhaAcao(){}

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

    public LinhaCuidado getLinhaCuidado() {
        return linhaCuidado;
    }

    public void setLinhaCuidado(LinhaCuidado linhaCuidado) {
        this.linhaCuidado = linhaCuidado;
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
        LinhaAcao linhaAcao = (LinhaAcao) o;
        return Objects.equals(id, linhaAcao.id) &&
                Objects.equals(nome, linhaAcao.nome) &&
                Objects.equals(descricao, linhaAcao.descricao) &&
                Objects.equals(linhaCuidado, linhaAcao.linhaCuidado) &&
                Objects.equals(responsavel, linhaAcao.responsavel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, linhaCuidado, responsavel);
    }
}
