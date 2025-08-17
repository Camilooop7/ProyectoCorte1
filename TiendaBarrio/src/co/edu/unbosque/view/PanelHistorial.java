package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class PanelHistorial extends JPanel {
    private JLabel fondo;
    private JButton btnVolver;
    private JButton btnLimpiar;
    private JScrollPane scrollPane;
    private JPanel panelContenido;
    private LinkedList<Carrito> historialCompras;

    public PanelHistorial() throws IOException {
        setBounds(0, 0, 1290, 750);
        setLayout(new BorderLayout());
        this.historialCompras = new LinkedList<>();

        panelContenido = new JPanel();
        panelContenido.setLayout(new GridLayout(0, 1, 10, 10));

        fondo = new JLabel();
        BufferedImage fd = ImageIO.read(new File("src/co/edu/unbosque/view/historial.png"));
        ImageIcon imagenFondo = new ImageIcon(fd.getScaledInstance(1290, 750, Image.SCALE_SMOOTH));
        fondo.setIcon(imagenFondo);
        fondo.setBounds(0, 0, 1290, 750);

        btnLimpiar = new JButton("Limpiar Historial");
        btnLimpiar.setBounds(1050, 600, 200, 100);
        btnLimpiar.setFocusable(false);
        btnLimpiar.setBackground(new Color(235, 219, 79));
        btnLimpiar.setFont(new Font("Baloo", Font.BOLD, 15));

        btnVolver = new JButton();
        btnVolver.setBounds(1120, 70, 120, 70);
        btnVolver.setFocusable(false);
        btnVolver.setBackground(new Color(0, 0, 0));
        btnVolver.setBorderPainted(false);
        btnVolver.setContentAreaFilled(false);
        btnVolver.setOpaque(false);

        JScrollPane scrollPrincipal = new JScrollPane(panelContenido);
        scrollPrincipal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPrincipal.setBounds(50, 230, 1000, 480);

        add(btnVolver);
        add(btnLimpiar);
        add(scrollPrincipal);
        add(fondo);
    }

    public void agregarCompras() {
        Node<Carrito> nodoCarrito = historialCompras.getFirst();
        int i = 0;
        while (nodoCarrito != null) {
            Carrito carrito = nodoCarrito.getInfo();
            JLabel nProducto = new JLabel(" Compra #" + (i + 1));
            JPanel panelCarrito = new JPanel();
            panelCarrito.setLayout(new BorderLayout());
            panelCarrito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            panelCarrito.setBackground(new Color(198, 195, 195));
            panelCarrito.add(nProducto, BorderLayout.NORTH);

            JPanel panelProductos = new JPanel();
            panelProductos.setLayout(new GridLayout(0, 1, 5, 5));

            Node<String> nodoProducto = carrito.getListaNombresProductos().getFirst();
            while (nodoProducto != null) {
                String nombreProducto = nodoProducto.getInfo();
                JTextArea txtAreaInfo = new JTextArea(nombreProducto);
                txtAreaInfo.setEditable(false);
                txtAreaInfo.setFont(new Font("Baloo", Font.BOLD, 18));
                txtAreaInfo.setBackground(new Color(230, 230, 230));
                panelProductos.add(txtAreaInfo);
                nodoProducto = nodoProducto.getNext();
            }

            JScrollPane scrollProductos = new JScrollPane(panelProductos);
            scrollProductos.setPreferredSize(new Dimension(950, 450));
            scrollProductos.setBorder(BorderFactory.createEmptyBorder());
            panelCarrito.add(scrollProductos, BorderLayout.CENTER);
            panelContenido.add(panelCarrito);

            nodoCarrito = nodoCarrito.getNext();
            i++;
        }
    }

    public void actualizarInfo() {
        panelContenido.removeAll();
        agregarCompras();
        revalidate();
        repaint();
    }

    // Método para agregar una compra al historial
    public void agregarCompraAlHistorial(Carrito carrito) {
        historialCompras.addLastR(carrito);
        actualizarInfo();
    }

    // Método para limpiar el historial
    public void limpiarHistorial() {
        historialCompras = new LinkedList<>();
        actualizarInfo();
    }

    // Getters y Setters
    public JLabel getFondo() {
        return fondo;
    }

    public void setFondo(JLabel fondo) {
        this.fondo = fondo;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JPanel getPanelContenido() {
        return panelContenido;
    }

    public void setPanelContenido(JPanel panelContenido) {
        this.panelContenido = panelContenido;
    }

    public LinkedList<Carrito> getHistorialCompras() {
        return historialCompras;
    }

    public void setHistorialCompras(LinkedList<Carrito> historialCompras) {
        this.historialCompras = historialCompras;
    }
}
