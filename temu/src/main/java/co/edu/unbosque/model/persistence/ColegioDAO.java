package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Colegio;
import co.edu.unbosque.model.ColegioDTO;

/**
 * Clase que implementa la interfaz OperacionDAO para manejar
 * los datos relacionados con los colegios.
 */
public class ColegioDAO implements OperacionDAO<ColegioDTO, Colegio> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "colegio.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "colegio.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Colegio.
     */
    private ArrayList<Colegio> listaColegio;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public ColegioDAO() {
        listaColegio = new ArrayList<Colegio>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todos los colegios almacenados en la lista.
     * @return Una cadena con todos los colegios o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaColegio.isEmpty()) {
            return "No hay colegios en la lista";
        } else {
            for (Colegio colegio : listaColegio) {
                rta += colegio;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los colegios en forma de lista de ColegioDTO.
     * @return Lista de ColegioDTO.
     */
    @Override
    public ArrayList<ColegioDTO> getAll() {
        return DataMapper.listaColegioToListaColegioDTO(listaColegio);
    }

    /**
     * Agrega un nuevo colegio si no existe ya en la lista.
     * @param newData Objeto ColegioDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(ColegioDTO newData) {
        if (find(DataMapper.colegioDTOToColegio(newData)) == null) {
            listaColegio.add(DataMapper.colegioDTOToColegio(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un colegio de la lista.
     * @param delete Objeto ColegioDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(ColegioDTO delete) {
        Colegio found = find(DataMapper.colegioDTOToColegio(delete));
        if (found != null) {
            escribirArchivoSerializado();
            escribirEnArchivo();
            return listaColegio.remove(found);
        } else {
            return false;
        }
    }

    /**
     * Busca un colegio en la lista por su nombre.
     * @param toFind Objeto Colegio a buscar.
     * @return El Colegio encontrado o null si no existe.
     */
    @Override
    public Colegio find(Colegio toFind) {
        if (!listaColegio.isEmpty()) {
            for (Colegio c : listaColegio) {
                if (c.getNombre().equals(toFind.getNombre())) {
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * Actualiza los datos de un colegio existente.
     * @param previous Datos anteriores.
     * @param newData Nuevos datos.
     * @return true si se actualizó, false si no se encontró.
     */
    @Override
    public boolean update(ColegioDTO previous, ColegioDTO newData) {
        Colegio found = find(DataMapper.colegioDTOToColegio(previous));
        if (found != null) {
            listaColegio.remove(found);
            listaColegio.add(DataMapper.colegioDTOToColegio(newData));
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
     * Guarda la lista de colegios en un archivo serializado.
     */
    @Override
    public void escribirArchivoSerializado() {
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaColegio);
    }

    /**
     * Carga la lista de colegios desde un archivo serializado.
     * Si no existe o está vacía, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaColegio = (ArrayList<Colegio>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaColegio == null) {
            listaColegio = new ArrayList<Colegio>();
        }
    }

    /**
     * Obtiene la lista completa de colegios.
     * @return Lista de Colegio.
     */
    public ArrayList<Colegio> getListaColegio() {
        return listaColegio;
    }

    /**
     * Establece una nueva lista de colegios.
     * @param listaColegio Lista a asignar.
     */
    public void setListaColegio(ArrayList<Colegio> listaColegio) {
        this.listaColegio = listaColegio;
    }
}
