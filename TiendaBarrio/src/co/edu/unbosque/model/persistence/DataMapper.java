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

public class DataMapper {
	// Verduras
	public static Verdura verduraDTOToVerdura(VerduraDTO dto) {
		return new Verdura(dto.getNombre(), dto.getPrecio(), dto.getImagen(), dto.getForma(), dto.getTamano());
	}

	public static VerduraDTO verduraToVerduraDTO(Verdura entity) {
		return new VerduraDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(), entity.getForma(),
				entity.getTamano());
	}

	public static LinkedList<VerduraDTO> listaVerduraToListaVerduraDTO(LinkedList<Verdura> listaVerduras) {
		LinkedList<VerduraDTO> dtoList = new LinkedList<>();
		listaVerduraToListaVerduraDTOR(listaVerduras.getFirst(), dtoList);
		return dtoList;
	}

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

	public static LinkedList<Verdura> listaVerduraDTOToListaVerdura(LinkedList<VerduraDTO> dtoList) {
		LinkedList<Verdura> entityList = new LinkedList<>();
		listaVerduraDTOToListaVerduraR(dtoList.getFirst(), entityList);
		return entityList;
	}

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

	// Frutas
	public static Fruta frutaDTOToFruta(FrutaDTO dto) {
		return new Fruta(dto.getNombre(), dto.getPrecio(), dto.getImagen(), dto.getAroma(), dto.getSabor());
	}

	public static FrutaDTO frutaToFrutaDTO(Fruta entity) {
		return new FrutaDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(), entity.getAroma(),
				entity.getSabor());
	}

	public static LinkedList<FrutaDTO> listaFrutaToListaFrutaDTO(LinkedList<Fruta> entityList) {
		LinkedList<FrutaDTO> dtoList = new LinkedList<>();
		listaFrutaToListaFrutaDTOR(entityList.getFirst(), dtoList);
		return dtoList;
	}

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

	public static LinkedList<Fruta> listaFrutaDTOToListaFruta(LinkedList<FrutaDTO> dtoList) {
		LinkedList<Fruta> entityList = new LinkedList<>();
		listaFrutaDTOToListaFrutaR(dtoList.getFirst(), entityList);
		return entityList;
	}

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

	// Paquete de Papas
	public static PaquetePapa paquetePapaDTOToPaquetePapa(PaquetePapaDTO dto) {
		return new PaquetePapa(dto.getNombre(), dto.getPrecio(), dto.getImagen(), dto.isEsPicante());
	}

	public static PaquetePapaDTO paquetePapaToPaquetePapaDTO(PaquetePapa entity) {
		return new PaquetePapaDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(), entity.isEsPicante());
	}

	public static LinkedList<PaquetePapaDTO> listaPaquetePapaToListaPaquetePapaDTO(LinkedList<PaquetePapa> entityList) {
		LinkedList<PaquetePapaDTO> dtoList = new LinkedList<>();
		listaPaquetePapaToListaPaquetePapaDTOR(entityList.getFirst(), dtoList);
		return dtoList;
	}

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

	public static LinkedList<PaquetePapa> listaPaquetePapaDTOToListaPaquetePapa(LinkedList<PaquetePapaDTO> dtoList) {
		LinkedList<PaquetePapa> entityList = new LinkedList<>();
		listaPaquetePapaDTOToListaPaquetePapaR(dtoList.getFirst(), entityList);
		return entityList;
	}

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

	// Gaseosas
	public static Gaseosa gaseosaDTOToGaseosa(GaseosaDTO dto) {
		return new Gaseosa(dto.getNombre(), dto.getPrecio(), dto.getImagen(), dto.getPresentacion(), dto.isEsZero());
	}

	public static GaseosaDTO gaseosaToGaseosaDTO(Gaseosa entity) {
		return new GaseosaDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(), entity.getPresentacion(),
				entity.isEsZero());
	}

	public static LinkedList<GaseosaDTO> listaGaseosaToListaGaseosaDTO(LinkedList<Gaseosa> entityList) {
		LinkedList<GaseosaDTO> dtoList = new LinkedList<>();
		listaGaseosaToListaGaseosaDTOR(entityList.getFirst(), dtoList);
		return dtoList;
	}

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

	public static LinkedList<Gaseosa> listaGaseosaDTOToListaGaseosa(LinkedList<GaseosaDTO> dtoList) {
		LinkedList<Gaseosa> entityList = new LinkedList<>();
		listaGaseosaDTOToListaGaseosaR(dtoList.getFirst(), entityList);
		return entityList;
	}

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

	// Jugos
	public static Jugo jugoDTOToJugo(JugoDTO dto) {
		return new Jugo(dto.getNombre(), dto.getPrecio(), dto.getImagen(), dto.getPresentacion(), dto.isEsNatural());
	}

	public static JugoDTO jugoToJugoDTO(Jugo entity) {
		return new JugoDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(), entity.getPresentacion(),
				entity.isEsNatural());
	}

	public static LinkedList<JugoDTO> listaJugoToListaJugoDTO(LinkedList<Jugo> entityList) {
		LinkedList<JugoDTO> dtoList = new LinkedList<>();
		listaJugoToListaJugoDTOR(entityList.getFirst(), dtoList);
		return dtoList;
	}

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

	public static LinkedList<Jugo> listaJugoDTOToListaJugo(LinkedList<JugoDTO> dtoList) {
		LinkedList<Jugo> entityList = new LinkedList<>();
		listaJugoDTOToListaJugoR(dtoList.getFirst(), entityList);
		return entityList;
	}

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

	// Usuarios
	public static Usuario usuarioDTOToUsuario(UsuarioDTO dto) {
		return new Usuario(dto.getNombre(), dto.getIdentificacion());
	}

	public static UsuarioDTO usuarioToUsuarioDTO(Usuario entity) {
		return new UsuarioDTO(entity.getNombre(), entity.getIdentificacion());
	}

	public static LinkedList<UsuarioDTO> listaUsuarioToListaUsuarioDTO(LinkedList<Usuario> entityList) {
		LinkedList<UsuarioDTO> dtoList = new LinkedList<>();
		listaUsuarioToListaUsuarioDTOR(entityList.getFirst(), dtoList);
		return dtoList;
	}

	private static void listaUsuarioToListaUsuarioDTOR(Node<Usuario> current, LinkedList<UsuarioDTO> dtoList) {
		if (current != null) {
			Usuario u = current.getInfo();
			UsuarioDTO dto = new UsuarioDTO(u.getNombre(), u.getIdentificacion());
			if (dtoList.isEmpty()) {
				dtoList.setFirst(new Node<UsuarioDTO>(dto));
			} else {
				dtoList.addLastR(dto);
			}
			listaUsuarioToListaUsuarioDTOR(current.getNext(), dtoList);
		}
	}

	public static LinkedList<Usuario> listaUsuarioDTOToListaUsuario(LinkedList<UsuarioDTO> dtoList) {
		LinkedList<Usuario> entityList = new LinkedList<>();
		listaUsuarioDTOToListaUsuarioR(dtoList.getFirst(), entityList);
		return entityList;
	}

	private static void listaUsuarioDTOToListaUsuarioR(Node<UsuarioDTO> current, LinkedList<Usuario> entityList) {
		if (current != null) {
			UsuarioDTO d = current.getInfo();
			Usuario entity = new Usuario(d.getNombre(), d.getIdentificacion());
			if (entityList.isEmpty()) {
				entityList.setFirst(new Node<Usuario>(entity));
			} else {
				entityList.addLastR(entity);
			}
			listaUsuarioDTOToListaUsuarioR(current.getNext(), entityList);
		}
	}

	// Carritos
	public static Carrito carritoDTOToCarrito(CarritoDTO dto) {
	    Carrito entity = new Carrito();
	    entity.setNombre(dto.getNombre());
	    entity.setListaNombresProductos(dto.getListaNombresProductos());
	    return entity;
	}

	public static CarritoDTO carritoToCarritoDTO(Carrito entity) {
	    CarritoDTO dto = new CarritoDTO();
	    dto.setNombre(entity.getNombre());
	    dto.setListaNombresProductos(entity.getListaNombresProductos());
	    return dto;
	}

	public static LinkedList<CarritoDTO> listaCarritoToListaCarritoDTO(LinkedList<Carrito> entityList) {
	    LinkedList<CarritoDTO> dtoList = new LinkedList<>();
	    listaCarritoToListaCarritoDTOR(entityList.getFirst(), dtoList);
	    return dtoList;
	}

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

	public static LinkedList<Carrito> listaCarritoDTOToListaCarrito(LinkedList<CarritoDTO> dtoList) {
	    LinkedList<Carrito> entityList = new LinkedList<>();
	    listaCarritoDTOToListaCarritoR(dtoList.getFirst(), entityList);
	    return entityList;
	}

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
