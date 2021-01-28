package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.usuarioLinhaAcao.UsuarioLinhaAcaoDAO;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.application.main.Main.*;

public class SqliteUsuarioAcaoDAO implements UsuarioLinhaAcaoDAO {

    @Override
    public Integer create(UsuarioLinhaAcao usuarioLinhaAcao) {
        String sql = "INSERT INTO UsuarioAcao(id_acao, cpf_usuario, status, pront_acolhimento) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, usuarioLinhaAcao.getAcao().getId());
            stmt.setString(2, usuarioLinhaAcao.getUsuario().getCpf());
            stmt.setString(3, String.valueOf(usuarioLinhaAcao.getStatus()));
            stmt.setInt(4, usuarioLinhaAcao.getResponsavelAcolhimento().getProntuario());

            stmt.execute();
            return usuarioLinhaAcao.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<UsuarioLinhaAcao> findOne(Integer key) {
        String sql = "SELECT * FROM UsuarioAcao WHERE id = ?";
        UsuarioLinhaAcao usuarioLinhaAcao = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                usuarioLinhaAcao = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(usuarioLinhaAcao);
    }

    @Override
    public List<UsuarioLinhaAcao> findAll() {
        List<UsuarioLinhaAcao> usuariosLinhaAcao = new ArrayList<>();
        String sql = "SELECT * FROM UsuarioAcao";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                UsuarioLinhaAcao usuarioLinhaAcao = resultSetToEntity(resultSet);
                usuariosLinhaAcao.add(usuarioLinhaAcao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuariosLinhaAcao;
    }

    private UsuarioLinhaAcao resultSetToEntity(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Integer id_acao = rs.getInt("id_acao");
        String cpf_usuario = rs.getString("cpf_usuario");
        String status = rs.getString("status");
        Integer pront_acolhimento = rs.getInt("pront_acolhimento");

        Acao acao = buscarAcaoUC.findOne(id_acao).
                orElseThrow(() -> new EntityNotFoundException("Acao nao encontrada"));

        Usuario usuario = buscarUsuarioUC.findOne(cpf_usuario).
                orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));

        Acolhimento acolhimento = buscarAcolhimentoUC.findOne(pront_acolhimento).
                orElseThrow(() -> new EntityNotFoundException("Acolhimento nao encontrado"));

        Status newStatus = Status.valueOfLabel(status);

        return new UsuarioLinhaAcao(id, acao, usuario, newStatus, acolhimento);
    }

    @Override
    public boolean update(UsuarioLinhaAcao usuarioLinhaAcao) {
        String sql = "UPDATE UsuarioAcao SET status = ?, id_acao = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, String.valueOf(usuarioLinhaAcao.getStatus()));
            stmt.setInt(2, usuarioLinhaAcao.getAcao().getId());
            stmt.setInt(3, usuarioLinhaAcao.getId());
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
    public boolean delete(UsuarioLinhaAcao usuarioLinhaAcao) {
        return false;
    }

    @Override
    public List<UsuarioLinhaAcao> findByAcolhimento(Integer key) {
        String sql = "SELECT * FROM UsuarioAcao WHERE pront_acolhimento = ?";
        List<UsuarioLinhaAcao> usuariosLinhaAcao = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                UsuarioLinhaAcao usuarioLinhaAcao = resultSetToEntity(resultSet);
                usuariosLinhaAcao.add(usuarioLinhaAcao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuariosLinhaAcao;
    }

    @Override
    public List<UsuarioLinhaAcao> findByDocente(Integer key) {
        String sql = "SELECT ua.id, ua.id_acao, ua.cpf_usuario, ua.status, ua.pront_acolhimento FROM UsuarioAcao ua JOIN Acao ac ON ua.id_acao = ac.id WHERE ac.pront_responsavel = ?";
        List<UsuarioLinhaAcao> usuariosLinhaAcao = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                UsuarioLinhaAcao usuarioLinhaAcao = resultSetToEntity2(resultSet);
                usuariosLinhaAcao.add(usuarioLinhaAcao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuariosLinhaAcao;
    }

    private UsuarioLinhaAcao resultSetToEntity2(ResultSet rs) throws SQLException {
        Integer id = rs.getInt(1);
        Integer id_acao = rs.getInt(2);
        String cpf_usuario = rs.getString(3);
        String status = rs.getString(4);
        Integer pront_acolhimento = rs.getInt(5);

        Acao acao = buscarAcaoUC.findOne(id_acao).
                orElseThrow(() -> new EntityNotFoundException("Acao nao encontrada"));

        Usuario usuario = buscarUsuarioUC.findOne(cpf_usuario).
                orElseThrow(() -> new EntityNotFoundException("Usuario nao encontrado"));

        Acolhimento acolhimento = buscarAcolhimentoUC.findOne(pront_acolhimento).
                orElseThrow(() -> new EntityNotFoundException("Acolhimento nao encontrado"));

        Status newStatus = Status.valueOfLabel(status);

        return new UsuarioLinhaAcao(id, acao, usuario, newStatus, acolhimento);
    }
}
