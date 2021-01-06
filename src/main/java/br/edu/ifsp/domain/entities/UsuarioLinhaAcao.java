package br.edu.ifsp.domain.entities;

public class UsuarioLinhaAcao {
    private Integer id;
    private LinhaAcao linhaAcao;
    private Usuario usuario;
    private Status status;
    private Acolhimento responsavelAcolhimento;

    public UsuarioLinhaAcao(Integer id, LinhaAcao linhaAcao, Usuario usuario, Status status, Acolhimento responsavelAcolhimento) {
        this.id = id;
        this.linhaAcao = linhaAcao;
        this.usuario = usuario;
        this.status = status;
        this.responsavelAcolhimento = responsavelAcolhimento;
    }

    public Integer getId() {
        return id;
    }

    public LinhaAcao getLinhaAcao() {
        return linhaAcao;
    }

    public void setLinhaAcao(LinhaAcao linhaAcao) {
        this.linhaAcao = linhaAcao;
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
                ", linhaAcao=" + linhaAcao +
                ", usuario=" + usuario +
                ", status=" + status +
                ", responsavelAcolhimento=" + responsavelAcolhimento +
                '}';
    }
}
