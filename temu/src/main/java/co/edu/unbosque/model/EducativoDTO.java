package co.edu.unbosque.model;

import java.io.Serializable;

public class EducativoDTO extends Juguete implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean esDidactico;

	public EducativoDTO() {
		// TODO Auto-generated constructor stub
	}

	public EducativoDTO(boolean esDidactico) {
		super();
		this.esDidactico = esDidactico;
	}

	public EducativoDTO(String nombre, int precio, String imagen, int edadRecomendada, boolean esDidactico) {
		super(nombre, precio, imagen, edadRecomendada);
		this.esDidactico = esDidactico;
	}

	public EducativoDTO(String nombre, int precio, String imagen, int edadRecomendada) {
		super(nombre, precio, imagen, edadRecomendada);
		// TODO Auto-generated constructor stub
	}

	public boolean isEsDidactico() {
		return esDidactico;
	}

	public void setEsDidactico(boolean esDidactico) {
		this.esDidactico = esDidactico;
	}

	@Override
	public String toString() {
		return super.toString() + "Educativo [esDidactico=" + esDidactico + "]";
	}

}
