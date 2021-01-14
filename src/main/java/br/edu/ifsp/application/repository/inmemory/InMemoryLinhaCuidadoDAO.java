package br.edu.ifsp.application.repository.inmemory;

import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.usecases.linhaCuidado.LinhaCuidadoDAO;

import java.util.*;

public class InMemoryLinhaCuidadoDAO implements LinhaCuidadoDAO {
    private static final Map<Integer, LinhaCuidado> db = new LinkedHashMap<>();

    @Override
    public Integer create(LinhaCuidado linhaCuidado) {
        db.put(linhaCuidado.getId(), linhaCuidado);
        return linhaCuidado.getId();
    }

    @Override
    public Optional<LinhaCuidado> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<LinhaCuidado> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(LinhaCuidado linhaCuidado) {
        Integer id = linhaCuidado.getId();
        if(db.containsKey(id)){
            db.replace(id, linhaCuidado);
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
    public boolean delete(LinhaCuidado linhaCuidado) {
        return deleteByKey(linhaCuidado.getId());
    }
}
