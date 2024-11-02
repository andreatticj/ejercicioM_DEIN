package eu.andreatt.ejerciciom_dein.model;

import java.util.Objects;

/** CLASE INFORMACIONAEROPUERTOSPRIVADOS PARA LOS EJERCICIOS (L-M) */
public class InformacionAeropuertosPrivados {

	/** VARIABLES */
	private int id, anioInauguracion, capacidad, numero, numeroSocios;
	private String nombre, pais, ciudad, calle;
	
	/** CONSTRUCTOR */
	public InformacionAeropuertosPrivados(int id, String nombre, int anioInauguracion, int capacidad, String pais, String ciudad, String calle, int numero, int numeroSocios) {
		this.id=id;
		this.anioInauguracion=anioInauguracion;
		this.capacidad=capacidad;
		this.numero=numero;
		this.numeroSocios=numeroSocios;
		this.nombre=nombre;
		this.pais=pais;
		this.ciudad=ciudad;
		this.calle=calle;
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getNumeroSocios() {
		return numeroSocios;
	}

	public void setNumeroSocios(int numeroSocios) {
		this.numeroSocios = numeroSocios;
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

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(anioInauguracion, calle, capacidad, ciudad, id, nombre, numero, numeroSocios, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformacionAeropuertosPrivados other = (InformacionAeropuertosPrivados) obj;
		return anioInauguracion == other.anioInauguracion && Objects.equals(calle, other.calle)
				&& capacidad == other.capacidad && Objects.equals(ciudad, other.ciudad) && id == other.id
				&& Objects.equals(nombre, other.nombre) && numero == other.numero && numeroSocios == other.numeroSocios
				&& Objects.equals(pais, other.pais);
	}
}