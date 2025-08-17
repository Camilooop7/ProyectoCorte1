package co.edu.unbosque.view;

import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.UsuarioDTO;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class PanelCarrito extends JPanel {

    private JLabel fondo;
    private JButton volver;
    private JButton comprar;
    private JButton agregarCarrito;
    private JComboBox<String> combocarritos;
    private JTable tablaCarrito;
    private JScrollPane scrollTabla;

    private UsuarioDTO usuario;

    public PanelCarrito(UsuarioDTO usuario) throws IOException {
        this.usuario = usuario; // guardamos la referencia del usuario

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

        String[] columnas = {"Imagen", "Nombre", "Precio"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return ImageIcon.class;
                return Object.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaCarrito = new JTable(modelo);
        tablaCarrito.setRowHeight(60);
        scrollTabla = new JScrollPane(tablaCarrito);
        scrollTabla.setBounds(115, 234, 787, 420);
        add(scrollTabla);

        add(fondo);
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public void recargarComboBox() {
        combocarritos.removeAllItems();

        if (usuario == null) return;
        java.util.LinkedList<Carrito> lista = usuario.getListaCarrtio(); // OJO: respeta el nombre exacto del getter
        if (lista == null || lista.isEmpty()) return;

        cargarRecursivo(lista, 0); // arranca desde índice 0
    }

    // Método auxiliar recursivo con parámetros
    private void cargarRecursivo(java.util.LinkedList<Carrito> lista, int index) {
        if (index >= lista.size()) return;          // caso base
        Carrito  c= lista.get(index);
        combocarritos.addItem(c == null ? "(sin nombre)" : c.toString());
        cargarRecursivo(lista, index + 1);          // recursión
    }
}