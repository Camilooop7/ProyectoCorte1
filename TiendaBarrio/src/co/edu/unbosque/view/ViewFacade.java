package co.edu.unbosque.view;

import java.io.IOException;

public class ViewFacade {
	private VentanaPrincipal vp;

	public ViewFacade() throws IOException {
		vp = new VentanaPrincipal();
	}

	public VentanaPrincipal getVp() {
		return vp;
	}

	public void setVp(VentanaPrincipal vp) {
		this.vp = vp;
	}

}
