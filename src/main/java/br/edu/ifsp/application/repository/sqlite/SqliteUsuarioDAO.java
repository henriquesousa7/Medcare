package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Usuario;
import br.edu.ifsp.domain.usecases.usuario.UsuarioDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteUsuarioDAO implements UsuarioDAO {
    @Override
    public String create(Usuario usuario) {
        String sql = "INSERT INTO Usuario(cpf, cartaoSus, nome, sexo, telefone, endereco, historicoMedico) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, usuario.getCpf());
            stmt.setInt(2, usuario.getNumeroCartaoSUS());
            stmt.setString(3, usuario.getNome());
            stmt.setString(4, String.valueOf(usuario.getSexo()));
            stmt.setString(5, usuario.getTelefone());
            stmt.setString(6, usuario.getEndereco());
            stmt.setString(7, usuario.getHistoricoMedico());

            stmt.execute();
            return usuario.getCpf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Usuario> findOne(String key) {
        String sql = "SELECT * FROM Usuario WHERE cpf = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                usuario = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = resultSetToEntity(resultSet);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    private Usuario resultSetToEntity(ResultSet rs) throws SQLException {
        String cpf = rs.getString("cpf");
        Integer cartaoSus = rs.getInt("cartaoSus");
        String nome = rs.getString("nome");
        char sexo = rs.getString("sexo").charAt(0);
        String telefone = rs.getString("telefone");
        String endereco = rs.getString("endereco");
        String historicoMedico = rs.getString("historicoMedico");

        return new Usuario(cpf, cartaoSus, nome, sexo, telefone, endereco, historicoMedico);
    }

    @Override
    public boolean update(Usuario usuario) {
        String sql = "UPDATE Usuario SET telefone = ?, endereco = ?, historicoMedico = ? WHERE cpf = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, usuario.getTelefone());
            stmt.setString(2, usuario.getEndereco());
            stmt.setString(3, usuario.getHistoricoMedico());
            stmt.setString(4, usuario.getCpf());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        String sql = "DELETE FROM Usuario WHERE cpf = ?";
        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, key);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Usuario usuario) {
        if (usuario == null || usuario.getCpf() == null)
            throw new IllegalArgumentException("Usuario and usuario CPF must not be null.");
        return deleteByKey(usuario.getCpf());
    }
}
