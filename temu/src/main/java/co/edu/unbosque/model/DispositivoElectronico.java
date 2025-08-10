package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class DispositivoElectronico extends Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String modelo;

	public DispositivoElectronico() {
		// TODO Auto-generated constructor stub
	}

	public DispositivoElectronico(String modelo) {
		super();
		this.modelo = modelo;
	}

	public DispositivoElectronico(String nombre, int precio, String imagen, String modelo) {
		super(nombre, precio, imagen);
		this.modelo = modelo;
	}

	public DispositivoElectronico(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		return super.toString() + "DispositivoElectronico [modelo=" + modelo + "]";
	}

}
