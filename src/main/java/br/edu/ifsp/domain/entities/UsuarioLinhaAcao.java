package br.edu.ifsp.domain.entities;

public class UsuarioLinhaAcao {
    private Integer id;
    private Acao acao;
    private Usuario usuario;
    private Status status;
    private Acolhimento responsavelAcolhimento;

    public UsuarioLinhaAcao(Integer id, Acao acao, Usuario usuario, Status status, Acolhimento responsavelAcolhimento) {
        this.id = id;
        this.acao = acao;
        this.usuario = usuario;
        this.status = status;
        this.responsavelAcolhimento = responsavelAcolhimento;
    }

    public Integer getId() {
        return id;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Acolhimento getResponsavelAcolhimento() {
        return responsavelAcolhimento;
    }

    public void setResponsavelAcolhimento(Acolhimento responsavelAcolhimento) {
        this.responsavelAcolhimento = responsavelAcolhimento;
    }

    @Override
    public String toString() {
        return "UsuarioLinhaAcao{" +
                "id=" + id +
                ", linhaAcao=" + acao +
                ", usuario=" + usuario +
                ", status=" + status +
                ", responsavelAcolhimento=" + responsavelAcolhimento +
                '}';
    }
}
