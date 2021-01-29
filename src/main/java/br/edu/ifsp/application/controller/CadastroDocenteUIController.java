package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Mantenedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.*;

public class CadastroDocenteUIController {
    @FXML
    private TextField txtProntuario;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;

    private Docente docente;
    private Mantenedor mantenedor;

    public void salvarOuAtualizar(ActionEvent actionEvent) throws IOException {
        getEntityFromView();
        boolean newDocente = buscarDocenteUC.findOne(docente.getProntuario()).isEmpty();

        if(newDocente){
            cadastrarNovoDocenteUC.salvarDocente(docente);
        }else{
            alterarDadosDocenteUC.alterarDocente(docente);
        }
        App.setRoot("ViewDocenteUI");
        ViewDocenteUIController controller = (ViewDocenteUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    private void getEntityFromView() {
        if (docente == null) {
            docente = new Docente();
        }
        docente.setProntuario(Integer.valueOf(txtProntuario.getText().trim()));
        docente.setNome(txtNome.getText());
        docente.setTelefone(txtTelefone.getText());
        docente.setEmail(txtEmail.getText());
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("ViewDocenteUI");
        ViewDocenteUIController controller = (ViewDocenteUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    public void setDocenteMantenedor(Docente docente, Mantenedor mantenedor) {
        if (docente != null) {
            this.docente = docente;
            setEntityIntoView();

            txtProntuario.setDisable(true);
            txtNome.setDisable(true);
        }
        this.mantenedor = mantenedor;
    }

    private void setEntityIntoView() {
        txtProntuario.setText(String.valueOf(docente.getProntuario()));
        txtNome.setText(docente.getNome());
        txtEmail.setText(docente.getEmail());
        txtTelefone.setText(docente.getTelefone());
    }
}
