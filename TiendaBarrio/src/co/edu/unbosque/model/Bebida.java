package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class Bebida extends Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String presentacion;

	public Bebida() {
		// TODO Auto-generated constructor stub
	}

	public Bebida(String presentacion) {
		super();
		this.presentacion = presentacion;
	}

	public Bebida(String nombre, int precio, String imagen, String presentacion) {
		super(nombre, precio, imagen);
		this.presentacion = presentacion;
	}

	public Bebida(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	@Override
	public String toString() {
		return "Bebida [presentacion=" + presentacion + "]";
	}

}
