package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Movil;
import co.edu.unbosque.model.MovilDTO;

/**
 * Clase DAO para la gestión de objetos Movil.
 * Implementa las operaciones CRUD y persistencia en archivo.
 */
public class MovilDAO implements OperacionDAO<MovilDTO, Movil> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "movil.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "movil.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Movil.
     */
    private ArrayList<Movil> listaMovil;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public MovilDAO() {
        listaMovil = new ArrayList<>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todos los móviles almacenados en la lista.
     * @return Una cadena con todos los objetos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaMovil.isEmpty()) {
            return "No hay móviles en la lista";
        } else {
            for (Movil movil : listaMovil) {
                rta += movil;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los objetos en forma de lista de MovilDTO.
     * @return Lista de MovilDTO.
     */
    @Override
    public ArrayList<MovilDTO> getAll() {
        return DataMapper.listaMovilToListaMovilDTO(listaMovil);
    }

    /**
     * Agrega un nuevo objeto si no existe ya en la lista.
     * @param newData Objeto MovilDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(MovilDTO newData) {
        if (find(DataMapper.movilDTOToMovil(newData)) == null) {
            listaMovil.add(DataMapper.movilDTOToMovil(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un objeto de la lista.
     * @param delete Objeto MovilDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(MovilDTO delete) {
        Movil found = find(DataMapper.movilDTOToMovil(delete));
        if (found != null) {
            listaMovil.remove(found);
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca un objeto en la lista por su nombre.
     * @param toFind Objeto Movil a buscar.
     * @return El objeto encontrado o null si no existe.
     */
    @Override
    public Movil find(Movil toFind) {
        if (!listaMovil.isEmpty()) {
            for (Movil e : listaMovil) {
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
    public boolean update(MovilDTO previous, MovilDTO newData) {
        Movil found = find(DataMapper.movilDTOToMovil(previous));
        if (found != null) {
            listaMovil.remove(found);
            listaMovil.add(DataMapper.movilDTOToMovil(newData));
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
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaMovil);
    }

    /**
     * Carga la lista desde un archivo serializado.
     * Si no existe o está vacía, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaMovil = (ArrayList<Movil>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaMovil == null) {
            listaMovil = new ArrayList<>();
        }
    }

    /**
     * Obtiene la lista completa.
     * @return Lista de Movil.
     */
    public ArrayList<Movil> getListaMovil() {
        return listaMovil;
    }

    /**
     * Establece una nueva lista.
     * @param listaMovil Lista a asignar.
     */
    public void setListaMovil(ArrayList<Movil> listaMovil) {
        this.listaMovil = listaMovil;
    }
}
