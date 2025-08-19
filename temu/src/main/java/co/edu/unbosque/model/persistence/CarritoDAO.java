package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.model.CarritoDTO;

/**
 * Clase en la cual se implementa la interfaz OperacionDAO para el manejo de datos de carritos
 */
public class CarritoDAO implements OperacionDAO<CarritoDTO, Carrito> {
    /**
     * Variable constante para el nombre del archivo CSV.
     */
    private final String TEXT_FILE_NAME = "carrito.csv";
    /**
     * Variable constante para el nombre del archivo serializado .dat.
     */
    private final String SERIAL_FILE_NAME = "carrito.dat";
    private static final long serialVersionUID = 1L;
    /**
     * Lista que almacena los objetos de tipo Carrito.
     */
    private ArrayList<Carrito> listaCarrito;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public CarritoDAO() {
        listaCarrito = new ArrayList<Carrito>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Método para mostrar todos los carritos almacenados.
     * @return Cadena con la información de todos los carritos o mensaje si está vacía.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaCarrito.isEmpty()) {
            return "No hay carritos en la lista";
        } else {
            for (Carrito carrito : listaCarrito) {
                rta += carrito.toString() + "\n";
            }
        }
        return rta;
    }

    /**
     * Método que devuelve la lista de carritos en forma de CarritoDTO.
     * @return Lista de CarritoDTO.
     */
    @Override
    public ArrayList<CarritoDTO> getAll() {
        return DataMapper.listaCarritoToListaCarritoDTO(listaCarrito);
    }

    /**
     * Método para agregar un nuevo carrito si no existe ya en la lista.
     * @param newData Objeto CarritoDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(CarritoDTO newData) {
        if (find(DataMapper.carritoDTOToCarrito(newData)) == null) {
            listaCarrito.add(DataMapper.carritoDTOToCarrito(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para eliminar un carrito de la lista.
     * @param delete Objeto CarritoDTO que se desea eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(CarritoDTO delete) {
        Carrito found = find(DataMapper.carritoDTOToCarrito(delete));
        if (found != null) {
            listaCarrito.remove(found);
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para buscar un carrito en la lista por su identificador.
     * @param toFind Objeto Carrito a buscar.
     * @return Objeto Carrito encontrado o null si no existe.
     */
    @Override
    public Carrito find(Carrito toFind) {
        if (!listaCarrito.isEmpty()) {
            for (Carrito c : listaCarrito) {
                if (c.getNombre().equals(toFind.getNombre())) { // Asumiendo que Carrito tiene un campo 'id'
                    return c;
            }
        }
    }
		return null;
    }

    public CarritoDTO findDTO(CarritoDTO toFind) {
    	if (!listaCarrito.isEmpty()) {
    		for (Carrito c : listaCarrito) {
    			if (c.getNombre().equals(toFind.getNombre())) { // Asumiendo que Carrito tiene un campo 'id'
    				return DataMapper.carritoToCarritoDTO(c);
    			}
    		}
    	}
    	return null;
    }

    /**
     * Método para actualizar los datos de un carrito existente.
     * @param previous Datos anteriores del carrito.
     * @param newData Nuevos datos del carrito.
     * @return true si se actualizó, false si no se encontró.
     */
    @Override
    public boolean update(CarritoDTO previous, CarritoDTO newData) {
        Carrito found = find(DataMapper.carritoDTOToCarrito(previous));
        if (found != null) {
            listaCarrito.remove(found);
            listaCarrito.add(DataMapper.carritoDTOToCarrito(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para escribir los datos en un archivo CSV.
     */
    @Override
    public void escribirEnArchivo() {
        // Implementar escritura en archivo CSV si es necesario
    }

    /**
     * Método para guardar la lista de carritos en un archivo serializado .dat.
     */
    @Override
    public void escribirArchivoSerializado() {
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaCarrito);
    }

    /**
     * Método para cargar la lista de carritos desde un archivo serializado.
     * Si el archivo no existe o está vacío, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaCarrito = (ArrayList<Carrito>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaCarrito == null) {
            listaCarrito = new ArrayList<Carrito>();
        }
    }

    /**
     * Getter para obtener la lista de carritos.
     */
    public ArrayList<Carrito> getListaCarrito() {
        return listaCarrito;
    }

    /**
     * Setter para modificar la lista de carritos.
     */
    public void setListaCarrito(ArrayList<Carrito> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }
}
