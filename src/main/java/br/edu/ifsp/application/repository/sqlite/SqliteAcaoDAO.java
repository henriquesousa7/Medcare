package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.Acao;
import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.usecases.linhaAcao.AcaoDAO;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.application.main.Main.buscarDocenteUC;
import static br.edu.ifsp.application.main.Main.buscarLinhaCuidadoUC;

public class SqliteAcaoDAO implements AcaoDAO {
    @Override
    public Integer create(Acao linhaAcao) {
        String sql = "INSERT INTO Acao(nome, descricao, pront_responsavel) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, linhaAcao.getNome());
            stmt.setString(2, linhaAcao.getDescricao());
            stmt.setInt(3, linhaAcao.getResponsavel().getProntuario());

            stmt.execute();
            return linhaAcao.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Acao> findOne(Integer key) {
        String sql = "SELECT * FROM Acao WHERE id = ?";
        Acao linhaAcao = null;

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setInt(1, key);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                linhaAcao = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(linhaAcao);
    }

    @Override
    public List<Acao> findAll() {
        List<Acao> linhasAcao = new ArrayList<>();
        String sql = "SELECT * FROM Acao";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Acao linhaAcao = resultSetToEntity(resultSet);
                linhasAcao.add(linhaAcao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasAcao;
    }

    private Acao resultSetToEntity(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descricao = rs.getString("descricao");
        Integer prontuarioDocente = rs.getInt("pront_responsavel");


        Docente docente = buscarDocenteUC.findOne(prontuarioDocente).
                orElseThrow(() -> new EntityNotFoundException("Docente nao existe"));

        return new Acao(id,nome,descricao,docente);
    }

    @Override
    public boolean update(Acao linhaAcao) {
        String sql = "UPDATE Acao SET nome = ?, descricao = ?, pront_responsavel = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, linhaAcao.getNome());
            stmt.setString(2, linhaAcao.getDescricao());
            stmt.setInt(3, linhaAcao.getResponsavel().getProntuario());
            stmt.setInt(4, linhaAcao.getId());
            stmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Acao WHERE id = ?";
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
    public boolean delete(Acao acao) {
        if (acao == null || acao.getId() == null)
            throw new IllegalArgumentException("Acao and Acao ID must not be null.");
        return deleteByKey(acao.getId());
    }
}
