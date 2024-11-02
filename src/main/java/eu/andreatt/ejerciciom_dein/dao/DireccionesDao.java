package eu.andreatt.ejerciciom_dein.dao;

import eu.andreatt.ejerciciom_dein.bbdd.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DireccionesDao {
	private ConexionBD conexion;
	
	/** INSERTAR DIRECCIÓN EN BASE DE DATOS */
	public boolean insertarDireccion(String pais, String ciudad, String calle, int numero) {
		try {
			conexion = new ConexionBD();
			String consulta = "INSERT INTO direcciones (pais, ciudad, calle, numero) VALUES (?, ?, ?, ?)";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, pais);
	        pstmt.setString(2, ciudad);
	        pstmt.setString(3, calle);
	        pstmt.setInt(4, numero);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** ACTUALIZAR DIRECCIÓN EN BASE DE DATOS */
	public boolean actualizarDireccion(int id, String pais, String ciudad, String calle, int numero) {
		try {
			conexion = new ConexionBD();       
			String consulta = "UPDATE direcciones SET pais = ?, ciudad = ?, calle = ?, numero = ? WHERE id = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, pais);
	        pstmt.setString(2, ciudad);
	        pstmt.setString(3, calle);
	        pstmt.setInt(4, numero);
	        pstmt.setInt(5, id);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** VALIDAR SI EXISTE DIRECCIÓN EN BASE DE DATOS */
	public int existeDireccion(String pais, String ciudad, String calle, int numero) {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT id FROM direcciones WHERE pais = ? AND ciudad = ? AND calle = ? AND numero = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, pais);
	        pstmt.setString(2, ciudad);
	        pstmt.setString(3, calle);
	        pstmt.setInt(4, numero);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("id");
	        }

	        rs.close();
	        conexion.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
	
	/** OBTENER ID MÁS GRANDE DE LAS DIRECCIONES EN BASE DE DATOS */
	public int dameMaxIdDirecciones() {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT MAX(id) AS max_id FROM direcciones";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("max_id");
	        }

	        rs.close();
	        conexion.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return -1;
	}
}