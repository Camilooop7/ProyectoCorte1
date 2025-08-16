package co.edu.unbosque.model.persistence;

import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.model.CarritoDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;
import java.io.Serializable;

public class CarritoDAO implements OperacionDAO<CarritoDTO, Carrito>, Serializable {
    private static final long serialVersionUID = 1L;
    private final String TEXT_FILE_NAME = "carrito.csv";
    private final String SERIAL_FILE_NAME = "carrito.dat";
    private LinkedList<Carrito> listaCarritos;

    public CarritoDAO() {
        listaCarritos = new LinkedList<>();
        cargarDesdeArchivo();
    }

    @Override
    public String showAll() {
        return listaCarritos.toString();
    }

    @Override
    public boolean add(CarritoDTO newData) {
        Carrito nuevo = DataMapper.carritoDTOToCarrito(newData);
        if (find(nuevo) == null) {
            listaCarritos.addLastR(nuevo);
            guardarCambios();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(CarritoDTO delete) {
        Carrito info = DataMapper.carritoDTOToCarrito(delete);
        if (listaCarritos.isEmpty()) {
            return false;
        }
        if (listaCarritos.getFirst().getInfo().getNombre().equals(info.getNombre())) {
            listaCarritos.setFirst(listaCarritos.getFirst().getNext());
            guardarCambios();
            return true;
        }
        boolean eliminado = deleteRecursivo(listaCarritos.getFirst(), info);
        if (eliminado) {
            guardarCambios();
        }
        return eliminado;
    }

    private boolean deleteRecursivo(Node<Carrito> previous, Carrito info) {
        if (previous == null || previous.getNext() == null) {
            return false;
        }
        if (previous.getNext().getInfo().getNombre().equals(info.getNombre())) {
            listaCarritos.extract(previous);
            return true;
        }
        return deleteRecursivo(previous.getNext(), info);
    }

    @Override
    public Carrito find(Carrito toFind) {
        if (!listaCarritos.isEmpty()) {
            return findR(listaCarritos.getFirst(), toFind);
        }
        return null;
    }

    private Carrito findR(Node<Carrito> current, Carrito toFind) {
        if (current == null) {
            return null;
        }
        if (current.getInfo().getNombre().equals(toFind.getNombre())) {
            return current.getInfo();
        }
        return findR(current.getNext(), toFind);
    }

    public boolean update(CarritoDTO previous, CarritoDTO newData) {
        Carrito nuevo = DataMapper.carritoDTOToCarrito(newData);
        Carrito previo = DataMapper.carritoDTOToCarrito(previous);
        if (listaCarritos.isEmpty()) {
            return false;
        }
        boolean actualizado = updateR(listaCarritos.getFirst(), previo, nuevo);
        if (actualizado) {
            guardarCambios();
        }
        return actualizado;
    }

    private boolean updateR(Node<Carrito> current, Carrito previo, Carrito nuevo) {
        if (current == null) {
            return false;
        }
        if (current.getInfo().getNombre().equals(previo.getNombre())) {
            current.setInfo(nuevo);
            return true;
        }
        return updateR(current.getNext(), previo, nuevo);
    }

    @Override
    public void escribirEnArchivo() {
        String contenido = escribirEnArchivoR("", listaCarritos.getFirst());
        FileManager.escribirArchivoTexto(TEXT_FILE_NAME, contenido);
    }

    private String escribirEnArchivoR(String contenido, Node<Carrito> current) {
        if (current == null) {
            return contenido;
        }
        Carrito c = current.getInfo();
        contenido += c.getNombre() + ";" + convertirListaNombresProductosAString(c.getListaNombresProductos()) + "\n";
        return escribirEnArchivoR(contenido, current.getNext());
    }

    private String convertirListaNombresProductosAString(LinkedList<String> listaNombresProductos) {
        return convertirListaNombresProductosAStringR(listaNombresProductos.getFirst(), "");
    }

    private String convertirListaNombresProductosAStringR(Node<String> current, String resultado) {
        if (current == null) {
            return resultado.isEmpty() ? resultado : resultado.substring(0, resultado.length() - 1);
        }
        resultado += current.getInfo() + "|";
        return convertirListaNombresProductosAStringR(current.getNext(), resultado);
    }

    @Override
    public void escribirArchivoSerializado() {
        FileManager.escribirArchivoSerializado(SERIAL_FILE_NAME, listaCarritos);
    }

    public void guardarCambios() {
        escribirEnArchivo();
        escribirArchivoSerializado();
    }

    @Override
    public void cargarDesdeArchivoSerializado() {
        LinkedList<Carrito> leida = (LinkedList<Carrito>) FileManager.leerArchivoSerializado(SERIAL_FILE_NAME);
        if (leida != null) {
            listaCarritos = leida;
        } else {
            listaCarritos = new LinkedList<>();
        }
    }

    @Override
    public void cargarDesdeArchivo() {
        String contenido = FileManager.leerArchivoTexto(TEXT_FILE_NAME);
        if (contenido == null || contenido.isBlank()) {
            return;
        }
        String[] filas = contenido.split("\n");
        cargarRecursivo(filas, 0);
    }

    private void cargarRecursivo(String[] filas, int index) {
        if (index >= filas.length) {
            return;
        }
        String[] columna = filas[index].split(";");
        if (columna.length < 2) {
            System.err.println("Línea mal formateada: " + filas[index]);
            cargarRecursivo(filas, index + 1);
            return;
        }
        try {
            String nombre = columna[0];
            LinkedList<String> listaNombresProductos = convertirStringAListaNombresProductos(columna[1]);
            Carrito nuevo = new Carrito(nombre);
            nuevo.setListaNombresProductos(listaNombresProductos);
            listaCarritos.addLastR(nuevo);
        } catch (Exception e) {
            System.err.println("Error al leer la línea: " + filas[index]);
        }
        cargarRecursivo(filas, index + 1);
    }

    private LinkedList<String> convertirStringAListaNombresProductos(String nombresProductosStr) {
        LinkedList<String> listaNombresProductos = new LinkedList<>();
        if (nombresProductosStr == null || nombresProductosStr.isEmpty()) {
            return listaNombresProductos;
        }
        String[] nombresProductosArray = nombresProductosStr.split("\\|");
        convertirNombresProductosRecursivo(nombresProductosArray, 0, listaNombresProductos);
        return listaNombresProductos;
    }

    private void convertirNombresProductosRecursivo(String[] nombresProductosArray, int index, LinkedList<String> listaNombresProductos) {
        if (index >= nombresProductosArray.length) {
            return;
        }
        listaNombresProductos.addLastR(nombresProductosArray[index]);
        convertirNombresProductosRecursivo(nombresProductosArray, index + 1, listaNombresProductos);
    }

    public boolean agregarNombreProductoACarrito(String nombreCarrito, String nombreProducto) {
        Carrito carrito = new Carrito(nombreCarrito);
        Carrito encontrado = find(carrito);
        if (encontrado != null) {
            encontrado.agregarNombreProducto(nombreProducto);
            guardarCambios();
            return true;
        }
        return false;
    }

    public LinkedList<Carrito> getListaCarritos() {
        return listaCarritos;
    }

    public void setListaCarritos(LinkedList<Carrito> listaCarritos) {
        this.listaCarritos = listaCarritos;
    }
}
