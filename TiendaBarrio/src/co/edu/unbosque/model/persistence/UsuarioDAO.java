package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.UsuarioDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;
import java.io.Serializable;

/**
 * Clase que gestiona la persistencia de los usuarios. Implementa operaciones
 * CRUD y persistencia en archivos de texto y serializados.
 */
public class UsuarioDAO implements OperacionDAO<UsuarioDTO, Usuario>, Serializable {

	private static final long serialVersionUID = 1L;
	private final String TEXT_FILE_NAME = "usuario.csv";
	private final String SERIAL_FILE_NAME = "usuario.dat";
	private LinkedList<Usuario> listaUsuarios;

	/**
	 * Constructor de UsuarioDAO. Inicializa la lista de usuarios y carga los datos
	 * desde los archivos.
	 */
	public UsuarioDAO() {
		listaUsuarios = new LinkedList<>();
		cargarDesdeArchivo();
		cargarDesdeArchivoSerializado();
	}

	/**
	 * Muestra todos los usuarios en formato de cadena.
	 * 
	 * @return Cadena con la información de todos los usuarios.
	 */
	@Override
	public String showAll() {
		return listaUsuarios.toString();
	}

	/**
	 * Agrega un nuevo usuario a la lista.
	 * 
	 * @param newData DTO del usuario a agregar.
	 * @return true si el usuario fue agregado exitosamente, false si ya existe.
	 */
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

	/**
	 * Elimina un usuario de la lista.
	 * 
	 * @param delete DTO del usuario a eliminar.
	 * @return true si el usuario fue eliminado exitosamente, false en caso
	 *         contrario.
	 */
	@Override
	public boolean delete(UsuarioDTO delete) {
		Usuario info = DataMapper.usuarioDTOToUsuario(delete);
		if (listaUsuarios.isEmpty()) {
			return false;
		}
		if (listaUsuarios.getFirst().getInfo().getNombre().equals(info.getNombre())) {
			listaUsuarios.setFirst(listaUsuarios.getFirst().getNext());
			guardarCambios();
			return true;
		}
		boolean eliminado = deleteRecursivo(listaUsuarios.getFirst(), info);
		if (eliminado) {
			guardarCambios();
		}
		return eliminado;
	}

	/**
	 * Elimina un usuario de forma recursiva en la lista.
	 * 
	 * @param previous Nodo anterior al nodo actual.
	 * @param info     Usuario a eliminar.
	 * @return true si el usuario fue eliminado, false en caso contrario.
	 */
	private boolean deleteRecursivo(Node<Usuario> previous, Usuario info) {
		if (previous == null || previous.getNext() == null) {
			return false;
		}
		if (previous.getNext().getInfo().getNombre().equals(info.getNombre())) {
			listaUsuarios.extract(previous);
			return true;
		}
		return deleteRecursivo(previous.getNext(), info);
	}

	/**
	 * Busca un usuario en la lista por su nombre o identificación.
	 * 
	 * @param toFind Usuario a buscar.
	 * @return El usuario encontrado, o null si no existe.
	 */
	@Override
	public Usuario find(Usuario toFind) {
		if (!listaUsuarios.isEmpty()) {
			return findR(listaUsuarios.getFirst(), toFind);
		}
		return null;
	}

	/**
	 * Busca un usuario de forma recursiva en la lista.
	 * 
	 * @param current Nodo actual de la lista.
	 * @param toFind  Usuario a buscar.
	 * @return El usuario encontrado, o null si no existe.
	 */
	private Usuario findR(Node<Usuario> current, Usuario toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getIdentificacion() == (toFind.getIdentificacion())
				|| current.getInfo().getNombre().equals(toFind.getNombre())) {
			return current.getInfo();
		}
		return findR(current.getNext(), toFind);
	}

	/**
	 * Actualiza la información de un usuario en la lista.
	 * 
	 * @param previous DTO del usuario con la información anterior.
	 * @param newData  DTO del usuario con la nueva información.
	 * @return true si el usuario fue actualizado exitosamente, false en caso
	 *         contrario.
	 */
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

	/**
	 * Actualiza la información de un usuario de forma recursiva en la lista.
	 * 
	 * @param current Nodo actual de la lista.
	 * @param previo  Usuario con la información anterior.
	 * @param nuevo   Usuario con la nueva información.
	 * @return true si el usuario fue actualizado, false en caso contrario.
	 */
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

	/**
	 * Escribe la lista de usuarios en un archivo de texto.
	 */
	@Override
	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaUsuarios.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	/**
	 * Escribe la lista de usuarios en un archivo de texto de forma recursiva.
	 * 
	 * @param contenido Contenido acumulado del archivo.
	 * @param current   Nodo actual de la lista.
	 * @return Contenido del archivo con los usuarios.
	 */
	private String escribirEnArchivoR(String contenido, Node<Usuario> current) {
		if (current == null) {
			return contenido;
		}
		Usuario v = current.getInfo();
		contenido += v.getNombre() + ";" + v.getIdentificacion() + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	/**
	 * Escribe la lista de usuarios en un archivo serializado.
	 */
	@Override
	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaUsuarios);
	}

	/**
	 * Guarda los cambios realizados en la lista de usuarios en los archivos.
	 */
	public void guardarCambios() {
		escribirEnArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Carga la lista de usuarios desde un archivo serializado.
	 */
	@Override
	public void cargarDesdeArchivoSerializado() {
		LinkedList<Usuario> leida = (LinkedList<Usuario>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (leida != null) {
			listaUsuarios = leida;
		} else {
			listaUsuarios = new LinkedList<>();
		}
	}

	/**
	 * Carga la lista de usuarios desde un archivo de texto.
	 */
	@Override
	public void cargarDesdeArchivo() {
		String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
		if (contenido == null || contenido.isBlank()) {
			return;
		}
		String[] filas = contenido.split("\n");
		cargarRecursivo(filas, 0);
	}

	/**
	 * Carga los usuarios desde un arreglo de líneas de forma recursiva.
	 * 
	 * @param filas Arreglo de líneas del archivo.
	 * @param index Índice actual del arreglo.
	 */
	private void cargarRecursivo(String[] filas, int index) {
		// Implementación pendiente
	}
}
