package co.edu.unbosque.model;

import java.io.Serializable;
import co.edu.unbosque.util.structure.LinkedList;

public class Carrito implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String nombreU;
	private LinkedList<String> listaNombresProductos;

	public Carrito() {
		this.listaNombresProductos = new LinkedList<>();
	}

	public Carrito(String nombre, String nombreU) {
		this.nombre = nombre;
		this.nombreU = nombreU;
		this.listaNombresProductos = new LinkedList<>();
	}

	public void agregarNombreProducto(String nombreProducto) {
		listaNombresProductos.addLastR(nombreProducto);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreU() {
		return nombreU;
	}

	public void setNombreU(String nombreU) {
		this.nombreU = nombreU;
	}

	public LinkedList<String> getListaNombresProductos() {
		return listaNombresProductos;
	}

	public void setListaNombresProductos(LinkedList<String> listaNombresProductos) {
		this.listaNombresProductos = listaNombresProductos;
	}
}
