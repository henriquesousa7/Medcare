package br.edu.ifsp.domain.usecases.usuario;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.utils.Notification;
import br.edu.ifsp.domain.usecases.utils.Validator;

public class UsuarioInputValidator extends Validator<Usuario> {
    @Override
    public Notification validate(Usuario usuario) {
        Notification notification = new Notification();

        if(usuario == null){
            notification.addError("Usuario is null");
            return notification;
        }

        if(nullOrEmpty(usuario.getCpf()))
            notification.addError("CPF is null or empty");
        if(usuario.getNumeroCartaoSUS() <= 0)
            notification.addError("Cartao SUS cannot be <= 0");
        if(nullOrEmpty(usuario.getNome()))
            notification.addError("Nome is null or empty");
        if(usuario.getSexo() == ' ')
            notification.addError("Sexo is null");
        if(nullOrEmpty(usuario.getTelefone()))
            notification.addError("Telefone is null or empty");
        if(nullOrEmpty(usuario.getEndereco()))
            notification.addError("Endereço is null or empty");
        if(nullOrEmpty(usuario.getHistoricoMedico()))
            notification.addError("Historico medico is null or empty");

        return notification;
    }
}
