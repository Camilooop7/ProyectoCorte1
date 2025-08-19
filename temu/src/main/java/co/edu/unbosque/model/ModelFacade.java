package co.edu.unbosque.model;

import co.edu.unbosque.model.persistence.AnimalDAO;
import co.edu.unbosque.model.persistence.AudifonoDAO;
import co.edu.unbosque.model.persistence.CarritoDAO;
import co.edu.unbosque.model.persistence.ColegioDAO;
import co.edu.unbosque.model.persistence.EducativoDAO;
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
 * Clase que actúa como fachada para manejar los DAOs y la lógica del modelo.
 */
public class ModelFacade {

	/**
	 * DAO para gestionar animales.
	 */
	public static AnimalDAO animalDAO;
	/**
	 * DAO para gestionar audífonos.
	 */
	public static AudifonoDAO audifonoDAO;
	/**
	 * DAO para gestionar colegios.
	 */
	public static ColegioDAO colegioDAO;
	/**
	 * DAO para gestionar productos educativos.
	 */
	public static EducativoDAO educativoDAO;
	/**
	 * DAO para gestionar hombres.
	 */
	public static HombreDAO hombreDAO;
	/**
	 * DAO para gestionar juegos de mesa.
	 */
	public static JuegoMesaDAO juegoMesaDAO;
	/**
	 * DAO para gestionar labiales.
	 */
	public static LabialDAO labialDAO;
	/**
	 * DAO para gestionar dispositivos móviles.
	 */
	public static MovilDAO movilDAO;
	/**
	 * DAO para gestionar mujeres.
	 */
	public static MujerDAO mujerDAO;
	/**
	 * DAO para gestionar productos de oficina.
	 */
	public static OficinaDAO oficinaDAO;
	/**
	 * DAO para gestionar películas.
	 */
	public static PeliculaDAO peliculaDAO;
	/**
	 * DAO para gestionar pestañinas.
	 */
	public static PestaninaDAO pestaninaDAO;
	
	public static PersonaDAO personaDAO;
	
	public static CarritoDAO carritoDAO;

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
		personaDAO = new PersonaDAO();
		carritoDAO = new CarritoDAO();
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

	public static PersonaDAO getPersonaDAO() {
		return personaDAO;
	}

	public static void setPersonaDAO(PersonaDAO personaDAO) {
		ModelFacade.personaDAO = personaDAO;
	}
	
}
