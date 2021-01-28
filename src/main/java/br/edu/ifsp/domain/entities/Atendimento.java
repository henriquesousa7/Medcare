package br.edu.ifsp.domain.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Atendimento {

    private Integer id;
    private UsuarioLinhaAcao usuarioLinhaAcao;
    private LocalDate data;
    private Status status;
    private Discente discenteResponsavel;

    public Atendimento(Integer id, LocalDate data){
        this.id = id;
        this.data = data;
    }

    public Atendimento(Integer id, UsuarioLinhaAcao usuarioLinhaAcao, LocalDate data, Status status, Discente discenteResponsavel) {
        this.id = id;
        this.usuarioLinhaAcao = usuarioLinhaAcao;
        this.data = data;
        this.status = status;
        this.discenteResponsavel = discenteResponsavel;
    }

    public Atendimento(){}

    public Integer getId() {
        return id;
    }

    public UsuarioLinhaAcao getUsuarioLinhaAcao() {
        return usuarioLinhaAcao;
    }

    public void setUsuarioLinhaAcao(UsuarioLinhaAcao usuarioLinhaAcao) {
        this.usuarioLinhaAcao = usuarioLinhaAcao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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
