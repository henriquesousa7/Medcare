package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.entities.Discente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.buscarAcaoUC;

public class EscolheAcaoUIController {
    @FXML
    private ComboBox<Acao> cbAcao;

    private Discente discente;
    private String mode;

    @FXML
    private void initialize() {
        cbAcao.setItems(FXCollections.observableArrayList(buscarAcaoUC.findAll()));
    }

    public void proximo(ActionEvent actionEvent) throws IOException {
        if(mode.equals("RELATORIOLA")) {
            App.setRoot("ViewAtendimentoDiscenteUI");
            ViewAtendimentoDiscenteUIController controller = (ViewAtendimentoDiscenteUIController) App.getController();
            controller.setSessionDiscenteModeAcao(discente, mode, cbAcao.getValue());
        } else {
            App.setRoot("ViewAtendimentoDiscenteUI");
            ViewAtendimentoDiscenteUIController controller = (ViewAtendimentoDiscenteUIController) App.getController();
            controller.setSessionDiscenteModeAcao(discente, mode, cbAcao.getValue());
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("DiscenteUI");
        DiscenteUIController controller = (DiscenteUIController) App.getController();
        controller.setSessionDiscente(discente);
    }

    public void setSessionDiscenteMode(Discente discente, String mode) {
        if (discente == null)
            throw new IllegalArgumentException("Discente can not be null.");

        this.discente = discente;
        this.mode = mode;
    }
}
