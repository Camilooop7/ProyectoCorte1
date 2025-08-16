package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Verdura;
import co.edu.unbosque.model.VerduraDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class VerduraDAO {
	private final String TEXT_FILE_NAME = "vegetal.csv";
	private final String SERIAL_FILE_NAME = "vegetal.dat";
	private LinkedList<Verdura> listaVerduras;

	public VerduraDAO() {
		listaVerduras = new LinkedList<>();
		cargarDesdeArchivo();
	}

	public boolean add(VerduraDTO newData) {
		Verdura nueva = DataMapper.verduraDTOToVerdura(newData);
		if (find(nueva) == null) {
			listaVerduras.addLastR(nueva);
			return true;
		}
		return false;
	}

	public Verdura find(Verdura toFind) {
		if (!listaVerduras.isEmpty()) {
			return findR(listaVerduras.getFirst(), toFind);
		}
		return null;
	}

	private Verdura findR(Node<Verdura> current, Verdura toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findR(current.getNext(), toFind);
	}

	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaVerduras.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	private String escribirEnArchivoR(String contenido, Node<Verdura> current) {
		if (current == null) {
			return contenido;
		}
		Verdura v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getPrecio() + ";" + v.getImagen() + ";" + v.getForma() + ";"
				+ v.getTamano() + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	public void cargarDesdeArchivoSerializado() {
		listaVerduras = (LinkedList<Verdura>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (listaVerduras == null) {
			listaVerduras = new LinkedList<>();
		}
	}

	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaVerduras);
	}

	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank() || contenido.isEmpty()) {
			return;
		}
		String[] filas = contenido.split("\n");
		cargarRecursivo(filas, 0);
	}

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

	public LinkedList<Verdura> getListaVerduras() {
		return listaVerduras;
	}

	public void setListaVerduras(LinkedList<Verdura> listaVerduras) {
		this.listaVerduras = listaVerduras;
	}

}
