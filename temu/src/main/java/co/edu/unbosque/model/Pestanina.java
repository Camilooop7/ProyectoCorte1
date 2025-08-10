package co.edu.unbosque.model;

import java.io.Serializable;

public class Pestanina extends Maquillaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private int duracion;

	public Pestanina() {
		// TODO Auto-generated constructor stub
	}

	public Pestanina(int duracion) {
		super();
		this.duracion = duracion;
	}

	public Pestanina(String nombre, int precio, String imagen, boolean esApruebaDeAgua, int duracion) {
		super(nombre, precio, imagen, esApruebaDeAgua);
		this.duracion = duracion;
	}

	public Pestanina(String nombre, int precio, String imagen, boolean esApruebaDeAgua) {
		super(nombre, precio, imagen, esApruebaDeAgua);
		// TODO Auto-generated constructor stub
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return super.toString() + "Pestanina [duracion=" + duracion + "]";
	}

}
