package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import co.edu.unbosque.model.ModelFacade;

import co.edu.unbosque.model.Movil;
import co.edu.unbosque.model.Audifono;
import co.edu.unbosque.model.Pelicula;
import co.edu.unbosque.model.Animal;
import co.edu.unbosque.model.Labial;
import co.edu.unbosque.model.Pestanina;
import co.edu.unbosque.model.JuegoMesa;
import co.edu.unbosque.model.Colegio;
import co.edu.unbosque.model.Oficina;
import co.edu.unbosque.model.Hombre;
import co.edu.unbosque.model.Mujer;

import co.edu.unbosque.model.MovilDTO;
import co.edu.unbosque.model.AudifonoDTO;
import co.edu.unbosque.model.PeliculaDTO;
import co.edu.unbosque.model.AnimalDTO;
import co.edu.unbosque.model.LabialDTO;
import co.edu.unbosque.model.PestaninaDTO;
import co.edu.unbosque.model.JuegoMesaDTO;
import co.edu.unbosque.model.ColegioDTO;
import co.edu.unbosque.model.OficinaDTO;
import co.edu.unbosque.model.HombreDTO;
import co.edu.unbosque.model.MujerDTO;

/**
 * Servicio principal para exponer información a la interfaz de usuario.
 * Proporciona métodos para listar y eliminar productos de diferentes
 * categorías.
 */
@Named("principalService")
@ApplicationScoped
public class PrincipalService {

	/** Instancia de ModelFacade para acceder a los DAOs. */
	private final ModelFacade mf = new ModelFacade();

	/**
	 * Método utilitario para manejar valores nulos en cadenas.
	 * 
	 * @param s Cadena a verificar.
	 * @return Cadena vacía si es nula, la misma cadena en caso contrario.
	 */
	private static String nvl(String s) {
		return s == null ? "" : s;
	}

	/**
	 * Método utilitario para manejar valores nulos en números.
	 * 
	 * @param n Número a verificar.
	 * @return 0 si es nulo, el valor del número en caso contrario.
	 */
	private static int nvi(Number n) {
		return n == null ? 0 : n.intValue();
	}

	/**
	 * Lista todos los móviles.
	 * 
	 * @return Lista de móviles.
	 */
	public List<Movil> listarMoviles() {
		List<Movil> out = new ArrayList<>();
		for (MovilDTO d : mf.getMovilDAO().getAll()) {
			Movil e = new Movil();
			e.setNombre(nvl(d.getNombre()));
			e.setImagen(nvl(d.getImagen()));
			e.setPrecio(nvi(d.getPrecio()));
			out.add(e);
		}
		return out;
	}

	/**
	 * Lista todos los audífonos.
	 * 
	 * @return Lista de audífonos.
	 */
	public List<Audifono> listarAudifonos() {
		List<Audifono> out = new ArrayList<>();
		for (AudifonoDTO d : mf.getAudifonoDAO().getAll()) {
			Audifono e = new Audifono();
			e.setNombre(nvl(d.getNombre()));
			e.setImagen(nvl(d.getImagen()));
			e.setPrecio(nvi(d.getPrecio()));
			out.add(e);
		}
		return out;
	}

	/**
	 * Lista todos los peluches de película.
	 * 
	 * @return Lista de peluches de película.
	 */
	public List<Pelicula> listarPeliculas() {
		List<Pelicula> out = new ArrayList<>();
		for (PeliculaDTO d : mf.getPeliculaDAO().getAll()) {
			Pelicula e = new Pelicula();
			e.setNombre(nvl(d.getNombre()));
			e.setImagen(nvl(d.getImagen()));
			e.setPrecio(nvi(d.getPrecio()));
			out.add(e);
		}
		return out;
	}

	/**
	 * Lista todos los peluches de animal.
	 * 
	 * @return Lista de peluches de animal.
	 */
	public List<Animal> listarAnimal() {
		List<Animal> out = new ArrayList<>();
		for (AnimalDTO d : mf.getAnimalDAO().getAll()) {
			Animal e = new Animal();
			e.setNombre(nvl(d.getNombre()));
			e.setImagen(nvl(d.getImagen()));
			e.setPrecio(nvi(d.getPrecio()));
			out.add(e);
		}
		return out;
	}

	/**
	 * Lista todos los labiales.
	 * 
	 * @return Lista de labiales.
	 */
	public List<Labial> listarLabiales() {
		List<Labial> out = new ArrayList<>();
		for (LabialDTO d : mf.getLabialDAO().getAll()) {
			Labial e = new Labial();
			e.setNombre(nvl(d.getNombre()));
			e.setImagen(nvl(d.getImagen()));
			e.setPrecio(nvi(d.getPrecio()));
			out.add(e);
		}
		return out;
	}

	/**
	 * Lista todas las pestañinas.
	 * 
	 * @return Lista de pestañinas.
	 */
	public List<Pestanina> listarPestaninas() {
		List<Pestanina> out = new ArrayList<>();
		for (PestaninaDTO d : mf.getPestaninaDAO().getAll()) {
			Pestanina e = new Pestanina();
			e.setNombre(nvl(d.getNombre()));
			e.setImagen(nvl(d.getImagen()));
			e.setPrecio(nvi(d.getPrecio()));
			out.add(e);
		}
		return out;
	}

	/**
	 * Lista todos los juegos de mesa.
	 * 
	 * @return Lista de juegos de mesa.
	 */
	public List<JuegoMesa> listarJuegoMesa() {
		List<JuegoMesa> out = new ArrayList<>();
		for (JuegoMesaDTO d : mf.getJuegoMesaDAO().getAll()) {
			JuegoMesa e = new JuegoMesa();
			e.setNombre(nvl(d.getNombre()));
			e.setImagen(nvl(d.getImagen()));
			e.setPrecio(nvi(d.getPrecio()));
			out.add(e);
		}
		return out;
	}

