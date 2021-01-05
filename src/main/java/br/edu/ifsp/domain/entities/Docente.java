package br.edu.ifsp.domain.entities;

public class Docente {

    private int prontuario;
    private String nome;
    private String email;
    private String telefone;

    public Docente(int prontuario, String nome, String email, String telefone) {
        this.prontuario = prontuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public int getProntuario() {
        return prontuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
