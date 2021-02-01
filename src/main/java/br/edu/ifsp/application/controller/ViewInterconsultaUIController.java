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
import java.util.List;

import static br.edu.ifsp.application.main.Main.*;

public class ViewInterconsultaUIController {
    @FXML
    private TableView<InterConsulta> tableView;
    @FXML
    private TableColumn<InterConsulta, Integer> cID;
    @FXML
    private TableColumn<InterConsulta, Acao> cAcao;
    @FXML
    private TableColumn<InterConsulta, Usuario> cUsuario;
    @FXML
    private TableColumn<InterConsulta, Status> cStatus;
    @FXML
    private TableColumn<InterConsulta, Docente> cNovoDocente;

    private ObservableList<InterConsulta> tableData;
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
        cAcao.setCellValueFactory(new PropertyValueFactory<>("acao"));
        cUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        cNovoDocente.setCellValueFactory(new PropertyValueFactory<>("docenteResponsavel"));
    }

    private void loadDataAndShow() {
        List<InterConsulta> interConsultas = buscarInterconsultaUC.findByDocente(docente.getProntuario());
        tableData.clear();
        tableData.addAll(interConsultas);
        tableView.refresh();
    }

    public void cadastrarUsuarioAcao(ActionEvent actionEvent) throws IOException {
        App.setRoot("CadastroInterconsultaUI");
        CadastroInterconsultaUIController controller = (CadastroInterconsultaUIController) App.getController();
        controller.setSessionDocente(docente);
    }

    public void aceitarInterconsulta(ActionEvent actionEvent) throws IOException {
        InterConsulta interConsulta = tableView.getSelectionModel().getSelectedItem();

        if(interConsulta != null){
            gerenciarInterConsultaUC.gerenciaInterConsulta(interConsulta, "aceito");
        }
        loadDataAndShow();
    }

    public void recusarInterconsulta(ActionEvent actionEvent) {
        InterConsulta interConsulta = tableView.getSelectionModel().getSelectedItem();

        if(interConsulta != null){
            gerenciarInterConsultaUC.gerenciaInterConsulta(interConsulta, "recusado");
        }
        loadDataAndShow();
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
