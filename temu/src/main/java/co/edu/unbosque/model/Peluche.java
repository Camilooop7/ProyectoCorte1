package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class Peluche extends Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String tamano;
	
	public Peluche() {
		// TODO Auto-generated constructor stub
	}
	

	public Peluche(String tamano) {
		super();
		this.tamano = tamano;
	}


	public Peluche(String nombre, int precio, String imagen, String tamano) {
		super(nombre, precio, imagen);
		this.tamano = tamano;
	}


	public Peluche(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}


	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}


	@Override
	public String toString() {
		return super.toString()+ "Peluche [tamano=" + tamano + "]";
	}
	

}
