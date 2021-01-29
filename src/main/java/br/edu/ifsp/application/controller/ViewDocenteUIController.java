package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Mantenedor;
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
import static br.edu.ifsp.application.main.Main.buscarDocenteUC;

public class ViewDocenteUIController {
    @FXML
    private TableView<Docente> tableView;
    @FXML
    private TableColumn<Docente, Integer> cProntuario;
    @FXML
    private TableColumn<Docente, String> cNome;
    @FXML
    private TableColumn<Docente, String> cEmail;
    @FXML
    private TableColumn<Docente, String> cTelefone;

    private ObservableList<Docente> tableData;
    private Mantenedor mantenedor;

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
    }

    private void loadDataAndShow() {
        List<Docente> docentes = buscarDocenteUC.findAll();
        tableData.clear();
        tableData.addAll(docentes);
    }

    public void cadastrarDocente(ActionEvent actionEvent) throws IOException {
        App.setRoot("CadastroDocenteUI");
        CadastroDocenteUIController controller = (CadastroDocenteUIController) App.getController();
        controller.setDocenteMantenedor(null, mantenedor);
    }

    public void alterarDocente(ActionEvent actionEvent) throws IOException {
        Docente docente = tableView.getSelectionModel().getSelectedItem();

        if(docente != null){
            App.setRoot("CadastroDocenteUI");
            CadastroDocenteUIController controller = (CadastroDocenteUIController) App.getController();
            controller.setDocenteMantenedor(docente, mantenedor);
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("MantenedorUI");
        MantenedorUIController controller = (MantenedorUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    public void setSessionMantenedor(Mantenedor mantenedor) {
        this.mantenedor = mantenedor;
    }
}
