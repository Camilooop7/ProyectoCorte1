package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Animal;
import co.edu.unbosque.model.AnimalDTO;

/**
 * Clase en la cual se implementa la interfaz OperacionDAO para el manejo de datos de animales
 */
public class AnimalDAO implements OperacionDAO<AnimalDTO, Animal> {

    /**
     * Variable constante para el nombre del archivo CSV.
     */
    private final String TEXT_FILE_NAME = "animal.csv";

    /**
     * Variable constante para el nombre del archivo serializado .dat.
     */
    private final String SERIAL_FILE_NAME = "animal.dat";

    private static final long serialVersionUID = 1L;

    /**
     * Lista que almacena los objetos de tipo Animal.
     */
    private ArrayList<Animal> listaAnimal;

    /**
     * Constructor que inicializa la lista y carga los datos desde el archivo serializado.
     */
    public AnimalDAO() {
        listaAnimal = new ArrayList<Animal>();
        cargarDesdeArchivoSerializado();
        escribirArchivoSerializado();
    }

    /**
     * Método para mostrar todos los animales almacenados.
     * @return Cadena con la información de todos los animales o mensaje si está vacía.
     */
    @Override
    public String showAll() {
        String rta = "";
        if (listaAnimal.isEmpty()) {
            return "No hay animales en la lista";
        } else {
            for (Animal animal : listaAnimal) {
                rta += animal;
            }
        }
        return rta;
    }

    /**
     * Método que devuelve la lista de animales en forma de AnimalDTO.
     * @return Lista de AnimalDTO.
     */
    @Override
    public ArrayList<AnimalDTO> getAll() {
        return DataMapper.listaAnimalToListaAnimalDTO(listaAnimal);
    }

    /**
     * Método para agregar un nuevo animal si no existe ya en la lista.
     * @param newData Objeto AnimalDTO con los datos a agregar.
     * @return true si se agregó, false si ya existía.
     */
    @Override
    public boolean add(AnimalDTO newData) {
        if (find(DataMapper.animalDTOToAnimal(newData)) == null) {
            listaAnimal.add(DataMapper.animalDTOToAnimal(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para eliminar un animal de la lista.
     * @param delete Objeto AnimalDTO que se desea eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    @Override
    public boolean delete(AnimalDTO delete) {
        Animal found = find(DataMapper.animalDTOToAnimal(delete));
        if (found != null) {
            escribirArchivoSerializado();
            escribirEnArchivo();
            return listaAnimal.remove(found);
        } else {
            return false;
        }
    }

    /**
     * Método para buscar un animal en la lista por su nombre.
     * @param toFind Objeto Animal a buscar.
     * @return Objeto Animal encontrado o null si no existe.
     */
    @Override
    public Animal find(Animal toFind) {
        if (!listaAnimal.isEmpty()) {
            for (Animal a : listaAnimal) {
                if (a.getNombre().equals(toFind.getNombre())) {
                    return a;
                }
            }
        }
        return null;
    }

    /**
     * Método para actualizar los datos de un animal existente.
     * @param previous Datos anteriores del animal.
     * @param newData Nuevos datos del animal.
     * @return true si se actualizó, false si no se encontró.
     */
    @Override
    public boolean update(AnimalDTO previous, AnimalDTO newData) {
        Animal found = find(DataMapper.animalDTOToAnimal(previous));
        if (found != null) {
            listaAnimal.remove(found);
            listaAnimal.add(DataMapper.animalDTOToAnimal(newData));
            escribirArchivoSerializado();
            escribirEnArchivo();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para escribir los datos en un archivo CSV.
     * Actualmente sin implementación.
     */
    @Override
    public void escribirEnArchivo() {
        // Implementar escritura en archivo CSV si es necesario
    }

    /**
     * Método para guardar la lista de animales en un archivo serializado .dat.
     */
    @Override
    public void escribirArchivoSerializado() {
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaAnimal);
    }

    /**
     * Método para cargar la lista de animales desde un archivo serializado.
     * Si el archivo no existe o está vacío, inicializa una lista nueva.
     */
    @Override
    public void cargarDesdeArchivoSerializado() {
        listaAnimal = (ArrayList<Animal>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (listaAnimal == null) {
            listaAnimal = new ArrayList<Animal>();
        }
    }

    /**
     * Getter para obtener la lista de animales.
     */
    public ArrayList<Animal> getListaAnimal() {
        return listaAnimal;
    }

    /**
     * Setter para modificar la lista de animales.
     */
    public void setListaAnimal(ArrayList<Animal> listaAnimal) {
        this.listaAnimal = listaAnimal;
    }
    
    
}
