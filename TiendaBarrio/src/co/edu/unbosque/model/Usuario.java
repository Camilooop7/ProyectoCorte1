package co.edu.unbosque.model;

import java.io.Serializable;
import co.edu.unbosque.util.structure.LinkedList;

/**
 * Clase que representa un usuario del sistema.
 * Implementa  Serializable para permitir la serialización de sus instancias.
 */
public class Usuario implements Serializable {

    /** Versión por defecto para la serialización. */
    private static final long serialVersionUID = 1L;

    /** Nombre del usuario. */
    private String nombre;

    /** Identificación del usuario. */
    private int identificacion;

    /** Lista de carritos asociados al usuario. */
    private LinkedList<Carrito> listaCarrtio;

    /**
     * Constructor por defecto de la clase Usuario.
     */
    public Usuario() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor que inicializa el nombre, identificación y lista de carritos del usuario.
     *
     * @param nombre El nombre del usuario.
     * @param identificacion La identificación del usuario.
     * @param listaCarrtio La lista de carritos asociados al usuario.
     */
    public Usuario(String nombre, int identificacion, LinkedList<Carrito> listaCarrtio) {
        super();
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.listaCarrtio = listaCarrtio;
    }

    /**
     * Obtiene la lista de carritos del usuario.
     *
     * @return La lista de carritos asociados al usuario.
     */
    public LinkedList<Carrito> getListaCarrtio() {
        return listaCarrtio;
    }

    /**
     * Establece la lista de carritos del usuario.
     *
     * @param listaCarrtio La nueva lista de carritos del usuario.
     */
    public void setListaCarrtio(LinkedList<Carrito> listaCarrtio) {
        this.listaCarrtio = listaCarrtio;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la identificación del usuario.
     *
     * @return La identificación del usuario.
     */
    public int getIdentificacion() {
        return identificacion;
    }

    /**
     * Establece la identificación del usuario.
     *
     * @param identificacion La nueva identificación del usuario.
     */
    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Devuelve una representación en cadena del usuario.
     *
     * @return Una cadena que representa al usuario.
     */
    @Override
    public String toString() {
        return "Usuario [nombre=" + nombre + ", identificacion=" + identificacion + "]";
    }
}
