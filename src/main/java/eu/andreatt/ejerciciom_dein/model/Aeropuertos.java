package eu.andreatt.ejerciciom_dein.model;

import java.sql.Blob;
import java.util.Objects;

/**
 * Clase que representa un aeropuerto en el contexto de los ejercicios L-M.
 * Incluye información sobre el aeropuerto como su nombre, año de inauguración, capacidad y un identificador de dirección.
 */
public class Aeropuertos {

	/** Identificador único del aeropuerto. */
	private int id;

	/** Año en que se inauguró el aeropuerto. */
	private int anioInauguracion;

	/** Capacidad del aeropuerto en términos de número de pasajeros. */
	private int capacidad;

	/** Identificador de la dirección asociada al aeropuerto. */
	private int id_direccion;

	/** Nombre del aeropuerto. */
	private String nombre;

	/** Imagen del aeropuertp*/
	private Blob imagen;

	/**
     * Constructor que inicializa un objeto Aeropuertos con los parámetros especificados.
     *
     * @param id               Identificador único del aeropuerto.
     * @param nombre           Nombre del aeropuerto.
     * @param anioInauguracion Año de inauguración del aeropuerto.
     * @param capacidad        Capacidad máxima del aeropuerto.
     * @param id_direccion     Identificador de la dirección asociada al aeropuerto.
     * @param imagen
     */
	public Aeropuertos(int id, String nombre, int anioInauguracion, int capacidad, int id_direccion, Blob imagen) {
		this.id = id;
		this.nombre = nombre;
		this.anioInauguracion = anioInauguracion;
		this.capacidad = capacidad;
		this.id_direccion = id_direccion;
		this.imagen = imagen;
	}

	/**
	 * Obtiene el identificador del aeropuerto.
	 *
	 * @return id El identificador único del aeropuerto.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador del aeropuerto.
	 *
	 * @param id Identificador único del aeropuerto a establecer.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el año de inauguración del aeropuerto.
	 *
	 * @return anioInauguracion El año de inauguración.
	 */
	public int getAnioInauguracion() {
		return anioInauguracion;
	}

	/**
	 * Establece el año de inauguración del aeropuerto.
	 *
	 * @param anioInauguracion Año de inauguración a establecer.
	 */
	public void setAnioInauguracion(int anioInauguracion) {
		this.anioInauguracion = anioInauguracion;
	}

	/**
	 * Obtiene la capacidad del aeropuerto.
	 *
	 * @return capacidad La capacidad del aeropuerto en términos de pasajeros.
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
	 * Obtiene el identificador de la dirección asociada al aeropuerto.
	 *
	 * @return id_direccion El identificador de la dirección.
	 */
	public int getId_direccion() {
		return id_direccion;
	}

	/**
	 * Establece el identificador de la dirección asociada al aeropuerto.
	 *
	 * @param id_direccion Identificador de la dirección a establecer.
	 */
	public void setId_direccion(int id_direccion) {
		this.id_direccion = id_direccion;
	}

	/**
	 * Obtiene el nombre del aeropuerto.
	 *
	 * @return nombre El nombre del aeropuerto.
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

	public Blob getImagen() {
		return imagen;
	}

	/**
	 * Calcula y devuelve el código hash de este objeto Aeropuertos.
	 *
	 * @return int Código hash basado en los atributos relevantes del aeropuerto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(anioInauguracion, capacidad, id, id_direccion, nombre);
	}

	/**
	 * Compara este objeto Aeropuertos con otro para determinar si son iguales.
	 *
	 * @param obj Objeto con el que se comparará este Aeropuertos.
	 * @return boolean True si los objetos son iguales, false de lo contrario.
	 */
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

	/**
	 * Devuelve una representación en forma de cadena del nombre del aeropuerto.
	 *
	 * @return String Nombre del aeropuerto.
	 */
	public String toString() {
		return this.nombre;
	}
}
