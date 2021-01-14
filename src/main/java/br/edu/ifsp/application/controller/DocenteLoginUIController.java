package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import javafx.event.ActionEvent;

import java.io.IOException;

public class DocenteLoginUIController {

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainUI");
    }
}
