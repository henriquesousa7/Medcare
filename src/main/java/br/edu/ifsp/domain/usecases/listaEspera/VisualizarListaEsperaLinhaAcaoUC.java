package br.edu.ifsp.domain.usecases.listaEspera;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.entities.LinhaAcao;
import br.edu.ifsp.domain.entities.Status;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.atendimento.AtendimentoDAO;

import java.util.ArrayList;
import java.util.List;

public class VisualizarListaEsperaLinhaAcaoUC {
    private AtendimentoDAO atendimentoDAO;

    public VisualizarListaEsperaLinhaAcaoUC(AtendimentoDAO atendimentoDAO) {
        this.atendimentoDAO = atendimentoDAO;
    }

    public List<Usuario> geraListaEspera(LinhaAcao linhaAcao){
        List<Usuario> usuarios = new ArrayList<>();

        for (Atendimento atendimento : atendimentoDAO.findAll()) {
            if(atendimento.getUsuarioLinhaAcao().getLinhaAcao().equals(linhaAcao)){
                if(atendimento.getStatus().equals(Status.AGUARDANDO))
                    usuarios.add(atendimento.getUsuarioLinhaAcao().getUsuario());
            }
        }
        return usuarios;
    }
}
