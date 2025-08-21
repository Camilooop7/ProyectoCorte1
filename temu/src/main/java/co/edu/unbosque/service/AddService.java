package co.edu.unbosque.service;

import java.util.ArrayList;

import co.edu.unbosque.model.*; // Entidades y DTOs
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * Servicio para agregar y eliminar productos de diferentes categorías. Utiliza
 * ModelFacade para interactuar con los DAOs correspondientes.
 */
@Named
@ApplicationScoped
public class AddService {

	/** Instancia de ModelFacade para acceder a los DAOs. */
	private ModelFacade mf;

	/**
	 * Constructor por defecto.
	 */
	public AddService() {
	}

	/**
	 * Inicializa el servicio y crea una instancia de ModelFacade.
	 */
	@PostConstruct
	void init() {
		mf = new ModelFacade();
	}

	/**
	 * Elimina un audífono por su nombre.
	 * 
	 * @param nombre Nombre del audífono a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarAudifono(String nombre) {
		if (nombre == null || nombre.isBlank())
			return false;
		AudifonoDTO dto = new AudifonoDTO();
		dto.setNombre(nombre);
		return mf.getAudifonoDAO().delete(dto);
	}

	/**
	 * Elimina un móvil por su nombre.
	 * 
	 * @param nombre Nombre del móvil a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarMovil(String nombre) {
		if (nombre == null || nombre.isBlank())
			return false;
		MovilDTO dto = new MovilDTO();
		dto.setNombre(nombre);
		return mf.getMovilDAO().delete(dto);
	}

	/**
	 * Elimina un labial por su nombre.
	 * 
	 * @param nombre Nombre del labial a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarLabial(String nombre) {
		if (nombre == null || nombre.isBlank())
			return false;
		LabialDTO dto = new LabialDTO();
		dto.setNombre(nombre);
		return mf.getLabialDAO().delete(dto);
	}

	/**
	 * Elimina una pestañina por su nombre.
	 * 
	 * @param nombre Nombre de la pestañina a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarPestanina(String nombre) {
		if (nombre == null || nombre.isBlank())
			return false;
		PestaninaDTO dto = new PestaninaDTO();
		dto.setNombre(nombre);
		return mf.getPestaninaDAO().delete(dto);
	}

	/**
	 * Elimina un juego de mesa por su nombre.
	 * 
	 * @param nombre Nombre del juego de mesa a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarJuegoMesa(String nombre) {
		if (nombre == null || nombre.isBlank())
			return false;
		JuegoMesaDTO dto = new JuegoMesaDTO();
		dto.setNombre(nombre);
		return mf.getJuegoMesaDAO().delete(dto);
	}

	/**
	 * Elimina un artículo de colegio por su nombre.
	 * 
	 * @param nombre Nombre del artículo de colegio a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarColegio(String nombre) {
		if (nombre == null || nombre.isBlank())
			return false;
		ColegioDTO dto = new ColegioDTO();
		dto.setNombre(nombre);
		return mf.getColegioDAO().delete(dto);
	}

	/**
	 * Elimina un artículo de oficina por su nombre.
	 * 
	 * @param nombre Nombre del artículo de oficina a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarOficina(String nombre) {
		if (nombre == null || nombre.isBlank())
			return false;
		OficinaDTO dto = new OficinaDTO();
		dto.setNombre(nombre);
		return mf.getOficinaDAO().delete(dto);
	}

	/**
	 * Elimina una prenda de hombre por su nombre.
	 * 
	 * @param nombre Nombre de la prenda de hombre a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarHombre(String nombre) {
		if (nombre == null || nombre.isBlank())
			return false;
		HombreDTO dto = new HombreDTO();
		dto.setNombre(nombre);
		return mf.getHombreDAO().delete(dto);
	}

	/**
	 * Elimina una prenda de mujer por su nombre.
	 * 
	 * @param nombre Nombre de la prenda de mujer a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarMujer(String nombre) {
		if (nombre == null || nombre.isBlank())
			return false;
		MujerDTO dto = new MujerDTO();
		dto.setNombre(nombre);
		return mf.getMujerDAO().delete(dto);
	}

	/**
	 * Elimina un peluche de película por su nombre.
	 * 
	 * @param nombre Nombre del peluche de película a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarPelicula(String nombre) {
		if (nombre == null || nombre.isBlank())
			return false;
		PeliculaDTO dto = new PeliculaDTO();
		dto.setNombre(nombre);
		return mf.getPeliculaDAO().delete(dto);
	}

	/**
	 * Elimina un peluche de animal por su nombre.
	 * 
	 * @param nombre Nombre del peluche de animal a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarAnimal(String nombre) {
		if (nombre == null || nombre.isBlank())
			return false;
		AnimalDTO dto = new AnimalDTO();
		dto.setNombre(nombre);
		return mf.getAnimalDAO().delete(dto);
	}

	/**
	 * Crea un nuevo audífono.
	 * 
	 * @param dto DTO del audífono a crear.
	 */
	public void crearAu(AudifonoDTO dto) {
		mf.getAudifonoDAO().add(dto);
	}

