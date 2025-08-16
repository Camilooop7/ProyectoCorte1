package co.edu.unbosque.model;

import java.util.Random;
import co.edu.unbosque.model.persistence.FrutaDAO;
import co.edu.unbosque.model.persistence.GaseosaDAO;
import co.edu.unbosque.model.persistence.JugoDAO;
import co.edu.unbosque.model.persistence.PaquetePapaDAO;
import co.edu.unbosque.model.persistence.UsuarioDAO;
import co.edu.unbosque.model.persistence.VerduraDAO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class ModelFacade {
	private VerduraDAO verduraDAO;
	private GaseosaDAO gaseosaDAO;
	private JugoDAO jugoDAO;
	private PaquetePapaDAO paquetePapaDAO;
	private FrutaDAO frutaDAO;
	private UsuarioDAO usuarioDAO;
	private LinkedList<String> listaProductos;

	public ModelFacade() {
		verduraDAO = new VerduraDAO();
		paquetePapaDAO = new PaquetePapaDAO();
		gaseosaDAO = new GaseosaDAO();
		jugoDAO = new JugoDAO();
		frutaDAO = new FrutaDAO();
		usuarioDAO = new UsuarioDAO();
		listaProductos = new LinkedList<>();
	}

	public LinkedList<String> crearStock(LinkedList<Verdura> a, LinkedList<Bebida> b, LinkedList<Fruta> c,
			LinkedList<PaquetePapa> d) {
		listaProductos = agregarVerdura(a, 0, listaProductos);
		//listaProductos = agregarBebida(b, 0, listaProductos);
		//listaProductos = agregarFruta(c, 0, listaProductos);
		//listaProductos = agregarPaquetePapa(d, 0, listaProductos);
		return listaProductos;
	}
	
	

	public LinkedList<String> agregarVerdura(LinkedList<Verdura> a, int cont, LinkedList<String> resultado) {
		if (cont >= 10) {
			return resultado;
		}
		Random r = new Random();
		int pos = r.nextInt(0, 24); // 0-23
		Node<Verdura> nodo = a.get(pos);
		if (nodo != null) {
			resultado.addLastR(nodo.getInfo().getNombre());
		}
		return agregarVerdura(a, cont + 1, resultado);
	}

	public LinkedList<String> agregarBebida(LinkedList<Bebida> b, int cont, LinkedList<String> resultado) {
		if (cont >= 10) {
			return resultado;
		}
		Random r = new Random();
		int pos = r.nextInt(24, 72); // 24-71
		Node<Bebida> nodo = b.get(pos - 24); // Ajuste para el índice real en la lista
		if (nodo != null) {
			resultado.addLastR(nodo.getInfo().getNombre());
		}
		return agregarBebida(b, cont + 1, resultado);
	}

	public LinkedList<String> agregarFruta(LinkedList<Fruta> c, int cont, LinkedList<String> resultado) {
		if (cont >= 10) {
			return resultado;
		}
		Random r = new Random();
		int pos = r.nextInt(72, 96); // 72-95
		Node<Fruta> nodo = c.get(pos - 72); // Ajuste para el índice real en la lista
		if (nodo != null) {
			resultado.addLastR(nodo.getInfo().getNombre());
		}
		return agregarFruta(c, cont + 1, resultado);
	}

	public LinkedList<String> agregarPaquetePapa(LinkedList<PaquetePapa> d, int cont, LinkedList<String> resultado) {
		if (cont >= 10) {
			return resultado;
		}
		Random r = new Random();
		int pos = r.nextInt(96, 121); // 96-120
		Node<PaquetePapa> nodo = d.get(pos - 96); // Ajuste para el índice real en la lista
		if (nodo != null) {
			resultado.addLastR(nodo.getInfo().getNombre());
		}
		return agregarPaquetePapa(d, cont + 1, resultado);
	}
	
	
	
	///Prueba 
	public LinkedList<Verdura> generarVerduras() {
	    LinkedList<Verdura> resultado = new LinkedList<>();
	    return generarVerdurasRec(listaProductos.getFirst(), verduraDAO.getListaVerduras().getFirst(), resultado);
	}

	private LinkedList<Verdura> generarVerdurasRec(Node<String> nodoProducto, Node<Verdura> nodoVerdura, LinkedList<Verdura> resultado) {
	    if (nodoProducto == null) {
	        return resultado;
	    }
	    if (nodoVerdura == null) {
	        return generarVerdurasRec(nodoProducto.getNext(), verduraDAO.getListaVerduras().getFirst(), resultado);
	    }
	    if (nodoVerdura.getInfo().getNombre().equalsIgnoreCase(nodoProducto.getInfo())) {
	        resultado.addLastR(nodoVerdura.getInfo());
	        return generarVerdurasRec(nodoProducto.getNext(), verduraDAO.getListaVerduras().getFirst(), resultado);
	    }
	    return generarVerdurasRec(nodoProducto, nodoVerdura.getNext(), resultado);
	}


	

	// Getters y Setters
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

	public LinkedList<String> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(LinkedList<String> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
}
