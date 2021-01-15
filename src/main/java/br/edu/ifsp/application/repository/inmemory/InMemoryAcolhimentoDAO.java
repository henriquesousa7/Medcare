package br.edu.ifsp.application.repository.inmemory;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.acolhimento.AcolhimentoDAO;

import java.util.*;

public class InMemoryAcolhimentoDAO implements AcolhimentoDAO {

    private static final Map<Integer, Acolhimento> db = new LinkedHashMap<>();

    @Override
    public Integer create(Acolhimento acolhimento) {
        db.put(acolhimento.getProntuario(), acolhimento);
        return acolhimento.getProntuario();
    }

    @Override
    public Optional<Acolhimento> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Acolhimento> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Acolhimento acolhimento) {
        Integer prontuario = acolhimento.getProntuario();
        if(db.containsKey(prontuario)) {
            db.replace(prontuario, acolhimento);
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
    public boolean delete(Acolhimento acolhimento) {
        return deleteByKey(acolhimento.getProntuario());
    }
}
