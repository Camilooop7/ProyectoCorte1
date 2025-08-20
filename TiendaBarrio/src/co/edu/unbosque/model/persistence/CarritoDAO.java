package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.model.CarritoDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;
import java.io.Serializable;

/**
 * Clase que implementa las operaciones de persistencia para los carritos de
 * compra. Permite agregar, eliminar, buscar y actualizar carritos, así como
 * gestionar la persistencia en archivos.
 */
public class CarritoDAO implements OperacionDAO<CarritoDTO, Carrito>, Serializable {

	private static final long serialVersionUID = 1L;
	private final String TEXT_FILE_NAME = "carrito.csv";
	private final String SERIAL_FILE_NAME = "carrito.dat";
	private LinkedList<Carrito> listaCarritos;

	/**
	 * Constructor de la clase CarritoDAO. Inicializa la lista de carritos y carga
	 * los datos desde el archivo serializado.
	 */
	public CarritoDAO() {
		listaCarritos = new LinkedList<>();
		cargarDesdeArchivoSerializado();
	}

	/**
	 * Muestra todos los carritos en formato de cadena.
	 * 
	 * @return Cadena con la información de todos los carritos.
	 */
	@Override
	public String showAll() {
		return listaCarritos.toString();
	}

	/**
	 * Agrega un nuevo carrito a la lista.
	 * 
	 * @param newData DTO del carrito a agregar.
	 * @return true si el carrito fue agregado exitosamente, false si ya existía.
	 */
	@Override
	public boolean add(CarritoDTO newData) {
		Carrito nuevo = DataMapper.carritoDTOToCarrito(newData);
		if (find(nuevo) == null) {
			listaCarritos.addLastR(nuevo);
			guardarCambios();
			return true;
		}
		return false;
	}

	/**
	 * Elimina un carrito de la lista.
	 * 
	 * @param delete DTO del carrito a eliminar.
	 * @return true si el carrito fue eliminado exitosamente, false en caso
	 *         contrario.
	 */
	@Override
	public boolean delete(CarritoDTO delete) {
		Carrito info = DataMapper.carritoDTOToCarrito(delete);
		if (listaCarritos.isEmpty()) {
			return false;
		}
		if (listaCarritos.getFirst().getInfo().getNombre().equals(info.getNombre())
				&& listaCarritos.getFirst().getInfo().getNombreU().equals(info.getNombreU())) {
			listaCarritos.setFirst(listaCarritos.getFirst().getNext());
			guardarCambios();
			return true;
		}
		boolean eliminado = deleteRecursivo(listaCarritos.getFirst(), info);
		if (eliminado) {
			guardarCambios();
		}
		return eliminado;
	}

	/**
	 * Elimina un carrito de la lista de forma recursiva.
	 * 
	 * @param previous Nodo anterior al nodo actual.
	 * @param info     Carrito a eliminar.
	 * @return true si el carrito fue eliminado, false en caso contrario.
	 */
	private boolean deleteRecursivo(Node<Carrito> previous, Carrito info) {
		if (previous == null || previous.getNext() == null) {
			return false;
		}
		if (previous.getNext().getInfo().getNombre().equals(info.getNombre())
				&& previous.getNext().getInfo().getNombreU().equals(info.getNombreU())) {
			listaCarritos.extract(previous);
			return true;
		}
		return deleteRecursivo(previous.getNext(), info);
	}

	/**
	 * Busca un carrito en la lista.
	 * 
	 * @param toFind Carrito a buscar.
	 * @return El carrito encontrado, o null si no existe.
	 */
	@Override
	public Carrito find(Carrito toFind) {
		if (!listaCarritos.isEmpty()) {
			return findR(listaCarritos.getFirst(), toFind);
		}
		return null;
	}

