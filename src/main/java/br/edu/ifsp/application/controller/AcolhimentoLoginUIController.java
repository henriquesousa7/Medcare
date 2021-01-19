package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.buscarAcolhimentoUC;

public class AcolhimentoLoginUIController {
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
        Acolhimento acolhimento = buscarAcolhimentoUC.checkLogin(txtEmail.getText().trim(), Integer.valueOf(txtPront.getText().trim()));

        if(acolhimento == null)
            throw new EntityNotFoundException("Acolhimento nao existe");

        System.out.println("Login efetuado com sucesso");
    }
}
