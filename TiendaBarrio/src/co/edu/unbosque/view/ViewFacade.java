package co.edu.unbosque.view;

import java.io.IOException;

/**
 * Clase que actúa como fachada para gestionar las vistas principales y
 * emergentes de la aplicación. Proporciona métodos para acceder y modificar las
 * instancias de las ventanas.
 */
public class ViewFacade {
	private VentanaPrincipal vp;
	private VentanaEmergente ve;

	/**
	 * Constructor de la clase ViewFacade. Inicializa las instancias de las ventanas
	 * principal y emergente.
	 * 
	 * @throws IOException Si ocurre un error al inicializar las ventanas.
	 */
	public ViewFacade() throws IOException {
		vp = new VentanaPrincipal();
		ve = new VentanaEmergente();
	}

	/**
	 * Obtiene la instancia de la ventana principal.
	 * 
	 * @return La instancia de VentanaPrincipal.
	 */
	public VentanaPrincipal getVp() {
		return vp;
	}

	/**
	 * Establece la instancia de la ventana principal.
	 * 
	 * @param vp La nueva instancia de VentanaPrincipal a establecer.
	 */
	public void setVp(VentanaPrincipal vp) {
		this.vp = vp;
	}

	/**
	 * Obtiene la instancia de la ventana emergente.
	 * 
	 * @return La instancia de VentanaEmergente.
	 */
	public VentanaEmergente getVe() {
		return ve;
	}

	/**
	 * Establece la instancia de la ventana emergente.
	 * 
	 * @param ve La nueva instancia de VentanaEmergente a establecer.
	 */
	public void setVe(VentanaEmergente ve) {
		this.ve = ve;
	}
}
