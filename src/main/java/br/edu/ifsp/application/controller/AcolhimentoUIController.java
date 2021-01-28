package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acolhimento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class AcolhimentoUIController {
    @FXML
    private Label lbNome;

    private Acolhimento acolhimento;

    public void cadastrarUsuario(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewUsuarioUI");
        ViewUsuarioUIController controller = (ViewUsuarioUIController) App.getController();
        controller.setAcolhimentoSession(acolhimento);
    }

    public void cadastrarUsuarioAcao(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewUsuarioAcaoUI");
        ViewUsuarioAcaoUIController controller = (ViewUsuarioAcaoUIController) App.getController();
        controller.setAcolhimentoSession(acolhimento);
    }

    public void setSessionAcolhimento(Acolhimento acolhimento){
        this.acolhimento = acolhimento;
        lbNome.setText(acolhimento.getNome());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainUI");
    }
}
