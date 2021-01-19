package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.usecases.docente.DocenteDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteDocenteDAO implements DocenteDAO {
    @Override
    public Integer create(Docente docente) {
        String sql = "INSERT INTO Docente(prontuario, nome, email, telefone) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, docente.getProntuario());
            stmt.setString(2, docente.getNome());
            stmt.setString(3, docente.getEmail());
            stmt.setString(4, docente.getTelefone());

            stmt.execute();
            return docente.getProntuario();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Docente> findOne(Integer key) {
        String sql = "SELECT * FROM Docente WHERE prontuario = ?";
        Docente docente = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                docente = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(docente);
    }

    public Optional<Docente> findOneByEmail(String email){
        String sql = "SELECT * FROM Docente WHERE email = ?";
        Docente docente = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                docente = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(docente);
    }

    private Docente resultSetToEntity(ResultSet rs) throws SQLException {
        Integer pront = rs.getInt("prontuario");
        String nome = rs.getString("nome");
        String email = rs.getString("email");
        String telefone = rs.getString("telefone");

        return new Docente(pront,nome,email,telefone);
    }

    @Override
    public List<Docente> findAll() {
        List<Docente> docentes = new ArrayList<>();
        String sql = "SELECT * FROM Docente";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Docente docente = resultSetToEntity(resultSet);
                docentes.add(docente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return docentes;
    }

    @Override
    public boolean update(Docente docente) {
        String sql = "UPDATE Docente SET email = ?, telefone = ? WHERE prontuario = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, docente.getEmail());
            stmt.setString(2, docente.getTelefone());
            stmt.setInt(3, docente.getProntuario());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Docente docente) {
        return false;
    }

    @Override
    public Docente checkLogin(String email, Integer prontuario) {
        Docente docente = findOneByEmail(email).orElse(null);

        if(docente.getProntuario() == prontuario)
            return docente;

        return null;
    }
}
