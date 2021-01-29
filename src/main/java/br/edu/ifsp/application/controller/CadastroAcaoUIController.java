package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.entities.Mantenedor;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.*;

public class CadastroAcaoUIController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextArea txtDesc;
    @FXML
    private ComboBox<Docente> cbResponsavel;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;

    private Acao acao;
    private Mantenedor mantenedor;

    @FXML
    private void initialize(){
        cbResponsavel.setItems(FXCollections.observableArrayList(buscarDocenteUC.findAll()));
    }

    public void salvarOuAtualizar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();

        if(acao.getId() == null){
            cadastrarAcaoUC.cadastraLinhaAcao(acao,acao.getResponsavel().getProntuario());
        }else{
            alterarAcaoUC.alteraLinhaAcao(acao);
        }
        App.setRoot("ViewAcaoUI");
    }

    private void getEntityFromView() {
        if (acao == null) {
            acao = new Acao();
        }
        acao.setNome(txtNome.getText());
        acao.setDescricao(txtDesc.getText());
        acao.setResponsavel(cbResponsavel.getValue());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewAcaoUI");
        ViewAcaoUIController controller = (ViewAcaoUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    public void setAcaoMantenedor(Acao acao, Mantenedor mantenedor) {
        if (acao != null) {
            this.acao = acao;
            setEntityIntoView();
        }
        this.mantenedor = mantenedor;
    }

    private void setEntityIntoView() {
        txtNome.setText(acao.getNome());
        txtDesc.setText(acao.getDescricao());
        cbResponsavel.getSelectionModel().select(acao.getResponsavel());
    }
}
