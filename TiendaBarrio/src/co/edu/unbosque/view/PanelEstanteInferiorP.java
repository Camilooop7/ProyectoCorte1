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
import co.edu.unbosque.model.PaquetePapa;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class PanelEstanteInferiorP extends JPanel {
    private LinkedList<JButton> botonesAnadir;

    public PanelEstanteInferiorP() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        botonesAnadir = new LinkedList<>();
    }

    public void agregarProductos(LinkedList<PaquetePapa> listaPaquetePapas, int totalProductos, Node<PaquetePapa> nodoActual, int cont) {
        if (nodoActual == null || cont >= totalProductos - (totalProductos / 2)) {
            return;
        }
        PaquetePapa paquetePapa = nodoActual.getInfo();
        JPanel panelProducto = crearPanelProducto(paquetePapa);
        add(panelProducto);
        agregarProductos(listaPaquetePapas, totalProductos, nodoActual.getNext(), cont + 1);
    }

    private JPanel crearPanelProducto(PaquetePapa paquetePapa) {
        JPanel panelProducto = new JPanel(new BorderLayout());
        panelProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelProducto.setBackground(new Color(255, 255, 255, 180));
        panelProducto.setPreferredSize(new java.awt.Dimension(180, 180));

        JLabel lblImagen = new JLabel(asignarImagen(paquetePapa));
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        panelProducto.add(lblImagen, BorderLayout.CENTER);

        JTextArea txtAreaInfo = new JTextArea(paquetePapa.toString());
        txtAreaInfo.setEditable(false);
        txtAreaInfo.setFont(new Font("Baloo", Font.BOLD, 14));
        txtAreaInfo.setBackground(new Color(255, 255, 255, 0));
        panelProducto.add(txtAreaInfo, BorderLayout.SOUTH);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(235, 219, 79));
        btnAgregar.setFont(new Font("Baloo", Font.BOLD, 12));
        btnAgregar.setActionCommand(paquetePapa.getNombre());
        botonesAnadir.addLastR(btnAgregar);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setOpaque(false);
        panelBotones.add(btnAgregar);
        panelProducto.add(panelBotones, BorderLayout.NORTH);

        return panelProducto;
    }

    private ImageIcon asignarImagen(PaquetePapa paquetePapa) {
        try {
            String ruta = paquetePapa.getImagen().replace("\\", "/");
            BufferedImage imagen = ImageIO.read(new File(ruta));
            Image imagenRedimensionada = imagen.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenRedimensionada);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + e.getMessage());
            return null;
        }
    }

    public LinkedList<JButton> getBotonesAnadir() {
        return botonesAnadir;
    }

    public void setBotonesAnadir(LinkedList<JButton> botonesAnadir) {
        this.botonesAnadir = botonesAnadir;
    }
}
