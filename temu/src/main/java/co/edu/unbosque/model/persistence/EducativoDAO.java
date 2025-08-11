package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Educativo;
import co.edu.unbosque.model.EducativoDTO;

/**
 * Clase que implementa la interfaz OperacionDAO para manejar
 * los datos relacionados con los juguetes educativos.
 */
public class EducativoDAO implements OperacionDAO<EducativoDTO, Educativo> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "educativo.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "educativo.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Educativo.
     */
    private ArrayList<Educativo> listaEducativo;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public EducativoDAO() {
        listaEducativo = new ArrayList<Educativo>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todos los juguetes educativos almacenados en la lista.
     * @return Una cadena con todos los objetos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaEducativo.isEmpty()) {
            return "No hay juguetes educativos en la lista";
        } else {
            for (Educativo educativo : listaEducativo) {
                rta += educativo;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los objetos en forma de lista de EducativoDTO.
     * @return Lista de EducativoDTO.
     */
    @Override
    public ArrayList<EducativoDTO> getAll() {
        return DataMapper.listaEducativoToListaEducativoDTO(listaEducativo);
    }

    /**
     * Agrega un nuevo objeto si no existe ya en la lista.
     * @param newData Objeto EducativoDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(EducativoDTO newData) {
        if (find(DataMapper.educativoDTOToEducativo(newData)) == null) {
            listaEducativo.add(DataMapper.educativoDTOToEducativo(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un objeto de la lista.
     * @param delete Objeto EducativoDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(EducativoDTO delete) {
        Educativo found = find(DataMapper.educativoDTOToEducativo(delete));
        if (found != null) {
            escribirArchivoSerializado();
            escribirEnArchivo();
            return listaEducativo.remove(found);
        } else {
            return false;
        }
    }

    /**
     * Busca un objeto en la lista por su nombre.
     * @param toFind Objeto Educativo a buscar.
     * @return El objeto encontrado o null si no existe.
     */
    @Override
    public Educativo find(Educativo toFind) {
        if (!listaEducativo.isEmpty()) {
            for (Educativo e : listaEducativo) {
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
    public boolean update(EducativoDTO previous, EducativoDTO newData) {
        Educativo found = find(DataMapper.educativoDTOToEducativo(previous));
        if (found != null) {
            listaEducativo.remove(found);
            listaEducativo.add(DataMapper.educativoDTOToEducativo(newData));
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
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaEducativo);
    }

    /**
     * Carga la lista desde un archivo serializado.
     * Si no existe o está vacío, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaEducativo = (ArrayList<Educativo>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaEducativo == null) {
            listaEducativo = new ArrayList<Educativo>();
        }
    }

    /**
     * Obtiene la lista completa.
     * @return Lista de Educativo.
     */
    public ArrayList<Educativo> getListaEducativo() {
        return listaEducativo;
    }

    /**
     * Establece una nueva lista.
     * @param listaEducativo Lista a asignar.
     */
    public void setListaEducativo(ArrayList<Educativo> listaEducativo) {
        this.listaEducativo = listaEducativo;
    }
}
