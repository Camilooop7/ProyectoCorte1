package co.edu.unbosque.model;

import java.io.Serializable;

public class Fruta extends Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String aroma;

	private String sabor;

	public Fruta() {
		// TODO Auto-generated constructor stub
	}

	public Fruta(String aroma, String sabor) {
		super();
		this.aroma = aroma;
		this.sabor = sabor;
	}

	public Fruta(String nombre, int precio, String imagen, String aroma, String sabor) {
		super(nombre, precio, imagen);
		this.aroma = aroma;
		this.sabor = sabor;
	}

	public Fruta(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	public String getAroma() {
		return aroma;
	}

	public void setAroma(String aroma) {
		this.aroma = aroma;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	@Override
	public String toString() {
		return super.toString() + "Fruta [aroma=" + aroma + ", sabor=" + sabor + "]";
	}

}
