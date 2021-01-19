package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.entities.Docente;
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

public class CadastroLinhaCuidadoUIController {
    @FXML
    private TextField txtNome;
    @FXML
    private TextArea txtDesc;
    @FXML
    private ComboBox<Acao> cbAcao;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;

    private LinhaCuidado linhaCuidado;

    @FXML
    private void initialize(){
        cbAcao.setItems(FXCollections.observableArrayList(buscarAcaoUC.findAll()));
    }

    public void salvarOuAtualizar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();

        if(linhaCuidado.getId() == null){
            cadastrarLinhaCuidadoUC.cadastraLinhaCuidado(linhaCuidado);
        }else{
            alterarLinhaCuidadoUC.alteraLinhaCuidado(linhaCuidado);
        }
        App.setRoot("ViewLinhaCuidadoUI");
    }

    private void getEntityFromView() {
        if (linhaCuidado == null) {
            linhaCuidado = new LinhaCuidado();
        }
        linhaCuidado.setNome(txtNome.getText());
        linhaCuidado.setDescricao(txtDesc.getText());
        linhaCuidado.setAcao(cbAcao.getValue());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewLinhaCuidadoUI");
    }

    public void setLinhaCuidado(LinhaCuidado linhaCuidado) {
        if (linhaCuidado == null)
            throw new IllegalArgumentException("Linha cuidado can not be null.");

        this.linhaCuidado = linhaCuidado;
        setEntityIntoView();
    }

    private void setEntityIntoView() {
        txtNome.setText(linhaCuidado.getNome());
        txtDesc.setText(linhaCuidado.getDescricao());
        cbAcao.getSelectionModel().select(linhaCuidado.getAcao());
    }
}
