package co.edu.unbosque.model.persistence;

/**
 * Interfaz en la cual se definen las operaciones para administrar los datos
 * entre los DTO y las entidades
 * 
 */
public interface OperacionDAO<D, E> {

	public String showAll();

	public boolean add(D newData);

	public boolean delete(D delte);

	public E find(E toFind);

	public void escribirEnArchivo();

	public void escribirArchivoSerializado();

	public void cargarDesdeArchivoSerializado();

	public void cargarDesdeArchivo();

}
