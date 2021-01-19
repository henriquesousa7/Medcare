package br.edu.ifsp.application.repository.inmemory;

import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.usecases.linhaAcao.AcaoDAO;

import java.util.*;

public class InMemoryAcaoDAO implements AcaoDAO {
    private static final Map<Integer, Acao> db = new LinkedHashMap<>();

    @Override
    public Integer create(Acao linhaAcao) {
        db.put(linhaAcao.getId(), linhaAcao);
        return linhaAcao.getId();
    }

    @Override
    public Optional<Acao> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Acao> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Acao linhaAcao) {
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
    public boolean delete(Acao linhaAcao) {
        return deleteByKey(linhaAcao.getId());
    }
}
