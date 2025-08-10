package co.edu.unbosque.model;

import java.awt.datatransfer.StringSelection;
import java.io.Serializable;

public class Animal extends Peluche implements Serializable{
	private static final long serialVersionUID = 1L;
	private String animal;
	
	public Animal() {
		// TODO Auto-generated constructor stub
	}

	public Animal(String animal) {
		super();
		this.animal = animal;
	}

	public Animal(String nombre, int precio, String imagen, int tamano, String animal) {
		super(nombre, precio, imagen, tamano);
		this.animal = animal;
	}

	public Animal(String nombre, int precio, String imagen, int tamano) {
		super(nombre, precio, imagen, tamano);
		// TODO Auto-generated constructor stub
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	@Override
	public String toString() {
		return super.toString()+"Animal [animal=" + animal + "]";
	}
	

}
