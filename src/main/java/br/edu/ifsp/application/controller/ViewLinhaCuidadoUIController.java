package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.LinhaCuidado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

import static br.edu.ifsp.application.main.Main.buscarDocenteUC;
import static br.edu.ifsp.application.main.Main.buscarLinhaCuidadoUC;

public class ViewLinhaCuidadoUIController {
    @FXML
    private TableView<LinhaCuidado> tableView;
    @FXML
    private TableColumn<LinhaCuidado, Integer> cID;
    @FXML
    private TableColumn<LinhaCuidado, String> cNome;
    @FXML
    private TableColumn<LinhaCuidado, String> cDescricao;
    @FXML
    private TableColumn<LinhaCuidado, Acao> cAcao;

    private ObservableList<LinhaCuidado> tableData;

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
        cAcao.setCellValueFactory(new PropertyValueFactory<>("acao"));
    }

    private void loadDataAndShow() {
        List<LinhaCuidado> linhasCuidado = buscarLinhaCuidadoUC.findAll();
        tableData.clear();
        tableData.addAll(linhasCuidado);
    }

    public void cadastrarLinhaCuidado(ActionEvent actionEvent) throws IOException {
        App.setRoot("CadastroLinhaCuidadoUI");
    }

    public void alterarLinhaCuidado(ActionEvent actionEvent) throws IOException {
        LinhaCuidado linhaCuidado = tableView.getSelectionModel().getSelectedItem();

        if(linhaCuidado != null){
            App.setRoot("CadastroLinhaCuidadoUI");
            CadastroLinhaCuidadoUIController controller = (CadastroLinhaCuidadoUIController) App.getController();
            controller.setLinhaCuidado(linhaCuidado);
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MantenedorUI");
    }
}
