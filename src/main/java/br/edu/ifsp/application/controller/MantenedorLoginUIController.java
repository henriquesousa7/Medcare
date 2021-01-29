package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Mantenedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.buscarAcolhimentoUC;
import static br.edu.ifsp.application.main.Main.buscarMantenedorUC;

public class MantenedorLoginUIController {
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPront;

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainUI");
    }

    public void login(ActionEvent actionEvent) throws IOException {
        Mantenedor mantenedor = buscarMantenedorUC.checkLogin(txtEmail.getText().trim(), Integer.valueOf(txtPront.getText().trim()));

        if(mantenedor != null) {
            App.setRoot("MantenedorUI");
            MantenedorUIController controller = (MantenedorUIController) App.getController();
            controller.setSessionMantenedor(mantenedor);
        }
    }
}