	/**
	 * Busca un carrito en la lista de forma recursiva.
	 * 
	 * @param current Nodo actual de la lista.
	 * @param toFind  Carrito a buscar.
	 * @return El carrito encontrado, o null si no existe.
	 */
	private Carrito findR(Node<Carrito> current, Carrito toFind) {
		if (current == null) {
			return null;
		}
		if (current.getInfo().getNombre().equals(toFind.getNombre())
				&& current.getInfo().getNombreU().equals(toFind.getNombreU())) {
			return current.getInfo();
		}
		return findR(current.getNext(), toFind);
	}

	/**
	 * Actualiza la información de un carrito en la lista.
	 * 
	 * @param previous DTO del carrito con la información anterior.
	 * @param newData  DTO del carrito con la nueva información.
	 * @return true si el carrito fue actualizado exitosamente, false en caso
	 *         contrario.
	 */
	public boolean update(CarritoDTO previous, CarritoDTO newData) {
		Carrito nuevo = DataMapper.carritoDTOToCarrito(newData);
		Carrito previo = DataMapper.carritoDTOToCarrito(previous);
		if (listaCarritos.isEmpty()) {
			return false;
		}
		boolean actualizado = updateR(listaCarritos.getFirst(), previo, nuevo);
		if (actualizado) {
			guardarCambios();
		}
		return actualizado;
	}

	/**
	 * Actualiza la información de un carrito en la lista de forma recursiva.
	 * 
	 * @param current Nodo actual de la lista.
	 * @param previo  Carrito con la información anterior.
	 * @param nuevo   Carrito con la nueva información.
	 * @return true si el carrito fue actualizado, false en caso contrario.
	 */
	private boolean updateR(Node<Carrito> current, Carrito previo, Carrito nuevo) {
		if (current == null) {
			return false;
		}
		if (current.getInfo().getNombre().equals(previo.getNombre())
				&& current.getInfo().getNombreU().equals(previo.getNombreU())) {
			current.setInfo(nuevo);
			return true;
		}
		return updateR(current.getNext(), previo, nuevo);
	}

	/**
	 * Escribe la información de los carritos en un archivo de texto.
	 */
	@Override
	public void escribirEnArchivo() {
		String contenido = escribirEnArchivoR("", listaCarritos.getFirst());
		FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
	}

	/**
	 * Escribe la información de los carritos en un archivo de texto de forma
	 * recursiva.
	 * 
	 * @param contenido Contenido acumulado del archivo.
	 * @param current   Nodo actual de la lista.
	 * @return Contenido del archivo con la información de los carritos.
	 */
	private String escribirEnArchivoR(String contenido, Node<Carrito> current) {
		if (current == null) {
			return contenido;
		}
		Carrito c = current.getInfo();
		contenido += c.getNombreU() + ";" + c.getNombre() + ";"
				+ convertirListaNombresProductosAString(c.getListaNombresProductos()) + "\n";
		return escribirEnArchivoR(contenido, current.getNext());
	}

	/**
	 * Convierte la lista de nombres de productos de un carrito a una cadena.
	 * 
	 * @param listaNombresProductos Lista de nombres de productos.
	 * @return Cadena con los nombres de productos separados por "|".
	 */
	private String convertirListaNombresProductosAString(LinkedList<String> listaNombresProductos) {
		return convertirListaNombresProductosAStringR(listaNombresProductos.getFirst(), "");
	}

	/**
	 * Convierte la lista de nombres de productos a una cadena de forma recursiva.
	 * 
	 * @param current   Nodo actual de la lista de nombres de productos.
	 * @param resultado Cadena acumulada con los nombres de productos.
	 * @return Cadena con los nombres de productos separados por "|".
	 */
	private String convertirListaNombresProductosAStringR(Node<String> current, String resultado) {
		if (current == null) {
			return resultado.isEmpty() ? resultado : resultado.substring(0, resultado.length() - 1);
		}
		resultado += current.getInfo() + "|";
		return convertirListaNombresProductosAStringR(current.getNext(), resultado);
	}

