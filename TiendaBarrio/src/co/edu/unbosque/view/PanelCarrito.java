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
	

	
	
	public PanelCarrito() throws IOException {
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
				if (columnIndex == 0) return ImageIcon.class; // primera columna imagen
				return Object.class;
			}
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // que no se pueda editar
			}
		};

		tablaCarrito = new JTable(modelo);
		tablaCarrito.setRowHeight(60); // para que se vean bien las imágenes
		scrollTabla = new JScrollPane(tablaCarrito);
		scrollTabla.setBounds(115, 234, 787, 420);
		add(scrollTabla);
		
		
		
		add(fondo);
	}
	public void recargarComboBox(UsuarioDTO usuario) {
	    combocarritos.removeAllItems(); 
	    if (usuario != null && usuario.getListaCarrtio() != null) {
	        cargarRecursivo(usuario.getListaCarrtio(), 0);
	    }
	}

	// Método auxiliar recursivo
	private void cargarRecursivo(java.util.LinkedList<Carrito> linkedList, int index) {
	    if (index < linkedList.size()) {  
	        Carrito c = linkedList.get(index);
	        combocarritos.addItem(c.toString()); 
	        cargarRecursivo(linkedList, index + 1); // llamada recursiva con el siguiente índice
	    }
	}

		}


	
	
	


