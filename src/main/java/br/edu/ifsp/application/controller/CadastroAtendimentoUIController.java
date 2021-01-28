package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.*;

public class CadastroAtendimentoUIController {
    @FXML
    private ComboBox<UsuarioLinhaAcao> cbUsuarioAcao;
    @FXML
    private DatePicker dtData;
    @FXML
    private ComboBox<Status> cbStatus;
    @FXML
    private ComboBox<Discente> cbDiscente;


    private Atendimento atendimento;
    private Docente docente;

    @FXML
    private void initialize(){

        cbDiscente.setItems(FXCollections.observableArrayList(buscarDiscenteUC.findAll()));
        cbStatus.setItems(FXCollections.observableArrayList(Status.values()));
    }

    public void salvarOuAtualizar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();

        if(atendimento.getId() == null){
            agendarAtendimentoUC.agendaAtendimento(atendimento);
        }else{
            alterarAtendimentoUC.alterarAtendimento(atendimento);
        }
        App.setRoot("ViewAtendimentoUI");
        ViewAtendimentoUIController controller = (ViewAtendimentoUIController) App.getController();
        controller.setSessionDocente(docente);
    }

    private void getEntityFromView() {
        if (atendimento == null) {
            atendimento = new Atendimento();
        }
        atendimento.setData(dtData.getValue());
        atendimento.setUsuarioLinhaAcao(cbUsuarioAcao.getValue());
        atendimento.setStatus(cbStatus.getValue());
        atendimento.setDiscenteResponsavel(cbDiscente.getValue());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewAtendimentoUI");
        ViewAtendimentoUIController controller = (ViewAtendimentoUIController) App.getController();
        controller.setSessionDocente(docente);
    }

    public void setAtendimentoEDocente(Atendimento atendimento, Docente docente) {
        if (atendimento != null) {
            this.atendimento = atendimento;
            setEntityIntoView();
            cbUsuarioAcao.setDisable(true);
        }
        this.docente = docente;
        cbUsuarioAcao.setItems(FXCollections.observableArrayList(buscarUsuarioLinhaAcaoUC.findByDocente(docente.getProntuario())));
    }

    private void setEntityIntoView() {
        cbUsuarioAcao.getSelectionModel().select(atendimento.getUsuarioLinhaAcao());
        dtData.setValue(atendimento.getData());
        cbStatus.getSelectionModel().select(atendimento.getStatus());
        cbDiscente.getSelectionModel().select(atendimento.getDiscenteResponsavel());
    }
}
