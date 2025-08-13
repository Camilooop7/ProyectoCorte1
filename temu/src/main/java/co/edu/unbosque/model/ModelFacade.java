package co.edu.unbosque.model;

import co.edu.unbosque.model.persistence.AnimalDAO;
import co.edu.unbosque.model.persistence.AudifonoDAO;
import co.edu.unbosque.model.persistence.ColegioDAO;
import co.edu.unbosque.model.persistence.EducativoDAO;
import co.edu.unbosque.model.persistence.HombreDAO;
import co.edu.unbosque.model.persistence.JuegoMesaDAO;
import co.edu.unbosque.model.persistence.LabialDAO;
import co.edu.unbosque.model.persistence.MovilDAO;
import co.edu.unbosque.model.persistence.MujerDAO;
import co.edu.unbosque.model.persistence.OficinaDAO;
import co.edu.unbosque.model.persistence.PeliculaDAO;
import co.edu.unbosque.model.persistence.PestaninaDAO;

/**
 * Clase que actúa como fachada para manejar los DAOs y la lógica del modelo.
 */
public class ModelFacade {

	/**
	 * DAO para gestionar animales.
	 */
	private AnimalDAO animalDAO;
	/**
	 * DAO para gestionar audífonos.
	 */
	private AudifonoDAO audifonoDAO;
	/**
	 * DAO para gestionar colegios.
	 */
	private ColegioDAO colegioDAO;
	/**
	 * DAO para gestionar productos educativos.
	 */
	private EducativoDAO educativoDAO;
	/**
	 * DAO para gestionar hombres.
	 */
	private HombreDAO hombreDAO;
	/**
	 * DAO para gestionar juegos de mesa.
	 */
	private JuegoMesaDAO juegoMesaDAO;
	/**
	 * DAO para gestionar labiales.
	 */
	private LabialDAO labialDAO;
	/**
	 * DAO para gestionar dispositivos móviles.
	 */
	private MovilDAO movilDAO;
	/**
	 * DAO para gestionar mujeres.
	 */
	private MujerDAO mujerDAO;
	/**
	 * DAO para gestionar productos de oficina.
	 */
	private OficinaDAO oficinaDAO;
	/**
	 * DAO para gestionar películas.
	 */
	private PeliculaDAO peliculaDAO;
	/**
	 * DAO para gestionar pestañinas.
	 */
	private PestaninaDAO pestaninaDAO;

	/**
	 * Constructor de la clase. Inicializa los DAOs.
	 */
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
	}

	/**
	 * GETTERS & SETTERS
	 */

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
}
