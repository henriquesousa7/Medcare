package br.edu.ifsp.domain.entities;

public class InterConsulta {

    private int id;
    private LinhaAcao linhaAcao;
    private Usuario usuario;
    private Status status;
    private Docente docenteResponsavel;

    public InterConsulta(int id, LinhaAcao linhaAcao, Usuario usuario, Docente docenteResponsavel, Status status) {
        this.id = id;
        this.linhaAcao = linhaAcao;
        this.usuario = usuario;
        this.status = status;
        this.docenteResponsavel = docenteResponsavel;
    }

    public int getId() {
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

    public Docente getDocenteResponsavel() {
        return docenteResponsavel;
    }

    public void setDocenteResponsavel(Docente docenteResponsavel) {
        this.docenteResponsavel = docenteResponsavel;
    }

    @Override
    public String toString() {
        return "InterConsulta{" +
                "linhaAcao=" + linhaAcao +
                ", usuario=" + usuario +
                ", status=" + status +
                '}';
    }
}
