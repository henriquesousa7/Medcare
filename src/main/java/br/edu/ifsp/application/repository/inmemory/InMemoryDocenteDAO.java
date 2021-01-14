package br.edu.ifsp.application.repository.inmemory;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.usecases.docente.DocenteDAO;

import java.util.*;

public class InMemoryDocenteDAO implements DocenteDAO {

    private static final Map<Integer, Docente> db = new LinkedHashMap<>();

    @Override
    public Integer create(Docente docente) {
        db.put(docente.getProntuario(), docente);
        return docente.getProntuario();
    }

    @Override
    public Optional<Docente> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Docente> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Docente docente) {
        Integer prontuario = docente.getProntuario();
        if(db.containsKey(prontuario)){
            db.replace(prontuario, docente);
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
    public boolean delete(Docente docente) {
        return deleteByKey(docente.getProntuario());
    }
}
