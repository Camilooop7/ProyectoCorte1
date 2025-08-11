package co.edu.unbosque.model;

import java.io.Serializable;

public class MovilDTO extends DispositivoElectronico implements Serializable {
	private static final long serialVersionUID = 1L;

	private int almacenamiento;

	public MovilDTO() {
		// TODO Auto-generated constructor stub
	}

	public MovilDTO(int almacenamiento) {
		super();
		this.almacenamiento = almacenamiento;
	}

	public MovilDTO(String nombre, int precio, String imagen, String modelo, int almacenamiento) {
		super(nombre, precio, imagen, modelo);
		this.almacenamiento = almacenamiento;
	}

	public MovilDTO(String nombre, int precio, String imagen, String modelo) {
		super(nombre, precio, imagen, modelo);
		// TODO Auto-generated constructor stub
	}

	public int getAlmacenamiento() {
		return almacenamiento;
	}

	public void setAlmacenamiento(int almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	@Override
	public String toString() {
		return super.toString() + "Movil [almacenamiento=" + almacenamiento + "]";
	}

}
