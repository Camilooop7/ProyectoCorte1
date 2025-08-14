package co.edu.unbosque.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named(value = "addbean")
@RequestScoped
public class AddBean {

	private String producto;
	private String subtipo;
	private String nombre;
	private int precio;
	private String imagen;

	private String atributo1;
	private String atributo2;
	private String atributo3;

	private List<String> productos;
	private List<String> subtipos;
	private List<String> atributos;

	public AddBean() {
		// Lista inicial de productos
		productos = Arrays.asList("Ropa", "Juguetes", "Peluches", "Dispositivos Electrónicos", "Maquillaje");

		subtipos = new ArrayList<>();
		atributos = new ArrayList<>();
	}

	public void onProductoChange() {
		subtipos.clear();
		atributos.clear();

		switch (producto) {
		case "Ropa":
			subtipos.addAll(Arrays.asList("Hombre", "Mujer"));
			atributos.addAll(Arrays.asList("Talla", "Color"));
			break;
		case "Juguetes":
			subtipos.addAll(Arrays.asList("Juego de Mesa", "Educativos"));
			atributos.add("Edad Recomendada");
			break;
		case "Peluches":
			subtipos.addAll(Arrays.asList("Pelicula", "Animal"));
			atributos.add("tamaño");
			break;

		case "Dispositivos Electrónicos":
			subtipos.addAll(Arrays.asList("Móvil", "Audífonos"));
			atributos.add("Modelo");
			break;
		case "Maquillaje":
			subtipos.addAll(Arrays.asList("Labial", "Pestañina"));
			atributos.add("A prueba de agua");
			break;
		}
	}

	public void onSubtipoChange() {
		atributos.clear();

		if ("Hombre".equals(subtipo)) {
			atributos.add("Es Deportiva");
		} else if ("Mujer".equals(subtipo)) {
			atributos.add("Es Conjunto");
		} else if ("Juego de Mesa".equals(subtipo)) {
			atributos.add("Cantidad de personas");
		} else if ("Educativos".equals(subtipo)) {
			atributos.add("Didácticos");
		} else if ("Animal".equals(subtipo)) {
			atributos.add("Animal");
		}else if ("Pelicula".equals(subtipo)) {
		atributos.add("Personaje");
		} else if ("Móvil".equals(subtipo)) {
			atributos.add("Almacenamiento");
		} else if ("Audífonos".equals(subtipo)) {
			atributos.add("Tipo de conexión");
		} else if ("Labial".equals(subtipo)) {
			atributos.add("Tono");
		} else if ("Pestañina".equals(subtipo)) {
			atributos.add("Duración");
		}

	}

	public void guardar() {
		System.out.println("Guardando producto: " + producto + " - " + subtipo);
	}

	// Getters y Setters
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getAtributo1() {
		return atributo1;
	}

	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}

	public String getAtributo2() {
		return atributo2;
	}

	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}

	public String getAtributo3() {
		return atributo3;
	}

	public void setAtributo3(String atributo3) {
		this.atributo3 = atributo3;
	}

	public List<String> getProductos() {
		return productos;
	}

	public List<String> getSubtipos() {
		return subtipos;
	}

	public List<String> getAtributos() {
		return atributos;
	}
}
