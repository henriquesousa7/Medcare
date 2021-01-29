package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.entities.Mantenedor;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.*;

public class CadastroDiscenteUIController {
    @FXML
    private TextField txtProntuario;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private ComboBox<Acao> cbAcao;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;

    private Discente discente;
    private Mantenedor mantenedor;

    @FXML
    private void initialize(){
        cbAcao.setItems(FXCollections.observableArrayList(buscarAcaoUC.findAll()));
    }

    public void salvarOuAtualizar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        boolean newDiscente = buscarDiscenteUC.findOne(discente.getProntuario()).isEmpty();

        if(newDiscente){
            cadastrarDiscenteUC.cadastraDiscente(discente, discente.getAcao().getId());
        }else{
            alterarDadosDiscenteUC.alterarDiscente(discente, discente.getAcao().getId());
        }
        App.setRoot("ViewDiscenteUI");
        ViewDiscenteUIController controller = (ViewDiscenteUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    private void getEntityFromView() {
        if (discente == null) {
            discente = new Discente();
        }
        discente.setProntuario(Integer.valueOf(txtProntuario.getText().trim()));
        discente.setNome(txtNome.getText());
        discente.setTelefone(txtTelefone.getText());
        discente.setEmail(txtEmail.getText());
        discente.setAcao(cbAcao.getValue());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewDiscenteUI");
        ViewDiscenteUIController controller = (ViewDiscenteUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    public void setDiscenteMantenedor(Discente discente, Mantenedor mantenedor) {
        if (discente != null) {
            this.discente = discente;
            setEntityIntoView();

            txtProntuario.setDisable(true);
            txtNome.setDisable(true);
        }
        this.mantenedor = mantenedor;
    }

    private void setEntityIntoView() {
        txtProntuario.setText(String.valueOf(discente.getProntuario()));
        txtNome.setText(discente.getNome());
        txtEmail.setText(discente.getEmail());
        txtTelefone.setText(discente.getTelefone());
        cbAcao.getSelectionModel().select(discente.getAcao());
    }
}
