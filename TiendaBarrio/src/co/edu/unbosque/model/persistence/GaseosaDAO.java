package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Gaseosa;
import co.edu.unbosque.model.GaseosaDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class GaseosaDAO {

	private final String TEXT_FILE_NAME = "bebida.csv";
	private final String SERIAL_FILE_NAME = "bebida.dat";
	private LinkedList<Gaseosa> listaGaseosas;

	public GaseosaDAO() {
		listaGaseosas = new LinkedList<>();
		cargarDesdeArchivo();
	}

	public boolean add(GaseosaDTO newData) {
		Gaseosa nueva = DataMapper.gaseosaDTOToGaseosa(newData);
		if (find(nueva) == null) {
			listaGaseosas.addLastR(nueva);
			return true;
		}
		return false;
	}

	public Gaseosa find(Gaseosa toFind) {
		if (!listaGaseosas.isEmpty()) {
			return findRecursivo(listaGaseosas.getFirst(), toFind);
		}
		return null;
	}

	private Gaseosa findRecursivo(Node<Gaseosa> current, Gaseosa toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findRecursivo(current.getNext(), toFind);
	}

	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaGaseosas.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	private String escribirEnArchivoR(String contenido, Node<Gaseosa> current) {
		if (current == null) {
			return contenido;
		}
		Gaseosa v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getPrecio() + ";" + v.getImagen() + ";" + v.getPresentacion() + ";" 
				+ v.isEsZero() + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	public void cargarDesdeArchivoSerializado() {
		listaGaseosas = (LinkedList<Gaseosa>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (listaGaseosas == null) {
			listaGaseosas = new LinkedList<>();
		}
	}

	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaGaseosas);
	}

	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank() || contenido.isEmpty()) {
			return;
		}
		String[] filas = contenido.split("\n");
		cargarGaseosaRecursivo(filas, 0);
	}

	private void cargarGaseosaRecursivo(String[] filas, int index) {
		if (index >= filas.length) {
			return;
		}

		String[] columna = filas[index].split(";");
		if (columna.length < 5) {
			System.err.println("Línea mal formateada: " + filas[index]);
			cargarGaseosaRecursivo(filas, index + 1);
			return;
		}

		try {
			String nombre = columna[0];
			int precio = Integer.parseInt(columna[1]);
			String imagen = columna[2];
			String presentacion = columna[3];
			boolean esZero = Boolean.parseBoolean(columna[5]);
			Gaseosa nuevaGaseosa = new Gaseosa(nombre, precio, imagen, presentacion, esZero);
			listaGaseosas.addLastR(nuevaGaseosa);
		} catch (NumberFormatException e) {
			System.err.println("Error al convertir el precio en la línea: " + filas[index]);
		}

		cargarGaseosaRecursivo(filas, index + 1);
	}
	
	
	
}
