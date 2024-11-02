package eu.andreatt.ejerciciom_dein.model;

import java.util.Objects;

/** CLASE AEROPUERTOS PARA LOS EJERCICIOS (L-M) */
public class Aeropuertos {

	/** VARIABLES */
	private int id, anioInauguracion, capacidad, id_direccion;
	private String nombre;

	/** CONSTRUCTOR */
	public Aeropuertos(int id, String nombre, int anioInauguracion, int capacidad, int id_direccion) {
		this.id = id;
		this.nombre = nombre;
		this.anioInauguracion = anioInauguracion;
		this.capacidad = capacidad;
		this.id_direccion = id_direccion;
	}

	/** GETTERS Y SETTERS */
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

	public int getId_direccion() {
		return id_direccion;
	}

	public void setId_direccion(int id_direccion) {
		this.id_direccion = id_direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(anioInauguracion, capacidad, id, id_direccion, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aeropuertos other = (Aeropuertos) obj;
		return anioInauguracion == other.anioInauguracion && capacidad == other.capacidad && id == other.id
				&& id_direccion == other.id_direccion && Objects.equals(nombre, other.nombre);
	}	
	
	/** TO STRING */
	public String toString() {
		return this.nombre;
	}
}