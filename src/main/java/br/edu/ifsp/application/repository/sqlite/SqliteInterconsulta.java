package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.*;
import br.edu.ifsp.domain.usecases.interconsulta.InterConsultaDAO;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.application.main.Main.*;

public class SqliteInterconsulta implements InterConsultaDAO {
    @Override
    public Integer create(InterConsulta interConsulta) {
        String sql = "INSERT INTO Interconsulta(id_acao, cpf_usuario, status, pront_responsavel) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, interConsulta.getAcao().getId());
            stmt.setString(2, interConsulta.getUsuario().getCpf());
            stmt.setString(3, String.valueOf(interConsulta.getStatus()));
            stmt.setInt(4, interConsulta.getDocenteResponsavel().getProntuario());

            stmt.execute();
            return interConsulta.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<InterConsulta> findOne(Integer key) {
        String sql = "SELECT * FROM Interconsulta WHERE id = ?";
        InterConsulta interConsulta = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                interConsulta = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(interConsulta);
    }

    @Override
    public List<InterConsulta> findAll() {
        List<InterConsulta> interConsultas = new ArrayList<>();
        String sql = "SELECT * FROM Interconsulta";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                InterConsulta interConsulta = resultSetToEntity(resultSet);
                interConsultas.add(interConsulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interConsultas;
    }

    private InterConsulta resultSetToEntity(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Integer id_acao = rs.getInt("id_acao");
        String cpf_usuario = rs.getString("cpf_usuario");
        String status = rs.getString("status");
        Integer pront_docente = rs.getInt("pront_responsavel");

        Acao acao = buscarAcaoUC.findOne(id_acao).
                orElseThrow(() -> new EntityNotFoundException("Acao nao encontrada"));

        Usuario usuario = buscarUsuarioUC.findOne(cpf_usuario).
                orElseThrow(() -> new EntityNotFoundException("Usuario nao existe"));

        Docente docente = buscarDocenteUC.findOne(pront_docente).
                orElseThrow(() -> new EntityNotFoundException("Docente nao existe"));

        Status newStatus = Status.valueOfLabel(status);

        return new InterConsulta(id, acao, usuario, docente, newStatus);
    }

    @Override
    public boolean update(InterConsulta type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Interconsulta WHERE id = ?";
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
    public boolean delete(InterConsulta interConsulta) {
        if (interConsulta == null || interConsulta.getId() == null)
            throw new IllegalArgumentException("Interconsulta and inteconsulta ID must not be null.");
        return deleteByKey(interConsulta.getId());
    }

    @Override
    public List<InterConsulta> findByDocente(Integer key) {
        List<InterConsulta> interConsultas = new ArrayList<>();
        String sql = "SELECT * FROM Interconsulta WHERE pront_responsavel = ?";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                InterConsulta interConsulta = resultSetToEntity(resultSet);
                interConsultas.add(interConsulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interConsultas;
    }
}
