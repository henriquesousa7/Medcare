package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.Acolhimento;
import br.edu.ifsp.domain.usecases.acolhimento.AcolhimentoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteAcolhimentoDAO implements AcolhimentoDAO {
    @Override
    public Integer create(Acolhimento acolhimento) {
        String sql = "INSERT INTO Acolhimento(prontuario, nome, email, telefone, cpf) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, acolhimento.getProntuario());
            stmt.setString(2, acolhimento.getNome());
            stmt.setString(3, acolhimento.getEmail());
            stmt.setString(4, acolhimento.getTelefone());
            stmt.setString(5, acolhimento.getCpf());

            stmt.execute();
            return acolhimento.getProntuario();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Acolhimento> findOne(Integer key) {
        String sql = "SELECT * FROM Acolhimento WHERE prontuario = ?";
        Acolhimento acolhimento = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                acolhimento = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(acolhimento);
    }

    public Optional<Acolhimento> findOneByEmail(String email){
        String sql = "SELECT * FROM Acolhimento WHERE email = ?";
        Acolhimento acolhimento = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                acolhimento = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(acolhimento);
    }

    @Override
    public List<Acolhimento> findAll() {
        List<Acolhimento> servidores = new ArrayList<>();
        String sql = "SELECT * FROM Acolhimento";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Acolhimento acolhimento = resultSetToEntity(resultSet);
                servidores.add(acolhimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servidores;
    }

    private Acolhimento resultSetToEntity(ResultSet rs) throws SQLException {
        Integer pront = rs.getInt("prontuario");
        String nome = rs.getString("nome");
        String email = rs.getString("email");
        String telefone = rs.getString("telefone");
        String cpf = rs.getString("cpf");

        return new Acolhimento(pront,nome,email,telefone,cpf);
    }

    @Override
    public boolean update(Acolhimento acolhimento) {
        String sql = "UPDATE Acolhimento SET email = ?, telefone = ? WHERE prontuario = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, acolhimento.getEmail());
            stmt.setString(2, acolhimento.getTelefone());
            stmt.setInt(3, acolhimento.getProntuario());
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
    public boolean delete(Acolhimento type) {
        return false;
    }

    @Override
    public Acolhimento checkLogin(String email, Integer prontuario) {
        Acolhimento acolhimento = findOneByEmail(email).orElse(null);

        if(acolhimento.getProntuario() == prontuario)
            return acolhimento;

        return null;
    }
}
