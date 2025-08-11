package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Pelicula;
import co.edu.unbosque.model.PeliculaDTO;

/**
 * Clase DAO para la gestión de objetos Pelicula.
 * Implementa las operaciones CRUD y persistencia en archivo.
 */
public class PeliculaDAO implements OperacionDAO<PeliculaDTO, Pelicula> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "pelicula.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "pelicula.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Pelicula.
     */
    private ArrayList<Pelicula> listaPelicula;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public PeliculaDAO() {
        listaPelicula = new ArrayList<>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todas las películas almacenadas en la lista.
     * @return Una cadena con todos los objetos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaPelicula.isEmpty()) {
            return "No hay películas en la lista";
        } else {
            for (Pelicula pelicula : listaPelicula) {
                rta += pelicula;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los objetos en forma de lista de PeliculaDTO.
     * @return Lista de PeliculaDTO.
     */
    @Override
    public ArrayList<PeliculaDTO> getAll() {
        return DataMapper.listaPeliculaToListaPeliculaDTO(listaPelicula);
    }

    /**
     * Agrega un nuevo objeto si no existe ya en la lista.
     * @param newData Objeto PeliculaDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(PeliculaDTO newData) {
        if (find(DataMapper.peliculaDTOToPelicula(newData)) == null) {
            listaPelicula.add(DataMapper.peliculaDTOToPelicula(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un objeto de la lista.
     * @param delete Objeto PeliculaDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(PeliculaDTO delete) {
        Pelicula found = find(DataMapper.peliculaDTOToPelicula(delete));
        if (found != null) {
            listaPelicula.remove(found);
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca un objeto en la lista por su nombre.
     * @param toFind Objeto Pelicula a buscar.
     * @return El objeto encontrado o null si no existe.
     */
    @Override
    public Pelicula find(Pelicula toFind) {
        if (!listaPelicula.isEmpty()) {
            for (Pelicula e : listaPelicula) {
                if (e.getNombre().equals(toFind.getNombre())) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * Actualiza los datos de un objeto existente.
     * @param previous Datos anteriores.
     * @param newData Nuevos datos.
     * @return true si se actualizó, false si no se encontró.
     */
    @Override
    public boolean update(PeliculaDTO previous, PeliculaDTO newData) {
        Pelicula found = find(DataMapper.peliculaDTOToPelicula(previous));
        if (found != null) {
            listaPelicula.remove(found);
            listaPelicula.add(DataMapper.peliculaDTOToPelicula(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Escribe los datos en un archivo CSV.
     * Actualmente sin implementación.
     */
    @Override
    public void escribirEnArchivo() {
        // Implementar escritura en CSV si es necesario
    }

    /**
     * Guarda la lista de objetos en un archivo serializado.
     */
    @Override
    public void escribirArchivoSerializado() {
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaPelicula);
    }

    /**
     * Carga la lista desde un archivo serializado.
     * Si no existe o está vacía, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaPelicula = (ArrayList<Pelicula>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaPelicula == null) {
            listaPelicula = new ArrayList<>();
        }
    }

    /**
     * Obtiene la lista completa.
     * @return Lista de Pelicula.
     */
    public ArrayList<Pelicula> getListaPelicula() {
        return listaPelicula;
    }

    /**
     * Establece una nueva lista.
     * @param listaPelicula Lista a asignar.
     */
    public void setListaPelicula(ArrayList<Pelicula> listaPelicula) {
        this.listaPelicula = listaPelicula;
    }
}
