package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.inmemory.*;
import br.edu.ifsp.application.repository.sqlite.DatabaseBuilder;
import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.acolhimento.AcolhimentoDAO;
import br.edu.ifsp.domain.usecases.acolhimento.AlterarServidorAcolhimentoUC;
import br.edu.ifsp.domain.usecases.acolhimento.BuscarAcolhimentoUC;
import br.edu.ifsp.domain.usecases.acolhimento.CadastrarServidorAcolhimentoUC;
import br.edu.ifsp.domain.usecases.agendamento.ConsultarAgendamentoUC;
import br.edu.ifsp.domain.usecases.atendimento.AgendarAtendimentoUC;
import br.edu.ifsp.domain.usecases.atendimento.AtendimentoDAO;
import br.edu.ifsp.domain.usecases.atendimento.BuscarAtendimentoUC;
import br.edu.ifsp.domain.usecases.discente.AlterarDadosDiscenteUC;
import br.edu.ifsp.domain.usecases.discente.BuscarDiscenteUC;
import br.edu.ifsp.domain.usecases.discente.CadastrarDiscenteUC;
import br.edu.ifsp.domain.usecases.discente.DiscenteDAO;
import br.edu.ifsp.domain.usecases.docente.AlterarDadosDocenteUC;
import br.edu.ifsp.domain.usecases.docente.BuscarDocenteUC;
import br.edu.ifsp.domain.usecases.docente.CadastrarNovoDocenteUC;
import br.edu.ifsp.domain.usecases.docente.DocenteDAO;
import br.edu.ifsp.domain.usecases.interconsulta.GerenciarInterConsultaUC;
import br.edu.ifsp.domain.usecases.interconsulta.InterConsultaDAO;
import br.edu.ifsp.domain.usecases.interconsulta.SolicitarInterConsultaUC;
import br.edu.ifsp.domain.usecases.linhaAcao.AlterarLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.BuscarLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.CadastrarLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.LinhaAcaoDAO;
import br.edu.ifsp.domain.usecases.linhaCuidado.AlterarLinhaCuidadoUC;
import br.edu.ifsp.domain.usecases.linhaCuidado.BuscarLinhaCuidadoUC;
import br.edu.ifsp.domain.usecases.linhaCuidado.CadastrarLinhaCuidadoUC;
import br.edu.ifsp.domain.usecases.linhaCuidado.LinhaCuidadoDAO;
import br.edu.ifsp.domain.usecases.listaEspera.VisualizarListaEsperaLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.relatorio.GerarRelatorioGeralUC;
import br.edu.ifsp.domain.usecases.relatorio.GerarRelatorioLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.usuario.AlterarDadosUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.CadastrarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;
import br.edu.ifsp.domain.usecases.usuarioLinhaAcao.BuscarUsuarioLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.usuarioLinhaAcao.CadastrarUsuarioLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.usuarioLinhaAcao.GerenciarUsuarioLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.usuarioLinhaAcao.UsuarioLinhaAcaoDAO;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private static CadastrarServidorAcolhimentoUC cadastrarServidorAcolhimentoUC;
    private static AlterarServidorAcolhimentoUC alterarServidorAcolhimentoUC;
    private static BuscarAcolhimentoUC buscarAcolhimentoUC;

    private static CadastrarNovoDocenteUC cadastrarNovoDocenteUC;
    private static AlterarDadosDocenteUC alterarDadosDocenteUC;
    private static BuscarDocenteUC buscarDocenteUC;

    private static CadastrarDiscenteUC cadastrarDiscenteUC;
    private static AlterarDadosDiscenteUC alterarDadosDiscenteUC;
    private static BuscarDiscenteUC buscarDiscenteUC;

    private static CadastrarLinhaCuidadoUC cadastrarLinhaCuidadoUC;
    private static AlterarLinhaCuidadoUC alterarLinhaCuidadoUC;
    private static BuscarLinhaCuidadoUC buscarLinhaCuidadoUC;

    private static CadastrarLinhaAcaoUC cadastrarLinhaAcaoUC;
    private static AlterarLinhaAcaoUC alterarLinhaAcaoUC;
    private static BuscarLinhaAcaoUC buscarLinhaAcaoUC;

    private static CadastrarUsuarioUC cadastrarUsuarioUC;
    private static AlterarDadosUsuarioUC alterarDadosUsuarioUC;
    private static BuscarUsuarioUC buscarUsuarioUC;

    private static CadastrarUsuarioLinhaAcaoUC cadastrarUsuarioLinhaAcaoUC;
    private static GerenciarUsuarioLinhaAcaoUC gerenciarUsuarioLinhaAcaoUC;
    private static BuscarUsuarioLinhaAcaoUC buscarUsuarioLinhaAcaoUC;

    private static AgendarAtendimentoUC agendarAtendimentoUC;
    private static BuscarAtendimentoUC buscarAtendimentoUC;

    private static SolicitarInterConsultaUC solicitarInterConsultaUC;
    private static GerenciarInterConsultaUC gerenciarInterConsultaUC;

    private static ConsultarAgendamentoUC consultarAgendamentoUC;

    private static GerarRelatorioGeralUC gerarRelatorioGeralUC;
    private static GerarRelatorioLinhaAcaoUC gerarRelatorioLinhaAcaoUC;

    private static VisualizarListaEsperaLinhaAcaoUC visualizarListaEsperaLinhaAcaoUC;

    public static void main(String[] args) {
        configureInjection();
        setupDatabase();
        App.main(args);
    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    private static void configureInjection() {
        // Acolhimento
        AcolhimentoDAO acolhimentoDAO = new InMemoryAcolhimentoDAO();
        cadastrarServidorAcolhimentoUC = new CadastrarServidorAcolhimentoUC(acolhimentoDAO);
        alterarServidorAcolhimentoUC = new AlterarServidorAcolhimentoUC(acolhimentoDAO);
        buscarAcolhimentoUC = new BuscarAcolhimentoUC(acolhimentoDAO);

        // Docente
        DocenteDAO docenteDAO = new InMemoryDocenteDAO();
        cadastrarNovoDocenteUC = new CadastrarNovoDocenteUC(docenteDAO);
        alterarDadosDocenteUC = new AlterarDadosDocenteUC(docenteDAO);
        buscarDocenteUC = new BuscarDocenteUC(docenteDAO);

        // Linha Cuidado
        LinhaCuidadoDAO linhaCuidadoDAO = new InMemoryLinhaCuidadoDAO();
        cadastrarLinhaCuidadoUC = new CadastrarLinhaCuidadoUC(linhaCuidadoDAO);
        alterarLinhaCuidadoUC = new AlterarLinhaCuidadoUC(linhaCuidadoDAO);
        buscarLinhaCuidadoUC = new BuscarLinhaCuidadoUC(linhaCuidadoDAO);

        // Linha Acao
        LinhaAcaoDAO linhaAcaoDAO = new InMemoryLinhaAcaoDAO();
        cadastrarLinhaAcaoUC = new CadastrarLinhaAcaoUC(linhaAcaoDAO, buscarDocenteUC, buscarLinhaCuidadoUC);
        alterarLinhaAcaoUC = new AlterarLinhaAcaoUC(linhaAcaoDAO);
        buscarLinhaAcaoUC = new BuscarLinhaAcaoUC(linhaAcaoDAO);

        // Discente
        DiscenteDAO discenteDAO = new InMemoryDiscenteDAO();
        cadastrarDiscenteUC = new CadastrarDiscenteUC(discenteDAO, buscarLinhaAcaoUC);
        alterarDadosDiscenteUC = new AlterarDadosDiscenteUC(discenteDAO, buscarLinhaAcaoUC);
        buscarDiscenteUC = new BuscarDiscenteUC(discenteDAO);

        // Usuario
        UsuarioDAO usuarioDAO = new InMemoryUsuarioDAO();
        cadastrarUsuarioUC = new CadastrarUsuarioUC(usuarioDAO);
        alterarDadosUsuarioUC = new AlterarDadosUsuarioUC(usuarioDAO);
        buscarUsuarioUC = new BuscarUsuarioUC(usuarioDAO);

        // Usuario a Linha de Acao
        UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO = new InMemoryUsuarioLinhaAcaoDAO();
        buscarUsuarioLinhaAcaoUC = new BuscarUsuarioLinhaAcaoUC(usuarioLinhaAcaoDAO);
        cadastrarUsuarioLinhaAcaoUC = new CadastrarUsuarioLinhaAcaoUC(usuarioLinhaAcaoDAO, buscarLinhaAcaoUC, buscarUsuarioUC, buscarAcolhimentoUC);
        gerenciarUsuarioLinhaAcaoUC = new GerenciarUsuarioLinhaAcaoUC(usuarioLinhaAcaoDAO, buscarUsuarioLinhaAcaoUC);

        // Atendimento
        AtendimentoDAO atendimentoDAO = new InMemoryAtendimentoDAO();
        agendarAtendimentoUC = new AgendarAtendimentoUC(atendimentoDAO, buscarUsuarioLinhaAcaoUC, buscarDiscenteUC);
        buscarAtendimentoUC = new BuscarAtendimentoUC(atendimentoDAO);

        // Interconsulta
        InterConsultaDAO interConsultaDAO = new InMemoryInterConsultaDAO();
        solicitarInterConsultaUC = new SolicitarInterConsultaUC(interConsultaDAO, buscarLinhaAcaoUC, buscarUsuarioUC, buscarDocenteUC);
        gerenciarInterConsultaUC = new GerenciarInterConsultaUC(interConsultaDAO);

        // Consultar agendamento
        consultarAgendamentoUC = new ConsultarAgendamentoUC(atendimentoDAO);

        // Gerar relatorio geral
        gerarRelatorioGeralUC = new GerarRelatorioGeralUC(atendimentoDAO);
        gerarRelatorioLinhaAcaoUC = new GerarRelatorioLinhaAcaoUC(atendimentoDAO);

        // Visualizar usuarios na lista de Espera
        visualizarListaEsperaLinhaAcaoUC = new VisualizarListaEsperaLinhaAcaoUC(atendimentoDAO);
    }
}
