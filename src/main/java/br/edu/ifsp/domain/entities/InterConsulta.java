package br.edu.ifsp.domain.entities;

public class InterConsulta {

    private Integer id;
    private Acao acao;
    private Usuario usuario;
    private Status status;
    private Docente docenteResponsavel;

    public InterConsulta(Integer id, Acao acao, Usuario usuario, Docente docenteResponsavel, Status status) {
        this.id = id;
        this.acao = acao;
        this.usuario = usuario;
        this.status = status;
        this.docenteResponsavel = docenteResponsavel;
    }

    public InterConsulta(){}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Atendimento atendimento) {
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
                ", acao=" + acao +
                ", usuario=" + usuario +
                ", status=" + status +
                ", docenteResponsavel=" + docenteResponsavel +
                '}';
    }
}
