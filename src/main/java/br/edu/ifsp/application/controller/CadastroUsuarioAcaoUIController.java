package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.usuarioLinhaAcao.GerenciarUsuarioLinhaAcaoUC;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.*;

public class CadastroUsuarioAcaoUIController {

    @FXML
    private ComboBox<Usuario> cbUsuario;
    @FXML
    private ComboBox<Acao> cbAcao;
    @FXML
    private ComboBox<Status> cbStatus;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;
    
    private UsuarioLinhaAcao usuarioLinhaAcao;
    private Acolhimento acolhimento;

    @FXML
    private void initialize(){
        cbUsuario.setItems(FXCollections.observableArrayList(buscarUsuarioUC.findAll()));
        cbAcao.setItems(FXCollections.observableArrayList(buscarAcaoUC.findAll()));
        cbStatus.setItems(FXCollections.observableArrayList(Status.values()));
    }
    
    public void salvarOuAtualizar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();

        if(usuarioLinhaAcao.getId() == null){
            cadastrarUsuarioLinhaAcaoUC.cadastraUsuarioLinhaAcao(usuarioLinhaAcao);
        }else{
            gerenciarUsuarioLinhaAcaoUC.gerenciaUsuarioLinhaAcao(usuarioLinhaAcao);
        }
        App.setRoot("ViewUsuarioAcaoUI");
        ViewUsuarioAcaoUIController controller = (ViewUsuarioAcaoUIController) App.getController();
        controller.setAcolhimentoSession(acolhimento);
    }

    private void getEntityFromView() {
        if (usuarioLinhaAcao == null) {
            usuarioLinhaAcao = new UsuarioLinhaAcao();
        }
        usuarioLinhaAcao.setAcao(cbAcao.getValue());
        usuarioLinhaAcao.setUsuario(cbUsuario.getValue());
        usuarioLinhaAcao.setStatus(cbStatus.getValue());
        usuarioLinhaAcao.setResponsavelAcolhimento(acolhimento);
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewUsuarioAcaoUI");
        ViewUsuarioAcaoUIController controller = (ViewUsuarioAcaoUIController) App.getController();
        controller.setAcolhimentoSession(acolhimento);
    }

    public void setUsuarioLinhaAcaoEAcolhimento(UsuarioLinhaAcao usuarioLinhaAcao, Acolhimento acolhimento) {
        if(usuarioLinhaAcao != null){
            this.usuarioLinhaAcao = usuarioLinhaAcao;
            setEntityIntoView();
            cbUsuario.setDisable(true);
        }
        this.acolhimento = acolhimento;
    }

    private void setEntityIntoView() {
        cbUsuario.getSelectionModel().select(usuarioLinhaAcao.getUsuario());
        cbAcao.getSelectionModel().select(usuarioLinhaAcao.getAcao());
        cbStatus.getSelectionModel().select(usuarioLinhaAcao.getStatus());
    }
}
