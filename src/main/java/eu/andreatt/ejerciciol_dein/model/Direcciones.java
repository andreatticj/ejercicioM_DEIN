package eu.andreatt.ejerciciol_dein.model;

import java.util.Objects;

/** CLASE DIRECCIONES PARA LOS EJERCICIOS (L-M) */
public class Direcciones {

	/** VARIABLES */
	private int id, numero;
	private String pais, ciudad, calle;

	/** CONSTRUCTOR */
	public Direcciones(int id, String pais, String ciudad, String calle, int numero) {
		this.id = id;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
	}

	/** GETTERS Y SETTERS */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(calle, ciudad, id, numero, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direcciones other = (Direcciones) obj;
		return Objects.equals(calle, other.calle) && Objects.equals(ciudad, other.ciudad) && id == other.id
				&& numero == other.numero && Objects.equals(pais, other.pais);
	}
}