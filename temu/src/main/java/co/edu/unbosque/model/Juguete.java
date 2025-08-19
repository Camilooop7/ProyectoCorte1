package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class Juguete extends Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String edadRecomendada;
	public Juguete() {
		// TODO Auto-generated constructor stub
	}
	public Juguete(String edadRecomendada) {
		super();
		this.edadRecomendada = edadRecomendada;
	}
	public Juguete(String nombre, int precio, String imagen, String edadRecomendada) {
		super(nombre, precio, imagen);
		this.edadRecomendada = edadRecomendada;
	}
	public Juguete(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}
	public String getEdadRecomendada() {
		return edadRecomendada;
	}
	public void setEdadRecomendada(String edadRecomendada) {
		this.edadRecomendada = edadRecomendada;
	}
	@Override
	public String toString() {
		return super.toString()+ "Juguete [edadRecomendada=" + edadRecomendada + "]";
	}
	

}
