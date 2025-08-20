package co.edu.unbosque.model;

import java.util.Random;
import co.edu.unbosque.model.persistence.CarritoDAO;
import co.edu.unbosque.model.persistence.FrutaDAO;
import co.edu.unbosque.model.persistence.GaseosaDAO;
import co.edu.unbosque.model.persistence.JugoDAO;
import co.edu.unbosque.model.persistence.PaquetePapaDAO;
import co.edu.unbosque.model.persistence.UsuarioDAO;
import co.edu.unbosque.model.persistence.VerduraDAO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

/**
 * Clase fachada que centraliza la lógica de negocio y acceso a datos
 * para la gestión de productos, usuarios y carritos.
 * Proporciona métodos para crear stock aleatorio y generar listas de productos.
 */
public class ModelFacade {

    /** DAO para la gestión de verduras. */
    private VerduraDAO verduraDAO;

    /** DAO para la gestión de gaseosas. */
    private GaseosaDAO gaseosaDAO;

    /** DAO para la gestión de jugos. */
    private JugoDAO jugoDAO;

    /** DAO para la gestión de paquetes de papa. */
    private PaquetePapaDAO paquetePapaDAO;

    /** DAO para la gestión de frutas. */
    private FrutaDAO frutaDAO;

    /** DAO para la gestión de usuarios. */
    private UsuarioDAO usuarioDAO;

    /** DAO para la gestión de carritos. */
    private CarritoDAO carritoDAO;

    /** Lista que almacena los nombres de los productos seleccionados aleatoriamente. */
    private LinkedList<String> listaProductos;

    /**
     * Constructor por defecto de ModelFacade.
     * Inicializa todos los DAOs y la lista de productos.
     */
    public ModelFacade() {
        verduraDAO = new VerduraDAO();
        paquetePapaDAO = new PaquetePapaDAO();
        gaseosaDAO = new GaseosaDAO();
        jugoDAO = new JugoDAO();
        frutaDAO = new FrutaDAO();
        usuarioDAO = new UsuarioDAO();
        carritoDAO = new CarritoDAO();
        listaProductos = new LinkedList<>();
    }

    /**
     * Crea un stock aleatorio de productos y devuelve una lista con sus nombres.
     *
     * @param a Lista de verduras.
     * @param b Lista de gaseosas.
     * @param e Lista de jugos.
     * @param c Lista de frutas.
     * @param d Lista de paquetes de papa.
     * @return Lista enlazada con los nombres de los productos seleccionados aleatoriamente.
     */
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

    /**
     * Agrega nombres de verduras aleatorias a la lista de productos.
     *
     * @param a Lista de verduras.
     * @param cont Contador de iteraciones.
     * @param resultado Lista donde se almacenan los nombres de los productos.
     * @return Lista actualizada con los nombres de las verduras.
     */
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

