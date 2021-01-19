package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MantenedorUIController {
    public void openDiscente(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewDiscenteUI");
    }

    public void openDocente(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewDocenteUI");
    }

    public void openAcolhimento(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewAcolhimentoUI");
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainUI");
    }

    public void openLinhaCuidado(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewLinhaCuidadoUI");
    }

    public void openLinhaAcao(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewAcaoUI");
    }
}
