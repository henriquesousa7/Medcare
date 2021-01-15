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
            statement.addBatch(createDocenteTable());
            statement.addBatch(createLinhaCuidadoTable());
            statement.addBatch(createLinhaAcaoTable());
            statement.addBatch(createDiscenteTable());
            statement.addBatch(createUsuarioTable());
            statement.addBatch(createUsuarioLinhaAcaoTable());
            statement.addBatch(createAtendimentoTable());
            statement.addBatch(createinterconsultaTable());
            statement.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createAcolhimentoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Acolhimento (\n");
        builder.append("prontuario INTEGER PRIMARY KEY NOT NULL, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL, \n");
        builder.append("telefone TEXT NOT NULL, \n");
        builder.append("cpf TEXT NOT NULL UNIQUE \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createDocenteTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Docente (\n");
        builder.append("prontuario INTEGER PRIMARY KEY NOT NULL, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL UNIQUE, \n");
        builder.append("telefone TEXT NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createLinhaCuidadoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Linhacuidado (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("descricao TEXT NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createLinhaAcaoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Linhaacao (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("descricao TEXT NOT NULL, \n");
        builder.append("id_linhaCuidado INTEGER, \n");
        builder.append("pront_responsavel INTEGER, \n");
        builder.append("FOREIGN KEY(id_linhaCuidado) REFERENCES Linhacuidado(id),\n");
        builder.append("FOREIGN KEY(pront_responsavel) REFERENCES Docente(prontuario) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createDiscenteTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Discente (\n");
        builder.append("prontuario INTEGER PRIMARY KEY NOT NULL, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL UNIQUE, \n");
        builder.append("telefone TEXT NOT NULL, \n");
        builder.append("id_linhaAcao TEXT NOT NULL, \n");
        builder.append("FOREIGN KEY(id_linhaAcao) REFERENCES Linhaacao(id) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createUsuarioTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Usuario (\n");
        builder.append("cpf TEXT PRIMARY KEY NOT NULL, \n");
        builder.append("cartaoSus INTEGER NOT NULL UNIQUE, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("sexo VARCHAR(1) NOT NULL, \n");
        builder.append("telefone TEXT NOT NULL UNIQUE, \n");
        builder.append("endereco TEXT NOT NULL, \n");
        builder.append("historicoMedico TEXT NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createUsuarioLinhaAcaoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE UsuarioLinhaacao (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("id_linhaAcao INTEGER NOT NULL, \n");
        builder.append("cpf_usuario TEXT NOT NULL, \n");
        builder.append("status TEXT NOT NULL, \n");
        builder.append("pront_acolhimento INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(id_linhaAcao) REFERENCES Linhaacao(id), \n");
        builder.append("FOREIGN KEY(cpf_usuario) REFERENCES Usuario(prontuario), \n");
        builder.append("FOREIGN KEY(pront_acolhimento) REFERENCES Acolhimento(prontuario) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createAtendimentoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Atendimento (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("id_usuarioLinhaAcao INTEGER NOT NULL, \n");
        builder.append("data_atend DATETIME NOT NULL, \n");
        builder.append("status TEXT NOT NULL, \n");
        builder.append("pront_responsavel INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(id_usuarioLinhaAcao) REFERENCES UsuarioLinhaacao(id), \n");
        builder.append("FOREIGN KEY(pront_responsavel) REFERENCES Discente(prontuario) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createinterconsultaTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Interconsulta (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("id_atendimento INTEGER NOT NULL, \n");
        builder.append("cpf_usuario TEXT NOT NULL, \n");
        builder.append("status TEXT NOT NULL, \n");
        builder.append("pront_responsavel INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(id_atendimento) REFERENCES Atendimento(id), \n");
        builder.append("FOREIGN KEY(cpf_usuario) REFERENCES Usuario(prontuario), \n");
        builder.append("FOREIGN KEY(pront_responsavel) REFERENCES Docente(prontuario) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

}
