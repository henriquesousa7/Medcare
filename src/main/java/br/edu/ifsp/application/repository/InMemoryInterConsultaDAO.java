package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.InterConsulta;
import br.edu.ifsp.domain.usecases.interconsulta.InterConsultaDAO;

import java.util.*;

public class InMemoryInterConsultaDAO implements InterConsultaDAO {
    private static final Map<Integer, InterConsulta> db = new LinkedHashMap<>();

    @Override
    public Integer create(InterConsulta interConsulta) {
        db.put(interConsulta.getId(), interConsulta);
        return interConsulta.getId();
    }

    @Override
    public Optional<InterConsulta> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<InterConsulta> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(InterConsulta interConsulta) {
        Integer id = interConsulta.getId();
        if(db.containsKey(id)){
            db.replace(id, interConsulta);
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
    public boolean delete(InterConsulta interConsulta) {
        return deleteByKey(interConsulta.getId());
    }
}
