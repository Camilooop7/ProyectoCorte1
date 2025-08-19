package co.edu.unbosque.model;

import java.io.Serializable;

public class PestaninaDTO extends Maquillaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private String duracion;

	public PestaninaDTO() {
		// TODO Auto-generated constructor stub
	}

	public PestaninaDTO(String duracion) {
		super();
		this.duracion = duracion;
	}

	public PestaninaDTO(String nombre, int precio, String imagen, String esApruebaDeAgua, String duracion) {
		super(nombre, precio, imagen, esApruebaDeAgua);
		this.duracion = duracion;
	}

	public PestaninaDTO(String nombre, int precio, String imagen, String esApruebaDeAgua) {
		super(nombre, precio, imagen, esApruebaDeAgua);
		// TODO Auto-generated constructor stub
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return super.toString() + "Pestanina [duracion=" + duracion + "]";
	}

}
