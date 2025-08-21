package co.edu.unbosque.model;

import java.io.Serializable;

public class EducativoDTO extends Juguete implements Serializable {
	private static final long serialVersionUID = 1L;
	private String esDidactico;

	public EducativoDTO() {
		// TODO Auto-generated constructor stub
	}

	public EducativoDTO(String esDidactico) {
		super();
		this.esDidactico = esDidactico;
	}

	public EducativoDTO(String nombre, int precio, String imagen, String edadRecomendada, String esDidactico) {
		super(nombre, precio, imagen, edadRecomendada);
		this.esDidactico = esDidactico;
	}

	public EducativoDTO(String nombre, int precio, String imagen, String edadRecomendada) {
		super(nombre, precio, imagen, edadRecomendada);
		// TODO Auto-generated constructor stub
	}



	public String getEsDidactico() {
		return esDidactico;
	}

	public void setEsDidactico(String esDidactico) {
		this.esDidactico = esDidactico;
	}

	@Override
	public String toString() {
		return super.toString() + "Educativo [esDidactico=" + esDidactico + "]";
	}

}
