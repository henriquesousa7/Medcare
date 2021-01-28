package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static br.edu.ifsp.application.main.Main.*;

public class ViewAtendimentoDiscenteUIController {
    @FXML
    private TableView<Atendimento> tableView;
    @FXML
    private TableColumn<Atendimento, Integer> cID;
    @FXML
    private TableColumn<Atendimento, UsuarioLinhaAcao> cUsuarioAcao;
    @FXML
    private TableColumn<Atendimento, LocalDateTime> cData;
    @FXML
    private TableColumn<Atendimento, Status> cStatus;
    @FXML
    private TableColumn<Atendimento, Discente> cDiscente;

    private ObservableList<Atendimento> tableData;
    private Discente discente;
    private String mode;
    private Acao acao;

    @FXML
    private void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValueSources();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableView.setItems(tableData);
    }

    private void bindColumnsToValueSources() {
        cID.setCellValueFactory(new PropertyValueFactory<>("id"));
        cUsuarioAcao.setCellValueFactory(new PropertyValueFactory<>("usuarioLinhaAcao"));
        cData.setCellValueFactory(new PropertyValueFactory<>("data"));
        cStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        cDiscente.setCellValueFactory(new PropertyValueFactory<>("discenteResponsavel"));
    }

    private void loadDataAndShow() {
        List<Atendimento> atendimentos = null;

        if(mode.equals("CONSULTA")) {
            atendimentos = consultarAgendamentoUC.consultaAgendamento(discente);
        } else if(mode.equals("RELATORIOGR")) {
            atendimentos = gerarRelatorioGeralUC.geraRelatorioGeral(discente);
        } else if(mode.equals("RELATORIOLA")) {
            atendimentos = gerarRelatorioLinhaAcaoUC.geraRelatorioLinhaAcao(acao, discente);
        } else {
            atendimentos = visualizarListaEsperaLinhaAcaoUC.geraListaEspera(acao, discente);
        }
        tableData.clear();
        tableData.addAll(atendimentos);
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        App.setRoot("DiscenteUI");
        DiscenteUIController controller = (DiscenteUIController) App.getController();
        controller.setSessionDiscente(discente);
    }

    public void setSessionDiscenteMode(Discente discente, String mode) {
        if (discente == null)
            throw new IllegalArgumentException("Discente can not be null.");

        this.discente = discente;
        this.mode = mode;
        loadDataAndShow();
    }

    public void setSessionDiscenteModeAcao(Discente discente, String mode, Acao acao) {
        if (discente == null)
            throw new IllegalArgumentException("Discente can not be null.");

        if (acao == null)
            throw new IllegalArgumentException("Acao can not be null.");

        this.discente = discente;
        this.mode = mode;
        this.acao = acao;
        loadDataAndShow();
    }
}
