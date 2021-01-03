package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.InMemoryAcolhimentoDAO;
import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.Acolhimento.AcolhimentoDAO;
import br.edu.ifsp.domain.usecases.Acolhimento.AlterarServidorAcolhimentoUC;
import br.edu.ifsp.domain.usecases.Acolhimento.CadastrarServidorAcolhimentoUC;

public class Main {

    private static CadastrarServidorAcolhimentoUC cadastrarServidorAcolhimentoUC;
    private static AlterarServidorAcolhimentoUC alterarServidorAcolhimentoUC;

    public static void main(String[] args) {
        configureInjection();

        Acolhimento acolhimento1 = new Acolhimento("SC3009165", "Henrique de Sousa", "22222222222");
        Acolhimento acolhimento2 = new Acolhimento("SC300902X", "Felipe de Sousa", "11111111111");

        System.out.println("Cadastrando");
        cadastrarServidorAcolhimentoUC.cadastraServidor(acolhimento1);
        cadastrarServidorAcolhimentoUC.cadastraServidor(acolhimento2);
        System.out.println("OK");

        acolhimento1.setNome("Diego Souza");
        System.out.println("Atualizando");
        alterarServidorAcolhimentoUC.alteraServidor(acolhimento1);
        System.out.println("OK");

    }

    private static void configureInjection() {
        AcolhimentoDAO acolhimentoDAO = new InMemoryAcolhimentoDAO();
        cadastrarServidorAcolhimentoUC = new CadastrarServidorAcolhimentoUC(acolhimentoDAO);
        alterarServidorAcolhimentoUC = new AlterarServidorAcolhimentoUC(acolhimentoDAO);
    }
}
