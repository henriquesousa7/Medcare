package br.edu.ifsp.domain.entities;

public class InterConsulta {

    private LinhaAcao linhaAcao;
    private Usuario usuario;
    private Status status;

    public InterConsulta(LinhaAcao linhaAcao, Usuario usuario, Status status) {
        this.linhaAcao = linhaAcao;
        this.usuario = usuario;
        this.status = status;
    }

    public void negarInterConsulta(InterConsulta interConsulta){
        return;
    }

    public void aceitarInterConsulta(InterConsulta interConsulta){
        return;
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
}
