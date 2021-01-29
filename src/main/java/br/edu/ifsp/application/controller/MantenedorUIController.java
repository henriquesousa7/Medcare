package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Mantenedor;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MantenedorUIController {

    private Mantenedor mantenedor;

    public void openDiscente(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewDiscenteUI");
        ViewDiscenteUIController controller = (ViewDiscenteUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    public void openDocente(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewDocenteUI");
        ViewDocenteUIController controller = (ViewDocenteUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    public void openAcolhimento(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewAcolhimentoUI");
        ViewAcolhimentoUIController controller = (ViewAcolhimentoUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainUI");
    }

    public void openLinhaCuidado(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewLinhaCuidadoUI");
        ViewLinhaCuidadoUIController controller = (ViewLinhaCuidadoUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    public void openLinhaAcao(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewAcaoUI");
        ViewAcaoUIController controller = (ViewAcaoUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    public void setSessionMantenedor(Mantenedor mantenedor) {
        this.mantenedor = mantenedor;
        System.out.println(mantenedor);
    }
}
