package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class Peluche extends Producto implements Serializable{
	private int tamano;
	
	public Peluche() {
		// TODO Auto-generated constructor stub
	}
	

	public Peluche(int tamano) {
		super();
		this.tamano = tamano;
	}


	public Peluche(String nombre, int precio, String imagen, int tamano) {
		super(nombre, precio, imagen);
		this.tamano = tamano;
	}


	public Peluche(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}


	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}


	@Override
	public String toString() {
		return super.toString()+ "Peluche [tamano=" + tamano + "]";
	}
	

}
