package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.JuegoMesa;
import co.edu.unbosque.model.JuegoMesaDTO;

/**
 * Clase que implementa la interfaz OperacionDAO para manejar
 * los datos relacionados con los juegos de mesa.
 */
public class JuegoMesaDAO implements OperacionDAO<JuegoMesaDTO, JuegoMesa> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "juegomesa.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "juegomesa.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo JuegoMesa.
     */
    private ArrayList<JuegoMesa> listaJuegoMesa;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public JuegoMesaDAO() {
        listaJuegoMesa = new ArrayList<>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todos los juegos de mesa almacenados en la lista.
     * @return Una cadena con todos los objetos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaJuegoMesa.isEmpty()) {
            return "No hay juegos de mesa en la lista";
        } else {
            for (JuegoMesa juego : listaJuegoMesa) {
                rta += juego;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los objetos en forma de lista de JuegoMesaDTO.
     * @return Lista de JuegoMesaDTO.
     */
    @Override
    public ArrayList<JuegoMesaDTO> getAll() {
        return DataMapper.listaJuegoMesaToListaJuegoMesaDTO(listaJuegoMesa);
    }

    /**
     * Agrega un nuevo objeto si no existe ya en la lista.
     * @param newData Objeto JuegoMesaDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(JuegoMesaDTO newData) {
        if (find(DataMapper.juegoMesaDTOToJuegoMesa(newData)) == null) {
            listaJuegoMesa.add(DataMapper.juegoMesaDTOToJuegoMesa(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un objeto de la lista.
     * @param delete Objeto JuegoMesaDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(JuegoMesaDTO delete) {
        JuegoMesa found = find(DataMapper.juegoMesaDTOToJuegoMesa(delete));
        if (found != null) {
            listaJuegoMesa.remove(found);
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca un objeto en la lista por su nombre.
     * @param toFind Objeto JuegoMesa a buscar.
     * @return El objeto encontrado o null si no existe.
     */
    @Override
    public JuegoMesa find(JuegoMesa toFind) {
        if (!listaJuegoMesa.isEmpty()) {
            for (JuegoMesa e : listaJuegoMesa) {
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
    public boolean update(JuegoMesaDTO previous, JuegoMesaDTO newData) {
        JuegoMesa found = find(DataMapper.juegoMesaDTOToJuegoMesa(previous));
        if (found != null) {
            listaJuegoMesa.remove(found);
            listaJuegoMesa.add(DataMapper.juegoMesaDTOToJuegoMesa(newData));
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
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaJuegoMesa);
    }

    /**
     * Carga la lista desde un archivo serializado.
     * Si no existe o está vacía, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaJuegoMesa = (ArrayList<JuegoMesa>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaJuegoMesa == null) {
            listaJuegoMesa = new ArrayList<>();
        }
    }

    /**
     * Obtiene la lista completa.
     * @return Lista de JuegoMesa.
     */
    public ArrayList<JuegoMesa> getListaJuegoMesa() {
        return listaJuegoMesa;
    }

    /**
     * Establece una nueva lista.
     * @param listaJuegoMesa Lista a asignar.
     */
    public void setListaJuegoMesa(ArrayList<JuegoMesa> listaJuegoMesa) {
        this.listaJuegoMesa = listaJuegoMesa;
    }
}
