package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Mantenedor;
import br.edu.ifsp.domain.usecases.Mantenedor.MantenedorDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteMantenedorDAO implements MantenedorDAO {
    @Override
    public Mantenedor checkLogin(String email, Integer prontuario) {
        Mantenedor mantenedor = findOneByEmail(email).orElse(null);

        if(mantenedor != null && mantenedor.getProntuario().equals(prontuario))
            return mantenedor;

        return null;
    }

    @Override
    public Integer create(Mantenedor mantenedor) {
        String sql = "INSERT INTO Mantenedor(prontuario, email) VALUES (?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, mantenedor.getProntuario());
            stmt.setString(2, mantenedor.getEmail());

            stmt.execute();
            return mantenedor.getProntuario();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Mantenedor> findOne(Integer key) {
        String sql = "SELECT * FROM Mantenedor WHERE prontuario = ?";
        Mantenedor mantenedor = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                mantenedor = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(mantenedor);
    }

    public Optional<Mantenedor> findOneByEmail(String email){
        String sql = "SELECT * FROM Mantenedor WHERE email = ?";
        Mantenedor mantenedor = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                mantenedor = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(mantenedor);
    }

    private Mantenedor resultSetToEntity(ResultSet rs) throws SQLException {
        Integer pront = rs.getInt("prontuario");
        String email = rs.getString("email");

        return new Mantenedor(pront, email);
    }

    @Override
    public List<Mantenedor> findAll() {
        List<Mantenedor> mantenedores = new ArrayList<>();
        String sql = "SELECT * FROM Mantenedor";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Mantenedor mantenedor = resultSetToEntity(resultSet);
                mantenedores.add(mantenedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mantenedores;
    }

    @Override
    public boolean update(Mantenedor mantenedor) {
        String sql = "UPDATE Mantenedor SET email = ? WHERE prontuario = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, mantenedor.getEmail());
            stmt.setInt(2, mantenedor.getProntuario());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Mantenedor WHERE prontuario = ?";
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
    public boolean delete(Mantenedor mantenedor) {
        if (mantenedor == null || mantenedor.getProntuario() == null)
            throw new IllegalArgumentException("Mantenedor and mantenedor prontuario must not be null.");
        return deleteByKey(mantenedor.getProntuario());
    }
}
