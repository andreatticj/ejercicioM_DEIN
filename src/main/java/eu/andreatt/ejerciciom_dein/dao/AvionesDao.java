package eu.andreatt.ejerciciom_dein.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import eu.andreatt.ejerciciom_dein.bbdd.ConexionBD;
import eu.andreatt.ejerciciom_dein.model.Aviones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AvionesDao {
	private ConexionBD conexion;
		
	/** CARGAR LOS AVIONES EXISTENTES EN BASE DE DATOS */
	public ObservableList<Aviones> cargarAviones() {

		ObservableList<Aviones> aviones = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT id, modelo, numero_asientos, velocidad_maxima, activado, id_aeropuerto FROM aviones";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String modelo = rs.getString("modelo");
				int numero_asientos = rs.getInt("numero_asientos");
				int velocidad_maxima = rs.getInt("velocidad_maxima");
				int activado = rs.getInt("activado");
				int id_aeropuerto = rs.getInt("id_aeropuerto");
				
				aviones.add(new Aviones(id, modelo, numero_asientos, velocidad_maxima, activado, id_aeropuerto));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aviones;
	}
	
	/** CARGAR LOS AVIONES SEGÚN EL AEROPUERTO SOLICITADO*/
	public ObservableList<Aviones> dameAvionesPorAeropuerto(int ida) {

		ObservableList<Aviones> aviones = FXCollections.observableArrayList();
		try {
			conexion = new ConexionBD();
			String consulta = "SELECT id, modelo, numero_asientos, velocidad_maxima, activado, id_aeropuerto FROM aviones where id_aeropuerto = "+ida;
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String modelo = rs.getString("modelo");
				int numero_asientos = rs.getInt("numero_asientos");
				float velocidad_maxima = rs.getFloat("velocidad_maxima");
				int activado = rs.getInt("activado");
				int id_aeropuerto = rs.getInt("id_aeropuerto");
				
				aviones.add(new Aviones(id, modelo, numero_asientos, velocidad_maxima, activado, id_aeropuerto));
			}
			rs.close();
			conexion.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aviones;
	}
	
	/** INSERTAR AVIÓN EN BASE DE DATOS */
	public boolean insertarAvion(String modelo, int numero_asientos, float velocidad_maxima, int activado, int id_aeropuerto) {
		try {
			conexion = new ConexionBD();       
			String consulta = "INSERT INTO aviones (modelo,numero_asientos,velocidad_maxima,activado,id_aeropuerto) VALUES (?, ?, ?, ?,?)";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, modelo);
	        pstmt.setInt(2, numero_asientos);
	        pstmt.setFloat(3, velocidad_maxima);
	        pstmt.setInt(4, activado);
	        pstmt.setInt(5, id_aeropuerto);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** BORRAR AVIÓN EN BASE DE DATOS */
	public boolean borrarAvion(int id) {
		try {
			conexion = new ConexionBD();       
			String consulta = "DELETE FROM aviones WHERE id = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, id);
	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** ACTUALIZAR AVIÓN EN BASE DE DATOS */
	public boolean actualizarAvionActivo(int id, int activado) {
		try {
			conexion = new ConexionBD();       
			String consulta = "UPDATE aviones SET activado = ? WHERE id = ?";
	
	    	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setInt(1, activado);
	        pstmt.setInt(2, id);

	    	     
	    	pstmt.executeUpdate();        
	    	conexion.closeConnection();
	    	return true;
	    
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    } 
		return false;
	}
	
	/** VALIDAR SI EXISTE MODELO DE AVIÓN EN BASE DE DATOS */
	public boolean existeModeloEnAeropuerto(String modelo, int id_aeropuerto) {
	    boolean existe = false;

	    try {
	        conexion = new ConexionBD();
	        String consulta = "SELECT * FROM aviones WHERE modelo = ? and id_aeropuerto = ?";
	        PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
	        pstmt.setString(1, modelo);
	        pstmt.setInt(2, id_aeropuerto);

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
}