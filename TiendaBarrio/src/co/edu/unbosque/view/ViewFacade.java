package co.edu.unbosque.view;

import java.io.IOException;

public class ViewFacade {
	private VentanaPrincipal vp;
	private VentanaEmergente ve;

	public ViewFacade() throws IOException {
		vp = new VentanaPrincipal();
		ve = new VentanaEmergente();
	}

	public VentanaPrincipal getVp() {
		return vp;
	}

	public void setVp(VentanaPrincipal vp) {
		this.vp = vp;
	}

	public VentanaEmergente getVe() {
		return ve;
	}

	public void setVe(VentanaEmergente ve) {
		this.ve = ve;
	}

}
