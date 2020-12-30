package br.edu.ifsp.domain.entities;

public class Discente {

    private int prontuario;
    private String nome;
    private LinhaAcao linhaAcao;

    public Discente(int prontuario, String nome, LinhaAcao linhaAcao) {
        this.prontuario = prontuario;
        this.nome = nome;
        this.linhaAcao = linhaAcao;
    }

    public int getProntuario() {
        return prontuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LinhaAcao getLinhaAcao() {
        return linhaAcao;
    }

    public void setLinhaAcao(LinhaAcao linhaAcao) {
        this.linhaAcao = linhaAcao;
    }
}
