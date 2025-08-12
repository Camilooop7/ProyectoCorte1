package co.edu.unbosque.model;

import java.io.Serializable;

public class VerduraDTO extends Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String forma;

	private String tamano;

	public VerduraDTO() {
		// TODO Auto-generated constructor stub
	}

	public VerduraDTO(String forma, String tamano) {
		super();
		this.forma = forma;
		this.tamano = tamano;
	}

	public VerduraDTO(String nombre, int precio, String imagen, String forma, String tamano) {
		super(nombre, precio, imagen);
		this.forma = forma;
		this.tamano = tamano;
	}

	public VerduraDTO(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	public String getForma() {
		return forma;
	}

	public void setForma(String forma) {
		this.forma = forma;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	@Override
	public String toString() {
		return super.toString() + "Verdura [forma=" + forma + ", tamano=" + tamano + "]";
	}

}
