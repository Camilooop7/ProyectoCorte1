package co.edu.unbosque.beans;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
@ManagedBean(name = "addbean")
@ViewScoped
public class AddBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String producto;
    private String tipo; // Nuevo campo
    private String subtipo;
    private String subsubtipo;
    private String nombre;
    private int precio;
    private List<String> productos;
    private List<String> tipos; // Nueva lista
    private List<String> subtipos;
    private List<String> subsubtipos;

    public AddBean() {
        productos = new ArrayList<>();
        productos.add("DispositivoElectronico");
        productos.add("Maquillaje");
        productos.add("Juguete");
        productos.add("Peluche");
        productos.add("Papeleria");
        productos.add("Ropa");
        tipos = new ArrayList<>();
        subtipos = new ArrayList<>();
        subsubtipos = new ArrayList<>();
    }

    public void onProductoChange() {
        subtipos.clear();
        tipos.clear(); // Limpiar tipos al cambiar producto
        subsubtipos.clear();
        subtipo = null;
        tipo = null; // Resetear valor seleccionado
        subsubtipo = null;

        // Cargar subtipos según el producto
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

        // Cargar tipos según el producto (independiente de subtipos)
        if ("DispositivoElectronico".equals(producto)) {
            tipos.add("MODELO: 5464");
            tipos.add("MODELO: 54655");
            tipos.add("MODELO: 5FS655");
            tipos.add("MODELO: 54SEF55");
        } else if ("Maquillaje".equals(producto)) {
            tipos.add("ES aprueba de agua SI");
            tipos.add("ES aprueba de agua NO");
        } else if ("Juguete".equals(producto)) {
            tipos.add("EDAD: +5");
            tipos.add("EDAD: +8");
            tipos.add("EDAD: +12");
            tipos.add("EDAD: +18");
        } else if ("Peluche".equals(producto)) {
            tipos.add("TAMANO: PEQUEÑO");
            tipos.add("TAMANO: MEDIANO");
            tipos.add("TAMANO: GRANDE");
        } else if ("Papeleria".equals(producto)) {
            tipos.add("CANTIDAD: 5 UND");
            tipos.add("CANTIDAD: 10 UND");
            tipos.add("CANTIDAD: 15 UND");
            tipos.add("CANTIDAD: 20 UND");
        } else if ("Ropa".equals(producto)) {
            tipos.add("TALLA L - COLOR BLANCO");
            tipos.add("TALLA M - COLOR AZUL");
            tipos.add("TALLA S - ROJO");
       
        }
    }

    public void onSubtipoChange() {
        subsubtipos.clear();  // limpiar opciones anteriores
        subsubtipo = null;    // reset valor seleccionado
        if ("Audifono".equals(subtipo)) {
            subsubtipos.add("Con cable");
            subsubtipos.add("Bluetooth");
        } else if ("Movil".equals(subtipo)) {
            subsubtipos.add("64 GB");
            subsubtipos.add("128 GB");
            subsubtipos.add("256 GB");
        } else if ("Labial".equals(subtipo)) {
            subsubtipos.add("Rojo");
            subsubtipos.add("morado");
            subsubtipos.add("Rosa");
        } else if ("Pestanina".equals(subtipo)) {
            subsubtipos.add("Duración 6 meses");
            subsubtipos.add("Duración 12 meses");
            subsubtipos.add("Duración 1 mes");
        } else if ("JuegoMesa".equals(subtipo)) {
             subsubtipos.add("4 personas");
             subsubtipos.add("8 personas");
             subsubtipos.add("2 personas");
        } else if ("Educativo".equals(subtipo)) {
             subsubtipos.add("Es didactico SI");
             subsubtipos.add("Es didactico NO");
        } else if ("Pelicula".equals(subtipo)) {
             subsubtipos.add("PERSONAJE PELICULA AVATAR");
             subsubtipos.add("PERSONAJE PELICULA UP");
             subsubtipos.add("PERSONAJE PELICULA STRANGER");
        } else if ("Animal".equals(subtipo)) {
            subsubtipos.add("PERRO");
            subsubtipos.add("GATO");
            subsubtipos.add("MONO");
            subsubtipos.add("PEZ");
            subsubtipos.add("OSO");
        } else if ("Colegio".equals(subtipo)) {
            subsubtipos.add("ES seguro SI");
            subsubtipos.add("ES seguro NO");
        } else if ("Oficina".equals(subtipo)) {
            subsubtipos.add("Es decorativo SI");
            subsubtipos.add("Es decorativo NO");
        } else if ("Hombre".equals(subtipo)) {
            subsubtipos.add("Es deportiva SI");
            subsubtipos.add("Es deportiva NO");
        } else if ("Mujer".equals(subtipo)) {
            subsubtipos.add("Es conjunto SI");
            subsubtipos.add("Es conjunto NO");
        }
    }

    // Guardar datos
    public void guardar() {
        System.out.println("Producto: " + producto);
        System.out.println("Tipo: " + tipo); // Nuevo campo
        System.out.println("Subtipo: " + subtipo);
        System.out.println("Sub-subtipo: " + subsubtipo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
    }

    // ===== GETTERS y SETTERS =====
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getSubsubtipo() {
        return subsubtipo;
    }

    public void setSubsubtipo(String subsubtipo) {
        this.subsubtipo = subsubtipo;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }

    public List<String> getTipos() {
        return tipos;
    }

    public void setTipos(List<String> tipos) {
        this.tipos = tipos;
    }

    public List<String> getSubtipos() {
        return subtipos;
    }

    public void setSubtipos(List<String> subtipos) {
        this.subtipos = subtipos;
    }

    public List<String> getSubsubtipos() {
        return subsubtipos;
    }

    public void setSubsubtipos(List<String> subsubtipos) {
        this.subsubtipos = subsubtipos;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
