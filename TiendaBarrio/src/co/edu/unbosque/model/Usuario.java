package co.edu.unbosque.model;

import java.io.Serializable;

import co.edu.unbosque.util.structure.LinkedList;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int identificacion;
	private LinkedList<Carrito> listaCarrtio;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombre, int identificacion, LinkedList<Carrito> listaCarrtio) {
		super();
		this.nombre = nombre;
		this.identificacion = identificacion;
		this.listaCarrtio = listaCarrtio;
	}

	public LinkedList<Carrito> getListaCarrtio() {
		return listaCarrtio;
	}

	public void setListaCarrtio(LinkedList<Carrito> listaCarrtio) {
		this.listaCarrtio = listaCarrtio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", identificacion=" + identificacion + "]";
	}

}
