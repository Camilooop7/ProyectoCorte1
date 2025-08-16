package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Jugo;
import co.edu.unbosque.model.JugoDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class JugoDAO {

	private final String TEXT_FILE_NAME = "jugo.csv";
	private final String SERIAL_FILE_NAME = "jugo.dat";
	private LinkedList<Jugo> listaJugos;

	public JugoDAO() {
		listaJugos = new LinkedList<>();
		cargarDesdeArchivo();
	}

	public boolean add(JugoDTO newData) {
		Jugo nueva = DataMapper.jugoDTOToJugo(newData);
		if (find(nueva) == null) {
			listaJugos.addLastR(nueva);
			return true;
		}
		return false;
	}

	public Jugo find(Jugo toFind) {
		if (!listaJugos.isEmpty()) {
			return findRecursivo(listaJugos.getFirst(), toFind);
		}
		return null;
	}

	private Jugo findRecursivo(Node<Jugo> current, Jugo toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findRecursivo(current.getNext(), toFind);
	}

	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaJugos.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	private String escribirEnArchivoR(String contenido, Node<Jugo> current) {
		if (current == null) {
			return contenido;
		}
		Jugo v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getPrecio() + ";" + v.getImagen() + ";" + v.getPresentacion() + ";"
				+ v.isEsNatural() + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	public void cargarDesdeArchivoSerializado() {
		listaJugos = (LinkedList<Jugo>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (listaJugos == null) {
			listaJugos = new LinkedList<>();
		}
	}

	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaJugos);
	}

	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank() || contenido.isEmpty()) {
			return;
		}
		String[] filas = contenido.split("\n");
		cargarJugoRecursivo(filas, 0);
	}

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

	public LinkedList<Jugo> getListaJugos() {
		return listaJugos;
	}

	public void setListaJugos(LinkedList<Jugo> listaJugos) {
		this.listaJugos = listaJugos;
	}

}
