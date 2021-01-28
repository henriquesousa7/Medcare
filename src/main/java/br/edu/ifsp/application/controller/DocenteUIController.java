package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Docente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DocenteUIController {
    @FXML
    private Label lbNome;

    private Docente docente;

    public void cadastrarAtendimento(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewAtendimentoUI");
        ViewAtendimentoUIController controller = (ViewAtendimentoUIController) App.getController();
        controller.setSessionDocente(docente);
    }

    public void cadastrarInterconsulta(ActionEvent actionEvent) {
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainUI");
    }

    public void setSessionDocente(Docente docente) {
        this.docente = docente;
        lbNome.setText(docente.getNome());
    }
}
