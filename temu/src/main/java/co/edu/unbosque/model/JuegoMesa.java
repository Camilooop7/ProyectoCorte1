package co.edu.unbosque.model;

import java.io.Serializable;

public class JuegoMesa extends Juguete implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cantidadPersona;
	public JuegoMesa() {
		// TODO Auto-generated constructor stub
	}
	public JuegoMesa(String cantidadPersona) {
		super();
		this.cantidadPersona = cantidadPersona;
	}
	public JuegoMesa(String nombre, int precio, String imagen, String edadRecomendada, String cantidadPersona) {
		super(nombre, precio, imagen, edadRecomendada);
		this.cantidadPersona = cantidadPersona;
	}
	public JuegoMesa(String nombre, int precio, String imagen, String edadRecomendada) {
		super(nombre, precio, imagen, edadRecomendada);
		// TODO Auto-generated constructor stub
	}

	public String getCantidadPersona() {
		return cantidadPersona;
	}
	public void setCantidadPersona(String cantidadPersona) {
		this.cantidadPersona = cantidadPersona;
	}
	@Override
	public String toString() {
		return super.toString()+"JuegoMesa [cantidadPersona=" + cantidadPersona + "]";
	}
	

}
