package br.edu.ifsp.application.repository.inmemory;

import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.entities.UsuarioLinhaAcao;
import br.edu.ifsp.domain.usecases.usuarioLinhaAcao.UsuarioLinhaAcaoDAO;

import java.util.*;

public class InMemoryUsuarioLinhaAcaoDAO implements UsuarioLinhaAcaoDAO {
    private static final Map<Integer, UsuarioLinhaAcao> db = new LinkedHashMap<>();

    @Override
    public Integer create(UsuarioLinhaAcao usuarioLinhaAcao) {
        db.put(usuarioLinhaAcao.getId(), usuarioLinhaAcao);
        return usuarioLinhaAcao.getId();
    }

    @Override
    public Optional<UsuarioLinhaAcao> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<UsuarioLinhaAcao> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(UsuarioLinhaAcao usuarioLinhaAcao) {
        Integer id = usuarioLinhaAcao.getId();
        if(db.containsKey(id)){
            db.replace(id, usuarioLinhaAcao);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        if (db.containsKey(key)) {
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(UsuarioLinhaAcao usuarioLinhaAcao) {
        return deleteByKey(usuarioLinhaAcao.getId());
    }

    @Override
    public List<UsuarioLinhaAcao> findByAcolhimento(Integer Key) {
        return null;
    }

    @Override
    public List<UsuarioLinhaAcao> findByDocente(Integer Key) {
        return null;
    }

    @Override
    public boolean updateByUsuario(Integer acaoId, String cpf) {
        return false;
    }
}
