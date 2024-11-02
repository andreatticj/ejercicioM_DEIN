package eu.andreatt.ejerciciom_dein.model;

import java.util.Objects;

/** CLASE INFORMACIONAEROPUERTOSPUBLICOS PARA LOS EJERCICIOS (L-M) */
public class InformacionAeropuertosPublicos {

	private int id, anioInauguracion, capacidad, numero, numTrabajadores;
	private float financiacion;
	private String nombre, pais, ciudad, calle;

	public InformacionAeropuertosPublicos(int id, String nombre, int anioInauguracion, int capacidad, String pais, String ciudad, String calle, int numero, float financiacion, int numTrabajadores) {
		this.id = id;
		this.nombre = nombre;
		this.anioInauguracion = anioInauguracion;
		this.capacidad = capacidad;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
		this.financiacion = financiacion;
		this.numTrabajadores = numTrabajadores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnioInauguracion() {
		return anioInauguracion;
	}

	public void setAnioInauguracion(int anioInauguracion) {
		this.anioInauguracion = anioInauguracion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getNumTrabajadores() {
		return numTrabajadores;
	}

	public void setNumTrabajadores(int numTrabajadores) {
		this.numTrabajadores = numTrabajadores;
	}

	public float getFinanciacion() {
		return financiacion;
	}

	public void setFinanciacion(float financiacion) {
		this.financiacion = financiacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	@Override
	public int hashCode() {
		return Objects.hash(anioInauguracion, calle, capacidad, ciudad, financiacion, id, nombre, numTrabajadores,
				numero, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacionAeropuertosPublicos other = (InformacionAeropuertosPublicos) obj;
		return anioInauguracion == other.anioInauguracion && Objects.equals(calle, other.calle)
				&& capacidad == other.capacidad && Objects.equals(ciudad, other.ciudad)
				&& Float.floatToIntBits(financiacion) == Float.floatToIntBits(other.financiacion) && id == other.id
				&& Objects.equals(nombre, other.nombre) && numTrabajadores == other.numTrabajadores
				&& numero == other.numero && Objects.equals(pais, other.pais);
	}
}