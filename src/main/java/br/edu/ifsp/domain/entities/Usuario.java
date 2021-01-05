package br.edu.ifsp.domain.entities;

public class Usuario {

    private String cpf;
    private int numeroCartaoSUS;
    private String nome;
    private char sexo;
    private String telefone;
    private String endereco;
    private String historicoMedico;

    public Usuario(String cpf, int numeroCartaoSUS, String nome, char sexo, String telefone, String endereco, String historicoMedico) {
        this.cpf = cpf;
        this.numeroCartaoSUS = numeroCartaoSUS;
        this.nome = nome;
        this.sexo = sexo;
        this.telefone = telefone;
        this.endereco = endereco;
        this.historicoMedico = historicoMedico;
    }

    public String getCpf() {
        return cpf;
    }

    public int getNumeroCartaoSUS() {
        return numeroCartaoSUS;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getHistoricoMedico() {
        return historicoMedico;
    }

    public void setHistoricoMedico(String historicoMedico) {
        this.historicoMedico = historicoMedico;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cpf='" + cpf + '\'' +
                ", numeroCartaoSUS=" + numeroCartaoSUS +
                ", nome='" + nome + '\'' +
                ", sexo=" + sexo +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", historicoMedico='" + historicoMedico + '\'' +
                '}';
    }
}
