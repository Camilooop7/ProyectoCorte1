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
import co.edu.unbosque.model.Jugo;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

/**
 * Clase que representa un panel para mostrar productos (jugos) en la parte
 * superior de una interfaz gráfica. Este panel permite agregar productos y
 * visualizarlos con sus respectivas imágenes y botones de acción.
 */
public class PanelEstanteSuperiorJ extends JPanel {

	/** Lista enlazada de botones para añadir productos. */
	private LinkedList<JButton> botonesAnadir;

	/**
	 * Constructor de la clase PanelEstanteSuperiorJ. Inicializa el panel con un
	 * diseño de flujo a la izquierda y transparencia.
	 */
	public PanelEstanteSuperiorJ() {
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		botonesAnadir = new LinkedList<>();
	}

	/**
	 * Agrega productos al panel de forma recursiva.
	 *
	 * @param listaJugos     Lista enlazada de objetos Jugo a agregar.
	 * @param totalProductos Número total de productos a agregar.
	 * @param nodoActual     Nodo actual de la lista enlazada.
	 * @param cont           Contador de productos agregados.
	 */
	public void agregarProductos(LinkedList<Jugo> listaJugos, int totalProductos, Node<Jugo> nodoActual, int cont) {
		if (nodoActual == null || cont >= totalProductos / 2) {
			return;
		}
		Jugo jugo = nodoActual.getInfo();
		JPanel panelProducto = crearPanelProducto(jugo);
		add(panelProducto);
		agregarProductos(listaJugos, totalProductos, nodoActual.getNext(), cont + 1);
	}

	/**
	 * Crea un panel para mostrar la información de un producto (jugo).
	 *
	 * @param jugo Objeto Jugo que contiene la información del producto.
	 * @return JPanel Panel configurado con la información del producto.
	 */
	private JPanel crearPanelProducto(Jugo jugo) {
		JPanel panelProducto = new JPanel(new BorderLayout());
		panelProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelProducto.setBackground(new Color(255, 255, 255, 180));
		panelProducto.setPreferredSize(new java.awt.Dimension(180, 180));

		JLabel lblImagen = new JLabel(asignarImagen(jugo));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		panelProducto.add(lblImagen, BorderLayout.CENTER);

		JTextArea txtAreaInfo = new JTextArea(jugo.toString());
		txtAreaInfo.setEditable(false);
		txtAreaInfo.setFont(new Font("Baloo", Font.BOLD, 14));
		txtAreaInfo.setBackground(new Color(255, 255, 255, 0));
		panelProducto.add(txtAreaInfo, BorderLayout.SOUTH);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(new Color(235, 219, 79));
		btnAgregar.setFont(new Font("Baloo", Font.BOLD, 12));
		btnAgregar.setActionCommand(jugo.getNombre());
		botonesAnadir.addLastR(btnAgregar);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBotones.setOpaque(false);
		panelBotones.add(btnAgregar);
		panelProducto.add(panelBotones, BorderLayout.NORTH);

		return panelProducto;
	}

	/**
	 * Asigna una imagen a un producto (jugo) y la redimensiona.
	 *
	 * @param jugo Objeto Jugo que contiene la ruta de la imagen.
	 * @return ImageIcon Imagen redimensionada para mostrar en el panel.
	 */
	private ImageIcon asignarImagen(Jugo jugo) {
		try {
			String ruta = jugo.getImagen().replace("\\", "/");
			BufferedImage imagen = ImageIO.read(new File(ruta));
			Image imagenRedimensionada = imagen.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			return new ImageIcon(imagenRedimensionada);
		} catch (Exception e) {
			System.err.println("Error cargando imagen: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Obtiene la lista de botones para añadir productos.
	 *
	 * @return LinkedList<JButton> Lista enlazada de botones.
	 */
	public LinkedList<JButton> getBotonesAnadir() {
		return botonesAnadir;
	}

	/**
	 * Establece la lista de botones para añadir productos.
	 *
	 * @param botonesAnadir Nueva lista enlazada de botones.
	 */
	public void setBotonesAnadir(LinkedList<JButton> botonesAnadir) {
		this.botonesAnadir = botonesAnadir;
	}
}
