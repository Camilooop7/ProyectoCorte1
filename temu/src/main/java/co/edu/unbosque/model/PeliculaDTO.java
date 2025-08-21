package co.edu.unbosque.model;

import java.io.Serializable;

public class PeliculaDTO extends Peluche implements Serializable{
	private static final long serialVersionUID = 1L;
	private String personaje;
	
	public PeliculaDTO() {
		// TODO Auto-generated constructor stub
	}

	public PeliculaDTO(String personaje) {
		super();
		this.personaje = personaje;
	}

	public PeliculaDTO(String nombre, int precio, String imagen, String tamano, String personaje) {
		super(nombre, precio, imagen, tamano);
		this.personaje = personaje;
	}

	public PeliculaDTO(String nombre, int precio, String imagen, String tamano) {
		super(nombre, precio, imagen, tamano);
		// TODO Auto-generated constructor stub
	}

	public String getPersonaje() {
		return personaje;
	}

	public void setPersonaje(String personaje) {
		this.personaje = personaje;
	}

	@Override
	public String toString() {
		return super.toString()+"Pelicula [personaje=" + personaje + "]";
	}
	

}
