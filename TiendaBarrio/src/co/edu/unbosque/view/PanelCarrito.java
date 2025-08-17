package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import co.edu.unbosque.model.*;
import co.edu.unbosque.model.persistence.*;
import co.edu.unbosque.util.structure.*;

public class PanelCarrito extends JPanel {
    private JLabel fondo;
    private JButton volver;
    private JButton comprar;
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
        BufferedImage fd = ImageIO.read(new File("src/co/edu/unbosque/view/CarritoInicio.png"));
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

        combocarritos = new JComboBox<>();
        combocarritos.setBounds(125, 130, 385, 70);
        add(combocarritos);

        // Quitar la columna "Imagen"
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
        tablaCarrito.setRowHeight(30); // Ajustar altura de fila
        tablaCarrito.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // Si es la columna "Disponibilidad" y el valor es "No disponible", cambiar el color a rojo
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

    public void recargarComboBox() {
        combocarritos.removeAllItems();
        if (usuario == null) {
            System.out.println("Usuario es null.");
            return;
        }
        CarritoDAO carritoDAO = new CarritoDAO();
        LinkedList<Carrito> listaCarritos = carritoDAO.getListaCarritos();
        Node<Carrito> nodoActual = listaCarritos.getFirst();
        while (nodoActual != null) {
            Carrito carrito = nodoActual.getInfo();
            if (carrito.getNombreU().equals(usuario.getNombre())) {
                combocarritos.addItem(carrito.getNombre());
                System.out.println("Carrito encontrado: " + carrito.getNombre());
            }
            nodoActual = nodoActual.getNext();
        }
        combocarritos.revalidate();
        combocarritos.repaint();
    }

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
        Node<String> nodoProducto = carrito.getListaNombresProductos().getFirst();
        while (nodoProducto != null) {
            String nombreProducto = nodoProducto.getInfo();
            Object producto = buscarProductoPorNombre(nombreProducto);
            if (producto != null) {
                String nombre = obtenerNombreProducto(producto);
                double precio = obtenerPrecioProducto(producto);
                // Verificar si el producto está en la lista de productos aleatorios
                String disponibilidad = estaEnProductosAleatorios(nombreProducto) ? "Disponible" : "No disponible";
                modelo.addRow(new Object[] { nombre, precio, disponibilidad });
                System.out.println("Producto agregado a la tabla: " + nombre + " (" + disponibilidad + ")");
            } else {
                System.out.println("Producto no encontrado: " + nombreProducto);
            }
            nodoProducto = nodoProducto.getNext();
        }
    }

    private boolean estaEnProductosAleatorios(String nombreProducto) {
        Node<String> nodoActual = productosAleatorios.getFirst();
        while (nodoActual != null) {
            if (nodoActual.getInfo().equalsIgnoreCase(nombreProducto)) {
                return true;
            }
            nodoActual = nodoActual.getNext();
        }
        return false;
    }

    public Object buscarProductoPorNombre(String nombre) {
        FrutaDAO frutaDAO = new FrutaDAO();
        VerduraDAO verduraDAO = new VerduraDAO();
        GaseosaDAO gaseosaDAO = new GaseosaDAO();
        JugoDAO jugoDAO = new JugoDAO();
        PaquetePapaDAO paquetePapaDAO = new PaquetePapaDAO();
        // Buscar en frutas
        LinkedList<Fruta> listaFrutas = frutaDAO.getListaFrutas();
        Node<Fruta> nodoFruta = listaFrutas.getFirst();
        while (nodoFruta != null) {
            if (nodoFruta.getInfo().getNombre().equals(nombre)) {
                return nodoFruta.getInfo();
            }
            nodoFruta = nodoFruta.getNext();
        }
        // Buscar en verduras
        LinkedList<Verdura> listaVerduras = verduraDAO.getListaVerduras();
        Node<Verdura> nodoVerdura = listaVerduras.getFirst();
        while (nodoVerdura != null) {
            if (nodoVerdura.getInfo().getNombre().equals(nombre)) {
                return nodoVerdura.getInfo();
            }
            nodoVerdura = nodoVerdura.getNext();
        }
        // Buscar en gaseosas
        LinkedList<Gaseosa> listaGaseosas = gaseosaDAO.getListaGaseosas();
        Node<Gaseosa> nodoGaseosa = listaGaseosas.getFirst();
        while (nodoGaseosa != null) {
            if (nodoGaseosa.getInfo().getNombre().equals(nombre)) {
                return nodoGaseosa.getInfo();
            }
            nodoGaseosa = nodoGaseosa.getNext();
        }
        // Buscar en jugos
        LinkedList<Jugo> listaJugos = jugoDAO.getListaJugos();
        Node<Jugo> nodoJugo = listaJugos.getFirst();
        while (nodoJugo != null) {
            if (nodoJugo.getInfo().getNombre().equals(nombre)) {
                return nodoJugo.getInfo();
            }
            nodoJugo = nodoJugo.getNext();
        }
        // Buscar en paquetes de papa
        LinkedList<PaquetePapa> listaPaquetesPapa = paquetePapaDAO.getListaPaquetePapas();
        Node<PaquetePapa> nodoPaquetePapa = listaPaquetesPapa.getFirst();
        while (nodoPaquetePapa != null) {
            if (nodoPaquetePapa.getInfo().getNombre().equals(nombre)) {
                return nodoPaquetePapa.getInfo();
            }
            nodoPaquetePapa = nodoPaquetePapa.getNext();
        }
        return null;
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
}
