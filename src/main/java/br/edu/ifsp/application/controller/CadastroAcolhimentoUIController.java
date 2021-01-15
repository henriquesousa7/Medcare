package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acolhimento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.buscarAcolhimentoUC;
import static br.edu.ifsp.application.main.Main.cadastrarServidorAcolhimentoUC;
import static br.edu.ifsp.application.main.Main.alterarServidorAcolhimentoUC;

public class CadastroAcolhimentoUIController {

    @FXML
    private TextField txtProntuario;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtCPF;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;

    private Acolhimento acolhimento = null;

    public void salvarOuAtualizar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        boolean newAcolhimento = buscarAcolhimentoUC.findOne(acolhimento.getProntuario()).isEmpty();

        if(newAcolhimento){
            cadastrarServidorAcolhimentoUC.cadastraServidor(acolhimento);
        }else{
            alterarServidorAcolhimentoUC.alteraServidor(acolhimento);
        }
        App.setRoot("ViewAcolhimentoUI");
    }

    public void setAcolhimento(Acolhimento acolhimento) {
        if (acolhimento == null)
            throw new IllegalArgumentException("Acolhimento can not be null.");

        this.acolhimento = acolhimento;
        setEntityIntoView();

        txtProntuario.setDisable(true);
        txtNome.setDisable(true);
        txtCPF.setDisable(true);
    }

    private void getEntityFromView() {
        if (acolhimento == null) {
            acolhimento = new Acolhimento(Integer.valueOf(txtProntuario.getText()), txtNome.getText(), txtEmail.getText(), txtTelefone.getText(), txtCPF.getText());
        }
        acolhimento.setTelefone(txtTelefone.getText());
        acolhimento.setEmail(txtEmail.getText());
    }

    private void setEntityIntoView() {
        txtProntuario.setText(String.valueOf(acolhimento.getProntuario()));
        txtNome.setText(acolhimento.getNome());
        txtEmail.setText(acolhimento.getEmail());
        txtTelefone.setText(acolhimento.getTelefone());
        txtCPF.setText(acolhimento.getCpf());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewAcolhimentoUI");
    }
}
