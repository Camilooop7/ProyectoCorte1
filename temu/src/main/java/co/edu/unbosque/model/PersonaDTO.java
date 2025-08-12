package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase Abstracta que representa un Persona,y extiende de USuario e implementa
 * Serializable.
 */
public class PersonaDTO extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Indica el nombre de la persona.
	 */
	private String nombre;
	/**
	 * Indica el genero de la persona.
	 */
	private String genero;
	/**
	 * Indica el pais de la persoan.
	 */
	private String direccion;
	/**
	 * Indica la edad de la persona.
	 */
	private int edad;

	/**
	 * Indica la identificacion de la persona.
	 */
	private int identificacion;

	/**
	 * Constructor vacío por defecto.
	 */
	/**
	 * Carrito actual del cliente.
	 */
	private Carrito carrito;

	/**
	 * Lista de carritos asociados al cliente.
	 */
	private ArrayList<Carrito> listaCarritos;

	/**
	 * Lista de productos favoritos del cliente.
	 */
	private ArrayList<Producto> listaProductoFavorito;

	/**
	 * Constructor vacío.
	 */
	public PersonaDTO() {
		carrito = null;
		listaCarritos = new ArrayList<>();
		listaProductoFavorito = new ArrayList<>();
	}

	public PersonaDTO(String nombre, String genero, String direccion, int edad, int identificacion, Carrito carrito,
			ArrayList<Producto> listaProductoFavorito) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.direccion = direccion;
		this.edad = edad;
		this.identificacion = identificacion;
		this.carrito = null;
		listaCarritos = new ArrayList<>();
		this.listaProductoFavorito = listaProductoFavorito;
	}

	/**
	 * Constructor para crear una persona con nombre de usuario, contraseña, correo,
	 * nombre, género, direccion, edad, identificacion y listas.
	 *
	 * @param username   El nombre de usuario de la persona.
	 * @param contrasena La contraseña de la persona.
	 * @param correo     El correo de la persona.
	 * @param nombre     El nombre de la persona.
	 * @param genero     El género de la persona.
	 * @param pais       El país de la persona.
	 * @param edad       La edad de la persona.
	 */

	public PersonaDTO(String username, String contrasena, String correo, String nombre, String genero, String direccion,
			int edad, int identificacion, Carrito carrito, ArrayList<Carrito> listaCarritos,
			ArrayList<Producto> listaProductoFavorito) {
		super(username, contrasena, correo);
		this.nombre = nombre;
		this.genero = genero;
		this.direccion = direccion;
		this.edad = edad;
		this.identificacion = identificacion;
		this.carrito = null;
		listaCarritos = new ArrayList<>();
		this.listaProductoFavorito = listaProductoFavorito;
	}

	/**
	 * Constructor para crear una persona con nombre de usuario, contraseña y
	 * correo.
	 *
	 * @param username   El nombre de usuario de la persona.
	 * @param contrasena La contraseña de la persona.
	 * @param correo     El correo de la persona.
	 */

	public PersonaDTO(String username, String contrasena, String correo) {
		super(username, contrasena, correo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return " Nombre: " + nombre + ", genero: " + genero + ", dirección: " + direccion + ", edad: " + edad + "; ";
	}

	/**
	 * GETTERS&SETTERS
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	/**
	 * Obtiene el carrito actual del cliente.
	 * 
	 * @return Carrito actual.
	 */
	public Carrito getCarrito() {
		return carrito;
	}

	/**
	 * Establece el carrito actual del cliente.
	 * 
	 * @param carrito Nuevo carrito.
	 */
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public ArrayList<Carrito> getListaCarritos() {
		return listaCarritos;
	}

	public void setListaCarritos(ArrayList<Carrito> listaCarritos) {
		this.listaCarritos = listaCarritos;
	}

	public ArrayList<Producto> getListaProductoFavorito() {
		return listaProductoFavorito;
	}

	public void setListaProductoFavorito(ArrayList<Producto> listaProductoFavorito) {
		this.listaProductoFavorito = listaProductoFavorito;
	}

}
