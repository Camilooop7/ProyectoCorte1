package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Labial;
import co.edu.unbosque.model.LabialDTO;

/**
 * Clase DAO para la gestión de objetos Labial.
 * Implementa las operaciones CRUD y persistencia en archivo.
 */
public class LabialDAO implements OperacionDAO<LabialDTO, Labial> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "labial.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "labial.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Labial.
     */
    private ArrayList<Labial> listaLabial;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public LabialDAO() {
        listaLabial = new ArrayList<>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todos los labiales almacenados en la lista.
     * @return Una cadena con todos los objetos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaLabial.isEmpty()) {
            return "No hay labiales en la lista";
        } else {
            for (Labial labial : listaLabial) {
                rta += labial;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los objetos en forma de lista de LabialDTO.
     * @return Lista de LabialDTO.
     */
    @Override
    public ArrayList<LabialDTO> getAll() {
        return DataMapper.listaLabialToListaLabialDTO(listaLabial);
    }

    /**
     * Agrega un nuevo objeto si no existe ya en la lista.
     * @param newData Objeto LabialDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(LabialDTO newData) {
        if (find(DataMapper.labialDTOToLabial(newData)) == null) {
            listaLabial.add(DataMapper.labialDTOToLabial(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un objeto de la lista.
     * @param delete Objeto LabialDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(LabialDTO delete) {
        Labial found = find(DataMapper.labialDTOToLabial(delete));
        if (found != null) {
            listaLabial.remove(found);
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca un objeto en la lista por su nombre.
     * @param toFind Objeto Labial a buscar.
     * @return El objeto encontrado o null si no existe.
     */
    @Override
    public Labial find(Labial toFind) {
        if (!listaLabial.isEmpty()) {
            for (Labial e : listaLabial) {
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
    public boolean update(LabialDTO previous, LabialDTO newData) {
        Labial found = find(DataMapper.labialDTOToLabial(previous));
        if (found != null) {
            listaLabial.remove(found);
            listaLabial.add(DataMapper.labialDTOToLabial(newData));
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
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaLabial);
    }

    /**
     * Carga la lista desde un archivo serializado.
     * Si no existe o está vacía, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaLabial = (ArrayList<Labial>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaLabial == null) {
            listaLabial = new ArrayList<>();
        }
    }

    /**
     * Obtiene la lista completa.
     * @return Lista de Labial.
     */
    public ArrayList<Labial> getListaLabial() {
        return listaLabial;
    }

    /**
     * Establece una nueva lista.
     * @param listaLabial Lista a asignar.
     */
    public void setListaLabial(ArrayList<Labial> listaLabial) {
        this.listaLabial = listaLabial;
    }
}
