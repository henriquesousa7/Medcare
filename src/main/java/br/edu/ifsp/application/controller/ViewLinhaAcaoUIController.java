package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.LinhaAcao;
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

import static br.edu.ifsp.application.main.Main.buscarLinhaAcaoUC;
import static br.edu.ifsp.application.main.Main.buscarLinhaCuidadoUC;

public class ViewLinhaAcaoUIController {

    @FXML
    private TableView<LinhaAcao> tableView;
    @FXML
    private TableColumn<LinhaAcao, Integer> cID;
    @FXML
    private TableColumn<LinhaAcao, String> cNome;
    @FXML
    private TableColumn<LinhaAcao, String> cDescricao;
    @FXML
    private TableColumn<LinhaAcao, LinhaCuidado> cLinhaCuidado;
    @FXML
    private TableColumn<LinhaAcao, Docente> cDocente;

    private ObservableList<LinhaAcao> tableData;

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
        cLinhaCuidado.setCellValueFactory(new PropertyValueFactory<>("linhaCuidado"));
        cDocente.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
    }

    private void loadDataAndShow() {
        List<LinhaAcao> linhasAcao = buscarLinhaAcaoUC.findAll();
        tableData.clear();
        tableData.addAll(linhasAcao);
    }

    public void cadastrarLinhaAcao(ActionEvent actionEvent) throws IOException {
        App.setRoot("CadastroLinhaAcaoUI");
    }

    public void alterarLinhaAcao(ActionEvent actionEvent) throws IOException {
       LinhaAcao linhaAcao = tableView.getSelectionModel().getSelectedItem();

        if(linhaAcao != null){
            App.setRoot("CadastroLinhaAcaoUI");
            CadastroLinhaAcaoUIController controller = (CadastroLinhaAcaoUIController) App.getController();
            controller.setLinhaAcao(linhaAcao);
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MantenedorUI");
    }
}
