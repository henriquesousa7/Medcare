package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Docente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.buscarDiscenteUC;
import static br.edu.ifsp.application.main.Main.buscarDocenteUC;

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
        Discente discente = buscarDiscenteUC.checkLogin(txtEmail.getText().trim(), Integer.valueOf(txtPront.getText().trim()));

        if(discente != null) {
            App.setRoot("DiscenteUI");
            DiscenteUIController controller = (DiscenteUIController) App.getController();
            controller.setSessionDiscente(discente);
        }
    }
}
