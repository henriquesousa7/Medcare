package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.usecases.discente.DiscenteDAO;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.application.main.Main.buscarAcaoUC;

public class SQliteDiscenteDAO implements DiscenteDAO {
    @Override
    public Integer create(Discente discente) {
        String sql = "INSERT INTO Discente(prontuario, nome, email, telefone, id_acao) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, discente.getProntuario());
            stmt.setString(2, discente.getNome());
            stmt.setString(3, discente.getEmail());
            stmt.setString(4, discente.getTelefone());
            stmt.setInt(5, discente.getAcao().getId());

            stmt.execute();
            return discente.getProntuario();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Discente> findOne(Integer key) {
        String sql = "SELECT * FROM Discente WHERE prontuario = ?";
        Discente discente = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                discente = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(discente);
    }

    public Optional<Discente> findOneByEmail(String email){
        String sql = "SELECT * FROM Discente WHERE email = ?";
        Discente discente = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                discente = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(discente);
    }

    @Override
    public List<Discente> findAll() {
        List<Discente> discentes = new ArrayList<>();
        String sql = "SELECT * FROM Discente";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Discente discente = resultSetToEntity(resultSet);
                discentes.add(discente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discentes;
    }

    private Discente resultSetToEntity(ResultSet rs) throws SQLException {
        Integer pront = rs.getInt("prontuario");
        String nome = rs.getString("nome");
        String email = rs.getString("email");
        String telefone = rs.getString("telefone");
        Integer id_acao = rs.getInt("id_acao");

        Acao acao = buscarAcaoUC.findOne(id_acao).
                orElseThrow(() -> new EntityNotFoundException("Linha acao nao existe"));

        return new Discente(pront,nome,email,telefone, acao);
    }

    @Override
    public boolean update(Discente discente) {
        String sql = "UPDATE Discente SET email = ?, telefone = ?, id_acao = ? WHERE prontuario = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, discente.getEmail());
            stmt.setString(2, discente.getTelefone());
            stmt.setInt(3, discente.getAcao().getId());
            stmt.setInt(4, discente.getProntuario());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Discente WHERE prontuario = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Discente discente) {
        if (discente == null || discente.getProntuario() == null)
            throw new IllegalArgumentException("Discente and discente prontuario must not be null.");
        return deleteByKey(discente.getProntuario());
    }

    @Override
    public Discente checkLogin(String email, Integer prontuario) {
        Discente discente = findOneByEmail(email).orElse(null);

        if(discente != null && discente.getProntuario().equals(prontuario))
            return discente;

        return null;
    }
}
