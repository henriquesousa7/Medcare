package br.edu.ifsp.domain.entities;

import java.time.LocalDateTime;

public class Atendimento {

    private UsuarioLinhaAcao usuarioLinhaAcao;
    private LocalDateTime data;
    private Status status;
    private Discente discenteResponsavel;

    public Atendimento(UsuarioLinhaAcao usuarioLinhaAcao, LocalDateTime data, Status status, Discente discenteResponsavel) {
        this.usuarioLinhaAcao = usuarioLinhaAcao;
        this.data = data;
        this.status = status;
        this.discenteResponsavel = discenteResponsavel;
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
                "usuarioLinhaAcao=" + usuarioLinhaAcao +
                ", data=" + data +
                ", status=" + status +
                ", discenteResponsavel=" + discenteResponsavel +
                '}';
    }
}
