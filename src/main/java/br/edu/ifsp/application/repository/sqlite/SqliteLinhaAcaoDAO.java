package br.edu.ifsp.application.repository.sqlite;

import br.edu.ifsp.domain.entities.Docente;
import br.edu.ifsp.domain.entities.LinhaAcao;
import br.edu.ifsp.domain.entities.LinhaCuidado;
import br.edu.ifsp.domain.usecases.linhaAcao.LinhaAcaoDAO;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.edu.ifsp.application.main.Main.buscarDocenteUC;
import static br.edu.ifsp.application.main.Main.buscarLinhaCuidadoUC;

public class SqliteLinhaAcaoDAO implements LinhaAcaoDAO {
    @Override
    public Integer create(LinhaAcao linhaAcao) {
        String sql = "INSERT INTO Linhaacao(nome, descricao, id_linhaCuidado, pront_responsavel) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            stmt.setString(1, linhaAcao.getNome());
            stmt.setString(2, linhaAcao.getDescricao());
            stmt.setInt(3, linhaAcao.getLinhaCuidado().getId());
            stmt.setInt(4, linhaAcao.getResponsavel().getProntuario());

            stmt.execute();
            return linhaAcao.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<LinhaAcao> findOne(Integer key) {
        String sql = "SELECT * FROM Linhaacao WHERE id = ?";
        LinhaAcao linhaAcao = null;

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
    public List<LinhaAcao> findAll() {
        List<LinhaAcao> linhasAcao = new ArrayList<>();
        String sql = "SELECT * FROM Linhaacao";

        try (PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                LinhaAcao linhaAcao = resultSetToEntity(resultSet);
                linhasAcao.add(linhaAcao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasAcao;
    }

    private LinhaAcao resultSetToEntity(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descricao = rs.getString("descricao");
        Integer linhaCuidadoID = rs.getInt("id_linhaCuidado");
        Integer prontuarioDocente = rs.getInt("pront_responsavel");

        LinhaCuidado linhaCuidado = buscarLinhaCuidadoUC.findOne(linhaCuidadoID).
                orElseThrow(() -> new EntityNotFoundException("Linha cuidado nao existe"));

        Docente docente = buscarDocenteUC.findOne(prontuarioDocente).
                orElseThrow(() -> new EntityNotFoundException("Docente nao existe"));

        return new LinhaAcao(id,nome,descricao,linhaCuidado, docente);
    }

    @Override
    public boolean update(LinhaAcao linhaAcao) {
        String sql = "UPDATE Linhaacao SET nome = ?, descricao = ?, id_linhaCuidado = ?, pront_responsavel = ? WHERE id = ?";

        try(PreparedStatement stmt = ConnectionFactory.createPreparedStatement(sql)){
            stmt.setString(1, linhaAcao.getNome());
            stmt.setString(2, linhaAcao.getDescricao());
            stmt.setInt(3, linhaAcao.getLinhaCuidado().getId());
            stmt.setInt(4, linhaAcao.getResponsavel().getProntuario());
            stmt.setInt(5, linhaAcao.getId());
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
    public boolean delete(LinhaAcao type) {
        return false;
    }
}
