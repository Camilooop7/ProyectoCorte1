package co.edu.unbosque.view;

public class ViewFacade {
	private VentanaPrincipal vp;

	public ViewFacade() {
		vp = new VentanaPrincipal();
	}

	public VentanaPrincipal getVp() {
		return vp;
	}

	public void setVp(VentanaPrincipal vp) {
		this.vp = vp;
	}

}