	/**
	 * Lista todos los artículos de colegio.
	 * 
	 * @return Lista de artículos de colegio.
	 */
	public List<Colegio> listarColegios() {
		List<Colegio> out = new ArrayList<>();
		for (ColegioDTO d : mf.getColegioDAO().getAll()) {
			Colegio e = new Colegio();
			e.setNombre(nvl(d.getNombre()));
			e.setImagen(nvl(d.getImagen()));
			e.setPrecio(nvi(d.getPrecio()));
			out.add(e);
		}
		return out;
	}

	/**
	 * Lista todos los artículos de oficina.
	 * 
	 * @return Lista de artículos de oficina.
	 */
	public List<Oficina> listarOficinas() {
		List<Oficina> out = new ArrayList<>();
		for (OficinaDTO d : mf.getOficinaDAO().getAll()) {
			Oficina e = new Oficina();
			e.setNombre(nvl(d.getNombre()));
			e.setImagen(nvl(d.getImagen()));
			e.setPrecio(nvi(d.getPrecio()));
			out.add(e);
		}
		return out;
	}

	/**
	 * Lista todas las prendas de hombre.
	 * 
	 * @return Lista de prendas de hombre.
	 */
	public List<Hombre> listarHombres() {
		List<Hombre> out = new ArrayList<>();
		for (HombreDTO d : mf.getHombreDAO().getAll()) {
			Hombre e = new Hombre();
			e.setNombre(nvl(d.getNombre()));
			e.setImagen(nvl(d.getImagen()));
			e.setPrecio(nvi(d.getPrecio()));
			out.add(e);
		}
		return out;
	}

	/**
	 * Lista todas las prendas de mujer.
	 * 
	 * @return Lista de prendas de mujer.
	 */
	public List<Mujer> listarMujeres() {
		List<Mujer> out = new ArrayList<>();
		for (MujerDTO d : mf.getMujerDAO().getAll()) {
			Mujer e = new Mujer();
			e.setNombre(nvl(d.getNombre()));
			e.setImagen(nvl(d.getImagen()));
			e.setPrecio(nvi(d.getPrecio()));
			out.add(e);
		}
		return out;
	}

	/**
	 * Elimina un móvil por su nombre.
	 * 
	 * @param nombre Nombre del móvil a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarMovil(String nombre) {
		MovilDTO dto = new MovilDTO();
		dto.setNombre(nvl(nombre));
		return mf.getMovilDAO().delete(dto);
	}

	/**
	 * Elimina un audífono por su nombre.
	 * 
	 * @param nombre Nombre del audífono a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarAudifono(String nombre) {
		AudifonoDTO dto = new AudifonoDTO();
		dto.setNombre(nvl(nombre));
		return mf.getAudifonoDAO().delete(dto);
	}

	/**
	 * Elimina un peluche de película por su nombre.
	 * 
	 * @param nombre Nombre del peluche de película a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarPelicula(String nombre) {
		PeliculaDTO dto = new PeliculaDTO();
		dto.setNombre(nvl(nombre));
		return mf.getPeliculaDAO().delete(dto);
	}

	/**
	 * Elimina un peluche de animal por su nombre.
	 * 
	 * @param nombre Nombre del peluche de animal a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarAnimal(String nombre) {
		AnimalDTO dto = new AnimalDTO();
		dto.setNombre(nvl(nombre));
		return mf.getAnimalDAO().delete(dto);
	}

	/**
	 * Elimina un labial por su nombre.
	 * 
	 * @param nombre Nombre del labial a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarLabial(String nombre) {
		LabialDTO dto = new LabialDTO();
		dto.setNombre(nvl(nombre));
		return mf.getLabialDAO().delete(dto);
	}

	/**
	 * Elimina una pestañina por su nombre.
	 * 
	 * @param nombre Nombre de la pestañina a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarPestanina(String nombre) {
		PestaninaDTO dto = new PestaninaDTO();
		dto.setNombre(nvl(nombre));
		return mf.getPestaninaDAO().delete(dto);
	}

	/**
	 * Elimina un juego de mesa por su nombre.
	 * 
	 * @param nombre Nombre del juego de mesa a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarJuegoMesa(String nombre) {
		JuegoMesaDTO dto = new JuegoMesaDTO();
		dto.setNombre(nvl(nombre));
		return mf.getJuegoMesaDAO().delete(dto);
	}

	/**
	 * Elimina un artículo de colegio por su nombre.
	 * 
	 * @param nombre Nombre del artículo de colegio a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarColegio(String nombre) {
		ColegioDTO dto = new ColegioDTO();
		dto.setNombre(nvl(nombre));
		return mf.getColegioDAO().delete(dto);
	}

	/**
	 * Elimina un artículo de oficina por su nombre.
	 * 
	 * @param nombre Nombre del artículo de oficina a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarOficina(String nombre) {
		OficinaDTO dto = new OficinaDTO();
		dto.setNombre(nvl(nombre));
		return mf.getOficinaDAO().delete(dto);
	}

	/**
	 * Elimina una prenda de hombre por su nombre.
	 * 
	 * @param nombre Nombre de la prenda de hombre a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarHombre(String nombre) {
		HombreDTO dto = new HombreDTO();
		dto.setNombre(nvl(nombre));
		return mf.getHombreDAO().delete(dto);
	}

	/**
	 * Elimina una prenda de mujer por su nombre.
	 * 
	 * @param nombre Nombre de la prenda de mujer a eliminar.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean eliminarMujer(String nombre) {
		MujerDTO dto = new MujerDTO();
		dto.setNombre(nvl(nombre));
		return mf.getMujerDAO().delete(dto);
	}
}
