package co.edu.unbosque.model.persistence;

import java.io.Serializable;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.UsuarioDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class UsuarioDAO implements OperacionDAO<UsuarioDTO, Usuario>, Serializable {

	private static final long serialVersionUID = 1L;

	private final String TEXT_FILE_NAME = "usuario.csv";
	private final String SERIAL_FILE_NAME = "usuario.dat";
	private LinkedList<Usuario> listaUsuarios;

	public UsuarioDAO() {
		listaUsuarios = new LinkedList<>();
		cargarDesdeArchivo();
	}

	@Override
	public String showAll() {
		return listaUsuarios.toString();
	}

	@Override
	public boolean add(UsuarioDTO newData) {
		Usuario nuevo = DataMapper.usuarioDTOToUsuario(newData);
		if (find(nuevo) == null) {
			listaUsuarios.addLastR(nuevo);
			guardarCambios();
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(UsuarioDTO delete) {
	    Usuario info = DataMapper.usuarioDTOToUsuario(delete);
	    if (listaUsuarios.isEmpty()) {
	        return false;
	    }

	    Node<Usuario> first = listaUsuarios.getFirst();

	    // Caso especial: eliminar el primer nodo
	    if (first.getInfo().equals(info)) {
	        listaUsuarios.setFirst(first.getNext());
	        guardarCambios();
	        return true;
	    }

	    // Llamamos al recursivo pasando como "previous" el primer nodo
	    boolean eliminado = deleteRecursivo(listaUsuarios.getFirst(), info);

	    if (eliminado) {
	        guardarCambios();
	    }

	    return eliminado;
	}

	private boolean deleteRecursivo(Node<Usuario> previous, Usuario info) {
	    // Si no hay siguiente, no se encontró
	    if (previous == null || previous.getNext() == null) {
	        return false;
	    }

	    // Si el siguiente nodo tiene la info buscada, lo eliminamos con extract
	    if (previous.getNext().getInfo().equals(info)) {
	        return listaUsuarios.extract(previous) != null;
	    }

	    // Avanzamos al siguiente nodo como nuevo "previous"
	    return deleteRecursivo(previous.getNext(), info);
	}


	@Override
	public Usuario find(Usuario toFind) {
		if (!listaUsuarios.isEmpty()) {
			return findR(listaUsuarios.getFirst(), toFind);
		}
		return null;
	}

	private Usuario findR(Node<Usuario> current, Usuario toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findR(current.getNext(), toFind);
	}

	public boolean update(UsuarioDTO previous, UsuarioDTO newData) {
		Usuario nuevo = DataMapper.usuarioDTOToUsuario(newData);
		Usuario previo = DataMapper.usuarioDTOToUsuario(previous);

		if (listaUsuarios.isEmpty()) {
			return false;
		}

		boolean actualizado = updateR(listaUsuarios.getFirst(), previo, nuevo);

		if (actualizado) {
			guardarCambios();
		}
		return actualizado;
	}

	private boolean updateR(Node<Usuario> current, Usuario previo, Usuario nuevo) {
		if (current == null) {
			return false;
		}
		if (current.getInfo().getNombre().equals(previo.getNombre())) {
			current.setInfo(nuevo);
			return true;
		}
		return updateR(current.getNext(), previo, nuevo);
	}

	@Override
	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaUsuarios.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	private String escribirEnArchivoR(String contenido, Node<Usuario> current) {
		if (current == null) {
			return contenido;
		}
		Usuario v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getIdentificacion() + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	@Override
	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaUsuarios);
	}

	public void guardarCambios() {
		escribirEnArchivo();
		escribirArchivoSerializado();
	}

	@Override
	public void cargarDesdeArchivoSerializado() {
		LinkedList<Usuario> leida = (LinkedList<Usuario>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (leida != null) {
			listaUsuarios = leida;
		} else {
			listaUsuarios = new LinkedList<>();
		}
	}

	@Override
	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank()) {
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
		if (columna.length < 2) {
			System.err.println("Línea mal formateada: " + filas[index]);
			cargarRecursivo(filas, index + 1);
			return;
		}

		try {
			String nombre = columna[0];
			int id = Integer.parseInt(columna[1]);
			Usuario nuevo = new Usuario(nombre, id);
			listaUsuarios.addLastR(nuevo);
		} catch (NumberFormatException e) {
			System.err.println("Error al convertir el ID en la línea: " + filas[index]);
		}

		cargarRecursivo(filas, index + 1);
	}
}
