package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.model.CarritoDTO;
import co.edu.unbosque.model.Fruta;
import co.edu.unbosque.model.FrutaDTO;
import co.edu.unbosque.model.Gaseosa;
import co.edu.unbosque.model.GaseosaDTO;
import co.edu.unbosque.model.Jugo;
import co.edu.unbosque.model.JugoDTO;
import co.edu.unbosque.model.PaquetePapa;
import co.edu.unbosque.model.PaquetePapaDTO;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.UsuarioDTO;
import co.edu.unbosque.model.Verdura;
import co.edu.unbosque.model.VerduraDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

/**
 * Clase utilitaria para mapear entre entidades y DTOs. Proporciona métodos para convertir entre
 * entidades y DTOs de forma individual y en listas enlazadas.
 */
public class DataMapper {

	// --- Verduras ---
	/**
	 * Convierte un DTO de Verdura a una entidad Verdura.
	 * 
	 * @param dto DTO de Verdura a convertir.
	 * @return Entidad Verdura resultante.
	 */
	public static Verdura verduraDTOToVerdura(VerduraDTO dto) {
		return new Verdura(dto.getNombre(), dto.getPrecio(), dto.getImagen(), dto.getForma(), dto.getTamano());
	}

	/**
	 * Convierte una entidad Verdura a un DTO de Verdura.
	 * 
	 * @param entity Entidad Verdura a convertir.
	 * @return DTO de Verdura resultante.
	 */
	public static VerduraDTO verduraToVerduraDTO(Verdura entity) {
		return new VerduraDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(), entity.getForma(),
				entity.getTamano());
	}

	/**
	 * Convierte una lista enlazada de Verduras a una lista enlazada de DTOs de
	 * Verdura.
	 * 
	 * @param listaVerduras Lista enlazada de Verduras a convertir.
	 * @return Lista enlazada de DTOs de Verdura resultante.
	 */
	public static LinkedList<VerduraDTO> listaVerduraToListaVerduraDTO(LinkedList<Verdura> listaVerduras) {
		LinkedList<VerduraDTO> dtoList = new LinkedList<>();
		listaVerduraToListaVerduraDTOR(listaVerduras.getFirst(), dtoList);
		return dtoList;
	}

	/**
	 * Convierte una lista enlazada de Verduras a una lista enlazada de DTOs de
	 * Verdura de forma recursiva.
	 * 
	 * @param current Nodo actual de la lista de Verduras.
	 * @param dtoList Lista enlazada de DTOs de Verdura donde se guardarán los
	 *                resultados.
	 */
	private static void listaVerduraToListaVerduraDTOR(Node<Verdura> current, LinkedList<VerduraDTO> dtoList) {
		if (current != null) {
			Verdura v = current.getInfo();
			VerduraDTO dto = new VerduraDTO(v.getNombre(), v.getPrecio(), v.getImagen(), v.getForma(), v.getTamano());
			if (dtoList.isEmpty()) {
				dtoList.setFirst(new Node<VerduraDTO>(dto));
			} else {
				dtoList.addLastR(dto);
			}
			listaVerduraToListaVerduraDTOR(current.getNext(), dtoList);
		}
	}

	/**
	 * Convierte una lista enlazada de DTOs de Verdura a una lista enlazada de
	 * Verduras.
	 * 
	 * @param dtoList Lista enlazada de DTOs de Verdura a convertir.
	 * @return Lista enlazada de Verduras resultante.
	 */
	public static LinkedList<Verdura> listaVerduraDTOToListaVerdura(LinkedList<VerduraDTO> dtoList) {
		LinkedList<Verdura> entityList = new LinkedList<>();
		listaVerduraDTOToListaVerduraR(dtoList.getFirst(), entityList);
		return entityList;
	}

	/**
	 * Convierte una lista enlazada de DTOs de Verdura a una lista enlazada de
	 * Verduras de forma recursiva.
	 * 
	 * @param current    Nodo actual de la lista de DTOs de Verdura.
	 * @param entityList Lista enlazada de Verduras donde se guardarán los
	 *                   resultados.
	 */
	private static void listaVerduraDTOToListaVerduraR(Node<VerduraDTO> current, LinkedList<Verdura> entityList) {
		if (current != null) {
			VerduraDTO d = current.getInfo();
			Verdura entity = new Verdura(d.getNombre(), d.getPrecio(), d.getImagen(), d.getForma(), d.getTamano());
			if (entityList.isEmpty()) {
				entityList.setFirst(new Node<Verdura>(entity));
			} else {
				entityList.addLastR(entity);
			}
			listaVerduraDTOToListaVerduraR(current.getNext(), entityList);
		}
	}

	// --- Frutas ---
	/**
	 * Convierte un DTO de Fruta a una entidad Fruta.
	 * 
	 * @param dto DTO de Fruta a convertir.
	 * @return Entidad Fruta resultante.
	 */
	public static Fruta frutaDTOToFruta(FrutaDTO dto) {
		return new Fruta(dto.getNombre(), dto.getPrecio(), dto.getImagen(), dto.getAroma(), dto.getSabor());
	}

	/**
	 * Convierte una entidad Fruta a un DTO de Fruta.
	 * 
	 * @param entity Entidad Fruta a convertir.
	 * @return DTO de Fruta resultante.
	 */
	public static FrutaDTO frutaToFrutaDTO(Fruta entity) {
		return new FrutaDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(), entity.getAroma(),
				entity.getSabor());
	}

	/**
	 * Convierte una lista enlazada de Frutas a una lista enlazada de DTOs de Fruta.
	 * 
	 * @param entityList Lista enlazada de Frutas a convertir.
	 * @return Lista enlazada de DTOs de Fruta resultante.
	 */
	public static LinkedList<FrutaDTO> listaFrutaToListaFrutaDTO(LinkedList<Fruta> entityList) {
		LinkedList<FrutaDTO> dtoList = new LinkedList<>();
		listaFrutaToListaFrutaDTOR(entityList.getFirst(), dtoList);
		return dtoList;
	}

	/**
	 * Convierte una lista enlazada de Frutas a una lista enlazada de DTOs de Fruta
	 * de forma recursiva.
	 * 
	 * @param current Nodo actual de la lista de Frutas.
	 * @param dtoList Lista enlazada de DTOs de Fruta donde se guardarán los
	 *                resultados.
	 */
	private static void listaFrutaToListaFrutaDTOR(Node<Fruta> current, LinkedList<FrutaDTO> dtoList) {
		if (current != null) {
			Fruta f = current.getInfo();
			FrutaDTO dto = new FrutaDTO(f.getNombre(), f.getPrecio(), f.getImagen(), f.getAroma(), f.getSabor());
			if (dtoList.isEmpty()) {
				dtoList.setFirst(new Node<FrutaDTO>(dto));
			} else {
				dtoList.addLastR(dto);
			}
			listaFrutaToListaFrutaDTOR(current.getNext(), dtoList);
		}
	}

	/**
	 * Convierte una lista enlazada de DTOs de Fruta a una lista enlazada de Frutas.
	 * 
	 * @param dtoList Lista enlazada de DTOs de Fruta a convertir.
	 * @return Lista enlazada de Frutas resultante.
	 */
	public static LinkedList<Fruta> listaFrutaDTOToListaFruta(LinkedList<FrutaDTO> dtoList) {
		LinkedList<Fruta> entityList = new LinkedList<>();
		listaFrutaDTOToListaFrutaR(dtoList.getFirst(), entityList);
		return entityList;
	}

	/**
	 * Convierte una lista enlazada de DTOs de Fruta a una lista enlazada de Frutas
	 * de forma recursiva.
	 * 
	 * @param current    Nodo actual de la lista de DTOs de Fruta.
	 * @param entityList Lista enlazada de Frutas donde se guardarán los resultados.
	 */
	private static void listaFrutaDTOToListaFrutaR(Node<FrutaDTO> current, LinkedList<Fruta> entityList) {
		if (current != null) {
			FrutaDTO d = current.getInfo();
			Fruta entity = new Fruta(d.getNombre(), d.getPrecio(), d.getImagen(), d.getAroma(), d.getSabor());
			if (entityList.isEmpty()) {
				entityList.setFirst(new Node<Fruta>(entity));
			} else {
				entityList.addLastR(entity);
			}
			listaFrutaDTOToListaFrutaR(current.getNext(), entityList);
		}
	}

	// --- Paquete de Papas ---
	/**
	 * Convierte un DTO de PaquetePapa a una entidad PaquetePapa.
	 * 
	 * @param dto DTO de PaquetePapa a convertir.
	 * @return Entidad PaquetePapa resultante.
	 */
	public static PaquetePapa paquetePapaDTOToPaquetePapa(PaquetePapaDTO dto) {
		return new PaquetePapa(dto.getNombre(), dto.getPrecio(), dto.getImagen(), dto.isEsPicante());
	}

	/**
	 * Convierte una entidad PaquetePapa a un DTO de PaquetePapa.
	 * 
	 * @param entity Entidad PaquetePapa a convertir.
	 * @return DTO de PaquetePapa resultante.
	 */
	public static PaquetePapaDTO paquetePapaToPaquetePapaDTO(PaquetePapa entity) {
		return new PaquetePapaDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(), entity.isEsPicante());
	}

	/**
	 * Convierte una lista enlazada de PaquetePapa a una lista enlazada de DTOs de
	 * PaquetePapa.
	 * 
	 * @param entityList Lista enlazada de PaquetePapa a convertir.
	 * @return Lista enlazada de DTOs de PaquetePapa resultante.
	 */
	public static LinkedList<PaquetePapaDTO> listaPaquetePapaToListaPaquetePapaDTO(LinkedList<PaquetePapa> entityList) {
		LinkedList<PaquetePapaDTO> dtoList = new LinkedList<>();
		listaPaquetePapaToListaPaquetePapaDTOR(entityList.getFirst(), dtoList);
		return dtoList;
	}

	/**
	 * Convierte una lista enlazada de PaquetePapa a una lista enlazada de DTOs de
	 * PaquetePapa de forma recursiva.
	 * 
	 * @param current Nodo actual de la lista de PaquetePapa.
	 * @param dtoList Lista enlazada de DTOs de PaquetePapa donde se guardarán los
	 *                resultados.
	 */
	private static void listaPaquetePapaToListaPaquetePapaDTOR(Node<PaquetePapa> current,
			LinkedList<PaquetePapaDTO> dtoList) {
		if (current != null) {
			PaquetePapa p = current.getInfo();
			PaquetePapaDTO dto = new PaquetePapaDTO(p.getNombre(), p.getPrecio(), p.getImagen(), p.isEsPicante());
			if (dtoList.isEmpty()) {
				dtoList.setFirst(new Node<PaquetePapaDTO>(dto));
			} else {
				dtoList.addLastR(dto);
			}
			listaPaquetePapaToListaPaquetePapaDTOR(current.getNext(), dtoList);
		}
	}

	/**
	 * Convierte una lista enlazada de DTOs de PaquetePapa a una lista enlazada de
	 * PaquetePapa.
	 * 
	 * @param dtoList Lista enlazada de DTOs de PaquetePapa a convertir.
	 * @return Lista enlazada de PaquetePapa resultante.
	 */
	public static LinkedList<PaquetePapa> listaPaquetePapaDTOToListaPaquetePapa(LinkedList<PaquetePapaDTO> dtoList) {
		LinkedList<PaquetePapa> entityList = new LinkedList<>();
		listaPaquetePapaDTOToListaPaquetePapaR(dtoList.getFirst(), entityList);
		return entityList;
	}

	/**
	 * Convierte una lista enlazada de DTOs de PaquetePapa a una lista enlazada de
	 * PaquetePapa de forma recursiva.
	 * 
	 * @param current    Nodo actual de la lista de DTOs de PaquetePapa.
	 * @param entityList Lista enlazada de PaquetePapa donde se guardarán los
	 *                   resultados.
	 */
	private static void listaPaquetePapaDTOToListaPaquetePapaR(Node<PaquetePapaDTO> current,
			LinkedList<PaquetePapa> entityList) {
		if (current != null) {
			PaquetePapaDTO d = current.getInfo();
			PaquetePapa entity = new PaquetePapa(d.getNombre(), d.getPrecio(), d.getImagen(), d.isEsPicante());
			if (entityList.isEmpty()) {
				entityList.setFirst(new Node<PaquetePapa>(entity));
			} else {
				entityList.addLastR(entity);
			}
			listaPaquetePapaDTOToListaPaquetePapaR(current.getNext(), entityList);
		}
	}

	// --- Gaseosas ---
	/**
	 * Convierte un DTO de Gaseosa a una entidad Gaseosa.
	 * 
	 * @param dto DTO de Gaseosa a convertir.
	 * @return Entidad Gaseosa resultante.
	 */
	public static Gaseosa gaseosaDTOToGaseosa(GaseosaDTO dto) {
		return new Gaseosa(dto.getNombre(), dto.getPrecio(), dto.getImagen(), dto.getPresentacion(), dto.isEsZero());
	}

	/**
	 * Convierte una entidad Gaseosa a un DTO de Gaseosa.
	 * 
	 * @param entity Entidad Gaseosa a convertir.
	 * @return DTO de Gaseosa resultante.
	 */
	public static GaseosaDTO gaseosaToGaseosaDTO(Gaseosa entity) {
		return new GaseosaDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(), entity.getPresentacion(),
				entity.isEsZero());
	}

	/**
	 * Convierte una lista enlazada de Gaseosas a una lista enlazada de DTOs de
	 * Gaseosa.
	 * 
	 * @param entityList Lista enlazada de Gaseosas a convertir.
	 * @return Lista enlazada de DTOs de Gaseosa resultante.
	 */
	public static LinkedList<GaseosaDTO> listaGaseosaToListaGaseosaDTO(LinkedList<Gaseosa> entityList) {
		LinkedList<GaseosaDTO> dtoList = new LinkedList<>();
		listaGaseosaToListaGaseosaDTOR(entityList.getFirst(), dtoList);
		return dtoList;
	}

	/**
	 * Convierte una lista enlazada de Gaseosas a una lista enlazada de DTOs de
	 * Gaseosa de forma recursiva.
	 * 
	 * @param current Nodo actual de la lista de Gaseosas.
	 * @param dtoList Lista enlazada de DTOs de Gaseosa donde se guardarán los
	 *                resultados.
	 */
	private static void listaGaseosaToListaGaseosaDTOR(Node<Gaseosa> current, LinkedList<GaseosaDTO> dtoList) {
		if (current != null) {
			Gaseosa g = current.getInfo();
			GaseosaDTO dto = new GaseosaDTO(g.getNombre(), g.getPrecio(), g.getImagen(), g.getPresentacion(),
					g.isEsZero());
			if (dtoList.isEmpty()) {
				dtoList.setFirst(new Node<GaseosaDTO>(dto));
			} else {
				dtoList.addLastR(dto);
			}
			listaGaseosaToListaGaseosaDTOR(current.getNext(), dtoList);
		}
	}

	/**
	 * Convierte una lista enlazada de DTOs de Gaseosa a una lista enlazada de
	 * Gaseosas.
	 * 
	 * @param dtoList Lista enlazada de DTOs de Gaseosa a convertir.
	 * @return Lista enlazada de Gaseosas resultante.
	 */
	public static LinkedList<Gaseosa> listaGaseosaDTOToListaGaseosa(LinkedList<GaseosaDTO> dtoList) {
		LinkedList<Gaseosa> entityList = new LinkedList<>();
		listaGaseosaDTOToListaGaseosaR(dtoList.getFirst(), entityList);
		return entityList;
	}

	/**
	 * Convierte una lista enlazada de DTOs de Gaseosa a una lista enlazada de
	 * Gaseosas de forma recursiva.
	 * 
	 * @param current    Nodo actual de la lista de DTOs de Gaseosa.
	 * @param entityList Lista enlazada de Gaseosas donde se guardarán los
	 *                   resultados.
	 */
	private static void listaGaseosaDTOToListaGaseosaR(Node<GaseosaDTO> current, LinkedList<Gaseosa> entityList) {
		if (current != null) {
			GaseosaDTO d = current.getInfo();
			Gaseosa entity = new Gaseosa(d.getNombre(), d.getPrecio(), d.getImagen(), d.getPresentacion(),
					d.isEsZero());
			if (entityList.isEmpty()) {
				entityList.setFirst(new Node<Gaseosa>(entity));
			} else {
				entityList.addLastR(entity);
			}
			listaGaseosaDTOToListaGaseosaR(current.getNext(), entityList);
		}
	}

	// --- Jugos ---
	/**
	 * Convierte un DTO de Jugo a una entidad Jugo.
	 * 
	 * @param dto DTO de Jugo a convertir.
	 * @return Entidad Jugo resultante.
	 */
	public static Jugo jugoDTOToJugo(JugoDTO dto) {
		return new Jugo(dto.getNombre(), dto.getPrecio(), dto.getImagen(), dto.getPresentacion(), dto.isEsNatural());
	}

	/**
	 * Convierte una entidad Jugo a un DTO de Jugo.
	 * 
	 * @param entity Entidad Jugo a convertir.
	 * @return DTO de Jugo resultante.
	 */
	public static JugoDTO jugoToJugoDTO(Jugo entity) {
		return new JugoDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(), entity.getPresentacion(),
				entity.isEsNatural());
	}

	/**
	 * Convierte una lista enlazada de Jugos a una lista enlazada de DTOs de Jugo.
	 * 
	 * @param entityList Lista enlazada de Jugos a convertir.
	 * @return Lista enlazada de DTOs de Jugo resultante.
	 */
	public static LinkedList<JugoDTO> listaJugoToListaJugoDTO(LinkedList<Jugo> entityList) {
		LinkedList<JugoDTO> dtoList = new LinkedList<>();
		listaJugoToListaJugoDTOR(entityList.getFirst(), dtoList);
		return dtoList;
	}

	/**
	 * Convierte una lista enlazada de Jugos a una lista enlazada de DTOs de Jugo de
	 * forma recursiva.
	 * 
	 * @param current Nodo actual de la lista de Jugos.
	 * @param dtoList Lista enlazada de DTOs de Jugo donde se guardarán los
	 *                resultados.
	 */
	private static void listaJugoToListaJugoDTOR(Node<Jugo> current, LinkedList<JugoDTO> dtoList) {
		if (current != null) {
			Jugo j = current.getInfo();
			JugoDTO dto = new JugoDTO(j.getNombre(), j.getPrecio(), j.getImagen(), j.getPresentacion(),
					j.isEsNatural());
			if (dtoList.isEmpty()) {
				dtoList.setFirst(new Node<JugoDTO>(dto));
			} else {
				dtoList.addLastR(dto);
			}
			listaJugoToListaJugoDTOR(current.getNext(), dtoList);
		}
	}

	/**
	 * Convierte una lista enlazada de DTOs de Jugo a una lista enlazada de Jugos.
	 * 
	 * @param dtoList Lista enlazada de DTOs de Jugo a convertir.
	 * @return Lista enlazada de Jugos resultante.
	 */
	public static LinkedList<Jugo> listaJugoDTOToListaJugo(LinkedList<JugoDTO> dtoList) {
		LinkedList<Jugo> entityList = new LinkedList<>();
		listaJugoDTOToListaJugoR(dtoList.getFirst(), entityList);
		return entityList;
	}

	/**
	 * Convierte una lista enlazada de DTOs de Jugo a una lista enlazada de Jugos de
	 * forma recursiva.
	 * 
	 * @param current    Nodo actual de la lista de DTOs de Jugo.
	 * @param entityList Lista enlazada de Jugos donde se guardarán los resultados.
	 */
	private static void listaJugoDTOToListaJugoR(Node<JugoDTO> current, LinkedList<Jugo> entityList) {
		if (current != null) {
			JugoDTO d = current.getInfo();
			Jugo entity = new Jugo(d.getNombre(), d.getPrecio(), d.getImagen(), d.getPresentacion(), d.isEsNatural());
			if (entityList.isEmpty()) {
				entityList.setFirst(new Node<Jugo>(entity));
			} else {
				entityList.addLastR(entity);
			}
			listaJugoDTOToListaJugoR(current.getNext(), entityList);
		}
	}

	// --- Usuarios ---
	/**
	 * Convierte un DTO de Usuario a una entidad Usuario.
	 * 
	 * @param dto DTO de Usuario a convertir.
	 * @return Entidad Usuario resultante.
	 */
	public static Usuario usuarioDTOToUsuario(UsuarioDTO dto) {
		return new Usuario(dto.getNombre(), dto.getIdentificacion(), dto.getListaCarrtio());
	}

	/**
	 * Convierte una entidad Usuario a un DTO de Usuario.
	 * 
	 * @param entity Entidad Usuario a convertir.
	 * @return DTO de Usuario resultante.
	 */
	public static UsuarioDTO usuarioToUsuarioDTO(Usuario entity) {
		return new UsuarioDTO(entity.getNombre(), entity.getIdentificacion(), entity.getListaCarrtio());
	}

	/**
	 * Convierte una lista enlazada de Usuarios a una lista enlazada de DTOs de
	 * Usuario.
	 * 
	 * @param entityList Lista enlazada de Usuarios a convertir.
	 * @return Lista enlazada de DTOs de Usuario resultante.
	 */
	public static LinkedList<UsuarioDTO> listaUsuarioToListaUsuarioDTO(LinkedList<Usuario> entityList) {
		LinkedList<UsuarioDTO> dtoList = new LinkedList<>();
		listaUsuarioToListaUsuarioDTOR(entityList.getFirst(), dtoList);
		return dtoList;
	}

	/**
	 * Convierte una lista enlazada de Usuarios a una lista enlazada de DTOs de
	 * Usuario de forma recursiva.
	 * 
	 * @param current Nodo actual de la lista de Usuarios.
	 * @param dtoList Lista enlazada de DTOs de Usuario donde se guardarán los
	 *                resultados.
	 */
	private static void listaUsuarioToListaUsuarioDTOR(Node<Usuario> current, LinkedList<UsuarioDTO> dtoList) {
		if (current != null) {
			Usuario u = current.getInfo();
			UsuarioDTO dto = new UsuarioDTO(u.getNombre(), u.getIdentificacion(), u.getListaCarrtio());
			if (dtoList.isEmpty()) {
				dtoList.setFirst(new Node<UsuarioDTO>(dto));
			} else {
				dtoList.addLastR(dto);
			}
			listaUsuarioToListaUsuarioDTOR(current.getNext(), dtoList);
		}
	}

	/**
	 * Convierte una lista enlazada de DTOs de Usuario a una lista enlazada de
	 * Usuarios.
	 * 
	 * @param dtoList Lista enlazada de DTOs de Usuario a convertir.
	 * @return Lista enlazada de Usuarios resultante.
	 */
	public static LinkedList<Usuario> listaUsuarioDTOToListaUsuario(LinkedList<UsuarioDTO> dtoList) {
		LinkedList<Usuario> entityList = new LinkedList<>();
		listaUsuarioDTOToListaUsuarioR(dtoList.getFirst(), entityList);
		return entityList;
	}

	/**
	 * Convierte una lista enlazada de DTOs de Usuario a una lista enlazada de
	 * Usuarios de forma recursiva.
	 * 
	 * @param current    Nodo actual de la lista de DTOs de Usuario.
	 * @param entityList Lista enlazada de Usuarios donde se guardarán los
	 *                   resultados.
	 */
	private static void listaUsuarioDTOToListaUsuarioR(Node<UsuarioDTO> current, LinkedList<Usuario> entityList) {
		if (current != null) {
			UsuarioDTO d = current.getInfo();
			Usuario entity = new Usuario(d.getNombre(), d.getIdentificacion(), d.getListaCarrtio());
			if (entityList.isEmpty()) {
				entityList.setFirst(new Node<Usuario>(entity));
			} else {
				entityList.addLastR(entity);
			}
			listaUsuarioDTOToListaUsuarioR(current.getNext(), entityList);
		}
	}

	// --- Carritos ---
	/**
	 * Convierte un DTO de Carrito a una entidad Carrito.
	 * 
	 * @param dto DTO de Carrito a convertir.
	 * @return Entidad Carrito resultante.
	 */
	public static Carrito carritoDTOToCarrito(CarritoDTO dto) {
		Carrito carrito = new Carrito(dto.getNombre(), dto.getNombreU());
		carrito.setListaNombresProductos(dto.getListaNombresProductos());
		return carrito;
	}

	/**
	 * Convierte una entidad Carrito a un DTO de Carrito.
	 * 
	 * @param carrito Entidad Carrito a convertir.
	 * @return DTO de Carrito resultante.
	 */
	public static CarritoDTO carritoToCarritoDTO(Carrito carrito) {
		CarritoDTO dto = new CarritoDTO(carrito.getNombre(), carrito.getNombreU());
		dto.setListaNombresProductos(carrito.getListaNombresProductos());
		return dto;
	}

	/**
	 * Convierte una lista enlazada de Carritos a una lista enlazada de DTOs de
	 * Carrito.
	 * 
	 * @param entityList Lista enlazada de Carritos a convertir.
	 * @return Lista enlazada de DTOs de Carrito resultante.
	 */
	public static LinkedList<CarritoDTO> listaCarritoToListaCarritoDTO(LinkedList<Carrito> entityList) {
		LinkedList<CarritoDTO> dtoList = new LinkedList<>();
		listaCarritoToListaCarritoDTOR(entityList.getFirst(), dtoList);
		return dtoList;
	}

	/**
	 * Convierte una lista enlazada de Carritos a una lista enlazada de DTOs de
	 * Carrito de forma recursiva.
	 * 
	 * @param current Nodo actual de la lista de Carritos.
	 * @param dtoList Lista enlazada de DTOs de Carrito donde se guardarán los
	 *                resultados.
	 */
	private static void listaCarritoToListaCarritoDTOR(Node<Carrito> current, LinkedList<CarritoDTO> dtoList) {
		if (current != null) {
			Carrito c = current.getInfo();
			CarritoDTO dto = new CarritoDTO();
			dto.setNombre(c.getNombre());
			dto.setListaNombresProductos(c.getListaNombresProductos());
			if (dtoList.isEmpty()) {
				dtoList.setFirst(new Node<CarritoDTO>(dto));
			} else {
				dtoList.addLastR(dto);
			}
			listaCarritoToListaCarritoDTOR(current.getNext(), dtoList);
		}
	}

	/**
	 * Convierte una lista enlazada de DTOs de Carrito a una lista enlazada de
	 * Carritos.
	 * 
	 * @param dtoList Lista enlazada de DTOs de Carrito a convertir.
	 * @return Lista enlazada de Carritos resultante.
	 */
	public static LinkedList<Carrito> listaCarritoDTOToListaCarrito(LinkedList<CarritoDTO> dtoList) {
		LinkedList<Carrito> entityList = new LinkedList<>();
		listaCarritoDTOToListaCarritoR(dtoList.getFirst(), entityList);
		return entityList;
	}

	/**
	 * Convierte una lista enlazada de DTOs de Carrito a una lista enlazada de
	 * Carritos de forma recursiva.
	 * 
	 * @param current    Nodo actual de la lista de DTOs de Carrito.
	 * @param entityList Lista enlazada de Carritos donde se guardarán los
	 *                   resultados.
	 */
	private static void listaCarritoDTOToListaCarritoR(Node<CarritoDTO> current, LinkedList<Carrito> entityList) {
		if (current != null) {
			CarritoDTO d = current.getInfo();
			Carrito entity = new Carrito();
			entity.setNombre(d.getNombre());
			entity.setListaNombresProductos(d.getListaNombresProductos());
			if (entityList.isEmpty()) {
				entityList.setFirst(new Node<Carrito>(entity));
			} else {
				entityList.addLastR(entity);
			}
			listaCarritoDTOToListaCarritoR(current.getNext(), entityList);
		}
	}
}
