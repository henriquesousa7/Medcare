package br.edu.ifsp.domain.entities;

public class Mantenedor {

    private String email;
    private Integer prontuario;

    public Mantenedor(Integer prontuario, String email) {
        this.prontuario = prontuario;
        this.email = email;
    }

    public Integer getProntuario() {
        return prontuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Mantenedor{" +
                "email='" + email + '\'' +
                ", prontuario=" + prontuario +
                '}';
    }
}
