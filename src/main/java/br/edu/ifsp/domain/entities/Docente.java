package br.edu.ifsp.domain.entities;

public class Docente {

    private int prontuario;
    private String nome;

    public Docente(int prontuario, String nome) {
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
