package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Audifono;
import co.edu.unbosque.model.AudifonoDTO;

/**
 * Clase que implementa la interfaz OperacionDAO para manejar
 * los datos relacionados con los audífonos.
 */
public class AudifonoDAO implements OperacionDAO<AudifonoDTO, Audifono> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "audifono.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "audifono.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Audifono.
     */
    private ArrayList<Audifono> listaAudifono;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public AudifonoDAO() {
        listaAudifono = new ArrayList<Audifono>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todos los audífonos almacenados en la lista.
     * @return Una cadena con todos los audífonos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaAudifono.isEmpty()) {
            return "No hay audífonos en la lista";
        } else {
            for (Audifono audifono : listaAudifono) {
                rta += audifono;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los audífonos en forma de lista de AudifonoDTO.
     * @return Lista de AudifonoDTO.
     */
    @Override
    public ArrayList<AudifonoDTO> getAll() {
        return DataMapper.listaAudifonoToListaAudifonoDTO(listaAudifono);
    }

    /**
     * Agrega un nuevo audífono si no existe ya en la lista.
     * @param newData Objeto AudifonoDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(AudifonoDTO newData) {
        if (find(DataMapper.audifonoDTOToAudifono(newData)) == null) {
            listaAudifono.add(DataMapper.audifonoDTOToAudifono(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un audífono de la lista.
     * @param delete Objeto AudifonoDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(AudifonoDTO delete) {
        Audifono found = find(DataMapper.audifonoDTOToAudifono(delete));
        if (found != null) {
            escribirArchivoSerializado();
            escribirEnArchivo();
            return listaAudifono.remove(found);
        } else {
            return false;
        }
    }

    /**
     * Busca un audífono en la lista por su nombre.
     * @param toFind Objeto Audifono a buscar.
     * @return El Audifono encontrado o null si no existe.
     */
    @Override
    public Audifono find(Audifono toFind) {
        if (!listaAudifono.isEmpty()) {
            for (Audifono a : listaAudifono) {
                if (a.getNombre().equals(toFind.getNombre())) {
                    return a;
                }
            }
        }
        return null;
    }

    /**
     * Actualiza los datos de un audífono existente.
     * @param previous Datos anteriores.
     * @param newData Nuevos datos.
     * @return true si se actualizó, false si no se encontró.
     */
    @Override
    public boolean update(AudifonoDTO previous, AudifonoDTO newData) {
        Audifono found = find(DataMapper.audifonoDTOToAudifono(previous));
        if (found != null) {
            listaAudifono.remove(found);
            listaAudifono.add(DataMapper.audifonoDTOToAudifono(newData));
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
     * Guarda la lista de audífonos en un archivo serializado.
     */
    @Override
    public void escribirArchivoSerializado() {
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaAudifono);
    }

    /**
     * Carga la lista de audífonos desde un archivo serializado.
     * Si no existe o está vacío, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaAudifono = (ArrayList<Audifono>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaAudifono == null) {
            listaAudifono = new ArrayList<Audifono>();
        }
    }

    /**
     * Obtiene la lista completa de audífonos.
     * @return Lista de Audifono.
     */
    public ArrayList<Audifono> getListaAudifono() {
        return listaAudifono;
    }

    /**
     * Establece una nueva lista de audífonos.
     * @param listaAudifono Lista a asignar.
     */
    public void setListaAudifono(ArrayList<Audifono> listaAudifono) {
        this.listaAudifono = listaAudifono;
    }
}
