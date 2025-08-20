package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Gaseosa;
import co.edu.unbosque.model.GaseosaDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

/**
 * Clase que gestiona la persistencia de las gaseosas. Permite agregar, buscar y
 * cargar gaseosas desde archivos de texto y serializados.
 */
public class GaseosaDAO {

	private final String TEXT_FILE_NAME = "gaseosa.csv";
	private final String SERIAL_FILE_NAME = "gaseosa.dat";
	private LinkedList<Gaseosa> listaGaseosas;

	/**
	 * Constructor de GaseosaDAO. Inicializa la lista de gaseosas y carga los datos
	 * desde el archivo de texto.
	 */
	public GaseosaDAO() {
		listaGaseosas = new LinkedList<>();
		cargarDesdeArchivo();
	}

	/**
	 * Agrega una nueva gaseosa a la lista.
	 * 
	 * @param newData DTO de la gaseosa a agregar.
	 * @return true si la gaseosa fue agregada exitosamente, false si ya existe.
	 */
	public boolean add(GaseosaDTO newData) {
		Gaseosa nueva = DataMapper.gaseosaDTOToGaseosa(newData);
		if (find(nueva) == null) {
			listaGaseosas.addLastR(nueva);
			return true;
		}
		return false;
	}

	/**
	 * Busca una gaseosa en la lista por su nombre.
	 * 
	 * @param toFind Gaseosa a buscar.
	 * @return La gaseosa encontrada, o null si no existe.
	 */
	public Gaseosa find(Gaseosa toFind) {
		if (!listaGaseosas.isEmpty()) {
			return findRecursivo(listaGaseosas.getFirst(), toFind);
		}
		return null;
	}

	/**
	 * Busca una gaseosa de forma recursiva en la lista.
	 * 
	 * @param current Nodo actual de la lista.
	 * @param toFind  Gaseosa a buscar.
	 * @return La gaseosa encontrada, o null si no existe.
	 */
	private Gaseosa findRecursivo(Node<Gaseosa> current, Gaseosa toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findRecursivo(current.getNext(), toFind);
	}

	/**
	 * Escribe la lista de gaseosas en un archivo de texto.
	 */
	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaGaseosas.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	/**
	 * Escribe la lista de gaseosas en un archivo de texto de forma recursiva.
	 * 
	 * @param contenido Contenido acumulado del archivo.
	 * @param current   Nodo actual de la lista.
	 * @return Contenido del archivo con las gaseosas.
	 */
	private String escribirEnArchivoR(String contenido, Node<Gaseosa> current) {
		if (current == null) {
			return contenido;
		}
		Gaseosa g = current.getInfo();
		contenido += g.getNombre() + ";" + g.getPrecio() + ";" + g.getImagen() + ";" + g.getPresentacion() + ";"
				+ g.isEsZero() + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	/**
	 * Carga la lista de gaseosas desde un archivo serializado.
	 */
	public void cargarDesdeArchivoSerializado() {
		listaGaseosas = (LinkedList<Gaseosa>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (listaGaseosas == null) {
			listaGaseosas = new LinkedList<>();
		}
	}

	/**
	 * Escribe la lista de gaseosas en un archivo serializado.
	 */
	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaGaseosas);
	}

	/**
	 * Carga la lista de gaseosas desde un archivo de texto.
	 */
	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank() || contenido.isEmpty()) {
			return;
		}
		String[] filas = contenido.split("\n");
		cargarGaseosaRecursivo(filas, 0);
	}

	/**
	 * Carga las gaseosas desde un arreglo de líneas de forma recursiva.
	 * 
	 * @param filas Arreglo de líneas del archivo.
	 * @param index Índice actual del arreglo.
	 */
	private void cargarGaseosaRecursivo(String[] filas, int index) {
		if (index >= filas.length) {
			return;
		}
		String[] columna = filas[index].split(";");
		if (columna.length < 4) {
			System.err.println("Línea mal formateada: " + filas[index]);
			cargarGaseosaRecursivo(filas, index + 1);
			return;
		}
		try {
			String nombre = columna[0];
			int precio = Integer.parseInt(columna[1]);
			String imagen = columna[2];
			String presentacion = columna[3];
			boolean esZero = columna.length > 4 ? Boolean.parseBoolean(columna[4]) : false;
			Gaseosa nuevaGaseosa = new Gaseosa(nombre, precio, imagen, presentacion, esZero);
			listaGaseosas.addLastR(nuevaGaseosa);
		} catch (NumberFormatException e) {
			System.err.println("Error al convertir el precio en la línea: " + filas[index]);
		}
		cargarGaseosaRecursivo(filas, index + 1);
	}

	// Getters y setters
	public LinkedList<Gaseosa> getListaGaseosas() {
		return listaGaseosas;
	}

	public void setListaGaseosas(LinkedList<Gaseosa> listaGaseosas) {
		this.listaGaseosas = listaGaseosas;
	}
}
