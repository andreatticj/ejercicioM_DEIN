package eu.andreatt.ejerciciom_dein.model;

import java.util.Objects;

/** CLASE AEROPUERTOSPUBLICOS PARA LOS EJERCICIOS (L-M) */
public class AeropuertosPublicos {

	/** VARIABLES */
	private int idAeropuerto, numTrabajadores;
	private float financiacion;

	/** CONSTRUCTOR */
	public AeropuertosPublicos(int idAeropuerto, float financiacion, int numTrabajadores) {
		this.idAeropuerto = idAeropuerto;
		this.financiacion = financiacion;
		this.numTrabajadores = numTrabajadores;
	}

	/** GETTERS Y SETTERS */
	public int getId_aeropuerto() {
		return idAeropuerto;
	}

	public void setId_aeropuerto(int id_aeropuerto) {
		this.idAeropuerto = id_aeropuerto;
	}

	public int getNum_trabajadores() {
		return numTrabajadores;
	}

	public void setNum_trabajadores(int num_trabajadores) {
		this.numTrabajadores = num_trabajadores;
	}

	public float getFinanciacion() {
		return financiacion;
	}

	public void setFinanciacion(float financiacion) {
		this.financiacion = financiacion;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(financiacion, idAeropuerto, numTrabajadores);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AeropuertosPublicos other = (AeropuertosPublicos) obj;
		return Float.floatToIntBits(financiacion) == Float.floatToIntBits(other.financiacion)
				&& idAeropuerto == other.idAeropuerto && numTrabajadores == other.numTrabajadores;
	}
}