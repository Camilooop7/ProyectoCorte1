package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import co.edu.unbosque.model.Verdura;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

/**
 * Panel que representa el estante inferior de verduras en la interfaz gráfica.
 * Muestra una lista de verduras disponibles y proporciona botones para agregarlas al carrito.
 */
public class PanelEstanteInferiorV extends JPanel {

    /** Lista de botones "Agregar" asociados a cada verdura en el estante. */
    private LinkedList<JButton> botonesAnadir;

    /**
     * Constructor del panel del estante inferior de verduras.
     * Configura el panel con un diseño de flujo a la izquierda y transparencia.
     */
    public PanelEstanteInferiorV() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        botonesAnadir = new LinkedList<>();
    }

    /**
     * Agrega los productos (verduras) al panel de manera recursiva.
     * Solo agrega la mitad inferior de la lista de verduras.
     *
     * @param listaVerduras   Lista enlazada de verduras disponibles.
     * @param totalProductos  Cantidad total de verduras en la lista.
     * @param nodoActual      Nodo actual de la lista de verduras.
     * @param cont            Contador para controlar la cantidad de verduras agregadas.
     */
    public void agregarProductos(LinkedList<Verdura> listaVerduras, int totalProductos, Node<Verdura> nodoActual, int cont) {
        if (nodoActual == null || cont >= totalProductos - (totalProductos / 2)) {
            return;
        }
        Verdura verdura = nodoActual.getInfo();
        JPanel panelProducto = crearPanelProducto(verdura);
        add(panelProducto);
        agregarProductos(listaVerduras, totalProductos, nodoActual.getNext(), cont + 1);
    }

    /**
     * Crea un panel individual para cada verdura.
     * El panel incluye la imagen de la verdura, su información y un botón para agregarla al carrito.
     *
     * @param verdura La verdura para la cual se crea el panel.
     * @return JPanel configurado con la información y el botón de la verdura.
     */
    private JPanel crearPanelProducto(Verdura verdura) {
        JPanel panelProducto = new JPanel(new BorderLayout());
        panelProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelProducto.setBackground(new Color(255, 255, 255, 180));
        panelProducto.setPreferredSize(new java.awt.Dimension(180, 180));

        JLabel lblImagen = new JLabel(asignarImagen(verdura));
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        panelProducto.add(lblImagen, BorderLayout.CENTER);

        JTextArea txtAreaInfo = new JTextArea(verdura.toString());
        txtAreaInfo.setEditable(false);
        txtAreaInfo.setFont(new Font("Baloo", Font.BOLD, 14));
        txtAreaInfo.setBackground(new Color(255, 255, 255, 0));
        panelProducto.add(txtAreaInfo, BorderLayout.SOUTH);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(235, 219, 79));
        btnAgregar.setFont(new Font("Baloo", Font.BOLD, 12));
        btnAgregar.setActionCommand(verdura.getNombre());
        botonesAnadir.addLastR(btnAgregar);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setOpaque(false);
        panelBotones.add(btnAgregar);
        panelProducto.add(panelBotones, BorderLayout.NORTH);

        return panelProducto;
    }

    /**
     * Asigna una imagen redimensionada a una verdura.
     * Carga la imagen desde la ruta especificada en el objeto Verdura.
     *
     * @param verdura La verdura de la cual se carga la imagen.
     * @return ImageIcon con la imagen redimensionada de la verdura.
     */
    private ImageIcon asignarImagen(Verdura verdura) {
        try {
            String ruta = verdura.getImagen().replace("\\", "/");
            BufferedImage imagen = ImageIO.read(new File(ruta));
            Image imagenRedimensionada = imagen.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenRedimensionada);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene la lista de botones "Agregar" asociados a las verduras.
     *
     * @return Lista enlazada de botones "Agregar".
     */
    public LinkedList<JButton> getBotonesAnadir() {
        return botonesAnadir;
    }

    /**
     * Establece la lista de botones "Agregar" asociados a las verduras.
     *
     * @param botonesAnadir Lista enlazada de botones "Agregar" a establecer.
     */
    public void setBotonesAnadir(LinkedList<JButton> botonesAnadir) {
        this.botonesAnadir = botonesAnadir;
    }
}
