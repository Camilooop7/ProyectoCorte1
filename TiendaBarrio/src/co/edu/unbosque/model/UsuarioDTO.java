package co.edu.unbosque.model;

public class UsuarioDTO {
	private String nombre;
	private int identificacion;

	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
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