    /**
     * Agrega nombres de gaseosas aleatorias a la lista de productos.
     *
     * @param b Lista de gaseosas.
     * @param cont Contador de iteraciones.
     * @param resultado Lista donde se almacenan los nombres de los productos.
     * @return Lista actualizada con los nombres de las gaseosas.
     */
    public LinkedList<String> agregarGaseosa(LinkedList<Gaseosa> b, int cont, LinkedList<String> resultado) {
        if (cont >= 10) {
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

    /**
     * Agrega nombres de jugos aleatorios a la lista de productos.
     *
     * @param e Lista de jugos.
     * @param cont Contador de iteraciones.
     * @param resultado Lista donde se almacenan los nombres de los productos.
     * @return Lista actualizada con los nombres de los jugos.
     */
    public LinkedList<String> agregarJugo(LinkedList<Jugo> e, int cont, LinkedList<String> resultado) {
        if (cont >= 10) {
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

    /**
     * Agrega nombres de frutas aleatorias a la lista de productos.
     *
     * @param c Lista de frutas.
     * @param cont Contador de iteraciones.
     * @param resultado Lista donde se almacenan los nombres de los productos.
     * @return Lista actualizada con los nombres de las frutas.
     */
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

    /**
     * Agrega nombres de paquetes de papa aleatorios a la lista de productos.
     *
     * @param d Lista de paquetes de papa.
     * @param cont Contador de iteraciones.
     * @param resultado Lista donde se almacenan los nombres de los productos.
     * @return Lista actualizada con los nombres de los paquetes de papa.
     */
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

    /**
     * Genera una lista de verduras a partir de los nombres almacenados en listaProductos.
     *
     * @return Lista enlazada de verduras.
     */
    public LinkedList<Verdura> generarVerdura() {
        LinkedList<Verdura> resultado = new LinkedList<>();
        return generarVerduraRec(listaProductos.getFirst(), verduraDAO.getListaVerduras().getFirst(), resultado);
    }

    /**
     * Método recursivo para generar la lista de verduras.
     *
     * @param nodoProducto Nodo actual de la lista de nombres de productos.
     * @param nodoVerdura Nodo actual de la lista de verduras.
     * @param resultado Lista donde se almacenan las verduras encontradas.
     * @return Lista de verduras generada.
     */
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

    /**
     * Genera una lista de gaseosas a partir de los nombres almacenados en listaProductos.
     *
     * @return Lista enlazada de gaseosas.
     */
    public LinkedList<Gaseosa> generarGaseosa() {
        LinkedList<Gaseosa> resultado = new LinkedList<>();
        return generarGaseosaRec(listaProductos.getFirst(), gaseosaDAO.getListaGaseosas().getFirst(), resultado);
    }

    /**
     * Método recursivo para generar la lista de gaseosas.
     *
     * @param nodoProducto Nodo actual de la lista de nombres de productos.
     * @param nodoGaseosa Nodo actual de la lista de gaseosas.
     * @param resultado Lista donde se almacenan las gaseosas encontradas.
     * @return Lista de gaseosas generada.
     */
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

    /**
     * Genera una lista de jugos a partir de los nombres almacenados en listaProductos.
     *
     * @return Lista enlazada de jugos.
     */
    public LinkedList<Jugo> generarJugo() {
        LinkedList<Jugo> resultado = new LinkedList<>();
        return generarJugoRec(listaProductos.getFirst(), jugoDAO.getListaJugos().getFirst(), resultado);
    }

    /**
     * Método recursivo para generar la lista de jugos.
     *
     * @param nodoProducto Nodo actual de la lista de nombres de productos.
     * @param nodoJugo Nodo actual de la lista de jugos.
     * @param resultado Lista donde se almacenan los jugos encontrados.
     * @return Lista de jugos generada.
     */
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

    /**
     * Genera una lista de frutas a partir de los nombres almacenados en listaProductos.
     *
     * @return Lista enlazada de frutas.
     */
    public LinkedList<Fruta> generarFruta() {
        LinkedList<Fruta> resultado = new LinkedList<>();
        return generarFrutaRec(listaProductos.getFirst(), frutaDAO.getListaFrutas().getFirst(), resultado);
    }

    /**
     * Método recursivo para generar la lista de frutas.
     *
     * @param nodoProducto Nodo actual de la lista de nombres de productos.
     * @param nodoFruta Nodo actual de la lista de frutas.
     * @param resultado Lista donde se almacenan las frutas encontradas.
     * @return Lista de frutas generada.
     */
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

    /**
     * Genera una lista de paquetes de papa a partir de los nombres almacenados en listaProductos.
     *
     * @return Lista enlazada de paquetes de papa.
     */
    public LinkedList<PaquetePapa> generarPaquetePapa() {
        LinkedList<PaquetePapa> resultado = new LinkedList<>();
        return generarPaquetePapaRec(listaProductos.getFirst(), paquetePapaDAO.getListaPaquetePapas().getFirst(),
                resultado);
    }

    /**
     * Método recursivo para generar la lista de paquetes de papa.
     *
     * @param nodoProducto Nodo actual de la lista de nombres de productos.
     * @param nodoPaquetePapa Nodo actual de la lista de paquetes de papa.
     * @param resultado Lista donde se almacenan los paquetes de papa encontrados.
     * @return Lista de paquetes de papa generada.
     */
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

    public CarritoDAO getCarritoDAO() {
        return carritoDAO;
    }

    public void setCarritoDAO(CarritoDAO carritoDAO) {
        this.carritoDAO = carritoDAO;
    }
}
