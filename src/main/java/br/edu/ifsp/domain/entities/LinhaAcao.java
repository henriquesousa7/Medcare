package br.edu.ifsp.domain.entities;

import java.util.List;
import java.util.Objects;

public class LinhaAcao {

    private Integer id;
    private String nome;
    private String descricao;
    private LinhaCuidado linhaCuidado;
    private List<Docente> responsaveis;

    public LinhaAcao(Integer id, String nome, String descricao, LinhaCuidado linhaCuidado, List<Docente> responsaveis) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.linhaCuidado = linhaCuidado;
        this.responsaveis = responsaveis;
    }

    public LinhaAcao(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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

    public List<Docente> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<Docente> responsaveis) {
        this.responsaveis = responsaveis;
    }

    @Override
    public String toString() {
        return "LinhaAcao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", linhaCuidado=" + linhaCuidado +
                ", responsaveis=" + responsaveis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinhaAcao linhaAcao = (LinhaAcao) o;
        return id == linhaAcao.id &&
                Objects.equals(nome, linhaAcao.nome) &&
                Objects.equals(descricao, linhaAcao.descricao) &&
                Objects.equals(linhaCuidado, linhaAcao.linhaCuidado) &&
                Objects.equals(responsaveis, linhaAcao.responsaveis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, linhaCuidado, responsaveis);
    }
}
