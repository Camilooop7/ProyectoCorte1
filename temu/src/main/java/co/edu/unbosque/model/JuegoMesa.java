package co.edu.unbosque.model;

import java.io.Serializable;

public class JuegoMesa extends Juguete implements Serializable{
	private int cantidadPersona;
	public JuegoMesa() {
		// TODO Auto-generated constructor stub
	}
	public JuegoMesa(int cantidadPersona) {
		super();
		this.cantidadPersona = cantidadPersona;
	}
	public JuegoMesa(String nombre, int precio, String imagen, int edadRecomendada, int cantidadPersona) {
		super(nombre, precio, imagen, edadRecomendada);
		this.cantidadPersona = cantidadPersona;
	}
	public JuegoMesa(String nombre, int precio, String imagen, int edadRecomendada) {
		super(nombre, precio, imagen, edadRecomendada);
		// TODO Auto-generated constructor stub
	}
	public int getCantidadPersona() {
		return cantidadPersona;
	}
	public void setCantidadPersona(int cantidadPersona) {
		this.cantidadPersona = cantidadPersona;
	}
	@Override
	public String toString() {
		return super.toString()+"JuegoMesa [cantidadPersona=" + cantidadPersona + "]";
	}
	

}
