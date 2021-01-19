package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Acao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

import static br.edu.ifsp.application.main.Main.buscarAcaoUC;

public class ViewAcaoUIController {

    @FXML
    private TableView<Acao> tableView;
    @FXML
    private TableColumn<Acao, Integer> cID;
    @FXML
    private TableColumn<Acao, String> cNome;
    @FXML
    private TableColumn<Acao, String> cDescricao;
    @FXML
    private TableColumn<Acao, Docente> cDocente;

    private ObservableList<Acao> tableData;

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
        cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        cDocente.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
    }

    private void loadDataAndShow() {
        List<Acao> acoes = buscarAcaoUC.findAll();
        tableData.clear();
        tableData.addAll(acoes);
    }

    public void cadastrarLinhaAcao(ActionEvent actionEvent) throws IOException {
        App.setRoot("CadastroAcaoUI");
    }

    public void alterarLinhaAcao(ActionEvent actionEvent) throws IOException {
       Acao acao = tableView.getSelectionModel().getSelectedItem();

        if(acao != null){
            App.setRoot("CadastroAcaoUI");
            CadastroAcaoUIController controller = (CadastroAcaoUIController) App.getController();
            controller.setAcao(acao);
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MantenedorUI");
    }
}
