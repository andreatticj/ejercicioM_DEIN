package eu.andreatt.ejerciciom_dein.model;

import java.sql.Blob;
import java.util.Objects;

/**
 * Clase que representa la información de los aeropuertos públicos en el contexto de los ejercicios L-M.
 * Esta clase almacena detalles sobre el aeropuerto, incluyendo su nombre, ubicación,
 * capacidad, financiación y número de trabajadores.
 */
public class InformacionAeropuertosPublicos {

	/** Identificador único del aeropuerto público. */
	private int id;

	/** Año en que se inauguró el aeropuerto. */
	private int anioInauguracion;

	/** Capacidad máxima del aeropuerto (número de aviones o pasajeros). */
	private int capacidad;

	/** Número específico de la dirección del aeropuerto. */
	private int numero;

	/** Número de trabajadores que operan en el aeropuerto público. */
	private int numTrabajadores;

	/** Monto de financiación asignado al aeropuerto. */
	private double financiacion;

	/** Nombre del aeropuerto. */
	private String nombre;

	/** País donde se encuentra el aeropuerto. */
	private String pais;

	/** Ciudad donde se ubica el aeropuerto. */
	private String ciudad;

	/** Calle donde se localiza el aeropuerto. */
	private String calle;

	private Blob imagen;

	/**
	 * Constructor que inicializa un objeto InformacionAeropuertosPublicos
	 * con los parámetros proporcionados.
	 *
	 * @param id Identificador único del aeropuerto público.
	 * @param nombre Nombre del aeropuerto.
	 * @param anioInauguracion Año de inauguración del aeropuerto.
	 * @param capacidad Capacidad máxima del aeropuerto.
	 * @param pais Nombre del país donde se ubica el aeropuerto.
	 * @param ciudad Nombre de la ciudad donde se localiza el aeropuerto.
	 * @param calle Nombre de la calle donde se encuentra el aeropuerto.
	 * @param numero Número específico de la dirección del aeropuerto.
	 * @param financiacion Monto de financiación asignado al aeropuerto.
	 * @param numTrabajadores Número de trabajadores que operan en el aeropuerto.
	 */
	public InformacionAeropuertosPublicos(int id, String nombre, int anioInauguracion, int capacidad, String pais, String ciudad, String calle, int numero, double financiacion, int numTrabajadores, Blob imagen) {
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
		this.imagen = imagen;
	}

	/**
	 * Obtiene el identificador del aeropuerto.
	 *
	 * @return id El identificador único del aeropuerto público.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador del aeropuerto.
	 *
	 * @param id Identificador único a establecer para el aeropuerto público.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el año de inauguración del aeropuerto.
	 *
	 * @return anioInauguracion El año en que se inauguró el aeropuerto.
	 */
	public int getAnioInauguracion() {
		return anioInauguracion;
	}

	/**
	 * Establece el año de inauguración del aeropuerto.
	 *
	 * @param anioInauguracion Año a establecer para la inauguración del aeropuerto.
	 */
	public void setAnioInauguracion(int anioInauguracion) {
		this.anioInauguracion = anioInauguracion;
	}

	/**
	 * Obtiene la capacidad del aeropuerto.
	 *
	 * @return capacidad La capacidad máxima del aeropuerto.
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * Establece la capacidad del aeropuerto.
	 *
	 * @param capacidad Capacidad a establecer para el aeropuerto.
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * Obtiene el número específico de la dirección del aeropuerto.
	 *
	 * @return numero El número de la dirección del aeropuerto.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Establece el número específico de la dirección del aeropuerto.
	 *
	 * @param numero Número a establecer para la dirección del aeropuerto.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Obtiene el número de trabajadores del aeropuerto.
	 *
	 * @return numTrabajadores El número de trabajadores que operan en el aeropuerto.
	 */
	public int getNumTrabajadores() {
		return numTrabajadores;
	}

	/**
	 * Establece el número de trabajadores del aeropuerto.
	 *
	 * @param numTrabajadores Número de trabajadores a establecer para el aeropuerto.
	 */
	public void setNumTrabajadores(int numTrabajadores) {
		this.numTrabajadores = numTrabajadores;
	}

	/**
	 * Obtiene el monto de financiación del aeropuerto.
	 *
	 * @return financiacion El monto de financiación asignado al aeropuerto.
	 */
	public double getFinanciacion() {
		return financiacion;
	}

	/**
	 * Establece el monto de financiación del aeropuerto.
	 *
	 * @param financiacion Monto a establecer para la financiación del aeropuerto.
	 */
	public void setFinanciacion(float financiacion) {
		this.financiacion = financiacion;
	}

	/**
	 * Obtiene el nombre del aeropuerto.
	 *
	 * @return nombre El nombre del aeropuerto público.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del aeropuerto.
	 *
	 * @param nombre Nombre a establecer para el aeropuerto.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el país del aeropuerto.
	 *
	 * @return pais El nombre del país donde se encuentra el aeropuerto.
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Establece el país del aeropuerto.
	 *
	 * @param pais Nombre del país a establecer para el aeropuerto.
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Obtiene la ciudad del aeropuerto.
	 *
	 * @return ciudad El nombre de la ciudad donde se encuentra el aeropuerto.
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Establece la ciudad del aeropuerto.
	 *
	 * @param ciudad Nombre de la ciudad a establecer para el aeropuerto.
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Obtiene la calle del aeropuerto.
	 *
	 * @return calle El nombre de la calle donde se localiza el aeropuerto.
	 */
	public String getCalle() {
		return calle;
	}

	/**
	 * Establece la calle del aeropuerto.
	 *
	 * @param calle Nombre de la calle a establecer para el aeropuerto.
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}

	/**
	 * Calcula y devuelve el código hash de este objeto InformacionAeropuertosPublicos.
	 *
	 * @return int Código hash basado en los atributos relevantes del aeropuerto público.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(anioInauguracion, calle, capacidad, ciudad, financiacion, id, nombre, numTrabajadores, numero, pais);
	}

	/**
	 * Compara este objeto InformacionAeropuertosPublicos con otro para determinar si son iguales.
	 *
	 * @param obj Objeto con el que se comparará esta información de aeropuerto público.
	 * @return boolean True si los aeropuertos son iguales, false de lo contrario.
	 */
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
				&& Double.doubleToLongBits(financiacion) == Double.doubleToLongBits(other.financiacion) && id == other.id
				&& Objects.equals(nombre, other.nombre) && numTrabajadores == other.numTrabajadores
				&& numero == other.numero && Objects.equals(pais, other.pais);
	}

	public Blob getImagen() {
		return imagen;
	}
}
