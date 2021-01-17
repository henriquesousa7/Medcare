package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.LinhaAcao;
import br.edu.ifsp.domain.entities.LinhaCuidado;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.*;
import static br.edu.ifsp.application.main.Main.alterarLinhaCuidadoUC;

public class CadastroLinhaAcaoUIController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextArea txtDesc;
    @FXML
    private ComboBox<LinhaCuidado> cbLinhaCuidado;
    @FXML
    private ComboBox<Docente> cbResponsavel;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;

    private LinhaAcao linhaAcao;

    @FXML
    private void initialize(){
        cbLinhaCuidado.setItems(FXCollections.observableArrayList(buscarLinhaCuidadoUC.findAll()));
        cbResponsavel.setItems(FXCollections.observableArrayList(buscarDocenteUC.findAll()));
    }

    public void salvarOuAtualizar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();

        if(linhaAcao.getId() == null){
            cadastrarLinhaAcaoUC.cadastraLinhaAcao(linhaAcao, linhaAcao.getLinhaCuidado().getId(), linhaAcao.getResponsavel().getProntuario());
        }else{
            alterarLinhaAcaoUC.alteraLinhaAcao(linhaAcao);
        }
        App.setRoot("ViewLinhaAcaoUI");
    }

    private void getEntityFromView() {
        if (linhaAcao == null) {
            linhaAcao = new LinhaAcao();
        }
        linhaAcao.setNome(txtNome.getText());
        linhaAcao.setDescricao(txtDesc.getText());
        linhaAcao.setLinhaCuidado(cbLinhaCuidado.getValue());
        linhaAcao.setResponsavel(cbResponsavel.getValue());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewLinhaAcaoUI");
    }

    public void setLinhaAcao(LinhaAcao linhaAcao) {
        if (linhaAcao == null)
            throw new IllegalArgumentException("Linha acao can not be null.");

        this.linhaAcao = linhaAcao;
        setEntityIntoView();
    }

    private void setEntityIntoView() {
        txtNome.setText(linhaAcao.getNome());
        txtDesc.setText(linhaAcao.getDescricao());
        cbLinhaCuidado.getSelectionModel().select(linhaAcao.getLinhaCuidado().getId());
        cbResponsavel.getSelectionModel().select(linhaAcao.getResponsavel());
    }
}
