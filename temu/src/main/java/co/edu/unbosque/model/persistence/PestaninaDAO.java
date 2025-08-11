package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Pestanina;
import co.edu.unbosque.model.PestaninaDTO;

/**
 * Clase DAO para la gestión de objetos Pestanina.
 * Implementa las operaciones CRUD y persistencia en archivo.
 */
public class PestaninaDAO implements OperacionDAO<PestaninaDTO, Pestanina> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "pestanina.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "pestanina.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Pestanina.
     */
    private ArrayList<Pestanina> listaPestanina;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public PestaninaDAO() {
        listaPestanina = new ArrayList<>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todas las pestañinas almacenadas en la lista.
     * @return Una cadena con todos los objetos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaPestanina.isEmpty()) {
            return "No hay pestañinas en la lista";
        } else {
            for (Pestanina pestanina : listaPestanina) {
                rta += pestanina;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los objetos en forma de lista de PestaninaDTO.
     * @return Lista de PestaninaDTO.
     */
    @Override
    public ArrayList<PestaninaDTO> getAll() {
        return DataMapper.listaPestaninaToListaPestaninaDTO(listaPestanina);
    }

    /**
     * Agrega un nuevo objeto si no existe ya en la lista.
     * @param newData Objeto PestaninaDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(PestaninaDTO newData) {
        if (find(DataMapper.pestaninaDTOToPestanina(newData)) == null) {
            listaPestanina.add(DataMapper.pestaninaDTOToPestanina(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un objeto de la lista.
     * @param delete Objeto PestaninaDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(PestaninaDTO delete) {
        Pestanina found = find(DataMapper.pestaninaDTOToPestanina(delete));
        if (found != null) {
            listaPestanina.remove(found);
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca un objeto en la lista por su nombre.
     * @param toFind Objeto Pestanina a buscar.
     * @return El objeto encontrado o null si no existe.
     */
    @Override
    public Pestanina find(Pestanina toFind) {
        if (!listaPestanina.isEmpty()) {
            for (Pestanina e : listaPestanina) {
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
    public boolean update(PestaninaDTO previous, PestaninaDTO newData) {
        Pestanina found = find(DataMapper.pestaninaDTOToPestanina(previous));
        if (found != null) {
            listaPestanina.remove(found);
            listaPestanina.add(DataMapper.pestaninaDTOToPestanina(newData));
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
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaPestanina);
    }

    /**
     * Carga la lista desde un archivo serializado.
     * Si no existe o está vacía, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaPestanina = (ArrayList<Pestanina>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaPestanina == null) {
            listaPestanina = new ArrayList<>();
        }
    }

    /**
     * Obtiene la lista completa.
     * @return Lista de Pestanina.
     */
    public ArrayList<Pestanina> getListaPestanina() {
        return listaPestanina;
    }

    /**
     * Establece una nueva lista.
     * @param listaPestanina Lista a asignar.
     */
    public void setListaPestanina(ArrayList<Pestanina> listaPestanina) {
        this.listaPestanina = listaPestanina;
    }
}
