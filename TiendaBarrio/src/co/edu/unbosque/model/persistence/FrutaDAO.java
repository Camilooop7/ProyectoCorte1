package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Fruta;
import co.edu.unbosque.model.FrutaDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

/**
 * Clase que gestiona la persistencia de las frutas. Permite agregar, buscar y
 * cargar frutas desde archivos de texto y serializados.
 */
public class FrutaDAO {

	private final String TEXT_FILE_NAME = "fruta.csv";
	private final String SERIAL_FILE_NAME = "fruta.dat";
	private LinkedList<Fruta> listaFrutas;

	/**
	 * Constructor de FrutaDAO. Inicializa la lista de frutas y carga los datos
	 * desde el archivo de texto.
	 */
	public FrutaDAO() {
		listaFrutas = new LinkedList<>();
		cargarDesdeArchivo();
	}

	/**
	 * Agrega una nueva fruta a la lista.
	 * 
	 * @param newData DTO de la fruta a agregar.
	 * @return true si la fruta fue agregada exitosamente, false si ya existe.
	 */
	public boolean add(FrutaDTO newData) {
		Fruta nueva = DataMapper.frutaDTOToFruta(newData);
		if (find(nueva) == null) {
			listaFrutas.addLastR(nueva);
			return true;
		}
		return false;
	}

	/**
	 * Busca una fruta en la lista por su nombre.
	 * 
	 * @param toFind Fruta a buscar.
	 * @return La fruta encontrada, o null si no existe.
	 */
	public Fruta find(Fruta toFind) {
		if (!listaFrutas.isEmpty()) {
			return findRecursivo(listaFrutas.getFirst(), toFind);
		}
		return null;
	}

	/**
	 * Busca una fruta de forma recursiva en la lista.
	 * 
	 * @param current Nodo actual de la lista.
	 * @param toFind  Fruta a buscar.
	 * @return La fruta encontrada, o null si no existe.
	 */
	private Fruta findRecursivo(Node<Fruta> current, Fruta toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findRecursivo(current.getNext(), toFind);
	}

	/**
	 * Escribe la lista de frutas en un archivo de texto.
	 */
	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaFrutas.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	/**
	 * Escribe la lista de frutas en un archivo de texto de forma recursiva.
	 * 
	 * @param contenido Contenido acumulado del archivo.
	 * @param current   Nodo actual de la lista.
	 * @return Contenido del archivo con las frutas.
	 */
	private String escribirEnArchivoR(String contenido, Node<Fruta> current) {
		if (current == null) {
			return contenido;
		}
		Fruta v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getPrecio() + ";" + v.getImagen() + ";" + v.getAroma() + ";" + v.getSabor()
				+ "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	/**
	 * Carga la lista de frutas desde un archivo serializado.
	 */
	public void cargarDesdeArchivoSerializado() {
		listaFrutas = (LinkedList<Fruta>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (listaFrutas == null) {
			listaFrutas = new LinkedList<>();
		}
	}

	/**
	 * Escribe la lista de frutas en un archivo serializado.
	 */
	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaFrutas);
	}

	/**
	 * Carga la lista de frutas desde un archivo de texto.
	 */
	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank() || contenido.isEmpty()) {
			return;
		}
		String[] filas = contenido.split("\n");
		cargarFrutaRecursivo(filas, 0);
	}

	/**
	 * Carga las frutas desde un arreglo de líneas de forma recursiva.
	 * 
	 * @param filas Arreglo de líneas del archivo.
	 * @param index Índice actual del arreglo.
	 */
	private void cargarFrutaRecursivo(String[] filas, int index) {
		if (index >= filas.length) {
			return;
		}
		String[] columna = filas[index].split(";");
		if (columna.length < 5) {
			System.err.println("Línea mal formateada: " + filas[index]);
			cargarFrutaRecursivo(filas, index + 1);
			return;
		}
		try {
			String nombre = columna[0];
			int precio = Integer.parseInt(columna[1]);
			String imagen = columna[2];
			String aroma = columna[3];
			String sabor = columna[4];
			Fruta nuevaFruta = new Fruta(nombre, precio, imagen, aroma, sabor);
			listaFrutas.addLastR(nuevaFruta);
		} catch (NumberFormatException e) {
			System.err.println("Error al convertir el precio en la línea: " + filas[index]);
		}
		cargarFrutaRecursivo(filas, index + 1);
	}

	// Getters y setters
	public LinkedList<Fruta> getListaFrutas() {
		return listaFrutas;
	}

	public void setListaFrutas(LinkedList<Fruta> listaFrutas) {
		this.listaFrutas = listaFrutas;
	}
}
