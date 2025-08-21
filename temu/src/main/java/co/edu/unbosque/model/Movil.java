package co.edu.unbosque.model;

import java.io.Serializable;

public class Movil extends DispositivoElectronico implements Serializable {
	private static final long serialVersionUID = 1L;

	private String almacenamiento;

	public Movil() {
		// TODO Auto-generated constructor stub
	}

	public Movil(String almacenamiento) {
		super();
		this.almacenamiento = almacenamiento;
	}

	public Movil(String nombre, int precio, String imagen, String modelo, String almacenamiento) {
		super(nombre, precio, imagen, modelo);
		this.almacenamiento = almacenamiento;
	}

	public Movil(String nombre, int precio, String imagen, String modelo) {
		super(nombre, precio, imagen, modelo);
		// TODO Auto-generated constructor stub
	}

	public String getAlmacenamiento() {
		return almacenamiento;
	}

	public void setAlmacenamiento(String almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	@Override
	public String toString() {
		return super.toString() + "Movil [almacenamiento=" + almacenamiento + "]";
	}

}
