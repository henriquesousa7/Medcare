package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.*;

public class CadastroInterconsultaUIController {
    @FXML
    private ComboBox<Usuario> cbUsuario;
    @FXML
    private ComboBox<Acao> cbAcao;
    @FXML
    private ComboBox<Status> cbStatus;
    @FXML
    private ComboBox<Docente> cbNovoDocente;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;

    private InterConsulta interConsulta;
    private Docente docente;

    @FXML
    private void initialize(){
        cbUsuario.setItems(FXCollections.observableArrayList(buscarUsuarioUC.findAll()));
        cbAcao.setItems(FXCollections.observableArrayList(buscarAcaoUC.findAll()));
        cbStatus.setItems(FXCollections.observableArrayList(Status.values()));
    }

    public void salvarOuAtualizar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();

        if(interConsulta.getId() == null){
            solicitarInterConsultaUC.solicitaInterConsulta(interConsulta);
        }
        App.setRoot("ViewInterconsultaUI");
        ViewInterconsultaUIController controller = (ViewInterconsultaUIController) App.getController();
        controller.setSessionDocente(docente);
    }

    private void getEntityFromView() {
        if (interConsulta == null) {
            interConsulta = new InterConsulta();
        }
        interConsulta.setUsuario(cbUsuario.getValue());
        interConsulta.setAcao(cbAcao.getValue());
        interConsulta.setStatus(cbStatus.getValue());
        interConsulta.setDocenteResponsavel(cbNovoDocente.getValue());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewInterconsultaUI");
        ViewInterconsultaUIController controller = (ViewInterconsultaUIController) App.getController();
        controller.setSessionDocente(docente);
    }

    public void setSessionDocente(Docente docente) {
        if (docente == null)
            throw new IllegalArgumentException("Docente can not be null.");

        this.docente = docente;
        cbNovoDocente.setItems(FXCollections.observableArrayList(buscarDocenteUC.findDiffFrom(this.docente)));
    }
}
