package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acolhimento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

import static br.edu.ifsp.application.main.Main.buscarAcolhimentoUC;

public class ViewAcolhimentoUIController {

    @FXML
    private TableView<Acolhimento> tableView;
    @FXML
    private TableColumn<Acolhimento, Integer> cProntuario;
    @FXML
    private TableColumn<Acolhimento, String> cNome;
    @FXML
    private TableColumn<Acolhimento, String> cEmail;
    @FXML
    private TableColumn<Acolhimento, String> cTelefone;
    @FXML
    private TableColumn<Acolhimento, String> cCPF;

    private ObservableList<Acolhimento> tableData;

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
        cProntuario.setCellValueFactory(new PropertyValueFactory<>("prontuario"));
        cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        cCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    }

    private void loadDataAndShow() {
        List<Acolhimento> acolhimento = buscarAcolhimentoUC.findAll();
        tableData.clear();
        tableData.addAll(acolhimento);
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MantenedorUI");
    }

    public void cadastrarAcolhimento(ActionEvent actionEvent) throws IOException {
        App.setRoot("CadastroAcolhimentoUI");
    }

    public void alterarAcolhimento(ActionEvent actionEvent) throws IOException {
        Acolhimento acolhimento = tableView.getSelectionModel().getSelectedItem();

        if(acolhimento != null){
            App.setRoot("CadastroAcolhimentoUI");
            CadastroAcolhimentoUIController controller = (CadastroAcolhimentoUIController) App.getController();
            controller.setAcolhimento(acolhimento);
        }
    }
}
