package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DiscenteLoginUIController {
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPront;
    @FXML
    private Button btnEntrar;

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainUI");
    }

    public void login(ActionEvent actionEvent) throws IOException {
    }
}
