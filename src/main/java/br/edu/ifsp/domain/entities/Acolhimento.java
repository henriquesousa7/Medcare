package br.edu.ifsp.domain.entities;

public class Acolhimento {

    private String prontuario;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    public Acolhimento(String prontuario, String nome, String email, String telefone, String cpf) {
        this.prontuario = prontuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getProntuario() {
        return prontuario;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
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

    @Override
    public String toString() {
        return "Acolhimento{" +
                "prontuario='" + prontuario + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
