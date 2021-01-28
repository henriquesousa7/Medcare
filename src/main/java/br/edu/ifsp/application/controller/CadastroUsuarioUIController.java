package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Usuario;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.*;

public class CadastroUsuarioUIController {
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSUS;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtHistorico;
    @FXML
    private ComboBox<String> cbSexo;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;

    private Usuario usuario;
    private Acolhimento acolhimento;

    @FXML
    private void initialize(){
        cbSexo.setItems(FXCollections.observableArrayList("M", "F"));
    }

    public void salvarOuAtualizar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        boolean newUsuario = buscarUsuarioUC.findOne(usuario.getCpf()).isEmpty();

        if(newUsuario){
            cadastrarUsuarioUC.cadastraUsuario(usuario);
        }else{
            alterarDadosUsuarioUC.alteraUsuario(usuario);
        }
        App.setRoot("ViewUsuarioUI");
        ViewUsuarioUIController controller = (ViewUsuarioUIController) App.getController();
        controller.setAcolhimentoSession(acolhimento);
    }

    private void getEntityFromView() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        usuario.setCpf(txtCPF.getText());
        usuario.setNome(txtNome.getText());
        usuario.setNumeroCartaoSUS(Integer.valueOf(txtSUS.getText()));
        usuario.setSexo(cbSexo.getValue().charAt(0));
        usuario.setTelefone(txtTelefone.getText());
        usuario.setEndereco(txtEndereco.getText());
        usuario.setHistoricoMedico(txtHistorico.getText());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewUsuarioUI");
        ViewUsuarioUIController controller = (ViewUsuarioUIController) App.getController();
        controller.setAcolhimentoSession(acolhimento);
    }

    public void setUsuarioEAcolhimento(Usuario usuario, Acolhimento acolhimento) {
        if (usuario != null) {
            this.usuario = usuario;
            setEntityIntoView();

            txtCPF.setDisable(true);
            txtNome.setDisable(true);
            txtSUS.setDisable(true);
            cbSexo.setDisable(true);
        }
        this.acolhimento = acolhimento;
    }

    private void setEntityIntoView() {
        txtCPF.setText(usuario.getCpf());
        txtNome.setText(usuario.getNome());
        txtSUS.setText(String.valueOf(usuario.getNumeroCartaoSUS()));
        cbSexo.getSelectionModel().select(String.valueOf(usuario.getSexo()));
        txtTelefone.setText(usuario.getTelefone());
        txtEndereco.setText(usuario.getEndereco());
        txtHistorico.setText(usuario.getHistoricoMedico());
    }
}
