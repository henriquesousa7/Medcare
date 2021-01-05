package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;

public class CadastrarUsuarioUC {
    private UsuarioDAO usuarioDAO;

    public CadastrarUsuarioUC(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public String cadastraUsuario(Usuario usuario){
        String cpf = usuario.getCpf();

        if(usuarioDAO.findOne(cpf).isPresent())
            throw new EntityAlreadyExistsException("Usuario ja existe");

        return usuarioDAO.create(usuario);
    }
}
