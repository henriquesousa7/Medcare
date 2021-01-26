package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Docente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.*;

public class DocenteLoginUIController {
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
        Docente docente = buscarDocenteUC.checkLogin(txtEmail.getText().trim(), Integer.valueOf(txtPront.getText().trim()));

        if(docente != null) {
            App.setRoot("DocenteUI");
            DocenteUIController controller = (DocenteUIController) App.getController();
            controller.setSessionDocente(docente);
        }
    }
}
