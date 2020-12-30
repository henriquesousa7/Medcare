package br.edu.ifsp.domain.entities;

public class UsuarioLinhaAcao {
    private LinhaAcao linhaAcao;
    private Usuario usuario;
    private Status status;
    private Acolhimento responsavelAcolhimento;

    public UsuarioLinhaAcao(LinhaAcao linhaAcao, Usuario usuario, Status status, Acolhimento responsavelAcolhimento) {
        this.linhaAcao = linhaAcao;
        this.usuario = usuario;
        this.status = status;
        this.responsavelAcolhimento = responsavelAcolhimento;
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
}
