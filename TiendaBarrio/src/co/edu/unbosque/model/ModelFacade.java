package co.edu.unbosque.model;

import co.edu.unbosque.model.persistence.FrutaDAO;
import co.edu.unbosque.model.persistence.GaseosaDAO;
import co.edu.unbosque.model.persistence.JugoDAO;
import co.edu.unbosque.model.persistence.PaquetePapaDAO;
import co.edu.unbosque.model.persistence.UsuarioDAO;
import co.edu.unbosque.model.persistence.VerduraDAO;

public class ModelFacade {

	private VerduraDAO verduraDAO;
	private GaseosaDAO gaseosaDAO;
	private JugoDAO jugoDAO;
	private PaquetePapaDAO paquetePapaDAO;
	private FrutaDAO frutaDAO;
	private UsuarioDAO usuarioDAO;

	public ModelFacade() {
		verduraDAO = new VerduraDAO();
		paquetePapaDAO = new PaquetePapaDAO();
		gaseosaDAO = new GaseosaDAO();
		jugoDAO = new JugoDAO();
		frutaDAO = new FrutaDAO();
		usuarioDAO = new UsuarioDAO();
	}

	public VerduraDAO getVerduraDAO() {
		return verduraDAO;
	}

	public void setVerduraDAO(VerduraDAO verduraDAO) {
		this.verduraDAO = verduraDAO;
	}

	public GaseosaDAO getGaseosaDAO() {
		return gaseosaDAO;
	}

	public void setGaseosaDAO(GaseosaDAO gaseosaDAO) {
		this.gaseosaDAO = gaseosaDAO;
	}

	public JugoDAO getJugoDAO() {
		return jugoDAO;
	}

	public void setJugoDAO(JugoDAO jugoDAO) {
		this.jugoDAO = jugoDAO;
	}

	public PaquetePapaDAO getPaquetePapaDAO() {
		return paquetePapaDAO;
	}

	public void setPaquetePapaDAO(PaquetePapaDAO paquetePapaDAO) {
		this.paquetePapaDAO = paquetePapaDAO;
	}

	public FrutaDAO getFrutaDAO() {
		return frutaDAO;
	}

	public void setFrutaDAO(FrutaDAO frutaDAO) {
		this.frutaDAO = frutaDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
