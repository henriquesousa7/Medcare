package br.edu.ifsp.domain.entities;

public class Mantenedor {

    private Integer prontuario;
    private String nome;

    public Mantenedor(Integer prontuario, String nome) {
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
