package br.edu.ifsp.domain.entities;

public class Acolhimento {

    private String prontuario;
    private String nome;
    private String cpf;

    public Acolhimento(String prontuario, String nome, String cpf) {
        this.prontuario = prontuario;
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getProntuario() {
        return prontuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
}
