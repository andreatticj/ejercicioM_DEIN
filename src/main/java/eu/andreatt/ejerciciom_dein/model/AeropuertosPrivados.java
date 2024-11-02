package eu.andreatt.ejerciciom_dein.model;

import java.util.Objects;

/** CLASE AEROPUERTOSPRIVADOS PARA LOS EJERCICIOS (L-M) */
public class AeropuertosPrivados {

	/** VARIABLES */
	private int idAeropuerto, numeroSocios;

	/** CONSTRUTOR */
	public AeropuertosPrivados(int idAeropuerto, int numeroSocios) {
		this.idAeropuerto = idAeropuerto;
		this.numeroSocios = numeroSocios;
	}

	/** GETTERS Y SETTERS */
	public int getId_aeropuerto() {
		return idAeropuerto;
	}

	public void setId_aeropuerto(int id_aeropuerto) {
		this.idAeropuerto = id_aeropuerto;
	}

	public int getNumero_socios() {
		return numeroSocios;
	}

	public void setNumero_socios(int numero_socios) {
		this.numeroSocios = numero_socios;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(idAeropuerto, numeroSocios);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AeropuertosPrivados other = (AeropuertosPrivados) obj;
		return idAeropuerto == other.idAeropuerto && numeroSocios == other.numeroSocios;
	}
}