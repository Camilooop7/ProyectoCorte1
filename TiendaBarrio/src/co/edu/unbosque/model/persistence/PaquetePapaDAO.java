package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.PaquetePapa;
import co.edu.unbosque.model.PaquetePapaDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class PaquetePapaDAO {

	
	private final String TEXT_FILE_NAME = "paquete.csv";
	private final String SERIAL_FILE_NAME = "paquete.dat";
	private LinkedList<PaquetePapa> listaPaquetePapas;

	public PaquetePapaDAO() {
		listaPaquetePapas = new LinkedList<>();
		cargarDesdeArchivo();
	}

	public boolean add(PaquetePapaDTO newData) {
		PaquetePapa nueva = DataMapper.paquetePapaDTOToPaquetePapa(newData);
		if (find(nueva) == null) {
			listaPaquetePapas.addLastR(nueva);
			return true;
		}
		return false;
	}

	public PaquetePapa find(PaquetePapa toFind) {
		if (!listaPaquetePapas.isEmpty()) {
			return findRecursivo(listaPaquetePapas.getFirst(), toFind);
		}
		return null;
	}

	private PaquetePapa findRecursivo(Node<PaquetePapa> current, PaquetePapa toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findRecursivo(current.getNext(), toFind);
	}

	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaPaquetePapas.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	private String escribirEnArchivoR(String contenido, Node<PaquetePapa> current) {
		if (current == null) {
			return contenido;
		}
		PaquetePapa v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getPrecio() + ";" + v.getImagen() + ";"
				+ v.isEsPicante() + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	public void cargarDesdeArchivoSerializado() {
		listaPaquetePapas = (LinkedList<PaquetePapa>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (listaPaquetePapas == null) {
			listaPaquetePapas = new LinkedList<>();
		}
	}

	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaPaquetePapas);
	}

	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank() || contenido.isEmpty()) {
			return;
		}
		String[] filas = contenido.split("\n");
		cargarPaquetePapaRecursivo(filas, 0);
	}

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
	
	
}
