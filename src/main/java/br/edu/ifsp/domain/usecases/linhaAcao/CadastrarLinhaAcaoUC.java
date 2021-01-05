package br.edu.ifsp.domain.usecases.linhaAcao;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.LinhaAcao;
import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.usecases.docente.BuscarDocenteUC;
import br.edu.ifsp.domain.usecases.linhaCuidado.BuscarLinhaCuidadoUC;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CadastrarLinhaAcaoUC {
    private LinhaAcaoDAO linhaAcaoDAO;
    private BuscarDocenteUC buscarDocenteUC;
    private BuscarLinhaCuidadoUC buscarLinhaCuidadoUC;

    public CadastrarLinhaAcaoUC(LinhaAcaoDAO linhaAcaoDAO, BuscarDocenteUC buscarDocenteUC, BuscarLinhaCuidadoUC buscarLinhaCuidadoUC) {
        this.linhaAcaoDAO = linhaAcaoDAO;
        this.buscarDocenteUC = buscarDocenteUC;
        this.buscarLinhaCuidadoUC = buscarLinhaCuidadoUC;
    }

    public Integer cadastraLinhaAcao(LinhaAcao linhaAcao, Integer linhaCuidadoID, Integer[] prontuarioDocentes){
        List<Docente> docentesList = new ArrayList<>();

        if (linhaCuidadoID == null || prontuarioDocentes == null)
            throw new IllegalArgumentException("Id da linha de cuidado e/ou prontuario do Docente nao pode ser nulo");

        LinhaCuidado linhaCuidado = buscarLinhaCuidadoUC.findOne(linhaCuidadoID).
                orElseThrow(() -> new EntityNotFoundException("Linha cuidado nao existe"));

        for (Integer prontuarioDocente : prontuarioDocentes) {
            Docente docente = buscarDocenteUC.findOne(prontuarioDocente).
                    orElseThrow(() -> new EntityNotFoundException("Docente nao existe"));
            docentesList.add(docente);
        }

        linhaAcao.setLinhaCuidado(linhaCuidado);
        linhaAcao.setResponsaveis(docentesList);

        return linhaAcaoDAO.create(linhaAcao);
    }
}
