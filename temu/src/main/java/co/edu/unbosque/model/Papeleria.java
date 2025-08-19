package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class Papeleria extends Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cantidadPaquete;

	public Papeleria() {
		// TODO Auto-generated constructor stub
	}

	public Papeleria(String cantidadPaquete) {
		super();
		this.cantidadPaquete = cantidadPaquete;
	}

	public Papeleria(String nombre, int precio, String imagen, String cantidadPaquete) {
		super(nombre, precio, imagen);
		this.cantidadPaquete = cantidadPaquete;
	}

	public Papeleria(String nombre, int  precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	public String getCantidadPaquete() {
		return cantidadPaquete;
	}

	public void setCantidadPaquete(String cantidadPaquete) {
		this.cantidadPaquete = cantidadPaquete;
	}

	@Override
	public String toString() {
		return super.toString() + "Papeleria [cantidadPaquete=" + cantidadPaquete + "]";
	}

}
