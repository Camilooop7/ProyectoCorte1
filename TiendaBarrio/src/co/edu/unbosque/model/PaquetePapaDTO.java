package co.edu.unbosque.model;

import java.io.Serializable;

public class PaquetePapaDTO extends Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean esPicante;

	public PaquetePapaDTO() {
		// TODO Auto-generated constructor stub
	}

	public PaquetePapaDTO(boolean esPicante) {
		super();
		this.esPicante = esPicante;
	}

	public PaquetePapaDTO(String nombre, int precio, String imagen, boolean esPicante) {
		super(nombre, precio, imagen);
		this.esPicante = esPicante;
	}

	public PaquetePapaDTO(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	public boolean isEsPicante() {
		return esPicante;
	}

	public void setEsPicante(boolean esPicante) {
		this.esPicante = esPicante;
	}

}
