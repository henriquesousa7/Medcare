package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Discente;
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

import static br.edu.ifsp.application.main.Main.buscarDiscenteUC;
import static br.edu.ifsp.application.main.Main.buscarDocenteUC;

public class ViewDiscenteUIController {
    @FXML
    private TableView<Discente> tableView;
    @FXML
    private TableColumn<Discente, Integer> cProntuario;
    @FXML
    private TableColumn<Discente, String> cNome;
    @FXML
    private TableColumn<Discente, String> cEmail;
    @FXML
    private TableColumn<Discente, String> cTelefone;
    @FXML
    private TableColumn<Discente, Acao> cAcao;

    private ObservableList<Discente> tableData;

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
        cAcao.setCellValueFactory(new PropertyValueFactory<>("acao"));
    }

    private void loadDataAndShow() {
        List<Discente> discentes = buscarDiscenteUC.findAll();
        tableData.clear();
        tableData.addAll(discentes);
    }

    public void cadastrarDiscente(ActionEvent actionEvent) throws IOException {
        App.setRoot("CadastroDiscenteUI");
    }

    public void alterarDiscente(ActionEvent actionEvent) throws IOException {
        Discente discente = tableView.getSelectionModel().getSelectedItem();

        if(discente != null){
            App.setRoot("CadastroDiscenteUI");
            CadastroDiscenteUIController controller = (CadastroDiscenteUIController) App.getController();
            controller.setDiscente(discente);
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MantenedorUI");
    }
}
