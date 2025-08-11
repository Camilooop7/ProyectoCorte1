package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Hombre;
import co.edu.unbosque.model.HombreDTO;

/**
 * Clase que implementa la interfaz OperacionDAO para manejar
 * los datos relacionados con productos para hombre.
 */
public class HombreDAO implements OperacionDAO<HombreDTO, Hombre> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "hombre.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "hombre.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Hombre.
     */
    private ArrayList<Hombre> listaHombre;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public HombreDAO() {
        listaHombre = new ArrayList<Hombre>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todos los productos para hombre almacenados en la lista.
     * @return Una cadena con todos los objetos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaHombre.isEmpty()) {
            return "No hay productos para hombre en la lista";
        } else {
            for (Hombre hombre : listaHombre) {
                rta += hombre;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los objetos en forma de lista de HombreDTO.
     * @return Lista de HombreDTO.
     */
    @Override
    public ArrayList<HombreDTO> getAll() {
        return DataMapper.listaHombreToListaHombreDTO(listaHombre);
    }

    /**
     * Agrega un nuevo objeto si no existe ya en la lista.
     * @param newData Objeto HombreDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(HombreDTO newData) {
        if (find(DataMapper.hombreDTOToHombre(newData)) == null) {
            listaHombre.add(DataMapper.hombreDTOToHombre(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un objeto de la lista.
     * @param delete Objeto HombreDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(HombreDTO delete) {
        Hombre found = find(DataMapper.hombreDTOToHombre(delete));
        if (found != null) {
            escribirArchivoSerializado();
            escribirEnArchivo();
            return listaHombre.remove(found);
        } else {
            return false;
        }
    }

    /**
     * Busca un objeto en la lista por su nombre.
     * @param toFind Objeto Hombre a buscar.
     * @return El objeto encontrado o null si no existe.
     */
    @Override
    public Hombre find(Hombre toFind) {
        if (!listaHombre.isEmpty()) {
            for (Hombre h : listaHombre) {
                if (h.getNombre().equals(toFind.getNombre())) {
                    return h;
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
    public boolean update(HombreDTO previous, HombreDTO newData) {
        Hombre found = find(DataMapper.hombreDTOToHombre(previous));
        if (found != null) {
            listaHombre.remove(found);
            listaHombre.add(DataMapper.hombreDTOToHombre(newData));
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
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaHombre);
    }

    /**
     * Carga la lista desde un archivo serializado.
     * Si no existe o está vacío, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaHombre = (ArrayList<Hombre>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaHombre == null) {
            listaHombre = new ArrayList<Hombre>();
        }
    }

    /**
     * Obtiene la lista completa.
     * @return Lista de Hombre.
     */
    public ArrayList<Hombre> getListaHombre() {
        return listaHombre;
    }

    /**
     * Establece una nueva lista.
     * @param listaHombre Lista a asignar.
     */
    public void setListaHombre(ArrayList<Hombre> listaHombre) {
        this.listaHombre = listaHombre;
    }
}
