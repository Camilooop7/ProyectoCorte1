package co.edu.unbosque.model;

import co.edu.unbosque.model.persistence.AnimalDAO;
import co.edu.unbosque.model.persistence.AudifonoDAO;
import co.edu.unbosque.model.persistence.CarritoDAO;
import co.edu.unbosque.model.persistence.ColegioDAO;
import co.edu.unbosque.model.persistence.EducativoDAO;
import co.edu.unbosque.model.persistence.HistorialDAO;
import co.edu.unbosque.model.persistence.HombreDAO;
import co.edu.unbosque.model.persistence.JuegoMesaDAO;
import co.edu.unbosque.model.persistence.LabialDAO;
import co.edu.unbosque.model.persistence.MovilDAO;
import co.edu.unbosque.model.persistence.MujerDAO;
import co.edu.unbosque.model.persistence.OficinaDAO;
import co.edu.unbosque.model.persistence.PeliculaDAO;
import co.edu.unbosque.model.persistence.PersonaDAO;
import co.edu.unbosque.model.persistence.PestaninaDAO;


/**
 * Fachada del modelo gestionada por CDI. Todos los DAOs se inyectan; no hay
 * 'static' ni inicialización manual.
 */
public class ModelFacade {

	private AnimalDAO animalDAO;
	private AudifonoDAO audifonoDAO;
	private ColegioDAO colegioDAO;
	private EducativoDAO educativoDAO;
	private HombreDAO hombreDAO;
	private JuegoMesaDAO juegoMesaDAO;
	private LabialDAO labialDAO;
	private MovilDAO movilDAO;
	private MujerDAO mujerDAO;
	private OficinaDAO oficinaDAO;
	private PeliculaDAO peliculaDAO;
	private PestaninaDAO pestaninaDAO;
	private PersonaDAO personaDAO;
	private CarritoDAO carritoDAO;
	private HistorialDAO historialDAO;

	public ModelFacade() {
		animalDAO = new AnimalDAO();
		audifonoDAO = new AudifonoDAO();
		colegioDAO = new ColegioDAO();
		educativoDAO = new EducativoDAO();
		hombreDAO = new HombreDAO();
		juegoMesaDAO = new JuegoMesaDAO();
		labialDAO = new LabialDAO();
		movilDAO = new MovilDAO();
		mujerDAO = new MujerDAO();
		oficinaDAO = new OficinaDAO();
		peliculaDAO = new PeliculaDAO();
		pestaninaDAO = new PestaninaDAO();
		personaDAO = new PersonaDAO();
		carritoDAO = new CarritoDAO();
		historialDAO = new HistorialDAO();

		// TODO Auto-generated constructor stub
	}
	// --- Getters para exponer los DAOs (agrega los que necesites usar) ---

	public AnimalDAO getAnimalDAO() {
		return animalDAO;
	}

	public void setAnimalDAO(AnimalDAO animalDAO) {
		this.animalDAO = animalDAO;
	}

	public AudifonoDAO getAudifonoDAO() {
		return audifonoDAO;
	}

	public void setAudifonoDAO(AudifonoDAO audifonoDAO) {
		this.audifonoDAO = audifonoDAO;
	}

	public ColegioDAO getColegioDAO() {
		return colegioDAO;
	}

	public void setColegioDAO(ColegioDAO colegioDAO) {
		this.colegioDAO = colegioDAO;
	}

	public EducativoDAO getEducativoDAO() {
		return educativoDAO;
	}

	public void setEducativoDAO(EducativoDAO educativoDAO) {
		this.educativoDAO = educativoDAO;
	}

	public HombreDAO getHombreDAO() {
		return hombreDAO;
	}

	public void setHombreDAO(HombreDAO hombreDAO) {
		this.hombreDAO = hombreDAO;
	}

	public JuegoMesaDAO getJuegoMesaDAO() {
		return juegoMesaDAO;
	}

	public void setJuegoMesaDAO(JuegoMesaDAO juegoMesaDAO) {
		this.juegoMesaDAO = juegoMesaDAO;
	}

	public LabialDAO getLabialDAO() {
		return labialDAO;
	}

	public void setLabialDAO(LabialDAO labialDAO) {
		this.labialDAO = labialDAO;
	}

	public MovilDAO getMovilDAO() {
		return movilDAO;
	}

	public void setMovilDAO(MovilDAO movilDAO) {
		this.movilDAO = movilDAO;
	}

	public MujerDAO getMujerDAO() {
		return mujerDAO;
	}

	public void setMujerDAO(MujerDAO mujerDAO) {
		this.mujerDAO = mujerDAO;
	}

	public OficinaDAO getOficinaDAO() {
		return oficinaDAO;
	}

	public void setOficinaDAO(OficinaDAO oficinaDAO) {
		this.oficinaDAO = oficinaDAO;
	}

	public PeliculaDAO getPeliculaDAO() {
		return peliculaDAO;
	}

	public void setPeliculaDAO(PeliculaDAO peliculaDAO) {
		this.peliculaDAO = peliculaDAO;
	}

	public PestaninaDAO getPestaninaDAO() {
		return pestaninaDAO;
	}

	public void setPestaninaDAO(PestaninaDAO pestaninaDAO) {
		this.pestaninaDAO = pestaninaDAO;
	}

	public PersonaDAO getPersonaDAO() {
		return personaDAO;
	}

	public void setPersonaDAO(PersonaDAO personaDAO) {
		this.personaDAO = personaDAO;
	}

	public CarritoDAO getCarritoDAO() {
		return carritoDAO;
	}

	public void setCarritoDAO(CarritoDAO carritoDAO) {
		this.carritoDAO = carritoDAO;
	}

	public HistorialDAO getHistorialDAO() {
		return historialDAO;
	}

	public void setHistorialDAO(HistorialDAO historialDAO) {
		this.historialDAO = historialDAO;
	}

	// Sin setters: las dependencias las gestiona el contenedor.
}
