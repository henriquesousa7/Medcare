package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.Discente;
import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.LinhaAcao;
import br.edu.ifsp.domain.usecases.discente.DiscenteDAO;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.application.main.Main.buscarLinhaAcaoUC;

public class SQliteDiscenteDAO implements DiscenteDAO {
    @Override
    public Integer create(Discente discente) {
        String sql = "INSERT INTO Discente(prontuario, nome, email, telefone, id_linhaAcao) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, discente.getProntuario());
            stmt.setString(2, discente.getNome());
            stmt.setString(3, discente.getEmail());
            stmt.setString(4, discente.getTelefone());
            stmt.setInt(5, discente.getLinhaAcao().getId());

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

        LinhaAcao linhaAcao = buscarLinhaAcaoUC.findOne(rs.getInt("id_linhaAcao")).
                orElseThrow(() -> new EntityNotFoundException("Linha acao nao existe"));

        return new Discente(pront,nome,email,telefone, linhaAcao);
    }

    @Override
    public boolean update(Discente discente) {
        String sql = "UPDATE Discente SET email = ?, telefone = ?, id_linhaAcao = ? WHERE prontuario = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, discente.getEmail());
            stmt.setString(2, discente.getTelefone());
            stmt.setInt(3, discente.getLinhaAcao().getId());
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
        return false;
    }

    @Override
    public boolean delete(Discente type) {
        return false;
    }
}
