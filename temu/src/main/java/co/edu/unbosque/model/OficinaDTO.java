package co.edu.unbosque.model;

import java.io.Serializable;

public class OficinaDTO extends Papeleria implements Serializable {
	private static final long serialVersionUID = 1L;
	private String esDecorativo;

	public OficinaDTO() {
		// TODO Auto-generated constructor stub
	}

	public OficinaDTO(String esDecorativo) {
		super();
		this.esDecorativo = esDecorativo;
	}

	public OficinaDTO(String nombre, int precio, String imagen, String cantidadPaquete, String esDecorativo) {
		super(nombre, precio, imagen, cantidadPaquete);
		this.esDecorativo = esDecorativo;
	}

	public OficinaDTO(String nombre, int precio, String imagen, String cantidadPaquete) {
		super(nombre, precio, imagen, cantidadPaquete);
		// TODO Auto-generated constructor stub
	}

	public String isEsDecorativo() {
		return esDecorativo;
	}

	public void setEsDecorativo(String esDecorativo) {
		this.esDecorativo = esDecorativo;
	}

	@Override
	public String toString() {
		return super.toString() + "Oficina [esDecorativo=" + esDecorativo + "]";
	}

}
