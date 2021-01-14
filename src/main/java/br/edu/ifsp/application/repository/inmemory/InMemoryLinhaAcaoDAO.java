package br.edu.ifsp.application.repository.inmemory;

import br.edu.ifsp.domain.entities.LinhaAcao;
import br.edu.ifsp.domain.usecases.linhaAcao.LinhaAcaoDAO;

import java.util.*;

public class InMemoryLinhaAcaoDAO implements LinhaAcaoDAO {
    private static final Map<Integer, LinhaAcao> db = new LinkedHashMap<>();

    @Override
    public Integer create(LinhaAcao linhaAcao) {
        db.put(linhaAcao.getId(), linhaAcao);
        return linhaAcao.getId();
    }

    @Override
    public Optional<LinhaAcao> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<LinhaAcao> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(LinhaAcao linhaAcao) {
        Integer id = linhaAcao.getId();
        if(db.containsKey(id)){
            db.replace(id, linhaAcao);
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
    public boolean delete(LinhaAcao linhaAcao) {
        return deleteByKey(linhaAcao.getId());
    }
}
