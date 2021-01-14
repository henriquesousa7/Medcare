package br.edu.ifsp.application.repository.inmemory;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;

import java.util.*;

public class InMemoryUsuarioDAO implements UsuarioDAO {
    private static final Map<String, Usuario> db = new LinkedHashMap<>();

    @Override
    public String create(Usuario usuario) {
        db.put(usuario.getCpf(), usuario);
        return usuario.getCpf();
    }

    @Override
    public Optional<Usuario> findOne(String key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Usuario> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Usuario usuario) {
        String cpf = usuario.getCpf();
        if(db.containsKey(cpf)){
            db.replace(cpf, usuario);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        if (db.containsKey(key)) {
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Usuario usuario) {
        return deleteByKey(usuario.getCpf());
    }
}
