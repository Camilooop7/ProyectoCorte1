package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.Historial;
import co.edu.unbosque.model.HistorialDTO;

/**
 * Clase DAO para la gestión de objetos Historial.
 * Implementa las operaciones CRUD y persistencia en archivo.
 */
public class HistorialDAO implements OperacionDAO<HistorialDTO, Historial> {
    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "historial.csv";
    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "historial.dat";
    private static final long serialVersionUID = 1L;
    /**
     * Lista que almacena los objetos de tipo Historial.
     */
    private ArrayList<Historial> listaHistorial;
    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public HistorialDAO() {
        listaHistorial = new ArrayList<>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }
    /**
     * Muestra todos los historiales almacenados en la lista.
     * @return Una cadena con todos los objetos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaHistorial.isEmpty()) {
            return "No hay historiales en la lista";
        } else {
            for (Historial historial : listaHistorial) {
                rta += historial.toString() + "\n";
            }
        }
        return rta;
    }
    /**
     * Obtiene todos los objetos en forma de lista de HistorialDTO.
     * @return Lista de HistorialDTO.
     */
    @Override
    public ArrayList<HistorialDTO> getAll() {
        return DataMapper.listaHistorialToListaHistorialDTO(listaHistorial);
    }
    /**
     * Agrega un nuevo objeto si no existe ya en la lista.
     * @param newData Objeto HistorialDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(HistorialDTO newData) {
        if (find(DataMapper.historialDTOToHistorial(newData)) == null) {
            listaHistorial.add(DataMapper.historialDTOToHistorial(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }
    /**
     * Elimina un objeto de la lista.
     * @param delete Objeto HistorialDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(HistorialDTO delete) {
        Historial found = find(DataMapper.historialDTOToHistorial(delete));
        if (found != null) {
            listaHistorial.remove(found);
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }
    /**
     * Busca un objeto en la lista por el nombre del carrito.
     * @param toFind Objeto Historial a buscar.
     * @return El objeto encontrado o null si no existe.
     */
    @Override
    public Historial find(Historial toFind) {
        if (!listaHistorial.isEmpty()) {
            for (Historial e : listaHistorial) {
                if (e.getCarro().equals(toFind.getCarro())) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * Busca un objeto en la lista por el nombre del carrito usando un HistorialDTO.
     * @param toFindDTO Objeto HistorialDTO a buscar.
     * @return El objeto encontrado en forma de DTO o null si no existe.
     */
    public HistorialDTO findP(HistorialDTO toFindDTO) {
        if (!listaHistorial.isEmpty()) {
            for (Historial e : listaHistorial) {
                if (e.getCarro().equals(toFindDTO.getCarro())) {
                    return DataMapper.historialToHistorialDTO(e);
                }
            }
        }
        return null;
    }

    /**
     * Busca un historial en la lista por el nombre del carrito.
     *
     * @param carro El nombre del carrito.
     * @return El HistorialDTO encontrado o null si no existe.
     */
    public HistorialDTO findC(String carro) {
        if (listaHistorial != null && !listaHistorial.isEmpty()) {
            for (Historial h : listaHistorial) {
                if (h.getCarro().equals(carro)) {
                    return DataMapper.historialToHistorialDTO(h);
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
    public boolean update(HistorialDTO previous, HistorialDTO newData) {
        Historial found = find(DataMapper.historialDTOToHistorial(previous));
        if (found != null) {
            listaHistorial.remove(found);
            listaHistorial.add(DataMapper.historialDTOToHistorial(newData));
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
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaHistorial);
    }
    /**
     * Carga la lista desde un archivo serializado.
     * Si no existe o está vacía, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaHistorial = (ArrayList<Historial>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaHistorial == null) {
            listaHistorial = new ArrayList<>();
        }
    }
    /**
     * Obtiene la lista completa.
     * @return Lista de Historial.
     */
    public ArrayList<Historial> getListaHistorial() {
        return listaHistorial;
    }
    /**
     * Establece una nueva lista.
     * @param listaHistorial Lista a asignar.
     */
    public void setListaHistorial(ArrayList<Historial> listaHistorial) {
        this.listaHistorial = listaHistorial;
    }
}
