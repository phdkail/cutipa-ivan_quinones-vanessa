package com.backend.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Connection {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/OdontologosParcial", "sa", "sa");
    }

    public static void ejecutarScriptInicial() {
        Connection connection = null;

        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/OdontologosParcial", "sa", "sa");
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS ODONTOLOGOS (" +
                    "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "NOMBRE VARCHAR(255), " +
                    "APELLIDO VARCHAR(255), " +
                    "NUMEROMATRICULA VARCHAR(255))";
            stmt.executeUpdate(sql);
            System.out.println("Tabla creada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }
}
