package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Verdura;
import co.edu.unbosque.model.VerduraDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

/**
 * Clase que gestiona la persistencia de las verduras. Permite agregar, buscar y
 * cargar verduras desde archivos de texto y serializados.
 */
public class VerduraDAO {

	private final String TEXT_FILE_NAME = "vegetal.csv";
	private final String SERIAL_FILE_NAME = "vegetal.dat";
	private LinkedList<Verdura> listaVerduras;

	/**
	 * Constructor de VerduraDAO. Inicializa la lista de verduras y carga los datos
	 * desde el archivo de texto.
	 */
	public VerduraDAO() {
		listaVerduras = new LinkedList<>();
		cargarDesdeArchivo();
	}

	/**
	 * Agrega una nueva verdura a la lista.
	 * 
	 * @param newData DTO de la verdura a agregar.
	 * @return true si la verdura fue agregada exitosamente, false si ya existe.
	 */
	public boolean add(VerduraDTO newData) {
		Verdura nueva = DataMapper.verduraDTOToVerdura(newData);
		if (find(nueva) == null) {
			listaVerduras.addLastR(nueva);
			return true;
		}
		return false;
	}

	/**
	 * Busca una verdura en la lista por su nombre.
	 * 
	 * @param toFind Verdura a buscar.
	 * @return La verdura encontrada, o null si no existe.
	 */
	public Verdura find(Verdura toFind) {
		if (!listaVerduras.isEmpty()) {
			return findR(listaVerduras.getFirst(), toFind);
		}
		return null;
	}

	/**
	 * Busca una verdura de forma recursiva en la lista.
	 * 
	 * @param current Nodo actual de la lista.
	 * @param toFind  Verdura a buscar.
	 * @return La verdura encontrada, o null si no existe.
	 */
	private Verdura findR(Node<Verdura> current, Verdura toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findR(current.getNext(), toFind);
	}

	/**
	 * Escribe la lista de verduras en un archivo de texto.
	 */
	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaVerduras.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	/**
	 * Escribe la lista de verduras en un archivo de texto de forma recursiva.
	 * 
	 * @param contenido Contenido acumulado del archivo.
	 * @param current   Nodo actual de la lista.
	 * @return Contenido del archivo con las verduras.
	 */
	private String escribirEnArchivoR(String contenido, Node<Verdura> current) {
		if (current == null) {
			return contenido;
		}
		Verdura v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getPrecio() + ";" + v.getImagen() + ";" + v.getForma() + ";"
				+ v.getTamano() + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	/**
	 * Carga la lista de verduras desde un archivo serializado.
	 */
	public void cargarDesdeArchivoSerializado() {
		listaVerduras = (LinkedList<Verdura>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (listaVerduras == null) {
			listaVerduras = new LinkedList<>();
		}
	}

	/**
	 * Escribe la lista de verduras en un archivo serializado.
	 */
	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaVerduras);
	}

	/**
	 * Carga la lista de verduras desde un archivo de texto.
	 */
	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank() || contenido.isEmpty()) {
			return;
		}
		String[] filas = contenido.split("\n");
		cargarRecursivo(filas, 0);
	}

	/**
	 * Carga las verduras desde un arreglo de líneas de forma recursiva.
	 * 
	 * @param filas Arreglo de líneas del archivo.
	 * @param index Índice actual del arreglo.
	 */
	private void cargarRecursivo(String[] filas, int index) {
		if (index >= filas.length) {
			return;
		}
		String[] columna = filas[index].split(";");
		if (columna.length < 5) {
			System.err.println("Línea mal formateada: " + filas[index]);
			cargarRecursivo(filas, index + 1);
			return;
		}
		try {
			String nombre = columna[0];
			int precio = Integer.parseInt(columna[1]);
			String imagen = columna[2];
			String forma = columna[3];
			String tamano = columna[4];
			Verdura nuevaVerdura = new Verdura(nombre, precio, imagen, forma, tamano);
			listaVerduras.addLastR(nuevaVerdura);
		} catch (NumberFormatException e) {
			System.err.println("Error al convertir el precio en la línea: " + filas[index]);
		}
		cargarRecursivo(filas, index + 1);
	}

	// Getters y setters
	public LinkedList<Verdura> getListaVerduras() {
		return listaVerduras;
	}

	public void setListaVerduras(LinkedList<Verdura> listaVerduras) {
		this.listaVerduras = listaVerduras;
	}
}
