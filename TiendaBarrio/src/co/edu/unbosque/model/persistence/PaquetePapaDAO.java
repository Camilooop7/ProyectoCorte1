package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.PaquetePapa;
import co.edu.unbosque.model.PaquetePapaDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

/**
 * Clase que gestiona la persistencia de los paquetes de papas. Permite agregar,
 * buscar y cargar paquetes de papas desde archivos de texto y serializados.
 */
public class PaquetePapaDAO {

	private final String TEXT_FILE_NAME = "paquete.csv";
	private final String SERIAL_FILE_NAME = "paquete.dat";
	private LinkedList<PaquetePapa> listaPaquetePapas;

	/**
	 * Constructor de PaquetePapaDAO. Inicializa la lista de paquetes de papas y
	 * carga los datos desde el archivo de texto.
	 */
	public PaquetePapaDAO() {
		listaPaquetePapas = new LinkedList<>();
		cargarDesdeArchivo();
	}

	/**
	 * Agrega un nuevo paquete de papas a la lista.
	 * 
	 * @param newData DTO del paquete de papas a agregar.
	 * @return true si el paquete fue agregado exitosamente, false si ya existe.
	 */
	public boolean add(PaquetePapaDTO newData) {
		PaquetePapa nueva = DataMapper.paquetePapaDTOToPaquetePapa(newData);
		if (find(nueva) == null) {
			listaPaquetePapas.addLastR(nueva);
			return true;
		}
		return false;
	}

	/**
	 * Busca un paquete de papas en la lista por su nombre.
	 * 
	 * @param toFind Paquete de papas a buscar.
	 * @return El paquete encontrado, o null si no existe.
	 */
	public PaquetePapa find(PaquetePapa toFind) {
		if (!listaPaquetePapas.isEmpty()) {
			return findRecursivo(listaPaquetePapas.getFirst(), toFind);
		}
		return null;
	}

	/**
	 * Busca un paquete de papas de forma recursiva en la lista.
	 * 
	 * @param current Nodo actual de la lista.
	 * @param toFind  Paquete de papas a buscar.
	 * @return El paquete encontrado, o null si no existe.
	 */
	private PaquetePapa findRecursivo(Node<PaquetePapa> current, PaquetePapa toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findRecursivo(current.getNext(), toFind);
	}

	/**
	 * Escribe la lista de paquetes de papas en un archivo de texto.
	 */
	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaPaquetePapas.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	/**
	 * Escribe la lista de paquetes de papas en un archivo de texto de forma
	 * recursiva.
	 * 
	 * @param contenido Contenido acumulado del archivo.
	 * @param current   Nodo actual de la lista.
	 * @return Contenido del archivo con los paquetes de papas.
	 */
	private String escribirEnArchivoR(String contenido, Node<PaquetePapa> current) {
		if (current == null) {
			return contenido;
		}
		PaquetePapa v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getPrecio() + ";" + v.getImagen() + ";" + v.isEsPicante() + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	/**
	 * Carga la lista de paquetes de papas desde un archivo serializado.
	 */
	public void cargarDesdeArchivoSerializado() {
		listaPaquetePapas = (LinkedList<PaquetePapa>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (listaPaquetePapas == null) {
			listaPaquetePapas = new LinkedList<>();
		}
	}

	/**
	 * Escribe la lista de paquetes de papas en un archivo serializado.
	 */
	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaPaquetePapas);
	}

	/**
	 * Carga la lista de paquetes de papas desde un archivo de texto.
	 */
	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank() || contenido.isEmpty()) {
			return;
		}
		String[] filas = contenido.split("\n");
		cargarPaquetePapaRecursivo(filas, 0);
	}

	/**
	 * Carga los paquetes de papas desde un arreglo de líneas de forma recursiva.
	 * 
	 * @param filas Arreglo de líneas del archivo.
	 * @param index Índice actual del arreglo.
	 */
	private void cargarPaquetePapaRecursivo(String[] filas, int index) {
		if (index >= filas.length) {
			return;
		}
		String[] columna = filas[index].split(";");
		if (columna.length < 4) {
			System.err.println("Línea mal formateada: " + filas[index]);
			cargarPaquetePapaRecursivo(filas, index + 1);
			return;
		}
		try {
			String nombre = columna[0];
			int precio = Integer.parseInt(columna[1]);
			String imagen = columna[2];
			boolean esPicante = Boolean.parseBoolean(columna[3]);
			PaquetePapa nuevaPaquetePapa = new PaquetePapa(nombre, precio, imagen, esPicante);
			listaPaquetePapas.addLastR(nuevaPaquetePapa);
		} catch (NumberFormatException e) {
			System.err.println("Error al convertir el precio en la línea: " + filas[index]);
		}
		cargarPaquetePapaRecursivo(filas, index + 1);
	}

	// Getters y setters
	public LinkedList<PaquetePapa> getListaPaquetePapas() {
		return listaPaquetePapas;
	}

	public void setListaPaquetePapas(LinkedList<PaquetePapa> listaPaquetePapas) {
		this.listaPaquetePapas = listaPaquetePapas;
	}
}
