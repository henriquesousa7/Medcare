package br.edu.ifsp.domain.entities;

public class Mantenedor {

    private int prontuario;
    private String nome;

    public Mantenedor(int prontuario, String nome) {
        this.prontuario = prontuario;
        this.nome = nome;
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
}
