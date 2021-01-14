package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class CadastrarUsuarioUC {
    private UsuarioDAO usuarioDAO;

    public CadastrarUsuarioUC(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public String cadastraUsuario(Usuario usuario){
        Validator<Usuario> validator = new UsuarioInputValidator();
        Notification notification = validator.validate(usuario);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String cpf = usuario.getCpf();

        if(usuarioDAO.findOne(cpf).isPresent())
            throw new EntityAlreadyExistsException("Usuario ja existe");

        return usuarioDAO.create(usuario);
    }
}
