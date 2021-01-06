package br.edu.ifsp.domain.usecases.usuarioLinhaAcao;

import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.acolhimento.BuscarAcolhimentoUC;
import br.edu.ifsp.domain.usecases.linhaAcao.BuscarLinhaAcaoUC;
import br.edu.ifsp.domain.usecases.usuario.BuscarUsuarioUC;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class CadastrarUsuarioLinhaAcaoUC {
    private UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO;
    private BuscarLinhaAcaoUC buscarLinhaAcaoUC;
    private BuscarUsuarioUC buscarUsuarioUC;
    private BuscarAcolhimentoUC buscarAcolhimentoUC;

    public CadastrarUsuarioLinhaAcaoUC(UsuarioLinhaAcaoDAO usuarioLinhaAcaoDAO, BuscarLinhaAcaoUC buscarLinhaAcaoUC,
                                       BuscarUsuarioUC buscarUsuarioUC, BuscarAcolhimentoUC buscarAcolhimentoUC) {
        this.usuarioLinhaAcaoDAO = usuarioLinhaAcaoDAO;
        this.buscarLinhaAcaoUC = buscarLinhaAcaoUC;
        this.buscarUsuarioUC = buscarUsuarioUC;
        this.buscarAcolhimentoUC = buscarAcolhimentoUC;
    }

    public Integer cadastraUsuarioLinhaAcao(Integer usuarioLinhaAcaoId, String acolhimentoPront, String usuarioCpf, Integer linhaAcaoID){
        if (usuarioLinhaAcaoId == null || acolhimentoPront == null || usuarioCpf == null || linhaAcaoID == null)
            throw new IllegalArgumentException("Valores para UsuarioLinhaAcao nao podem ser nulo");

        Acolhimento acolhimento = buscarAcolhimentoUC.findOne(acolhimentoPront).
                orElseThrow(() -> new EntityNotFoundException("Acolhimento nao existe"));

        Usuario usuario = buscarUsuarioUC.findOne(usuarioCpf).
                orElseThrow(() -> new EntityNotFoundException("Usuario nao existe"));

        LinhaAcao linhaAcao = buscarLinhaAcaoUC.findOne(linhaAcaoID).
                orElseThrow(() -> new EntityNotFoundException("Linha açao nao existe"));

        UsuarioLinhaAcao usuarioLinhaAcao = new UsuarioLinhaAcao(usuarioLinhaAcaoId, linhaAcao, usuario, Status.AGUARDANDO, acolhimento);

        return usuarioLinhaAcaoDAO.create(usuarioLinhaAcao);
    }
}
