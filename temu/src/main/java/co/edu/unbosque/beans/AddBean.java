package co.edu.unbosque.beans;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import co.edu.unbosque.model.*;

@Named("addbean")
@ViewScoped
public class AddBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String producto;
    private String subtipo;
    private String subsubtipo;
    private int precio;
    private String atributoProducto;
    private Map<String, String> atributosSubtipo;
    private List<String> productos;
    private List<String> subtipos;
    private List<String> subsubtipos;
    private Map<String, List<String>> atributosPorSubtipo;

    public AddBean() {
        productos = new ArrayList<>();
        productos.add("DispositivoElectronico");
        productos.add("Maquillaje");
        productos.add("Juguete");
        productos.add("Peluche");
        productos.add("Papeleria");
        productos.add("Ropa");

        subtipos = new ArrayList<>();
        subsubtipos = new ArrayList<>();
        atributosSubtipo = new HashMap<>();

        atributosPorSubtipo = new HashMap<>();
        atributosPorSubtipo.put("Audifono", List.of("Marca", "Tipo de conexión"));
        atributosPorSubtipo.put("Movil", List.of("Marca", "Almacenamiento", "RAM"));
        atributosPorSubtipo.put("Labial", List.of("Color", "Acabado"));
        atributosPorSubtipo.put("Pestanina", List.of("Longitud", "Efecto"));
        atributosPorSubtipo.put("JuegoMesa", List.of("Número de jugadores", "Edad recomendada"));
        atributosPorSubtipo.put("Educativo", List.of("Tema", "Edad recomendada"));
        atributosPorSubtipo.put("Pelicula", List.of("Personaje", "Franquicia"));
        atributosPorSubtipo.put("Animal", List.of("Tipo de animal", "Tamaño"));
        atributosPorSubtipo.put("Colegio", List.of("Tipo de producto", "Marca"));
        atributosPorSubtipo.put("Oficina", List.of("Tipo de producto", "Marca"));
        atributosPorSubtipo.put("Hombre", List.of("Talla", "Color", "Material"));
        atributosPorSubtipo.put("Mujer", List.of("Talla", "Color", "Material"));
    }

    public void onProductoChange() {
        subtipos.clear();
        subsubtipos.clear();
        subtipo = null;
        subsubtipo = null;
        atributoProducto = null;
        atributosSubtipo.clear();

        if ("DispositivoElectronico".equals(producto)) {
            subtipos.add("Audifono");
            subtipos.add("Movil");
        } else if ("Maquillaje".equals(producto)) {
            subtipos.add("Labial");
            subtipos.add("Pestanina");
        } else if ("Juguete".equals(producto)) {
            subtipos.add("JuegoMesa");
            subtipos.add("Educativo");
        } else if ("Peluche".equals(producto)) {
            subtipos.add("Pelicula");
            subtipos.add("Animal");
        } else if ("Papeleria".equals(producto)) {
            subtipos.add("Colegio");
            subtipos.add("Oficina");
        } else if ("Ropa".equals(producto)) {
            subtipos.add("Hombre");
            subtipos.add("Mujer");
        }
    }

    public void onSubtipoChange() {
        subsubtipos.clear();
        atributosSubtipo.clear();

        if (subtipo != null && atributosPorSubtipo.containsKey(subtipo)) {
            List<String> atributos = atributosPorSubtipo.get(subtipo);
            for (String atributo : atributos) {
                atributosSubtipo.put(atributo, "");
            }
        }
    }

    public void guardar() {
        try {
            Object productoCreado = null;
            String nombre = atributoProducto;
            String imagen = "ruta/por/defecto.jpg"; // Puedes cambiar esto según tu lógica de imágenes

            switch (producto) {
                case "DispositivoElectronico":
                    if ("Audifono".equals(subtipo)) {
                        Audifono audifono = new Audifono();
                        audifono.setNombre(nombre);
                        audifono.setPrecio(precio);
                        audifono.setImagen(imagen);
                        audifono.setModelo(atributosSubtipo.get("Marca"));
                        audifono.setTipoConexion(atributosSubtipo.get("Tipo de conexión"));
                        productoCreado = audifono;
                    } else if ("Movil".equals(subtipo)) {
                        Movil movil = new Movil();
                        movil.setNombre(nombre);
                        movil.setPrecio(precio);
                        movil.setImagen(imagen);
                        movil.setModelo(atributosSubtipo.get("Marca"));
                        movil.setAlmacenamiento(Integer.parseInt(atributosSubtipo.get("Almacenamiento")));
                        productoCreado = movil;
                    }
                    break;
                case "Maquillaje":
                    if ("Labial".equals(subtipo)) {
                        Labial labial = new Labial();
                        labial.setNombre(nombre);
                        labial.setPrecio(precio);
                        labial.setImagen(imagen);
                        labial.setEsApruebaDeAgua(false); // Puedes cambiar esto según tu lógica
                        labial.setTono(atributosSubtipo.get("Color"));
                        productoCreado = labial;
                    } else if ("Pestanina".equals(subtipo)) {
                        Pestanina pestanina = new Pestanina();
                        pestanina.setNombre(nombre);
                        pestanina.setPrecio(precio);
                        pestanina.setImagen(imagen);
                        pestanina.setEsApruebaDeAgua(false); // Puedes cambiar esto según tu lógica
                        pestanina.setDuracion(Integer.parseInt(atributosSubtipo.get("Duración")));
                        productoCreado = pestanina;
                    }
                    break;
                case "Juguete":
                    if ("JuegoMesa".equals(subtipo)) {
                        JuegoMesa juegoMesa = new JuegoMesa();
                        juegoMesa.setNombre(nombre);
                        juegoMesa.setPrecio(precio);
                        juegoMesa.setImagen(imagen);
                        juegoMesa.setEdadRecomendada(Integer.parseInt(atributosSubtipo.get("Edad recomendada")));
                        juegoMesa.setCantidadPersona(Integer.parseInt(atributosSubtipo.get("Número de jugadores")));
                        productoCreado = juegoMesa;
                    } else if ("Educativo".equals(subtipo)) {
                        Educativo educativo = new Educativo();
                        educativo.setNombre(nombre);
                        educativo.setPrecio(precio);
                        educativo.setImagen(imagen);
                        educativo.setEdadRecomendada(Integer.parseInt(atributosSubtipo.get("Edad recomendada")));
                        educativo.setEsDidactico(Boolean.parseBoolean(atributosSubtipo.get("Es didáctico")));
                        productoCreado = educativo;
                    }
                    break;
                case "Peluche":
                    if ("Pelicula".equals(subtipo)) {
                        Pelicula pelicula = new Pelicula();
                        pelicula.setNombre(nombre);
                        pelicula.setPrecio(precio);
                        pelicula.setImagen(imagen);
                        pelicula.setTamano(30); // Valor por defecto, puedes cambiarlo
                        pelicula.setPersonaje(atributosSubtipo.get("Personaje"));
                        productoCreado = pelicula;
                    } else if ("Animal".equals(subtipo)) {
                        Animal animal = new Animal();
                        animal.setNombre(nombre);
                        animal.setPrecio(precio);
                        animal.setImagen(imagen);
                        animal.setTamano(30); // Valor por defecto, puedes cambiarlo
                        animal.setAnimal(atributosSubtipo.get("Tipo de animal"));
                        productoCreado = animal;
                    }
                    break;
                case "Papeleria":
                    if ("Colegio".equals(subtipo)) {
                        Colegio colegio = new Colegio(); // Asegúrate de tener esta clase
                        colegio.setNombre(nombre);
                        colegio.setPrecio(precio);
                        colegio.setImagen(imagen);
                        colegio.setCantidadPaquete(1); // Valor por defecto, puedes cambiarlo
                        productoCreado = colegio;
                    } else if ("Oficina".equals(subtipo)) {
                        Oficina oficina = new Oficina();
                        oficina.setNombre(nombre);
                        oficina.setPrecio(precio);
                        oficina.setImagen(imagen);
                        oficina.setCantidadPaquete(1); // Valor por defecto, puedes cambiarlo
                        oficina.setEsDecorativo(Boolean.parseBoolean(atributosSubtipo.get("Es decorativo")));
                        productoCreado = oficina;
                    }
                    break;
                case "Ropa":
                    if ("Hombre".equals(subtipo)) {
                        Hombre hombre = new Hombre();
                        hombre.setNombre(nombre);
                        hombre.setPrecio(precio);
                        hombre.setImagen(imagen);
                        hombre.setTalla(Integer.parseInt(atributosSubtipo.get("Talla")));
                        hombre.setColor(atributosSubtipo.get("Color"));
                        hombre.setEsDeportiva(Boolean.parseBoolean(atributosSubtipo.get("Es deportiva")));
                        productoCreado = hombre;
                    } else if ("Mujer".equals(subtipo)) {
                        Mujer mujer = new Mujer();
                        mujer.setNombre(nombre);
                        mujer.setPrecio(precio);
                        mujer.setImagen(imagen);
                        mujer.setTalla(Integer.parseInt(atributosSubtipo.get("Talla")));
                        mujer.setColor(atributosSubtipo.get("Color"));
                        mujer.setEsConjunto(Boolean.parseBoolean(atributosSubtipo.get("Es conjunto")));
                        productoCreado = mujer;
                    }
                    break;
            }

            System.out.println("Producto creado: " + productoCreado);
            // Aquí puedes guardar el producto en la base de datos o hacer lo que necesites
        } catch (Exception e) {
            System.err.println("Error al crear el producto: " + e.getMessage());
        }
    }

    // Getters y Setters
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public String getSubtipo() { return subtipo; }
    public void setSubtipo(String subtipo) { this.subtipo = subtipo; }
    public String getSubsubtipo() { return subsubtipo; }
    public void setSubsubtipo(String subsubtipo) { this.subsubtipo = subsubtipo; }
    public int getPrecio() { return precio; }
    public void setPrecio(int precio) { this.precio = precio; }
    public String getAtributoProducto() { return atributoProducto; }
    public void setAtributoProducto(String atributoProducto) { this.atributoProducto = atributoProducto; }
    public Map<String, String> getAtributosSubtipo() { return atributosSubtipo; }
    public void setAtributosSubtipo(Map<String, String> atributosSubtipo) { this.atributosSubtipo = atributosSubtipo; }
    public List<String> getProductos() { return productos; }
    public List<String> getSubtipos() { return subtipos; }
    public List<String> getSubsubtipos() { return subsubtipos; }
}
