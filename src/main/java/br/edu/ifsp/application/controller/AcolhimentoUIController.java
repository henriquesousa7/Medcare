package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acolhimento;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AcolhimentoUIController {
    Acolhimento acolhimento;

    public void cadastrarUsuario(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewUsuarioUI");
    }

    public void cadastrarUsuarioAcao(ActionEvent actionEvent) {
    }

    public void setSessionAcolhimento(Acolhimento acolhimento){
        this.acolhimento = acolhimento;
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainUI");
    }
}
