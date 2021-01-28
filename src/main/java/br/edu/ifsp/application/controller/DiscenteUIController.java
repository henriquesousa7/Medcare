package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Docente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class DiscenteUIController {
    @FXML
    private Label lbNome;

    private Discente discente;

    public void consultarAgendamento(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewAtendimentoDiscenteUI");
        ViewAtendimentoDiscenteUIController controller = (ViewAtendimentoDiscenteUIController) App.getController();
        controller.setSessionDiscenteMode(discente, "CONSULTA");
    }

    public void relatorioGeral(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewAtendimentoDiscenteUI");
        ViewAtendimentoDiscenteUIController controller = (ViewAtendimentoDiscenteUIController) App.getController();
        controller.setSessionDiscenteMode(discente, "RELATORIOGR");
    }

    public void relatorioGeralAcao(ActionEvent actionEvent) throws IOException {
        App.setRoot("EscolheAcaoUI");
        EscolheAcaoUIController controller = (EscolheAcaoUIController) App.getController();
        controller.setSessionDiscenteMode(discente, "RELATORIOLA");
    }

    public void listaEspera(ActionEvent actionEvent) throws IOException {
        App.setRoot("EscolheAcaoUI");
        EscolheAcaoUIController controller = (EscolheAcaoUIController) App.getController();
        controller.setSessionDiscenteMode(discente, "LISTAESPERA");
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainUI");
    }

    public void setSessionDiscente(Discente discente) {
        this.discente = discente;
        lbNome.setText(discente.getNome());
    }
}
