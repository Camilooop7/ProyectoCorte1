package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Mujer;
import co.edu.unbosque.model.MujerDTO;

/**
 * Clase DAO para la gestión de objetos Mujer.
 * Implementa las operaciones CRUD y persistencia en archivo.
 */
public class MujerDAO implements OperacionDAO<MujerDTO, Mujer> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "mujer.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "mujer.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Mujer.
     */
    private ArrayList<Mujer> listaMujer;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public MujerDAO() {
        listaMujer = new ArrayList<>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todas las prendas de mujer almacenadas en la lista.
     * @return Una cadena con todos los objetos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaMujer.isEmpty()) {
            return "No hay prendas de mujer en la lista";
        } else {
            for (Mujer mujer : listaMujer) {
                rta += mujer;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los objetos en forma de lista de MujerDTO.
     * @return Lista de MujerDTO.
     */
    @Override
    public ArrayList<MujerDTO> getAll() {
        return DataMapper.listaMujerToListaMujerDTO(listaMujer);
    }

    /**
     * Agrega un nuevo objeto si no existe ya en la lista.
     * @param newData Objeto MujerDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(MujerDTO newData) {
        if (find(DataMapper.mujerDTOToMujer(newData)) == null) {
            listaMujer.add(DataMapper.mujerDTOToMujer(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un objeto de la lista.
     * @param delete Objeto MujerDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(MujerDTO delete) {
        Mujer found = find(DataMapper.mujerDTOToMujer(delete));
        if (found != null) {
            listaMujer.remove(found);
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca un objeto en la lista por su nombre.
     * @param toFind Objeto Mujer a buscar.
     * @return El objeto encontrado o null si no existe.
     */
    @Override
    public Mujer find(Mujer toFind) {
        if (!listaMujer.isEmpty()) {
            for (Mujer e : listaMujer) {
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
    public boolean update(MujerDTO previous, MujerDTO newData) {
        Mujer found = find(DataMapper.mujerDTOToMujer(previous));
        if (found != null) {
            listaMujer.remove(found);
            listaMujer.add(DataMapper.mujerDTOToMujer(newData));
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
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaMujer);
    }

    /**
     * Carga la lista desde un archivo serializado.
     * Si no existe o está vacía, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaMujer = (ArrayList<Mujer>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaMujer == null) {
            listaMujer = new ArrayList<>();
        }
    }

    /**
     * Obtiene la lista completa.
     * @return Lista de Mujer.
     */
    public ArrayList<Mujer> getListaMujer() {
        return listaMujer;
    }

    /**
     * Establece una nueva lista.
     * @param listaMujer Lista a asignar.
     */
    public void setListaMujer(ArrayList<Mujer> listaMujer) {
        this.listaMujer = listaMujer;
    }
}
