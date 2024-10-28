package eu.andreatt.ejerciciol_dein.bbdd;

import eu.andreatt.ejerciciol_dein.util.Propiedades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConexionBD {
    private Connection conexion;
    
    public ConexionBD() throws SQLException {
        String url = Propiedades.getValor("url") + "?serverTimezone=" + TimeZone.getDefault().getID();
        String user = Propiedades.getValor("user");
        String password = Propiedades.getValor("password");

        System.out.println("Conectando a la base de datos con URL: " + url + ", Usuario: " + user);
        conexion = DriverManager.getConnection(url, user, password);
        conexion.setAutoCommit(true);
    }
    public Connection getConexion() {
        return conexion;
    }
    public void closeConnection() throws SQLException {
    	conexion.close();
    }
}