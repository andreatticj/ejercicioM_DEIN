package eu.andreatt.ejerciciol_dein.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import eu.andreatt.ejerciciol_dein.bbdd.ConexionBD;
import eu.andreatt.ejerciciol_dein.model.Aeropuertos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AeropuertosDao {
	private ConexionBD conexion;
	
	/** CARGAR LOS AEROPUERTOS EXISTENTES EN BASE DE DATOS */
	public ObservableList<Aeropuertos> cargarAeropuertos() {

		ObservableList<Aeropuertos> aeropuertos = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT id, nombre, anio_inauguracion, capacidad, id_direccion FROM aeropuertos";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int anio_inauguracion = rs.getInt("anio_inauguracion");
				int capacidad = rs.getInt("capacidad");
				int id_direccion = rs.getInt("id_direccion");
				
				aeropuertos.add(new Aeropuertos(id, nombre, anio_inauguracion, capacidad, id_direccion));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aeropuertos;
	}
	
	/** INSERTAR AEROPUERTO EN BASE DE DATOS */
	public boolean insertarAeropuerto(String nombre, int anioInauguracion, int capacidad, int idDireccion) {
		try {
			conexion = new ConexionBD();       
			String consulta = "INSERT INTO aeropuertos (nombre, anio_inauguracion, capacidad, id_direccion) VALUES (?, ?, ?, ?)";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);
	        pstmt.setInt(2, anioInauguracion);
	        pstmt.setInt(3, capacidad);
	        pstmt.setInt(4, idDireccion);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR AEROPUERTO EN BASE DE DATOS */
	public boolean borrarAeropuerto(String nombre, int anioInauguracion, int capacidad, int idDireccion) {
		try {
		    conexion = new ConexionBD();
		    String consulta = "DELETE FROM aeropuertos WHERE nombre = ? AND anio_inauguracion = ? AND capacidad = ? AND id_direccion = ?";
		    PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
		    
		    pstmt.setString(1, nombre);
		    pstmt.setInt(2, anioInauguracion);
		    pstmt.setInt(3, capacidad);
		    pstmt.setInt(4, idDireccion);

		    int filasAfectadas = pstmt.executeUpdate();
		    
		    conexion.closeConnection();

		    if (filasAfectadas > 0) {
		        return true;
		    } else {
		        return false;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return false;
	}
	
	/** ACTUALIZAR AEROPUERTO EN BASE DE DATOS */
	public boolean actualizarAeropuerto(int id, String nombre, int anioInauguracion, int capacidad, int id_direccion) {
		try {
			conexion = new ConexionBD();       
			String consulta = "UPDATE aeropuertos SET nombre = ?, anio_inauguracion = ?, capacidad = ?, id_direccion = ? WHERE id = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);
	        pstmt.setInt(2, anioInauguracion);
	        pstmt.setInt(3, capacidad);
	        pstmt.setInt(4, id_direccion);
	        pstmt.setInt(5, id);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** OBTENER EL ID MÁS GRANDE DE LOS AEROPUERTOS EN BASE DE DATOS */
	public int dameMaxIdAeropuertos() {
	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT MAX(id) AS max_id FROM aeropuertos";
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
	
	/** OBTERNER EL ID DEL AEROPUERTO SOLICITADO */
	public int dameIdDeAeropuerto(String nombre, int anioInauguracion, int capacidad, int idDireccion) {
		try {
		    conexion = new ConexionBD();
		    String consulta = "SELECT id FROM aeropuertos WHERE nombre = ? AND anio_inauguracion = ? AND capacidad = ? AND id_direccion = ?";
		    PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
		    
		    pstmt.setString(1, nombre);
		    pstmt.setInt(2, anioInauguracion);
		    pstmt.setInt(3, capacidad);
		    pstmt.setInt(4, idDireccion);

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
	
	/** VALIDAR SI EXISTE EL NOMBRE DEL AEROPUERTO EN BASE DE DATOS */
	public boolean existeNombreAeropuerto(String nombre) {
	    boolean existe = false;

	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT * FROM aeropuertos WHERE nombre = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, nombre);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            existe = true;
	        }

	        rs.close();
	        conexion.closeConnection();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return existe;
	}

	/** INSERTAR IMAGEN EN AEROPUERTO DE BASE DE DATOS */
	public boolean insertarImagen(String ruta, int id) {
		try {	
			File file = new File(ruta);
			byte[] imageData = Files.readAllBytes(file.toPath());
			
			conexion = new ConexionBD();       
			String consulta = "UPDATE aeropuertos SET imagen = ? WHERE id = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setBytes(1, imageData);
	        pstmt.setInt(2, id);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException | IOException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** OBTENER IMAGEN DE AEROPUERTO DE BASE DE DATOS */
	public InputStream dameImagen(int id) {
		try {
		    conexion = new ConexionBD();
		    String consulta = "SELECT imagen FROM aeropuertos WHERE id = ?";
		    PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
		    
		    pstmt.setInt(1, id);
		    ResultSet rs = pstmt.executeQuery();

		    if (rs.next()) {
		        return rs.getBinaryStream("imagen");
		    }

		    rs.close();
		    conexion.closeConnection();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return null;
	}	
}