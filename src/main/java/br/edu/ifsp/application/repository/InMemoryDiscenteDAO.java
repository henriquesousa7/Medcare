package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.usecases.discente.DiscenteDAO;

import java.util.*;

public class InMemoryDiscenteDAO implements DiscenteDAO {
    private static final Map<Integer, Discente> db = new LinkedHashMap<>();

    @Override
    public Integer create(Discente discente) {
        db.put(discente.getProntuario(), discente);
        return discente.getProntuario();
    }

    @Override
    public Optional<Discente> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Discente> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Discente discente) {
        Integer prontuario = discente.getProntuario();
        if(db.containsKey(prontuario)){
            db.replace(prontuario, discente);
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
    public boolean delete(Discente discente) {
        return deleteByKey(discente.getProntuario());
    }
}
