package br.edu.ifsp.domain.entities;

public class Discente {

    private Integer prontuario;
    private String nome;
    private String email;
    private String telefone;
    private LinhaAcao linhaAcao;

    public Discente(Integer prontuario, String nome, String email, String telefone, LinhaAcao linhaAcao) {
        this.prontuario = prontuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.linhaAcao = linhaAcao;
    }

    public Discente(Integer prontuario, String nome, String email, String telefone) {
        this.prontuario = prontuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Integer getProntuario() {
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

    public LinhaAcao getLinhaAcao() {
        return linhaAcao;
    }

    public void setLinhaAcao(LinhaAcao linhaAcao) {
        this.linhaAcao = linhaAcao;
    }

    @Override
    public String toString() {
        return "Discente{" +
                "prontuario=" + prontuario +
                ", nome='" + nome + '\'' +
                ", linhaAcao=" + linhaAcao +
                '}';
    }
}
