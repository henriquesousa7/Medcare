package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.InMemoryAcolhimentoDAO;
import br.edu.ifsp.application.repository.InMemoryDocenteDAO;
import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.usecases.acolhimento.AcolhimentoDAO;
import br.edu.ifsp.domain.usecases.acolhimento.AlterarServidorAcolhimentoUC;
import br.edu.ifsp.domain.usecases.acolhimento.CadastrarServidorAcolhimentoUC;
import br.edu.ifsp.domain.usecases.docente.AlterarDadosDocente;
import br.edu.ifsp.domain.usecases.docente.CadastrarNovoDocenteUC;
import br.edu.ifsp.domain.usecases.docente.DocenteDAO;

public class Main {

    private static CadastrarServidorAcolhimentoUC cadastrarServidorAcolhimentoUC;
    private static AlterarServidorAcolhimentoUC alterarServidorAcolhimentoUC;

    private static CadastrarNovoDocenteUC cadastrarNovoDocenteUC;
    private static AlterarDadosDocente alterarDadosDocente;

    public static void main(String[] args) {
        configureInjection();

        /* Servidor acolhimento */
        Acolhimento acolhimento1 = new Acolhimento("SC3009165", "Henrique de Sousa", "22222222222");
        Acolhimento acolhimento2 = new Acolhimento("SC300902X", "Felipe de Sousa", "11111111111");

        System.out.println("Cadastrando Acolhimento");
        cadastrarServidorAcolhimentoUC.cadastraServidor(acolhimento1);
        cadastrarServidorAcolhimentoUC.cadastraServidor(acolhimento2);
        System.out.println("OK");

        System.out.println("Atualizando acolhimento");

        alterarServidorAcolhimentoUC.alteraServidor(acolhimento1);
        System.out.println("OK");
        /* --------------------------------------------------------------------------------------------------------------------*/
        /* Docente*/
        Docente docente1 = new Docente(3008765, "Roberto Carlos");
        Docente docente2 = new Docente(3005555, "Fabio Silva");

        System.out.println("Cadastrando Docente");
        cadastrarNovoDocenteUC.salvarDocente(docente1);
        cadastrarNovoDocenteUC.salvarDocente(docente2);
        System.out.println("OK");

        System.out.println("Alterando dados docente");

        alterarDadosDocente.alterarDocente(docente1);
        alterarDadosDocente.alterarDocente(docente2);
        System.out.println("OK");
        /* --------------------------------------------------------------------------------------------------------------------*/

    }

    private static void configureInjection() {
        AcolhimentoDAO acolhimentoDAO = new InMemoryAcolhimentoDAO();
        cadastrarServidorAcolhimentoUC = new CadastrarServidorAcolhimentoUC(acolhimentoDAO);
        alterarServidorAcolhimentoUC = new AlterarServidorAcolhimentoUC(acolhimentoDAO);

        DocenteDAO docenteDAO = new InMemoryDocenteDAO();
        cadastrarNovoDocenteUC = new CadastrarNovoDocenteUC(docenteDAO);
        alterarDadosDocente = new AlterarDadosDocente(docenteDAO);
    }
}
