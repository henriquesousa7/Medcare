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
            someInserts();
        }
    }

    private void someInserts() {
        String sql1 = "INSERT INTO Acolhimento(prontuario, nome, email, telefone, cpf) VALUES (3309165, 'Henrique Sousa', 'henrique@hotmail.com', '16986574312', '12312312311');";
        String sql2 = "INSERT INTO Acolhimento(prontuario, nome, email, telefone, cpf) VALUES (3309166, 'Felipe Sousa', 'felipe@hotmail.com', '16986574313', '32112312311');";

        String sql3 = "INSERT INTO Docente(prontuario, nome, email, telefone) VALUES (2009165, 'Diego Ribas', 'diego@hotmail.com', '16955442313');";
        String sql4 = "INSERT INTO Docente(prontuario, nome, email, telefone) VALUES (2009166, 'Gabriel Ramos', 'gabriel@hotmail.com', '16956432313');";

        String sql5 = "INSERT INTO Acao(id, nome, descricao, pront_responsavel) VALUES (1, 'Neurologia infatil', 'Neurologia infatil descricao', 2009165);";
        String sql6 = "INSERT INTO Acao(id, nome, descricao, pront_responsavel) VALUES (2, 'Fisioterapia para idoso', 'Fisioterapia para idoso descricao', 2009166);";

        String sql7 = "INSERT INTO Linhacuidado(id, nome, descricao, id_acao) VALUES (1, 'Neurologia', 'Neurologia em geral', 1);";
        String sql8 = "INSERT INTO Linhacuidado(id, nome, descricao, id_acao) VALUES (2, 'Fisioterapia', 'Fisioterapia em geral', 2);";

        String sql9 = "INSERT INTO Discente(prontuario, nome, email, telefone, id_acao) VALUES (1009165, 'Amanda Santos', 'amanda@hotmail.com', '16993456718', 1);";
        String sql10 = "INSERT INTO Discente(prontuario, nome, email, telefone, id_acao) VALUES (1009166, 'Laura Torres', 'laura@hotmail.com', '16993456712', 2);";

        String sql11 = "INSERT INTO Usuario(cpf, cartaoSus, nome, sexo, telefone, endereco, historicoMedico) VALUES ('47847847878', 1234, 'Gabriel Ramos', 'M', '16923145678', 'Rua Sete', 'Perna quebrada');";
        String sql12 = "INSERT INTO Usuario(cpf, cartaoSus, nome, sexo, telefone, endereco, historicoMedico) VALUES ('98798798798', 5678, 'Luiza Ramos', 'F', '16923121678', 'Rua Oito', 'Neurologia cronica');";

        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(sql1);
            statement.addBatch(sql2);
            statement.addBatch(sql3);
            statement.addBatch(sql4);
            statement.addBatch(sql5);
            statement.addBatch(sql6);
            statement.addBatch(sql7);
            statement.addBatch(sql8);
            statement.addBatch(sql9);
            statement.addBatch(sql10);
            statement.addBatch(sql11);
            statement.addBatch(sql12);
            statement.executeBatch();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createAcolhimentoTable());
            statement.addBatch(createDocenteTable());
            statement.addBatch(createAcaoTable());
            statement.addBatch(createLinhaCuidadoTable());
            statement.addBatch(createDiscenteTable());
            statement.addBatch(createUsuarioTable());
            statement.addBatch(createUsuarioAcaoTable());
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
        builder.append("descricao TEXT NOT NULL, \n");
        builder.append("id_acao INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(id_acao) REFERENCES Acao(id)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createAcaoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Acao (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("descricao TEXT NOT NULL, \n");
        builder.append("pront_responsavel INTEGER, \n");
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
        builder.append("id_acao INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(id_acao) REFERENCES Acao(id) \n");
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

    private String createUsuarioAcaoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE UsuarioAcao (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("id_acao INTEGER NOT NULL, \n");
        builder.append("cpf_usuario TEXT NOT NULL, \n");
        builder.append("status TEXT NOT NULL, \n");
        builder.append("pront_acolhimento INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(id_acao) REFERENCES Acao(id), \n");
        builder.append("FOREIGN KEY(cpf_usuario) REFERENCES Usuario(cpf), \n");
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
        builder.append("FOREIGN KEY(id_usuarioLinhaAcao) REFERENCES UsuarioAcao(id), \n");
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
        builder.append("FOREIGN KEY(cpf_usuario) REFERENCES Usuario(cpf), \n");
        builder.append("FOREIGN KEY(pront_responsavel) REFERENCES Docente(prontuario) \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

}
