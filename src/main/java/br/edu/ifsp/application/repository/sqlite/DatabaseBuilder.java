package br.edu.ifsp.application.repository.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public void buildDatabaseIfMissing() {
        if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createAcolhimentoTable());
//            statement.addBatch(createDocenteTable());
//            statement.addBatch(createLinhaCuidadoTable());
//            statement.addBatch(createLinhaAcaoTable());
//            statement.addBatch(createDiscenteTable());
//            statement.addBatch(createUsuarioTable());
//            statement.addBatch(createUsuarioLinhaAcaoTable());
//            statement.addBatch(createAtendimentoTable());
//            statement.addBatch(createinterconsultaTable());
            statement.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createAcolhimentoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Acolhimento (\n");
        builder.append("prontuario TEXT PRIMARY KEY, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL UNIQUE, \n");
        builder.append("telefone TEXT NOT NULL, \n");
        builder.append("cpf TEXT NOT NULL UNIQUE \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createDocenteTable() {
        return null;
    }

    private String createLinhaCuidadoTable() {
        return null;
    }

    private String createLinhaAcaoTable() {
        return null;
    }

    private String createDiscenteTable() {
        return null;
    }

    private String createUsuarioTable() {
        return null;
    }

    private String createUsuarioLinhaAcaoTable() {
        return null;
    }

    private String createAtendimentoTable() {
        return null;
    }

    private String createinterconsultaTable() {
        return null;
    }

}
