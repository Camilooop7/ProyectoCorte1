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

	public LinkedList<String> crearStock(LinkedList<Verdura> a, LinkedList<Gaseosa> b, LinkedList<Jugo> e,
			LinkedList<Fruta> c, LinkedList<PaquetePapa> d) {
		if (a == null)
			a = new LinkedList<>();
		if (b == null)
			b = new LinkedList<>();
		if (e == null)
			e = new LinkedList<>();
		if (c == null)
			c = new LinkedList<>();
		if (d == null)
			d = new LinkedList<>();

		listaProductos = agregarVerdura(a, 0, listaProductos);
		listaProductos = agregarGaseosa(b, 0, listaProductos);
		listaProductos = agregarJugo(e, 0, listaProductos);
		listaProductos = agregarFruta(c, 0, listaProductos);
		listaProductos = agregarPaquetePapa(d, 0, listaProductos);
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

	public LinkedList<String> agregarGaseosa(LinkedList<Gaseosa> b, int cont, LinkedList<String> resultado) {
		if (cont >= 10) { // Solo 5 gaseosas para no exceder el rango
			return resultado;
		}
		Random r = new Random();
		int pos = r.nextInt(0, 24); // 0-23
		Node<Gaseosa> nodo = b.get(pos);
		if (nodo != null) {
			resultado.addLastR(nodo.getInfo().getNombre());
		}
		return agregarGaseosa(b, cont + 1, resultado);
	}

	public LinkedList<String> agregarJugo(LinkedList<Jugo> e, int cont, LinkedList<String> resultado) {
		if (cont >= 10) { // Solo 5 jugos para no exceder el rango
			return resultado;
		}
		Random r = new Random();
		int pos = r.nextInt(0, 24); // 0-23
		Node<Jugo> nodo = e.get(pos);
		if (nodo != null) {
			resultado.addLastR(nodo.getInfo().getNombre());
		}
		return agregarJugo(e, cont + 1, resultado);
	}

	public LinkedList<String> agregarFruta(LinkedList<Fruta> c, int cont, LinkedList<String> resultado) {
		if (cont >= 10) {
			return resultado;
		}
		Random r = new Random();
		int pos = r.nextInt(72, 96); // 72-95
		Node<Fruta> nodo = c.get(pos - 72);
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
		Node<PaquetePapa> nodo = d.get(pos - 96);
		if (nodo != null) {
			resultado.addLastR(nodo.getInfo().getNombre());
		}
		return agregarPaquetePapa(d, cont + 1, resultado);
	}

	// Prueba
	public LinkedList<Verdura> generarVerdura() {
		LinkedList<Verdura> resultado = new LinkedList<>();
		return generarVerduraRec(listaProductos.getFirst(), verduraDAO.getListaVerduras().getFirst(), resultado);
	}

	private LinkedList<Verdura> generarVerduraRec(Node<String> nodoProducto, Node<Verdura> nodoVerdura,
			LinkedList<Verdura> resultado) {
		if (nodoProducto == null) {
			return resultado;
		}
		if (nodoVerdura == null) {
			return generarVerduraRec(nodoProducto.getNext(), verduraDAO.getListaVerduras().getFirst(), resultado);
		}
		if (nodoVerdura.getInfo().getNombre().equalsIgnoreCase(nodoProducto.getInfo())) {
			resultado.addLastR(nodoVerdura.getInfo());
			return generarVerduraRec(nodoProducto.getNext(), verduraDAO.getListaVerduras().getFirst(), resultado);
		}
		return generarVerduraRec(nodoProducto, nodoVerdura.getNext(), resultado);
	}

	// Método para generar la lista de Gaseosas
	public LinkedList<Gaseosa> generarGaseosa() {
		LinkedList<Gaseosa> resultado = new LinkedList<>();
		return generarGaseosaRec(listaProductos.getFirst(), gaseosaDAO.getListaGaseosas().getFirst(), resultado);
	}

	private LinkedList<Gaseosa> generarGaseosaRec(Node<String> nodoProducto, Node<Gaseosa> nodoGaseosa,
			LinkedList<Gaseosa> resultado) {
		if (nodoProducto == null) {
			return resultado;
		}
		if (nodoGaseosa == null) {
			return generarGaseosaRec(nodoProducto.getNext(), gaseosaDAO.getListaGaseosas().getFirst(), resultado);
		}
		if (nodoGaseosa.getInfo().getNombre().equalsIgnoreCase(nodoProducto.getInfo())) {
			resultado.addLastR(nodoGaseosa.getInfo());
			return generarGaseosaRec(nodoProducto.getNext(), gaseosaDAO.getListaGaseosas().getFirst(), resultado);
		}
		return generarGaseosaRec(nodoProducto, nodoGaseosa.getNext(), resultado);
	}

	// Método para generar la lista de Jugos
	public LinkedList<Jugo> generarJugo() {
		LinkedList<Jugo> resultado = new LinkedList<>();
		return generarJugoRec(listaProductos.getFirst(), jugoDAO.getListaJugos().getFirst(), resultado);
	}

	private LinkedList<Jugo> generarJugoRec(Node<String> nodoProducto, Node<Jugo> nodoJugo,
			LinkedList<Jugo> resultado) {
		if (nodoProducto == null) {
			return resultado;
		}
		if (nodoJugo == null) {
			return generarJugoRec(nodoProducto.getNext(), jugoDAO.getListaJugos().getFirst(), resultado);
		}
		if (nodoJugo.getInfo().getNombre().equalsIgnoreCase(nodoProducto.getInfo())) {
			resultado.addLastR(nodoJugo.getInfo());
			return generarJugoRec(nodoProducto.getNext(), jugoDAO.getListaJugos().getFirst(), resultado);
		}
		return generarJugoRec(nodoProducto, nodoJugo.getNext(), resultado);
	}

	// Método para generar la lista de Frutas
	public LinkedList<Fruta> generarFruta() {
		LinkedList<Fruta> resultado = new LinkedList<>();
		return generarFrutaRec(listaProductos.getFirst(), frutaDAO.getListaFrutas().getFirst(), resultado);
	}

	private LinkedList<Fruta> generarFrutaRec(Node<String> nodoProducto, Node<Fruta> nodoFruta,
			LinkedList<Fruta> resultado) {
		if (nodoProducto == null) {
			return resultado;
		}
		if (nodoFruta == null) {
			return generarFrutaRec(nodoProducto.getNext(), frutaDAO.getListaFrutas().getFirst(), resultado);
		}
		if (nodoFruta.getInfo().getNombre().equalsIgnoreCase(nodoProducto.getInfo())) {
			resultado.addLastR(nodoFruta.getInfo());
			return generarFrutaRec(nodoProducto.getNext(), frutaDAO.getListaFrutas().getFirst(), resultado);
		}
		return generarFrutaRec(nodoProducto, nodoFruta.getNext(), resultado);
	}

	// Método para generar la lista de Paquetes de Papa
	public LinkedList<PaquetePapa> generarPaquetePapa() {
		LinkedList<PaquetePapa> resultado = new LinkedList<>();
		return generarPaquetePapaRec(listaProductos.getFirst(), paquetePapaDAO.getListaPaquetePapas().getFirst(),
				resultado);
	}

	private LinkedList<PaquetePapa> generarPaquetePapaRec(Node<String> nodoProducto, Node<PaquetePapa> nodoPaquetePapa,
			LinkedList<PaquetePapa> resultado) {
		if (nodoProducto == null) {
			return resultado;
		}
		if (nodoPaquetePapa == null) {
			return generarPaquetePapaRec(nodoProducto.getNext(), paquetePapaDAO.getListaPaquetePapas().getFirst(),
					resultado);
		}
		if (nodoPaquetePapa.getInfo().getNombre().equalsIgnoreCase(nodoProducto.getInfo())) {
			resultado.addLastR(nodoPaquetePapa.getInfo());
			return generarPaquetePapaRec(nodoProducto.getNext(), paquetePapaDAO.getListaPaquetePapas().getFirst(),
					resultado);
		}
		return generarPaquetePapaRec(nodoProducto, nodoPaquetePapa.getNext(), resultado);
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