	/**
	 * Escribe la lista de carritos en un archivo serializado.
	 */
	@Override
	public void escribirArchivoSerializado() {
		FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaCarritos);
	}

	/**
	 * Guarda los cambios realizados en la lista de carritos en los archivos de
	 * texto y serializado.
	 */
	public void guardarCambios() {
		escribirEnArchivo();
		escribirArchivoSerializado();
	}

	/**
	 * Carga la lista de carritos desde un archivo serializado.
	 */
	@Override
	public void cargarDesdeArchivoSerializado() {
		LinkedList<Carrito> leida = (LinkedList<Carrito>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
		if (leida != null) {
			listaCarritos = leida;
		} else {
			listaCarritos = new LinkedList<>();
		}
	}

	/**
	 * Carga la lista de carritos desde un archivo de texto.
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
	 * Carga la lista de carritos desde un arreglo de cadenas de forma recursiva.
	 * 
	 * @param filas Arreglo de cadenas con la información de los carritos.
	 * @param index Índice actual del arreglo.
	 */
	private void cargarRecursivo(String[] filas, int index) {
		if (index >= filas.length) {
			return;
		}
		String[] columna = filas[index].split(";");
		try {
			String nombreU = columna[0];
			String nombreCarrito = columna[1];
			LinkedList<String> listaNombresProductos = convertirStringAListaNombresProductos(columna[2]);
			Carrito nuevo = new Carrito(nombreCarrito, nombreU);
			nuevo.setListaNombresProductos(listaNombresProductos);
			listaCarritos.addLastR(nuevo);
		} catch (Exception e) {
			System.err.println("Error al leer la línea: " + filas[index]);
		}
		cargarRecursivo(filas, index + 1);
	}

	/**
	 * Convierte una cadena de nombres de productos a una lista enlazada.
	 * 
	 * @param nombresProductosStr Cadena con los nombres de productos separados por
	 *                            "|".
	 * @return Lista enlazada con los nombres de productos.
	 */
	private LinkedList<String> convertirStringAListaNombresProductos(String nombresProductosStr) {
		LinkedList<String> listaNombresProductos = new LinkedList<>();
		if (nombresProductosStr == null || nombresProductosStr.isEmpty()) {
			return listaNombresProductos;
		}
		String[] nombresProductosArray = nombresProductosStr.split("\\|");
		convertirNombresProductosRecursivo(nombresProductosArray, 0, listaNombresProductos);
		return listaNombresProductos;
	}

	/**
	 * Convierte un arreglo de nombres de productos a una lista enlazada de forma
	 * recursiva.
	 * 
	 * @param nombresProductosArray Arreglo de nombres de productos.
	 * @param index                 Índice actual del arreglo.
	 * @param listaNombresProductos Lista enlazada donde se guardarán los nombres de
	 *                              productos.
	 */
	private void convertirNombresProductosRecursivo(String[] nombresProductosArray, int index,
			LinkedList<String> listaNombresProductos) {
		if (index >= nombresProductosArray.length) {
			return;
		}
		listaNombresProductos.addLastR(nombresProductosArray[index]);
		convertirNombresProductosRecursivo(nombresProductosArray, index + 1, listaNombresProductos);
	}

	/**
	 * Agrega un nombre de producto a un carrito específico.
	 * 
	 * @param nombreU        Nombre del usuario dueño del carrito.
	 * @param nombreCarrito  Nombre del carrito.
	 * @param nombreProducto Nombre del producto a agregar.
	 * @return true si el producto fue agregado exitosamente, false en caso
	 *         contrario.
	 */
	public boolean agregarNombreProductoACarrito(String nombreU, String nombreCarrito, String nombreProducto) {
		Carrito carrito = new Carrito(nombreCarrito, nombreU);
		Carrito encontrado = find(carrito);
		if (encontrado != null) {
			encontrado.agregarNombreProducto(nombreProducto);
			guardarCambios();
			return true;
		}
		return false;
	}

	// Getters y setters
	public LinkedList<Carrito> getListaCarritos() {
		return listaCarritos;
	}

	public void setListaCarritos(LinkedList<Carrito> listaCarritos) {
		this.listaCarritos = listaCarritos;
	}
}
