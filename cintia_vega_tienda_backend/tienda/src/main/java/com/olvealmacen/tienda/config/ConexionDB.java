package com.olvealmacen.tienda.config;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConexionDB {

    private static final String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10706172";
    private static final String USER = "sql10706172";
    private static final String PASS = "vMP9pFVTCT";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✔ Conexión exitosa a la base de datos");
        } catch (Exception e) {
            System.out.println("❌ Error en la conexión: " + e.getMessage());
        }

        return conn;
    }
}
