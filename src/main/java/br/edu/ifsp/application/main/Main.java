package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.inmemory.*;
import br.edu.ifsp.application.repository.sqlite.*;
import br.edu.ifsp.application.view.App;
import br.edu.ifsp.domain.usecases.acolhimento.AcolhimentoDAO;
import br.edu.ifsp.domain.usecases.acolhimento.AlterarServidorAcolhimentoUC;
import br.edu.ifsp.domain.usecases.acolhimento.BuscarAcolhimentoUC;
import br.edu.ifsp.domain.usecases.acolhimento.CadastrarServidorAcolhimentoUC;
import br.edu.ifsp.domain.usecases.agendamento.ConsultarAgendamentoUC;
import br.edu.ifsp.domain.usecases.atendimento.AgendarAtendimentoUC;
import br.edu.ifsp.domain.usecases.atendimento.AlterarAtendimentoUC;
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
import br.edu.ifsp.domain.usecases.linhaAcao.AlterarAcaoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.BuscarAcaoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.CadastrarAcaoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.AcaoDAO;
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

public class Main {

    public static CadastrarServidorAcolhimentoUC cadastrarServidorAcolhimentoUC;
    public static AlterarServidorAcolhimentoUC alterarServidorAcolhimentoUC;
    public static BuscarAcolhimentoUC buscarAcolhimentoUC;

    public static CadastrarNovoDocenteUC cadastrarNovoDocenteUC;
    public static AlterarDadosDocenteUC alterarDadosDocenteUC;
    public static BuscarDocenteUC buscarDocenteUC;

    public static CadastrarDiscenteUC cadastrarDiscenteUC;
    public static AlterarDadosDiscenteUC alterarDadosDiscenteUC;
    public static BuscarDiscenteUC buscarDiscenteUC;

    public static CadastrarLinhaCuidadoUC cadastrarLinhaCuidadoUC;
    public static AlterarLinhaCuidadoUC alterarLinhaCuidadoUC;
    public static BuscarLinhaCuidadoUC buscarLinhaCuidadoUC;

    public static CadastrarAcaoUC cadastrarAcaoUC;
    public static AlterarAcaoUC alterarAcaoUC;
    public static BuscarAcaoUC buscarAcaoUC;

    public static CadastrarUsuarioUC cadastrarUsuarioUC;
    public static AlterarDadosUsuarioUC alterarDadosUsuarioUC;
    public static BuscarUsuarioUC buscarUsuarioUC;

    public static CadastrarUsuarioLinhaAcaoUC cadastrarUsuarioLinhaAcaoUC;
    public static GerenciarUsuarioLinhaAcaoUC gerenciarUsuarioLinhaAcaoUC;
    public static BuscarUsuarioLinhaAcaoUC buscarUsuarioLinhaAcaoUC;

    public static AgendarAtendimentoUC agendarAtendimentoUC;
    public static BuscarAtendimentoUC buscarAtendimentoUC;
    public static AlterarAtendimentoUC alterarAtendimentoUC;

    public static SolicitarInterConsultaUC solicitarInterConsultaUC;
    public static GerenciarInterConsultaUC gerenciarInterConsultaUC;

    public static ConsultarAgendamentoUC consultarAgendamentoUC;

    public static GerarRelatorioGeralUC gerarRelatorioGeralUC;
    public static GerarRelatorioLinhaAcaoUC gerarRelatorioLinhaAcaoUC;

    public static VisualizarListaEsperaLinhaAcaoUC visualizarListaEsperaLinhaAcaoUC;

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
        AcolhimentoDAO acolhimentoDAO = new SqliteAcolhimentoDAO();
        cadastrarServidorAcolhimentoUC = new CadastrarServidorAcolhimentoUC(acolhimentoDAO);
        alterarServidorAcolhimentoUC = new AlterarServidorAcolhimentoUC(acolhimentoDAO);
        buscarAcolhimentoUC = new BuscarAcolhimentoUC(acolhimentoDAO);

        // Docente
        DocenteDAO docenteDAO = new SqliteDocenteDAO();
        cadastrarNovoDocenteUC = new CadastrarNovoDocenteUC(docenteDAO);
        alterarDadosDocenteUC = new AlterarDadosDocenteUC(docenteDAO);
        buscarDocenteUC = new BuscarDocenteUC(docenteDAO);

        // Linha Cuidado
        LinhaCuidadoDAO linhaCuidadoDAO = new SqliteLinhaCuidadoDAO();
        cadastrarLinhaCuidadoUC = new CadastrarLinhaCuidadoUC(linhaCuidadoDAO);
        alterarLinhaCuidadoUC = new AlterarLinhaCuidadoUC(linhaCuidadoDAO);
        buscarLinhaCuidadoUC = new BuscarLinhaCuidadoUC(linhaCuidadoDAO);

        // Linha Acao
        AcaoDAO acaoDAO = new SqliteAcaoDAO();
        cadastrarAcaoUC = new CadastrarAcaoUC(acaoDAO, buscarDocenteUC);
        alterarAcaoUC = new AlterarAcaoUC(acaoDAO);
        buscarAcaoUC = new BuscarAcaoUC(acaoDAO);

        // Discente
        DiscenteDAO discenteDAO = new SQliteDiscenteDAO();
        cadastrarDiscenteUC = new CadastrarDiscenteUC(discenteDAO, buscarAcaoUC);
        alterarDadosDiscenteUC = new AlterarDadosDiscenteUC(discenteDAO, buscarAcaoUC);
        buscarDiscenteUC = new BuscarDiscenteUC(discenteDAO);

        // Usuario
        UsuarioDAO usuarioDAO = new SqliteUsuarioDAO();
        cadastrarUsuarioUC = new CadastrarUsuarioUC(usuarioDAO);
        alterarDadosUsuarioUC = new AlterarDadosUsuarioUC(usuarioDAO);
        buscarUsuarioUC = new BuscarUsuarioUC(usuarioDAO);

        // Usuario a Linha de Acao
        UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO = new SqliteUsuarioAcaoDAO();
        buscarUsuarioLinhaAcaoUC = new BuscarUsuarioLinhaAcaoUC(usuarioLinhaAcaoDAO);
        cadastrarUsuarioLinhaAcaoUC = new CadastrarUsuarioLinhaAcaoUC(usuarioLinhaAcaoDAO, buscarAcaoUC, buscarUsuarioUC, buscarAcolhimentoUC);
        gerenciarUsuarioLinhaAcaoUC = new GerenciarUsuarioLinhaAcaoUC(usuarioLinhaAcaoDAO, buscarUsuarioLinhaAcaoUC);

        // Atendimento
        AtendimentoDAO atendimentoDAO = new SqliteAtendimentoDAO();
        agendarAtendimentoUC = new AgendarAtendimentoUC(atendimentoDAO);
        alterarAtendimentoUC = new AlterarAtendimentoUC(atendimentoDAO);
        buscarAtendimentoUC = new BuscarAtendimentoUC(atendimentoDAO);

        // Interconsulta
        InterConsultaDAO interConsultaDAO = new InMemoryInterConsultaDAO();
        solicitarInterConsultaUC = new SolicitarInterConsultaUC(interConsultaDAO, buscarAtendimentoUC, buscarUsuarioUC, buscarDocenteUC);
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
