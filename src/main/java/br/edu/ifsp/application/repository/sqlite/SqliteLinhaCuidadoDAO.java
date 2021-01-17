package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.usecases.linhaCuidado.LinhaCuidadoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteLinhaCuidadoDAO implements LinhaCuidadoDAO {
    @Override
    public Integer create(LinhaCuidado linhaCuidado) {
        String sql = "INSERT INTO Linhacuidado(nome, descricao) VALUES (?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, linhaCuidado.getNome());
            stmt.setString(2, linhaCuidado.getDescricao());

            stmt.execute();
            return linhaCuidado.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<LinhaCuidado> findOne(Integer key) {
        String sql = "SELECT * FROM Linhacuidado WHERE id = ?";
        LinhaCuidado linhaCuidado = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                linhaCuidado = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(linhaCuidado);
    }

    @Override
    public List<LinhaCuidado> findAll() {
        List<LinhaCuidado> linhasCuidado = new ArrayList<>();
        String sql = "SELECT * FROM Linhacuidado";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                LinhaCuidado linhaCuidado = resultSetToEntity(resultSet);
                linhasCuidado.add(linhaCuidado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasCuidado;
    }

    @Override
    public Optional<LinhaCuidado> findByName(String name) {
        String sql = "SELECT * FROM Linhacuidado WHERE nome = ?";
        LinhaCuidado linhaCuidado = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                linhaCuidado = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(linhaCuidado);
    }

    private LinhaCuidado resultSetToEntity(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descricao = rs.getString("descricao");

        return new LinhaCuidado(id,nome,descricao);
    }

    @Override
    public boolean update(LinhaCuidado linhaCuidado) {
        String sql = "UPDATE Linhacuidado SET nome = ?, descricao = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, linhaCuidado.getNome());
            stmt.setString(2, linhaCuidado.getDescricao());
            stmt.setInt(3, linhaCuidado.getId());
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
    public boolean delete(LinhaCuidado linhaCuidado) {
        return false;
    }
}
