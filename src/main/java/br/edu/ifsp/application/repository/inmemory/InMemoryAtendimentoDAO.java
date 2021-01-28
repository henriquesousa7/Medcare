package br.edu.ifsp.application.repository.inmemory;

import br.edu.ifsp.domain.entities.Atendimento;
import br.edu.ifsp.domain.usecases.atendimento.AtendimentoDAO;

import java.util.*;

public class InMemoryAtendimentoDAO implements AtendimentoDAO {
    private static final Map<Integer, Atendimento> db = new LinkedHashMap<>();

    @Override
    public Integer create(Atendimento atendimento) {
        db.put(atendimento.getId(), atendimento);
        return atendimento.getId();
    }

    @Override
    public Optional<Atendimento> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Atendimento> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Atendimento atendimento) {
        Integer id = atendimento.getId();
        if(db.containsKey(id)){
            db.replace(id, atendimento);
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
    public boolean delete(Atendimento atendimento) {
        return deleteByKey(atendimento.getId());
    }

    @Override
    public List<Atendimento> findByDocenteAcao(Integer key) {
        return null;
    }

    @Override
    public List<Atendimento> findByDiscente(Integer key) {
        return null;
    }
}
