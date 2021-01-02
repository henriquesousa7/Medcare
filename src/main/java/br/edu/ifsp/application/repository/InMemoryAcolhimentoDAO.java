package br.edu.ifsp.application.repository;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.Acolhimento.AcolhimentoDAO;

import java.util.*;

public class InMemoryAcolhimentoDAO implements AcolhimentoDAO {

    private static final Map<String, Acolhimento> db = new LinkedHashMap<>();

    @Override
    public String create(Acolhimento acolhimento) {
        db.put(acolhimento.getProntuario(), acolhimento);
        return acolhimento.getProntuario();
    }

    @Override
    public Optional<Acolhimento> findOne(String key) {
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
        String prontuario = acolhimento.getProntuario();
        if(db.containsKey(prontuario)) {
            db.replace(prontuario, acolhimento);
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
    public boolean delete(Acolhimento acolhimento) {
        return deleteByKey(acolhimento.getProntuario());
    }
}
