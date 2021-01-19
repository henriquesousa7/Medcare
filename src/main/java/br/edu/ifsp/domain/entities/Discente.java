package br.edu.ifsp.domain.entities;

public class Discente {

    private Integer prontuario;
    private String nome;
    private String email;
    private String telefone;
    private Acao acao;

    public Discente(Integer prontuario, String nome, String email, String telefone, Acao acao) {
        this.prontuario = prontuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.acao = acao;
    }

    public Discente(Integer prontuario, String nome, String email, String telefone) {
        this.prontuario = prontuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Discente(){}

    public void setProntuario(Integer prontuario) {
        this.prontuario = prontuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    @Override
    public String toString() {
        return "Discente{" +
                "prontuario=" + prontuario +
                ", nome='" + nome + '\'' +
                ", linhaAcao=" + acao +
                '}';
    }
}
