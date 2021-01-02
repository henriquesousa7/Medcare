package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.InMemoryAcolhimentoDAO;
import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.Acolhimento.AcolhimentoDAO;
import br.edu.ifsp.domain.usecases.Acolhimento.CadastrarServidorAcolhimentoUC;

public class Main {

    private static CadastrarServidorAcolhimentoUC cadastrarServidorAcolhimentoUC;

    public static void main(String[] args) {
        configureInjection();

        Acolhimento acolhimento1 = new Acolhimento("SC3009165", "Henrique de Sousa", "22222222222");
        Acolhimento acolhimento2 = new Acolhimento("SC300902X", "Felipe de Sousa", "11111111111");

        cadastrarServidorAcolhimentoUC.cadastraServidor(acolhimento1);
        cadastrarServidorAcolhimentoUC.cadastraServidor(acolhimento2);

    }

    private static void configureInjection() {
        AcolhimentoDAO acolhimentoDAO = new InMemoryAcolhimentoDAO();
        cadastrarServidorAcolhimentoUC = new CadastrarServidorAcolhimentoUC(acolhimentoDAO);
    }
}
