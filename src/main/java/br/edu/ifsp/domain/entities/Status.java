package br.edu.ifsp.domain.entities;

public enum Status {

    ANDAMENTO("EM_ANDAMENTO"),
    FINALIZADO("FINALIZADO"),
    AGUARDANDO("AGUARDANDO_ATENDIMENTO"),
    CANCELADO("CANCELADO");

    private String label;

    Status(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
