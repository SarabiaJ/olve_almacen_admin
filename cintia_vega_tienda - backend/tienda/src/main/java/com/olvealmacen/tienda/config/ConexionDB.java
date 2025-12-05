package com.olvealmacen.tienda.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

    private Connection conexion;

    private final String URL = "jdbc:mysql://bu3rt8ydrm49xkd4i9kg-mysql.services.clever-cloud.com:3306/bu3rt8ydrm49xkd4i9kg";
    private final String USER = "ul9ouwu7gio8snht";
    private final String PASS = "Rts8pMQwJLmhcmyBCSP5";

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASS);
            return conexion;
        } catch (Exception e) {
            System.out.println("ERROR CONECTANDO A BD: " + e.getMessage());
            return null;
        }
    }
}
