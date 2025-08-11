package co.edu.unbosque.model;

import java.io.Serializable;

public class AudifonoDTO extends DispositivoElectronico implements Serializable {
	private static final long serialVersionUID = 1L;

	private String tipoConexion;

	public AudifonoDTO() {
		// TODO Auto-generated constructor stub
	}

	public AudifonoDTO(String tipoConexion) {
		super();
		this.tipoConexion = tipoConexion;
	}

	public AudifonoDTO(String nombre, int precio, String imagen, String modelo, String tipoConexion) {
		super(nombre, precio, imagen, modelo);
		this.tipoConexion = tipoConexion;
	}

	public AudifonoDTO(String nombre, int precio, String imagen, String modelo) {
		super(nombre, precio, imagen, modelo);
		// TODO Auto-generated constructor stub
	}

	public String getTipoConexion() {
		return tipoConexion;
	}

	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}

	@Override
	public String toString() {
		return super.toString() + "Audifono [tipoConexion=" + tipoConexion + "]";
	}

}
