package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.*;
import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.acolhimento.AcolhimentoDAO;
import br.edu.ifsp.domain.usecases.acolhimento.AlterarServidorAcolhimentoUC;
import br.edu.ifsp.domain.usecases.acolhimento.CadastrarServidorAcolhimentoUC;
import br.edu.ifsp.domain.usecases.discente.AlterarDadosDiscenteUC;
import br.edu.ifsp.domain.usecases.discente.CadastrarDiscenteUC;
import br.edu.ifsp.domain.usecases.discente.DiscenteDAO;
import br.edu.ifsp.domain.usecases.docente.AlterarDadosDocenteUC;
import br.edu.ifsp.domain.usecases.docente.BuscarDocenteUC;
import br.edu.ifsp.domain.usecases.docente.CadastrarNovoDocenteUC;
import br.edu.ifsp.domain.usecases.docente.DocenteDAO;
import br.edu.ifsp.domain.usecases.linhaAcao.AlterarLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.BuscarLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.CadastrarLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.LinhaAcaoDAO;
import br.edu.ifsp.domain.usecases.linhaCuidado.AlterarLinhaCuidadoUC;
import br.edu.ifsp.domain.usecases.linhaCuidado.BuscarLinhaCuidadoUC;
import br.edu.ifsp.domain.usecases.linhaCuidado.CadastrarLinhaCuidadoUC;
import br.edu.ifsp.domain.usecases.linhaCuidado.LinhaCuidadoDAO;
import br.edu.ifsp.domain.usecases.usuario.AlterarDadosUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.CadastrarUsuarioUC;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;

public class Main {

    private static CadastrarServidorAcolhimentoUC cadastrarServidorAcolhimentoUC;
    private static AlterarServidorAcolhimentoUC alterarServidorAcolhimentoUC;

    private static CadastrarNovoDocenteUC cadastrarNovoDocenteUC;
    private static AlterarDadosDocenteUC alterarDadosDocenteUC;
    private static BuscarDocenteUC buscarDocenteUC;

    private static CadastrarDiscenteUC cadastrarDiscenteUC;
    private static AlterarDadosDiscenteUC alterarDadosDiscenteUC;

    private static CadastrarLinhaCuidadoUC cadastrarLinhaCuidadoUC;
    private static AlterarLinhaCuidadoUC alterarLinhaCuidadoUC;
    private static BuscarLinhaCuidadoUC buscarLinhaCuidadoUC;

    private static CadastrarLinhaAcaoUC cadastrarLinhaAcaoUC;
    private static AlterarLinhaAcaoUC alterarLinhaAcaoUC;
    private static BuscarLinhaAcaoUC buscarLinhaAcaoUC;

    private static CadastrarUsuarioUC cadastrarUsuarioUC;
    private static AlterarDadosUsuarioUC alterarDadosUsuarioUC;

