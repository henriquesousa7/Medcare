package br.edu.ifsp.domain.entities;

public class InterConsulta {

    private Integer id;
    private Atendimento atendimento;
    private Usuario usuario;
    private Status status;
    private Docente docenteResponsavel;

    public InterConsulta(Integer id, Atendimento atendimento, Usuario usuario, Docente docenteResponsavel, Status status) {
        this.id = id;
        this.atendimento = atendimento;
        this.usuario = usuario;
        this.status = status;
        this.docenteResponsavel = docenteResponsavel;
    }

    public Integer getId() {
        return id;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
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
                "id=" + id +
                ", atendimento=" + atendimento +
                ", usuario=" + usuario +
                ", status=" + status +
                ", docenteResponsavel=" + docenteResponsavel +
                '}';
    }
}
