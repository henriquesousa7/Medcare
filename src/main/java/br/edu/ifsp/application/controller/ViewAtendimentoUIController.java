package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static br.edu.ifsp.application.main.Main.*;

public class ViewAtendimentoUIController {
    @FXML
    private TableView<Atendimento> tableView;
    @FXML
    private TableColumn<Atendimento, Integer> cID;
    @FXML
    private TableColumn<Atendimento, UsuarioLinhaAcao> cUsuarioAcao;
    @FXML
    private TableColumn<Atendimento, LocalDateTime> cData;
    @FXML
    private TableColumn<Atendimento, Status> cStatus;
    @FXML
    private TableColumn<Atendimento, Discente> cDiscente;

    private ObservableList<Atendimento> tableData;
    private Docente docente;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cUsuarioAcao.setCellValueFactory(new PropertyValueFactory<>("usuarioLinhaAcao"));
        cData.setCellValueFactory(new PropertyValueFactory<>("data"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        cDiscente.setCellValueFactory(new PropertyValueFactory<>("discenteResponsavel"));
    }

    private void loadDataAndShow() {
        List<Atendimento> atendimentos = buscarAtendimentoUC.findByDocenteAcao(docente.getProntuario());
        tableData.clear();
        tableData.addAll(atendimentos);
    }

    public void cadastrarAtendimento(ActionEvent actionEvent) throws IOException {
        App.setRoot("CadastroAtendimentoUI");
        CadastroAtendimentoUIController controller = (CadastroAtendimentoUIController) App.getController();
        controller.setAtendimentoEDocente(null, docente);
    }

    public void alterarAtendimento(ActionEvent actionEvent) throws IOException {
        Atendimento atendimento = tableView.getSelectionModel().getSelectedItem();

        if(atendimento != null){
            App.setRoot("CadastroAtendimentoUI");
            CadastroAtendimentoUIController controller = (CadastroAtendimentoUIController) App.getController();
            controller.setAtendimentoEDocente(atendimento, docente);
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("DocenteUI");
        DocenteUIController controller = (DocenteUIController) App.getController();
        controller.setSessionDocente(docente);
    }

    public void setSessionDocente(Docente docente) {
        if (docente == null)
            throw new IllegalArgumentException("Docente can not be null.");

        this.docente = docente;
        loadDataAndShow();
    }
}
