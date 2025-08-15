package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Fruta;
import co.edu.unbosque.model.FrutaDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class FrutaDAO {

	private final String TEXT_FILE_NAME = "fruta.csv";
	private final String SERIAL_FILE_NAME = "fruta.dat";
	private LinkedList<Fruta> listaFrutas;

	public FrutaDAO() {
		listaFrutas = new LinkedList<>();
		cargarDesdeArchivo();
	}

	public boolean add(FrutaDTO newData) {
		Fruta nueva = DataMapper.frutaDTOToFruta(newData);
		if (find(nueva) == null) {
			listaFrutas.addLastR(nueva);
			return true;
		}
		return false;
	}

	public Fruta find(Fruta toFind) {
		if (!listaFrutas.isEmpty()) {
			return findRecursivo(listaFrutas.getFirst(), toFind);
		}
		return null;
	}

	private Fruta findRecursivo(Node<Fruta> current, Fruta toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findRecursivo(current.getNext(), toFind);
	}

	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaFrutas.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	private String escribirEnArchivoR(String contenido, Node<Fruta> current) {
		if (current == null) {
			return contenido;
		}
		Fruta v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getPrecio() + ";" + v.getImagen() + ";" + v.getAroma() + ";"
				+ v.getSabor() + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	public void cargarDesdeArchivoSerializado() {
		listaFrutas = (LinkedList<Fruta>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (listaFrutas == null) {
			listaFrutas = new LinkedList<>();
		}
	}

	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaFrutas);
	}

	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank() || contenido.isEmpty()) {
			return;
		}
		String[] filas = contenido.split("\n");
		cargarFrutaRecursivo(filas, 0);
	}

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
			String sabor= columna[4];
			Fruta nuevaFruta = new Fruta(nombre, precio, imagen, aroma, sabor);
			listaFrutas.addLastR(nuevaFruta);
		} catch (NumberFormatException e) {
			System.err.println("Error al convertir el precio en la línea: " + filas[index]);
		}

		cargarFrutaRecursivo(filas, index + 1);
	}
	
	
	
}
