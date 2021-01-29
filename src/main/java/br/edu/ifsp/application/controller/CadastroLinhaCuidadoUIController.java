package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.entities.Docente;
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
    private Mantenedor mantenedor;

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
        ViewLinhaCuidadoUIController controller = (ViewLinhaCuidadoUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
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
        ViewLinhaCuidadoUIController controller = (ViewLinhaCuidadoUIController) App.getController();
        controller.setSessionMantenedor(mantenedor);
    }

    public void setLinhaCuidadoMantenedor(LinhaCuidado linhaCuidado, Mantenedor mantenedor) {
        if (linhaCuidado != null) {
            this.linhaCuidado = linhaCuidado;
            setEntityIntoView();
        }
        this.mantenedor = mantenedor;
    }

    private void setEntityIntoView() {
        txtNome.setText(linhaCuidado.getNome());
        txtDesc.setText(linhaCuidado.getDescricao());
        cbAcao.getSelectionModel().select(linhaCuidado.getAcao());
    }
}
