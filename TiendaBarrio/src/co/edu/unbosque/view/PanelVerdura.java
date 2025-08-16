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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import co.edu.unbosque.model.Verdura;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class PanelVerdura extends JPanel {
    private JScrollPane scrollPaneA;
    private JScrollPane scrollPaneB;
    private JPanel panelColumnaA;
    private JPanel panelColumnaB;
    private LinkedList<JButton> botonesAnadir;

    public PanelVerdura() {
        setBounds(380, 240, 870, 470);
        setLayout(null); // Layout absoluto
        botonesAnadir = new LinkedList<>();

        // Columna izquierda
        panelColumnaA = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelColumnaA.setOpaque(false);
        scrollPaneA = new JScrollPane(panelColumnaA);
        scrollPaneA.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneA.setOpaque(false);
        scrollPaneA.getViewport().setOpaque(false);
        scrollPaneA.setBounds(0, 0, 430, 470); // Mitad izquierda
        add(scrollPaneA);

        // Columna derecha
        panelColumnaB = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelColumnaB.setOpaque(false);
        scrollPaneB = new JScrollPane(panelColumnaB);
        scrollPaneB.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneB.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneB.setOpaque(false);
        scrollPaneB.getViewport().setOpaque(false);
        scrollPaneB.setBounds(430, 0, 430, 470); // Mitad derecha
        add(scrollPaneB);
    }

    public void agregarProducto(Node<Verdura> nodo, int index, int total) {
        if (nodo == null) {
            return;
        }
        Verdura verdura = nodo.getInfo();
        JPanel panelProducto = new JPanel(new BorderLayout());
        panelProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelProducto.setBackground(new Color(255, 255, 255, 180));
        panelProducto.setPreferredSize(new java.awt.Dimension(380, 120));

        JLabel lblImagen = new JLabel(asignarImagen(verdura));
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        panelProducto.add(lblImagen, BorderLayout.WEST);

        JTextArea txtAreaInfo = new JTextArea(verdura.toString());
        txtAreaInfo.setEditable(false);
        txtAreaInfo.setFont(new Font("Baloo", Font.BOLD, 14));
        txtAreaInfo.setBackground(new Color(255, 255, 255, 0));
        panelProducto.add(txtAreaInfo, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setOpaque(false);
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(235, 219, 79));
        btnAgregar.setFont(new Font("Baloo", Font.BOLD, 12));
        botonesAnadir.addLastR(btnAgregar);
        panelBotones.add(btnAgregar);
        panelProducto.add(panelBotones, BorderLayout.SOUTH);

        // Dividir mitad en A y mitad en B
        if (index < total / 2) {
            panelColumnaA.add(panelProducto);
        } else {
            panelColumnaB.add(panelProducto);
        }
        agregarProducto(nodo.getNext(), index + 1, total);
    }

    private ImageIcon asignarImagen(Verdura obj) {
        try {
            String ruta = obj.getImagen();
            ruta = ruta.replace("\\", "/");
            BufferedImage fd = ImageIO.read(new File(ruta));
            Image fdRedim = fd.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            return new ImageIcon(fdRedim);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + e.getMessage());
            return null;
        }
    }

    public void actualizarInfo(LinkedList<Verdura> listaDatos) {
        botonesAnadir = new LinkedList<>();
        panelColumnaA.removeAll();
        panelColumnaB.removeAll();
        int total = contarNodos(listaDatos.getFirst(), 0);
        agregarProducto(listaDatos.getFirst(), 0, total);
        revalidate();
        repaint();
    }

    private int contarNodos(Node<Verdura> nodo, int cont) {
        if (nodo == null) return cont;
        return contarNodos(nodo.getNext(), cont + 1);
    }
}
