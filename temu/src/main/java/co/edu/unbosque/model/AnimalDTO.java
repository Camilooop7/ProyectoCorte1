package co.edu.unbosque.model;

import java.io.Serializable;

public class AnimalDTO extends Peluche implements Serializable{
	private static final long serialVersionUID = 1L;
	private String animal;
	
	public AnimalDTO() {
		// TODO Auto-generated constructor stub
	}

	public AnimalDTO(String animal) {
		super();
		this.animal = animal;
	}

	public AnimalDTO(String nombre, int precio, String imagen, String tamano, String animal) {
		super(nombre, precio, imagen, tamano);
		this.animal = animal;
	}

	public AnimalDTO(String nombre, int precio, String imagen, String tamano) {
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
