package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Oficina;
import co.edu.unbosque.model.OficinaDTO;

/**
 * Clase DAO para la gestión de objetos Oficina.
 * Implementa las operaciones CRUD y persistencia en archivo.
 */
public class OficinaDAO implements OperacionDAO<OficinaDTO, Oficina> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "oficina.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "oficina.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Oficina.
     */
    private ArrayList<Oficina> listaOficina;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public OficinaDAO() {
        listaOficina = new ArrayList<>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todas las oficinas almacenadas en la lista.
     * @return Una cadena con todos los objetos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaOficina.isEmpty()) {
            return "No hay artículos de oficina en la lista";
        } else {
            for (Oficina oficina : listaOficina) {
                rta += oficina;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los objetos en forma de lista de OficinaDTO.
     * @return Lista de OficinaDTO.
     */
    @Override
    public ArrayList<OficinaDTO> getAll() {
        return DataMapper.listaOficinaToListaOficinaDTO(listaOficina);
    }

    /**
     * Agrega un nuevo objeto si no existe ya en la lista.
     * @param newData Objeto OficinaDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(OficinaDTO newData) {
        if (find(DataMapper.oficinaDTOToOficina(newData)) == null) {
            listaOficina.add(DataMapper.oficinaDTOToOficina(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un objeto de la lista.
     * @param delete Objeto OficinaDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(OficinaDTO delete) {
        Oficina found = find(DataMapper.oficinaDTOToOficina(delete));
        if (found != null) {
            listaOficina.remove(found);
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca un objeto en la lista por su nombre.
     * @param toFind Objeto Oficina a buscar.
     * @return El objeto encontrado o null si no existe.
     */
    @Override
    public Oficina find(Oficina toFind) {
        if (!listaOficina.isEmpty()) {
            for (Oficina e : listaOficina) {
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
    public boolean update(OficinaDTO previous, OficinaDTO newData) {
        Oficina found = find(DataMapper.oficinaDTOToOficina(previous));
        if (found != null) {
            listaOficina.remove(found);
            listaOficina.add(DataMapper.oficinaDTOToOficina(newData));
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
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaOficina);
    }

    /**
     * Carga la lista desde un archivo serializado.
     * Si no existe o está vacía, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaOficina = (ArrayList<Oficina>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaOficina == null) {
            listaOficina = new ArrayList<>();
        }
    }

    /**
     * Obtiene la lista completa.
     * @return Lista de Oficina.
     */
    public ArrayList<Oficina> getListaOficina() {
        return listaOficina;
    }

    /**
     * Establece una nueva lista.
     * @param listaOficina Lista a asignar.
     */
    public void setListaOficina(ArrayList<Oficina> listaOficina) {
        this.listaOficina = listaOficina;
    }
}
