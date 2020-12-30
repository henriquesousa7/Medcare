package br.edu.ifsp.domain.entities;

import java.util.List;

public class LinhaAcao {

    private int id;
    private String descricao;
    private LinhaCuidado linhaCuidado;
    private List<Docente> responsaveis;

    public LinhaAcao(int id, String descricao, LinhaCuidado linhaCuidado, List<Docente> responsaveis) {
        this.id = id;
        this.descricao = descricao;
        this.linhaCuidado = linhaCuidado;
        this.responsaveis = responsaveis;
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
}