	/**
	 * Crea un nuevo móvil.
	 * 
	 * @param dto DTO del móvil a crear.
	 */
	public void crearMo(MovilDTO dto) {
		mf.getMovilDAO().add(dto);
	}

	/**
	 * Crea un nuevo labial.
	 * 
	 * @param dto DTO del labial a crear.
	 */
	public void crearLa(LabialDTO dto) {
		mf.getLabialDAO().add(dto);
	}

	/**
	 * Crea una nueva pestañina.
	 * 
	 * @param dto DTO de la pestañina a crear.
	 */
	public void crearPes(PestaninaDTO dto) {
		mf.getPestaninaDAO().add(dto);
	}

	/**
	 * Crea un nuevo juego de mesa.
	 * 
	 * @param dto DTO del juego de mesa a crear.
	 */
	public void crearJue(JuegoMesaDTO dto) {
		mf.getJuegoMesaDAO().add(dto);
	}

	/**
	 * Crea un nuevo artículo de colegio.
	 * 
	 * @param dto DTO del artículo de colegio a crear.
	 */
	public void crearCo(ColegioDTO dto) {
		mf.getColegioDAO().add(dto);
	}

	/**
	 * Crea un nuevo artículo de oficina.
	 * 
	 * @param dto DTO del artículo de oficina a crear.
	 */
	public void crearOfi(OficinaDTO dto) {
		mf.getOficinaDAO().add(dto);
	}

	/**
	 * Crea una nueva prenda de hombre.
	 * 
	 * @param dto DTO de la prenda de hombre a crear.
	 */
	public void crearHom(HombreDTO dto) {
		mf.getHombreDAO().add(dto);
	}

	/**
	 * Crea una nueva prenda de mujer.
	 * 
	 * @param dto DTO de la prenda de mujer a crear.
	 */
	public void crearMuj(MujerDTO dto) {
		mf.getMujerDAO().add(dto);
	}

	/**
	 * Crea un nuevo peluche de película.
	 * 
	 * @param dto DTO del peluche de película a crear.
	 */
	public void crearPel(PeliculaDTO dto) {
		mf.getPeliculaDAO().add(dto);
	}

	/**
	 * Crea un nuevo peluche de animal.
	 * 
	 * @param dto DTO del peluche de animal a crear.
	 */
	public void crearAni(AnimalDTO dto) {
		mf.getAnimalDAO().add(dto);
	}

	/**
	 * Lista todos los móviles.
	 * 
	 * @return Lista de móviles.
	 */
	public ArrayList<Movil> listarMoviles() {
		return mf.getMovilDAO().getListaMovil();
	}

	/**
	 * Lista todos los labiales.
	 * 
	 * @return Lista de labiales.
	 */
	public ArrayList<Labial> listarLabiales() {
		return mf.getLabialDAO().getListaLabial();
	}

	/**
	 * Lista todas las pestañinas.
	 * 
	 * @return Lista de pestañinas.
	 */
	public ArrayList<Pestanina> listarPestaninas() {
		return mf.getPestaninaDAO().getListaPestanina();
	}

	/**
	 * Lista todos los juegos de mesa.
	 * 
	 * @return Lista de juegos de mesa.
	 */
	public ArrayList<JuegoMesa> listarJuegoMesa() {
		return mf.getJuegoMesaDAO().getListaJuegoMesa();
	}

	/**
	 * Lista todos los artículos de colegio.
	 * 
	 * @return Lista de artículos de colegio.
	 */
	public ArrayList<Colegio> listarColegios() {
		return mf.getColegioDAO().getListaColegio();
	}

	/**
	 * Lista todos los artículos de oficina.
	 * 
	 * @return Lista de artículos de oficina.
	 */
	public ArrayList<Oficina> listarOficinas() {
		return mf.getOficinaDAO().getListaOficina();
	}

	/**
	 * Lista todas las prendas de hombre.
	 * 
	 * @return Lista de prendas de hombre.
	 */
	public ArrayList<Hombre> listarHombres() {
		return mf.getHombreDAO().getListaHombre();
	}

	/**
	 * Lista todas las prendas de mujer.
	 * 
	 * @return Lista de prendas de mujer.
	 */
	public ArrayList<Mujer> listarMujeres() {
		return mf.getMujerDAO().getListaMujer();
	}

	/**
	 * Lista todos los audífonos.
	 * 
	 * @return Lista de audífonos.
	 */
	public ArrayList<Audifono> listarAudifonos() {
		return mf.getAudifonoDAO().getListaAudifono();
	}

	/**
	 * Lista todos los peluches de película.
	 * 
	 * @return Lista de peluches de película.
	 */
	public ArrayList<Pelicula> listarPeliculas() {
		return mf.getPeliculaDAO().getListaPelicula();
	}

	/**
	 * Lista todos los peluches de animal.
	 * 
	 * @return Lista de peluches de animal.
	 */
	public ArrayList<Animal> listarAnimal() {
		return mf.getAnimalDAO().getListaAnimal();
	}

	/**
	 * Obtiene la instancia de ModelFacade.
	 * 
	 * @return Instancia de ModelFacade.
	 */
	public ModelFacade getModelFacade() {
		return mf;
	}
}
