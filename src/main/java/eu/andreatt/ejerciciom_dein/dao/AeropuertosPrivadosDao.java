package eu.andreatt.ejerciciom_dein.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eu.andreatt.ejerciciom_dein.bbdd.ConexionBD;
import eu.andreatt.ejerciciom_dein.model.InformacionAeropuertosPrivados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AeropuertosPrivadosDao {
	private ConexionBD conexion;
	
	/** CARGAR LOS AEROPUERTOS PRIVADOS EXISTENTES EN BASE DE DATOS */
	public ObservableList<InformacionAeropuertosPrivados> cargarAeropuertosPrivados() {

		ObservableList<InformacionAeropuertosPrivados> aeropuertosPrivados = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT a.id, a.nombre, a.anio_inauguracion, a.capacidad, d.pais, d.ciudad, d.calle, d.numero, ap.numero_socios FROM aeropuertos AS a JOIN direcciones AS d ON a.id_direccion = d.id JOIN aeropuertos_privados AS ap ON a.id = ap.id_aeropuerto;";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int anioInauguracion = rs.getInt("anio_inauguracion");
				int capacidad = rs.getInt("capacidad");
				String pais = rs.getString("pais");
				String ciudad = rs.getString("ciudad");
				String calle = rs.getString("calle");
				int numero = rs.getInt("numero");
				int numeroSocios = rs.getInt("numero_socios");
				
				aeropuertosPrivados.add(new InformacionAeropuertosPrivados(id, nombre, anioInauguracion, capacidad, pais, ciudad, calle, numero, numeroSocios));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aeropuertosPrivados;
	}
	
	/** INSERTAR AEROPUERTO PRIVADO EN BASE DE DATOS */
	public boolean insertarAeropuertoPrivado(int idAeropuerto, int numSocios) {
		try {
			conexion = new ConexionBD();       
			String consulta = "INSERT INTO aeropuertos_privados (id_aeropuerto, numero_socios) VALUES (?, ?)";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, idAeropuerto);
	        pstmt.setInt(2, numSocios);

	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR AEROPUERTO PRIVADO EN BASE DE DATOS */
	public boolean borrarAeropuertoPrivado(int idAeropuerto) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM aeropuertos_privados WHERE id_aeropuerto = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, idAeropuerto);
	    	     
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
	
	/** ACTUALIZAR AEROPUERTO PRIVADO EN BASE DE DATOS */
	public boolean actualizarAeropuertoPrivado(int idAeropuerto, int numSocios) {
		try {
			conexion = new ConexionBD();       
			String consulta = "UPDATE aeropuertos_privados SET numero_socios = ? WHERE id_aeropuerto = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, idAeropuerto);
	        pstmt.setInt(2, numSocios);
	    	     
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
}