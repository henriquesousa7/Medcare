package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainUIController {

    public void openMantenedor(ActionEvent actionEvent) throws IOException {
        App.setRoot("MantenedorLoginUI");
    }

    public void openDiscente(ActionEvent actionEvent) throws IOException {
        App.setRoot("DiscenteLoginUI");
    }

    public void openDocente(ActionEvent actionEvent) throws IOException {
        App.setRoot("DocenteLoginUI");
    }

    public void openAcolhimento(ActionEvent actionEvent) throws IOException {
        App.setRoot("AcolhimentoLoginUI");
    }
}
