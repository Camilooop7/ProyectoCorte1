package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import co.edu.unbosque.model.*;
import co.edu.unbosque.model.persistence.*;
import co.edu.unbosque.util.structure.*;

public class PanelCarrito extends JPanel {
    private JLabel fondo;
    private JButton volver;
    private JButton comprar;
    private JButton eliminar;
    private JTextArea precio;
    private String total;
    private JButton agregarCarrito;
    private JComboBox<String> combocarritos;
    private JTable tablaCarrito;
    private JScrollPane scrollTabla;
    private UsuarioDTO usuario;
    private LinkedList<String> productosAleatorios;

    public PanelCarrito(UsuarioDTO usuario) throws IOException {
        this.usuario = usuario;
        setBounds(0, 0, 1290, 750);
        setLayout(null);
        fondo = new JLabel();
        BufferedImage fd = ImageIO.read(new File("src/co/edu/unbosque/view/Carrote.png"));
        ImageIcon imagenFondo = new ImageIcon(fd);
        Image fdRedim = fd.getScaledInstance(1290, 750, Image.SCALE_SMOOTH);
        fondo.setIcon(new ImageIcon(fdRedim));
        fondo.setBounds(0, 0, 1290, 750);
        volver = new JButton();
        volver.setBounds(1100, 72, 97, 95);
        volver.setFocusable(false);
        volver.setBackground(new Color(0, 0, 0));
        volver.setContentAreaFilled(false);
        volver.setOpaque(false);
        volver.setBorderPainted(true);
        volver.setVisible(true);
        add(volver);
        comprar = new JButton();
        comprar.setBounds(949, 590, 260, 95);
        comprar.setFocusable(false);
        comprar.setBackground(new Color(0, 0, 0));
        comprar.setContentAreaFilled(false);
        comprar.setOpaque(false);
        comprar.setBorderPainted(true);
        comprar.setVisible(true);
        add(comprar);
        agregarCarrito = new JButton();
        agregarCarrito.setBounds(535, 126, 85, 82);
        agregarCarrito.setFocusable(false);
        agregarCarrito.setBackground(new Color(0, 0, 0));
        agregarCarrito.setContentAreaFilled(false);
        agregarCarrito.setOpaque(false);
        agregarCarrito.setBorderPainted(true);
        agregarCarrito.setVisible(true);
        add(agregarCarrito);

        eliminar = new JButton();
        eliminar.setBounds(638, 126, 85, 82);
        eliminar.setFocusable(false);
        eliminar.setBackground(new Color(0, 0, 0));
        eliminar.setContentAreaFilled(false);
        eliminar.setOpaque(false);
        eliminar.setBorderPainted(true);
        eliminar.setVisible(true);
        add(eliminar);

        precio = new JTextArea();
        precio.setText(total);
        precio.setEditable(false);
        precio.setBounds(1080, 550, 260, 95);
        precio.setFont(new Font("Baloo", Font.BOLD, 24));
        precio.setOpaque(false);
        precio.setBackground(new Color(0, 0, 0, 0));
        precio.setBorder(null);
        add(precio);

        combocarritos = new JComboBox<>();
        combocarritos.setBounds(125, 130, 385, 70);
        add(combocarritos);
        String[] columnas = { "Nombre", "Precio", "Disponibilidad" };
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Object.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaCarrito = new JTable(modelo);
        tablaCarrito.setRowHeight(30);
        tablaCarrito.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == 2 && "No disponible".equals(value)) {
                    c.setForeground(Color.RED);
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });
        scrollTabla = new JScrollPane(tablaCarrito);
        scrollTabla.setBounds(115, 234, 787, 420);
        add(scrollTabla);
        add(fondo);
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public void setProductosAleatorios(LinkedList<String> productosAleatorios) {
        this.productosAleatorios = productosAleatorios;
    }

    // Método recursivo para recargar el ComboBox de carritos
    public void recargarComboBox() {
        combocarritos.removeAllItems();
        if (usuario == null) {
            System.out.println("Usuario es null.");
            return;
        }
        CarritoDAO carritoDAO = new CarritoDAO();
        LinkedList<Carrito> listaCarritos = carritoDAO.getListaCarritos();
        recargarComboBoxRecursivo(listaCarritos.getFirst(), usuario.getNombre());
        combocarritos.revalidate();
        combocarritos.repaint();
    }

    private void recargarComboBoxRecursivo(Node<Carrito> nodoActual, String nombreUsuario) {
        if (nodoActual != null) {
            Carrito carrito = nodoActual.getInfo();
            if (carrito.getNombreU().equals(nombreUsuario)) {
                combocarritos.addItem(carrito.getNombre());
                System.out.println("Carrito encontrado: " + carrito.getNombre());
            }
            recargarComboBoxRecursivo(nodoActual.getNext(), nombreUsuario);
        }
    }

    // Método recursivo para cargar productos del carrito
    public void cargarProductosDelCarrito() {
        DefaultTableModel modelo = (DefaultTableModel) tablaCarrito.getModel();
        modelo.setRowCount(0);
        if (usuario == null || combocarritos.getSelectedItem() == null || productosAleatorios == null) {
            System.out.println("Usuario, carrito o lista de productos aleatorios no seleccionado.");
            return;
        }
        String nombreCarritoSeleccionado = (String) combocarritos.getSelectedItem();
        CarritoDAO carritoDAO = new CarritoDAO();
        Carrito carrito = carritoDAO.find(new Carrito(nombreCarritoSeleccionado, usuario.getNombre()));
        if (carrito == null) {
            System.out.println("Carrito no encontrado: " + nombreCarritoSeleccionado);
            return;
        }
        if (carrito.getListaNombresProductos().isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        cargarProductosDelCarritoRecursivo(carrito.getListaNombresProductos().getFirst(), modelo);
        calcularTotal();
    }

    private void cargarProductosDelCarritoRecursivo(Node<String> nodoProducto, DefaultTableModel modelo) {
        if (nodoProducto != null) {
            String nombreProducto = nodoProducto.getInfo();
            Object producto = buscarProductoPorNombre(nombreProducto);
            if (producto != null) {
                String nombre = obtenerNombreProducto(producto);
                double precio = obtenerPrecioProducto(producto);
                String disponibilidad = estaEnProductosAleatorios(nombreProducto) ? "Disponible" : "No disponible";
                modelo.addRow(new Object[] { nombre, precio, disponibilidad });
                System.out.println("Producto agregado a la tabla: " + nombre + " (" + disponibilidad + ")");
            } else {
                System.out.println("Producto no encontrado: " + nombreProducto);
            }
            cargarProductosDelCarritoRecursivo(nodoProducto.getNext(), modelo);
        }
    }

    // Método recursivo para verificar si un producto está en la lista de productos aleatorios
    public boolean estaEnProductosAleatorios(String nombreProducto) {
        return estaEnProductosAleatoriosRecursivo(productosAleatorios.getFirst(), nombreProducto);
    }

    private boolean estaEnProductosAleatoriosRecursivo(Node<String> nodoActual, String nombreProducto) {
        if (nodoActual == null) {
            return false;
        }
        if (nodoActual.getInfo().equalsIgnoreCase(nombreProducto)) {
            return true;
        }
        return estaEnProductosAleatoriosRecursivo(nodoActual.getNext(), nombreProducto);
    }

    // Método recursivo para buscar un producto por nombre
    public Object buscarProductoPorNombre(String nombre) {
        Object producto = buscarEnFrutasRecursivo(new FrutaDAO().getListaFrutas().getFirst(), nombre);
        if (producto != null) return producto;
        producto = buscarEnVerdurasRecursivo(new VerduraDAO().getListaVerduras().getFirst(), nombre);
        if (producto != null) return producto;
        producto = buscarEnGaseosasRecursivo(new GaseosaDAO().getListaGaseosas().getFirst(), nombre);
        if (producto != null) return producto;
        producto = buscarEnJugosRecursivo(new JugoDAO().getListaJugos().getFirst(), nombre);
        if (producto != null) return producto;
        producto = buscarEnPaquetesPapaRecursivo(new PaquetePapaDAO().getListaPaquetePapas().getFirst(), nombre);
        return producto;
    }

    private Object buscarEnFrutasRecursivo(Node<Fruta> nodoActual, String nombre) {
        if (nodoActual == null) {
            return null;
        }
        if (nodoActual.getInfo().getNombre().equals(nombre)) {
            return nodoActual.getInfo();
        }
        return buscarEnFrutasRecursivo(nodoActual.getNext(), nombre);
    }

    private Object buscarEnVerdurasRecursivo(Node<Verdura> nodoActual, String nombre) {
        if (nodoActual == null) {
            return null;
        }
        if (nodoActual.getInfo().getNombre().equals(nombre)) {
            return nodoActual.getInfo();
        }
        return buscarEnVerdurasRecursivo(nodoActual.getNext(), nombre);
    }

    private Object buscarEnGaseosasRecursivo(Node<Gaseosa> nodoActual, String nombre) {
        if (nodoActual == null) {
            return null;
        }
        if (nodoActual.getInfo().getNombre().equals(nombre)) {
            return nodoActual.getInfo();
        }
        return buscarEnGaseosasRecursivo(nodoActual.getNext(), nombre);
    }

    private Object buscarEnJugosRecursivo(Node<Jugo> nodoActual, String nombre) {
        if (nodoActual == null) {
            return null;
        }
        if (nodoActual.getInfo().getNombre().equals(nombre)) {
            return nodoActual.getInfo();
        }
        return buscarEnJugosRecursivo(nodoActual.getNext(), nombre);
    }

    private Object buscarEnPaquetesPapaRecursivo(Node<PaquetePapa> nodoActual, String nombre) {
        if (nodoActual == null) {
            return null;
        }
        if (nodoActual.getInfo().getNombre().equals(nombre)) {
            return nodoActual.getInfo();
        }
        return buscarEnPaquetesPapaRecursivo(nodoActual.getNext(), nombre);
    }

    public String obtenerNombreProducto(Object producto) {
        if (producto instanceof Fruta)
            return ((Fruta) producto).getNombre();
        if (producto instanceof Verdura)
            return ((Verdura) producto).getNombre();
        if (producto instanceof Gaseosa)
            return ((Gaseosa) producto).getNombre();
        if (producto instanceof Jugo)
            return ((Jugo) producto).getNombre();
        if (producto instanceof PaquetePapa)
            return ((PaquetePapa) producto).getNombre();
        return "Desconocido";
    }

    public double obtenerPrecioProducto(Object producto) {
        if (producto instanceof Fruta)
            return ((Fruta) producto).getPrecio();
        if (producto instanceof Verdura)
            return ((Verdura) producto).getPrecio();
        if (producto instanceof Gaseosa)
            return ((Gaseosa) producto).getPrecio();
        if (producto instanceof Jugo)
            return ((Jugo) producto).getPrecio();
        if (producto instanceof PaquetePapa)
            return ((PaquetePapa) producto).getPrecio();
        return 0.0;
    }

    // Método recursivo para calcular el total
    public void calcularTotal() {
        if (usuario == null || combocarritos.getSelectedItem() == null || productosAleatorios == null) {
            System.out.println("Usuario, carrito o lista de productos aleatorios no seleccionado.");
            precio.setText("Total: $0.0");
            return;
        }
        String nombreCarritoSeleccionado = (String) combocarritos.getSelectedItem();
        CarritoDAO carritoDAO = new CarritoDAO();
        Carrito carrito = carritoDAO.find(new Carrito(nombreCarritoSeleccionado, usuario.getNombre()));
        if (carrito == null) {
            System.out.println("Carrito no encontrado: " + nombreCarritoSeleccionado);
            precio.setText("Total: $0.0");
            return;
        }
        if (carrito.getListaNombresProductos().isEmpty()) {
            System.out.println("El carrito está vacío.");
            precio.setText("Total: $0.0");
            return;
        }
        double total = calcularTotalRecursivo(carrito.getListaNombresProductos().getFirst(), 0.0);
        precio.setText(String.format("Total: $%.2f", total));
    }

    private double calcularTotalRecursivo(Node<String> nodoProducto, double totalAcumulado) {
        if (nodoProducto == null) {
            return totalAcumulado;
        }
        String nombreProducto = nodoProducto.getInfo();
        Object producto = buscarProductoPorNombre(nombreProducto);
        if (producto != null && estaEnProductosAleatorios(nombreProducto)) {
            totalAcumulado += obtenerPrecioProducto(producto);
        }
        return calcularTotalRecursivo(nodoProducto.getNext(), totalAcumulado);
    }

    // Getters y Setters
    public JLabel getFondo() {
        return fondo;
    }

    public void setFondo(JLabel fondo) {
        this.fondo = fondo;
    }

    public JButton getVolver() {
        return volver;
    }

    public void setVolver(JButton volver) {
        this.volver = volver;
    }

    public JButton getComprar() {
        return comprar;
    }

    public void setComprar(JButton comprar) {
        this.comprar = comprar;
    }

    public JButton getAgregarCarrito() {
        return agregarCarrito;
    }

    public void setAgregarCarrito(JButton agregarCarrito) {
        this.agregarCarrito = agregarCarrito;
    }

    public JComboBox<String> getCombocarrito() {
        return combocarritos;
    }

    public String getCombocarritoS() {
        return (String) combocarritos.getSelectedItem();
    }

    public void setCombocarritos(JComboBox<String> combocarritos) {
        this.combocarritos = combocarritos;
    }

    public JTable getTablaCarrito() {
        return tablaCarrito;
    }

    public void setTablaCarrito(JTable tablaCarrito) {
        this.tablaCarrito = tablaCarrito;
    }

    public JScrollPane getScrollTabla() {
        return scrollTabla;
    }

    public void setScrollTabla(JScrollPane scrollTabla) {
        this.scrollTabla = scrollTabla;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public JButton getEliminar() {
        return eliminar;
    }

    public void setEliminar(JButton eliminar) {
        this.eliminar = eliminar;
    }

    public JTextArea getPrecio() {
        return precio;
    }

    public void setPrecio(JTextArea precio) {
        this.precio = precio;
        precio.setText(total);
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }
}
