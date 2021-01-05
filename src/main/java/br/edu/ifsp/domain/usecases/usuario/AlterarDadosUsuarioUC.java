package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class AlterarDadosUsuarioUC {
    private UsuarioDAO usuarioDAO;

    public AlterarDadosUsuarioUC(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public boolean alteraUsuario(Usuario usuario){
        String cpf = usuario.getCpf();

        if(usuarioDAO.findOne(cpf).isEmpty())
            throw new EntityNotFoundException("Usuario nao existe");

        return usuarioDAO.update(usuario);
    }
}
