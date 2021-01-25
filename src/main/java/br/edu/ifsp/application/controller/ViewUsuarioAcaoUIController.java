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

import static br.edu.ifsp.application.main.Main.buscarUsuarioLinhaAcaoUC;
import static br.edu.ifsp.application.main.Main.buscarUsuarioUC;

public class ViewUsuarioAcaoUIController {
    @FXML
    private TableView<UsuarioLinhaAcao> tableView;
    @FXML
    private TableColumn<UsuarioLinhaAcao, Integer> cID;
    @FXML
    private TableColumn<UsuarioLinhaAcao, Acao> cAcao;
    @FXML
    private TableColumn<UsuarioLinhaAcao, Usuario> cUsuario;
    @FXML
    private TableColumn<UsuarioLinhaAcao, Status> cStatus;
    @FXML
    private TableColumn<UsuarioLinhaAcao, Acolhimento> cAcolhimento;

    private ObservableList<UsuarioLinhaAcao> tableData;
    private Acolhimento acolhimento;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
        loadDataAndShow();
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
        cAcolhimento.setCellValueFactory(new PropertyValueFactory<>("responsavelAcolhimento"));
    }

    private void loadDataAndShow() {
        List<UsuarioLinhaAcao> usuariosAcao = buscarUsuarioLinhaAcaoUC.findAll();
        tableData.clear();
        tableData.addAll(usuariosAcao);
    }

    public void cadastrarUsuarioAcao(ActionEvent actionEvent) throws IOException {
        App.setRoot("CadastroUsuarioAcaoUI");
        CadastroUsuarioAcaoUIController controller = (CadastroUsuarioAcaoUIController) App.getController();
        controller.setUsuarioLinhaAcaoEAcolhimento(null, acolhimento);
    }

    public void alterarUsuarioAcao(ActionEvent actionEvent) throws IOException {
        UsuarioLinhaAcao usuarioLinhaAcao = tableView.getSelectionModel().getSelectedItem();

        if(usuarioLinhaAcao != null){
            App.setRoot("CadastroUsuarioAcaoUI");
            CadastroUsuarioAcaoUIController controller = (CadastroUsuarioAcaoUIController) App.getController();
            controller.setUsuarioLinhaAcaoEAcolhimento(usuarioLinhaAcao, acolhimento);
        }
    }

    public void setAcolhimentoSession(Acolhimento acolhimento) {
        if (acolhimento == null)
            throw new IllegalArgumentException("Acolhimento can not be null.");

        this.acolhimento = acolhimento;
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("AcolhimentoUI");
        AcolhimentoUIController controller = (AcolhimentoUIController) App.getController();
        controller.setSessionAcolhimento(acolhimento);
    }
}
