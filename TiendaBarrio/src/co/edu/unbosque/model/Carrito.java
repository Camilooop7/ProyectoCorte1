package co.edu.unbosque.model;

import java.io.Serializable;
import co.edu.unbosque.util.structure.LinkedList;

public class Carrito implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private LinkedList<String> listaNombresProductos; // Cambiado a LinkedList<String>

    public Carrito() {
        this.listaNombresProductos = new LinkedList<>();
    }

    public Carrito(String nombre) {
        this.nombre = nombre;
        this.listaNombresProductos = new LinkedList<>();
    }

    public void agregarNombreProducto(String nombreProducto) {
        listaNombresProductos.addLastR(nombreProducto);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<String> getListaNombresProductos() {
        return listaNombresProductos;
    }

    public void setListaNombresProductos(LinkedList<String> listaNombresProductos) {
        this.listaNombresProductos = listaNombresProductos;
    }
}
