package co.edu.unbosque.model;

import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int identificacion;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombre, int identificacion) {
		super();
		this.nombre = nombre;
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", identificacion=" + identificacion + "]";
	}

}
