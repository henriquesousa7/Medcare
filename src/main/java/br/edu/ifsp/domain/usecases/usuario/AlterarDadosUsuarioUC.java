package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class AlterarDadosUsuarioUC {
    private UsuarioDAO usuarioDAO;

    public AlterarDadosUsuarioUC(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public boolean alteraUsuario(Usuario usuario){
        Validator<Usuario> validator = new UsuarioInputValidator();
        Notification notification = validator.validate(usuario);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String cpf = usuario.getCpf();

        if(usuarioDAO.findOne(cpf).isEmpty())
            throw new EntityNotFoundException("Usuario nao existe");

        return usuarioDAO.update(usuario);
    }
}
