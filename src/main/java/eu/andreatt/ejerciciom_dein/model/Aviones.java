package eu.andreatt.ejerciciom_dein.model;

import java.util.Objects;

/** CLASE AVIONES PARA LOS EJERCICIOS (L-M) */
public class Aviones {

	/** VARIABLES */
	private int id, numeroAsientos, activado, idAeropuerto;
	private String modelo;
	private float velocidadMaxima;

	/** CONSTRUCTOR */
	public Aviones(int id, String modelo, int numeroAsientos, float velocidadMaxima, int activado, int idAeropuerto) {
		this.id = id;
		this.modelo = modelo;
		this.numeroAsientos = numeroAsientos;
		this.velocidadMaxima = velocidadMaxima;
		this.activado = activado;
		this.idAeropuerto = idAeropuerto;
	}

	/** GETTERS Y SETTERS */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroAsientos() {
		return numeroAsientos;
	}

	public void setNumeroAsientos(int numeroAsientos) {
		this.numeroAsientos = numeroAsientos;
	}

	public float getVelocidadMaxima() {
		return velocidadMaxima;
	}

	public void setVelocidadMaxima(float velocidadMaxima) {
		this.velocidadMaxima = velocidadMaxima;
	}

	public int getActivado() {
		return activado;
	}

	public void setActivado(int activado) {
		this.activado = activado;
	}

	public int getIdAeropuerto() {
		return idAeropuerto;
	}

	public void setIdAeropuerto(int idAeropuerto) {
		this.idAeropuerto = idAeropuerto;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(activado, id, idAeropuerto, modelo, numeroAsientos, velocidadMaxima);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aviones other = (Aviones) obj;
		return activado == other.activado && id == other.id && idAeropuerto == other.idAeropuerto
				&& Objects.equals(modelo, other.modelo) && numeroAsientos == other.numeroAsientos
				&& velocidadMaxima == other.velocidadMaxima;
	}
	
	/** TO STRING */
	public String toString() {
		return this.modelo;
	}
}