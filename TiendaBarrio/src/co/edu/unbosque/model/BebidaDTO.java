package co.edu.unbosque.model;

import java.io.Serializable;

public class BebidaDTO extends Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sabor;
	private boolean esZero;

	public BebidaDTO() {
		// TODO Auto-generated constructor stub
	}

	public BebidaDTO(String sabor, boolean esZero) {
		super();
		this.sabor = sabor;
		this.esZero = esZero;
	}

	public BebidaDTO(String nombre, int precio, String imagen, String sabor, boolean esZero) {
		super(nombre, precio, imagen);
		this.sabor = sabor;
		this.esZero = esZero;
	}

	public BebidaDTO(String nombre, int precio, String imagen) {
		super(nombre, precio, imagen);
		// TODO Auto-generated constructor stub
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public boolean isEsZero() {
		return esZero;
	}

	public void setEsZero(boolean esZero) {
		this.esZero = esZero;
	}

	@Override
	public String toString() {
		return super.toString() + "Bebida [sabor=" + sabor + ", esZero=" + esZero + "]";
	}

}
