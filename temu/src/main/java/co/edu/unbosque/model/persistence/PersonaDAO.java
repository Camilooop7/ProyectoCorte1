package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Persona;
import co.edu.unbosque.model.PersonaDTO;

/**
 * Clase DAO para la gestión de objetos Persona.
 * Implementa las operaciones CRUD y persistencia en archivo.
 */
public class PersonaDAO implements OperacionDAO<PersonaDTO, Persona> {

    /**
     * Nombre del archivo CSV donde se almacenarán los datos.
     */
    private final String TEXT_FILE_NAME = "persona.csv";

    /**
     * Nombre del archivo serializado donde se guardarán los datos.
     */
    private final String SERIAL_FILE_NAME = "persona.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Persona.
     */
    private ArrayList<Persona> listaPersona;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public PersonaDAO() {
        listaPersona = new ArrayList<>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Muestra todas las personas almacenadas en la lista.
     * @return Una cadena con todos los objetos o un mensaje si no hay registros.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaPersona.isEmpty()) {
            return "No hay personas en la lista";
        } else {
            for (Persona persona : listaPersona) {
                rta += persona;
            }
        }
        return rta;
    }

    /**
     * Obtiene todos los objetos en forma de lista de PersonaDTO.
     * @return Lista de PersonaDTO.
     */
    @Override
    public ArrayList<PersonaDTO> getAll() {
        return DataMapper.listaPersonaToListaPersonaDTO(listaPersona);
    }

    /**
     * Agrega un nuevo objeto si no existe ya en la lista.
     * @param newData Objeto PersonaDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(PersonaDTO newData) {
        if (find(DataMapper.personaDTOToPersona(newData)) == null) {
            listaPersona.add(DataMapper.personaDTOToPersona(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un objeto de la lista.
     * @param delete Objeto PersonaDTO a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(PersonaDTO delete) {
        Persona found = find(DataMapper.personaDTOToPersona(delete));
        if (found != null) {
            listaPersona.remove(found);
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca un objeto en la lista por su nombre de usuario.
     * @param toFind Objeto Persona a buscar.
     * @return El objeto encontrado o null si no existe.
     */
    @Override
    public Persona find(Persona toFind) {
        if (!listaPersona.isEmpty()) {
            for (Persona e : listaPersona) {
                if (e.getUsername().equals(toFind.getUsername())) {
                    return e;
                }
            }
        }
        return null;
    }
    
    /**
     * Busca un objeto en la lista por su nombre de usuario usando un PersonaDTO.
     * @param toFindDTO Objeto PersonaDTO a buscar.
     * @return El objeto encontrado en forma de DTO o null si no existe.
     */
    public PersonaDTO findP(PersonaDTO toFindDTO) {
        if (!listaPersona.isEmpty()) {
            for (Persona e : listaPersona) {
                if (e.getUsername().equals(toFindDTO.getUsername())) {
                    return DataMapper.personaToPersonaDTO(e);
                }
            }
        }
        return null;
    }
    
    /**
     * Busca una persona en la lista por correo y contraseña.
     *
     * @param correo     El correo de la persona.
     * @param contrasena La contraseña de la persona.
     * @return La Persona encontrada o null si no existe.
     */
    public PersonaDTO findC(String correo, String contrasena) {
        if (listaPersona != null && !listaPersona.isEmpty()) {
            for (Persona p : listaPersona) {
                if (p.getCorreo().equals(correo) && p.getContrasena().equals(contrasena)) {
                    return DataMapper.personaToPersonaDTO(p);
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
    public boolean update(PersonaDTO previous, PersonaDTO newData) {
        Persona found = find(DataMapper.personaDTOToPersona(previous));
        if (found != null) {
            listaPersona.remove(found);
            listaPersona.add(DataMapper.personaDTOToPersona(newData));
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
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaPersona);
    }

    /**
     * Carga la lista desde un archivo serializado.
     * Si no existe o está vacía, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaPersona = (ArrayList<Persona>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaPersona == null) {
            listaPersona = new ArrayList<>();
        }
    }

    /**
     * Obtiene la lista completa.
     * @return Lista de Persona.
     */
    public ArrayList<Persona> getListaPersona() {
        return listaPersona;
    }

    /**
     * Establece una nueva lista.
     * @param listaPersona Lista a asignar.
     */
    public void setListaPersona(ArrayList<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }
}
