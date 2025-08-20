package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Jugo;
import co.edu.unbosque.model.JugoDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

/**
 * Clase que gestiona la persistencia de los jugos. Permite agregar, buscar y
 * cargar jugos desde archivos de texto y serializados.
 */
public class JugoDAO {

	private final String TEXT_FILE_NAME = "jugo.csv";
	private final String SERIAL_FILE_NAME = "jugo.dat";
	private LinkedList<Jugo> listaJugos;

	/**
	 * Constructor de JugoDAO. Inicializa la lista de jugos y carga los datos desde
	 * el archivo de texto.
	 */
	public JugoDAO() {
		listaJugos = new LinkedList<>();
		cargarDesdeArchivo();
	}

	/**
	 * Agrega un nuevo jugo a la lista.
	 * 
	 * @param newData DTO del jugo a agregar.
	 * @return true si el jugo fue agregado exitosamente, false si ya existe.
	 */
	public boolean add(JugoDTO newData) {
		Jugo nueva = DataMapper.jugoDTOToJugo(newData);
		if (find(nueva) == null) {
			listaJugos.addLastR(nueva);
			return true;
		}
		return false;
	}

	/**
	 * Busca un jugo en la lista por su nombre.
	 * 
	 * @param toFind Jugo a buscar.
	 * @return El jugo encontrado, o null si no existe.
	 */
	public Jugo find(Jugo toFind) {
		if (!listaJugos.isEmpty()) {
			return findRecursivo(listaJugos.getFirst(), toFind);
		}
		return null;
	}

	/**
	 * Busca un jugo de forma recursiva en la lista.
	 * 
	 * @param current Nodo actual de la lista.
	 * @param toFind  Jugo a buscar.
	 * @return El jugo encontrado, o null si no existe.
	 */
	private Jugo findRecursivo(Node<Jugo> current, Jugo toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findRecursivo(current.getNext(), toFind);
	}

	/**
	 * Escribe la lista de jugos en un archivo de texto.
	 */
	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaJugos.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	/**
	 * Escribe la lista de jugos en un archivo de texto de forma recursiva.
	 * 
	 * @param contenido Contenido acumulado del archivo.
	 * @param current   Nodo actual de la lista.
	 * @return Contenido del archivo con los jugos.
	 */
	private String escribirEnArchivoR(String contenido, Node<Jugo> current) {
		if (current == null) {
			return contenido;
		}
		Jugo v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getPrecio() + ";" + v.getImagen() + ";" + v.getPresentacion() + ";"
				+ v.isEsNatural() + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	/**
	 * Carga la lista de jugos desde un archivo serializado.
	 */
	public void cargarDesdeArchivoSerializado() {
		listaJugos = (LinkedList<Jugo>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (listaJugos == null) {
			listaJugos = new LinkedList<>();
		}
	}

	/**
	 * Escribe la lista de jugos en un archivo serializado.
	 */
	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaJugos);
	}

	/**
	 * Carga la lista de jugos desde un archivo de texto.
	 */
	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank() || contenido.isEmpty()) {
			return;
		}
		String[] filas = contenido.split("\n");
		cargarJugoRecursivo(filas, 0);
	}

	/**
	 * Carga los jugos desde un arreglo de líneas de forma recursiva.
	 * 
	 * @param filas Arreglo de líneas del archivo.
	 * @param index Índice actual del arreglo.
	 */
	private void cargarJugoRecursivo(String[] filas, int index) {
		if (index >= filas.length) {
			return;
		}
		String[] columna = filas[index].split(";");
		if (columna.length < 5) {
			System.err.println("Línea mal formateada: " + filas[index]);
			cargarJugoRecursivo(filas, index + 1);
			return;
		}
		try {
			String nombre = columna[0];
			int precio = Integer.parseInt(columna[1]);
			String imagen = columna[2];
			String presentacion = columna[3];
			boolean esNatural = Boolean.parseBoolean(columna[4]);
			Jugo nuevaJugo = new Jugo(nombre, precio, imagen, presentacion, esNatural);
			listaJugos.addLastR(nuevaJugo);
		} catch (NumberFormatException e) {
			System.err.println("Error al convertir el precio en la línea: " + filas[index]);
		}
		cargarJugoRecursivo(filas, index + 1);
	}

	// Getters y setters
	public LinkedList<Jugo> getListaJugos() {
		return listaJugos;
	}

	public void setListaJugos(LinkedList<Jugo> listaJugos) {
		this.listaJugos = listaJugos;
	}
}
