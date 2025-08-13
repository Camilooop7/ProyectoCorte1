package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Verdura;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class VerduraDAO {
	/**
	 * Se declara una variable constante definida por el final la cual no deja de
	 * ser modificada una vez inicializada y se le indica el tipo de archivo .dat <
	 */
	private final String TEXT_FILE_NAME = "vegetal.csv";
	private final String SERIAL_FILE_NAME = "vegetal.dat";

	private LinkedList<Verdura> listaVerduras;

	public VerduraDAO() {
		listaVerduras = new LinkedList<>();
		cargarDesdeArchivo();
		escribirEnArchivo();

	}

//	public String showAll() {
//		if (listaVerduras.isEmpty()) {
//			return "No hay torneos en la lista";
//		}
//		return showAllRecursive(listaVerduras.getFirst());
//	}
//
//	private String showAllRecursive(Node<Verdura> current) {
//		if (current == null) {
//			return "";
//		}
//		return current.getInfo().toString() + "\n" + showAllRecursive(current.getNext());
//	}
//
//	public LinkedList<VerduraDTO> getAll() {
//		return DataMapper.listaVerduraToListaVerduraDTO(listaVerduras);
//	}
//PARA LA LISTA DE TODOS LOS PRODUCTOS 

//	public Verdura find(Verdura toFind) {
//		if (!listaVerduras.isEmpty()) {
//			return findRecursivo(listaVerduras.getFirst(), toFind);
//		}
//		return null;
//	}
//
//	private Verdura findRecursivo(Node<Verdura> current, Verdura toFind) {
//		if (current == null) {
//			return null;
//		}
//		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
//			return current.getInfo();
//		}
//		return findRecursivo(current.getNext(), toFind);
//	}

	public void escribirEnArchivo() {
		String contenido = "";
		if (!listaVerduras.isEmpty()) {
			contenido = escribirEnArchivoR("", listaVerduras.getFirst());
		}
		Node<Verdura> current = listaVerduras.getFirst();
		escribirEnArchivoR(contenido, current);
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	public String escribirEnArchivoR(String contenido, Node<Verdura> current) {
		if (current == null) {
			return contenido;
		}

		Verdura v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getPrecio() + ";" + v.getImagen() + ";" + v.getForma() + ";"
				+ v.getTamano() + "\n";
		current = current.getNext();
		return escribirEnArchivoR(contenido, current.getNext());
	}

	public void cargarDesdeArchivoSerializado() {
		listaVerduras = (LinkedList<Verdura>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (listaVerduras == null) {
			listaVerduras = new LinkedList<Verdura>();
		}
	}

	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaVerduras);
	}

	public void cargarDesdeArchivo() {
		// TODO Auto-generated method stub

	}

}
