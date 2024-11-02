package eu.andreatt.ejerciciom_dein.model;

import java.util.Objects;

/** CLASE USUARIOS PARA LOS EJERCICIOS (L-M) */
public class Usuarios {

	/** VARIABLES */
	private String usuario, password;

	/** CONSTRUCTOR */
	public Usuarios(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}

	/** GETTERS Y SETTERS */
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/** EQUALS Y HASHCODE */
	@Override
	public int hashCode() {
		return Objects.hash(password, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios other = (Usuarios) obj;
		return Objects.equals(password, other.password) && Objects.equals(usuario, other.usuario);
	}
}