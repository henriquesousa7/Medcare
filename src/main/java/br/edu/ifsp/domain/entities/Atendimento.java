package br.edu.ifsp.domain.entities;

import java.time.LocalDateTime;

public class Atendimento {

    private Integer id;
    private UsuarioLinhaAcao usuarioLinhaAcao;
    private LocalDateTime data;
    private Status status;
    private Discente discenteResponsavel;

    public Atendimento(Integer id, LocalDateTime data){
        this.id = id;
        this.data = data;
    }

    public Atendimento(Integer id, UsuarioLinhaAcao usuarioLinhaAcao, LocalDateTime data, Status status, Discente discenteResponsavel) {
        this.id = id;
        this.usuarioLinhaAcao = usuarioLinhaAcao;
        this.data = data;
        this.status = status;
        this.discenteResponsavel = discenteResponsavel;
    }

    public Integer getId() {
        return id;
    }

    public UsuarioLinhaAcao getUsuarioLinhaAcao() {
        return usuarioLinhaAcao;
    }

    public void setUsuarioLinhaAcao(UsuarioLinhaAcao usuarioLinhaAcao) {
        this.usuarioLinhaAcao = usuarioLinhaAcao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Discente getDiscenteResponsavel() {
        return discenteResponsavel;
    }

    public void setDiscenteResponsavel(Discente discenteResponsavel) {
        this.discenteResponsavel = discenteResponsavel;
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "id=" + id +
                ", usuarioLinhaAcao=" + usuarioLinhaAcao +
                ", data=" + data +
                ", status=" + status +
                ", discenteResponsavel=" + discenteResponsavel +
                "}";
    }
}
