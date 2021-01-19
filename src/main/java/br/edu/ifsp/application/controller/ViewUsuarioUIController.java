package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Usuario;
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
import static br.edu.ifsp.application.main.Main.buscarUsuarioUC;

public class ViewUsuarioUIController {
    @FXML
    private TableView<Usuario> tableView;
    @FXML
    private TableColumn<Usuario, String> cCPF;
    @FXML
    private TableColumn<Usuario, String> cNome;
    @FXML
    private TableColumn<Usuario, Integer> cSUS;
    @FXML
    private TableColumn<Usuario, String> cTelefone;
    @FXML
    private TableColumn<Usuario, String> cSexo;
    @FXML
    private TableColumn<Usuario, String> cEndereco;
    @FXML
    private TableColumn<Usuario, String> cHistorico;

    private ObservableList<Usuario> tableData;

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
        cCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        cNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cSUS.setCellValueFactory(new PropertyValueFactory<>("numeroCartaoSUS"));
        cTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        cSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        cEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        cHistorico.setCellValueFactory(new PropertyValueFactory<>("historicoMedico"));
    }

    private void loadDataAndShow() {
        List<Usuario> usuarios = buscarUsuarioUC.findAll();
        tableData.clear();
        tableData.addAll(usuarios);
    }

    public void cadastrarUsuario(ActionEvent actionEvent) throws IOException {
        App.setRoot("CadastroUsuarioUI");
    }

    public void alterarUsuario(ActionEvent actionEvent) throws IOException {
        Usuario usuario = tableView.getSelectionModel().getSelectedItem();

        if(usuario != null){
            App.setRoot("CadastroUsuarioUI");
            CadastroUsuarioUIController controller = (CadastroUsuarioUIController) App.getController();
            controller.setUsuario(usuario);
        }
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("AcolhimentoUI");
    }
}
