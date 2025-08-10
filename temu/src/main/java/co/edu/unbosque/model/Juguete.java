package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class Juguete extends Producto implements Serializable{
	private int edadRecomendada;
	public Juguete() {
		// TODO Auto-generated constructor stub
	}
	public Juguete(int edadRecomendada) {
		super();
		this.edadRecomendada = edadRecomendada;
	}
	public Juguete(String nombre, int precio, String imagen, int edadRecomendada) {
		super(nombre, precio, imagen);
		this.edadRecomendada = edadRecomendada;
	}
	public Juguete(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}
	public int getEdadRecomendada() {
		return edadRecomendada;
	}
	public void setEdadRecomendada(int edadRecomendada) {
		this.edadRecomendada = edadRecomendada;
	}
	@Override
	public String toString() {
		return super.toString()+ "Juguete [edadRecomendada=" + edadRecomendada + "]";
	}
	

}
