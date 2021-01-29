package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.atendimento.AtendimentoDAO;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.application.main.Main.*;

public class SqliteAtendimentoDAO implements AtendimentoDAO {

    @Override
    public Integer create(Atendimento atendimento) {
        String sql = "INSERT INTO Atendimento(id_UsuarioLinhaAcao, data_atend, status, pront_responsavel) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, atendimento.getUsuarioLinhaAcao().getId());
            stmt.setDate(2, Date.valueOf(atendimento.getData()));
            stmt.setString(3, String.valueOf(atendimento.getStatus()));
            stmt.setInt(4, atendimento.getDiscenteResponsavel().getProntuario());

            stmt.execute();
            return atendimento.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Atendimento> findOne(Integer key) {
        String sql = "SELECT * FROM Atendimento WHERE id = ?";
        Atendimento atendimento = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                atendimento = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(atendimento);
    }

    @Override
    public List<Atendimento> findAll() {
        List<Atendimento> atendimentos = new ArrayList<>();
        String sql = "SELECT * FROM Atendimento";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Atendimento atendimento = resultSetToEntity(resultSet);
                atendimentos.add(atendimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return atendimentos;
    }

    private Atendimento resultSetToEntity(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Integer id_UsuarioLinhaAcao = rs.getInt("id_usuarioLinhaAcao");
        Date data_atend = rs.getDate("data_atend");
        String status = rs.getString("status");
        Integer pront_responsavel = rs.getInt("pront_responsavel");

        UsuarioLinhaAcao usuarioLinhaAcao = buscarUsuarioLinhaAcaoUC.findOne(id_UsuarioLinhaAcao).
                orElseThrow(() -> new EntityNotFoundException("UsuarioLinhaAcao nao encontrado"));

        Discente discente = buscarDiscenteUC.findOne(pront_responsavel).
                orElseThrow(() -> new EntityNotFoundException("Discente nao encontrado"));

        Status newStatus = Status.valueOfLabel(status);

        return new Atendimento(id, usuarioLinhaAcao, data_atend.toLocalDate(), newStatus, discente);
    }

    private Atendimento resultSetToEntity2(ResultSet rs) throws SQLException {
        Integer id = rs.getInt(1);
        Integer id_UsuarioLinhaAcao = rs.getInt(2);
        Date data_atend = rs.getDate(3);
        String status = rs.getString(4);
        Integer pront_responsavel = rs.getInt(5);

        UsuarioLinhaAcao usuarioLinhaAcao = buscarUsuarioLinhaAcaoUC.findOne(id_UsuarioLinhaAcao).
                orElseThrow(() -> new EntityNotFoundException("UsuarioLinhaAcao nao encontrado"));

        Discente discente = buscarDiscenteUC.findOne(pront_responsavel).
                orElseThrow(() -> new EntityNotFoundException("Discente nao encontrado"));

        Status newStatus = Status.valueOfLabel(status);

        return new Atendimento(id, usuarioLinhaAcao, data_atend.toLocalDate(), newStatus, discente);
    }

    @Override
    public List<Atendimento> findByDocenteAcao(Integer key) {
        String sql = "SELECT at.id, at.id_usuarioLinhaAcao, at.data_atend, at.status, at.pront_responsavel, ac.pront_responsavel FROM Atendimento at " +
                "INNER JOIN UsuarioAcao ua ON at.id_usuarioLinhaAcao = ua.id " +
                "INNER JOIN Acao ac ON ac.id = ua.id_acao WHERE ac.pront_responsavel = ?";
        List<Atendimento> atendimentos = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getInt(6) == key) {
                    Atendimento atendimento = resultSetToEntity2(resultSet);
                    atendimentos.add(atendimento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return atendimentos;
    }

    @Override
    public List<Atendimento> findByDiscente(Integer key) {
        String sql = "SELECT * FROM Atendimento WHERE pront_responsavel = ?";
        List<Atendimento> atendimentos = new ArrayList<>();

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Atendimento atendimento = resultSetToEntity(resultSet);
                atendimentos.add(atendimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return atendimentos;
    }

    @Override
    public boolean update(Atendimento atendimento) {
        String sql = "UPDATE Atendimento SET status = ?, id_usuarioLinhaAcao = ?, pront_responsavel = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, String.valueOf(atendimento.getStatus()));
            stmt.setInt(2, atendimento.getUsuarioLinhaAcao().getId());
            stmt.setInt(3, atendimento.getDiscenteResponsavel().getProntuario());
            stmt.setInt(4, atendimento.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Atendimento WHERE id = ?";
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
    public boolean delete(Atendimento atendimento) {
        if (atendimento == null || atendimento.getId() == null)
            throw new IllegalArgumentException("Atendimento and atendimento ID must not be null.");
        return deleteByKey(atendimento.getId());
    }
}
