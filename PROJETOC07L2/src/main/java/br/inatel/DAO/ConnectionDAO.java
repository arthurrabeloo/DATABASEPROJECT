package br.inatel.DAO;

import java.sql.*;

public abstract class ConnectionDAO {
    Connection con; // conexão
    PreparedStatement pst; // declaração (query) preparada
    Statement st; // declaração (query) simples
    ResultSet rs; // resposta do banco

    String database = "mydb"; // nome do BD
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/" + database
            + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    public void connectToDb() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        }
    }
}
