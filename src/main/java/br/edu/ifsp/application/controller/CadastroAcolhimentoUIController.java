package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Mantenedor;
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

    private Acolhimento acolhimento;
    private Mantenedor mantenedor;

    public void salvarOuAtualizar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        boolean newAcolhimento = buscarAcolhimentoUC.findOne(acolhimento.getProntuario()).isEmpty();

        if(newAcolhimento){
            cadastrarServidorAcolhimentoUC.cadastraServidor(acolhimento);
        }else{
            alterarServidorAcolhimentoUC.alteraServidor(acolhimento);
        }
        App.setRoot("ViewAcolhimentoUI");
        ViewAcolhimentoUIController controller = (ViewAcolhimentoUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    public void setAcolhimentoMantenedor(Acolhimento acolhimento, Mantenedor mantenedor) {
        if (acolhimento != null) {
            this.acolhimento = acolhimento;
            setEntityIntoView();

            txtProntuario.setDisable(true);
            txtNome.setDisable(true);
            txtCPF.setDisable(true);
        }
        this.mantenedor = mantenedor;
    }

    private void getEntityFromView() {
        if (acolhimento == null) {
            acolhimento = new Acolhimento();
        }
        acolhimento.setProntuario(Integer.valueOf(txtProntuario.getText().trim()));
        acolhimento.setNome(txtNome.getText());
        acolhimento.setTelefone(txtTelefone.getText());
        acolhimento.setEmail(txtEmail.getText());
        acolhimento.setCpf(txtCPF.getText());
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
        ViewAcolhimentoUIController controller = (ViewAcolhimentoUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }
}