    public static void main(String[] args) {
        configureInjection();

        /* Servidor acolhimento */
        Acolhimento acolhimento1 = new Acolhimento("SC3009165", "Henrique de Sousa", "henrique@hotmail.com", "16992675413", "22222222222");
        Acolhimento acolhimento2 = new Acolhimento("SC300902X", "Felipe de Sousa", "felipe@hotmail.com", "16993456713", "11111111111");

        cadastrarServidorAcolhimentoUC.cadastraServidor(acolhimento1);
        cadastrarServidorAcolhimentoUC.cadastraServidor(acolhimento2);

        acolhimento1.setEmail("henrique@gmail.com");
        acolhimento1.setTelefone("16981625543");
        alterarServidorAcolhimentoUC.alteraServidor(acolhimento1);

        /* --------------------------------------------------------------------------------------------------------------------*/
        /* Docente*/
        Docente docente1 = new Docente(3008765, "Roberto Carlos", "roberto@hotmail.com", "16997865413");
        Docente docente2 = new Docente(3005555, "Fabio Silva", "fabio@yahoo.com.br", "11967852341");

        Integer doc1 = cadastrarNovoDocenteUC.salvarDocente(docente1);
        Integer doc2 = cadastrarNovoDocenteUC.salvarDocente(docente2);


        docente1.setEmail("roberto@yahoo.com.br");
        docente2.setTelefone("16954876132");
        alterarDadosDocenteUC.alterarDocente(docente1);
        alterarDadosDocenteUC.alterarDocente(docente2);

        /* --------------------------------------------------------------------------------------------------------------------*/
        /* Linha cuidado*/
        LinhaCuidado linhaCuidado1 = new LinhaCuidado(1, "Cárdio-Respiratória", "Doenças Cárdio-Respiratórias");
        LinhaCuidado linhaCuidado2 = new LinhaCuidado(2, "e Doenças Metabólicas", "Relacionados a Doenças Metabólicas");

        Integer lc1 = cadastrarLinhaCuidadoUC.cadastraLinhaCuidado(linhaCuidado1);
        Integer lc2 = cadastrarLinhaCuidadoUC.cadastraLinhaCuidado(linhaCuidado2);

        linhaCuidado2.setNome("Doenças Cerebrais");
        linhaCuidado2.setDescricao("Doenças relacionado ao cerebro");
        alterarLinhaCuidadoUC.alteraLinhaCuidado(linhaCuidado2);
        /* --------------------------------------------------------------------------------------------------------------------*/
        /* Linha acao*/
        Integer[] docentes = {doc1, doc2};
        LinhaAcao linhaAcao1 = new LinhaAcao(1, "Fisioterapia CardioVascular", "Fisioterapia CardioVascular DESC");
        LinhaAcao linhaAcao2 = new LinhaAcao(2, "Fisioterapia Respiratória", "Fisioterapia Respiratória DESC");

        Integer linhaAcaoId1 = cadastrarLinhaAcaoUC.cadastraLinhaAcao(linhaAcao1, lc1, docentes);
        Integer linhaAcaoId2 = cadastrarLinhaAcaoUC.cadastraLinhaAcao(linhaAcao2, lc2, docentes);

        linhaAcao1.setDescricao("Fisioterapia CardioVascular DESCRICAO");
        alterarLinhaAcaoUC.alteraLinhaAcao(linhaAcao1);
        /* --------------------------------------------------------------------------------------------------------------------*/
        /* Discente*/
        Discente discente1 = new Discente(3004213, "Alan Moreira", "alan@gmail.com", "16978342565");
        Discente discente2 = new Discente(3005675, "Renato Moreira", "renato@gmail.com", "11987652314");

        cadastrarDiscenteUC.cadastraDiscente(discente1, linhaAcaoId1);
        cadastrarDiscenteUC.cadastraDiscente(discente2, linhaAcaoId2);

        discente1.setEmail("alan.moreira@hotmail.com");
        alterarDadosDiscenteUC.alterarDiscente(discente1, linhaAcaoId2);
        /* --------------------------------------------------------------------------------------------------------------------*/
        /* Usuario*/
        Usuario usuario1 = new Usuario("12312312376", 1234, "Alfonso Oliveira", 'M', "1693456875",
                                "Rua Sete", "Diabetes");
        Usuario usuario2 = new Usuario("98798798776", 5678, "Lucas Oliveira", 'M', "1693458653",
                                "Rua Oito", "Braço quebrado");

        cadastrarUsuarioUC.cadastraUsuario(usuario1);
        cadastrarUsuarioUC.cadastraUsuario(usuario2);

        usuario2.setEndereco("Rua Dez");
        usuario2.setHistoricoMedico("Pressao alta");
        alterarDadosUsuarioUC.alteraUsuario(usuario2);
        /* --------------------------------------------------------------------------------------------------------------------*/

    }

    private static void configureInjection() {
        // Acolhimento
        AcolhimentoDAO acolhimentoDAO = new InMemoryAcolhimentoDAO();
        cadastrarServidorAcolhimentoUC = new CadastrarServidorAcolhimentoUC(acolhimentoDAO);
        alterarServidorAcolhimentoUC = new AlterarServidorAcolhimentoUC(acolhimentoDAO);

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

        // Usuario
        UsuarioDAO usuarioDAO = new InMemoryUsuarioDAO();
        cadastrarUsuarioUC = new CadastrarUsuarioUC(usuarioDAO);
        alterarDadosUsuarioUC = new AlterarDadosUsuarioUC(usuarioDAO);
    }
}
