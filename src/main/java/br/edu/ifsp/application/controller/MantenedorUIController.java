package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MantenedorUIController {
    public void openDiscente(ActionEvent actionEvent) throws IOException {
    }

    public void openDocente(ActionEvent actionEvent) throws IOException {
    }

    public void openAcolhimento(ActionEvent actionEvent) throws IOException {
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainUI");
    }
}
