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
import co.edu.unbosque.model.Fruta;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

/**
 * Panel que representa el estante inferior de frutas en la interfaz gráfica.
 * Muestra una lista de frutas disponibles y proporciona botones para agregarlas
 * al carrito.
 */
public class PanelEstanteInferiorF extends JPanel {

	/** Lista de botones "Agregar" asociados a cada fruta en el estante. */
	private LinkedList<JButton> botonesAnadir;

	/**
	 * Constructor del panel del estante inferior de frutas. Configura el panel con
	 * un diseño de flujo a la izquierda y transparencia.
	 */
	public PanelEstanteInferiorF() {
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		botonesAnadir = new LinkedList<>();
	}

	/**
	 * Agrega los productos (frutas) al panel de manera recursiva. Solo agrega la
	 * mitad inferior de la lista de frutas.
	 *
	 * @param listaFrutas    Lista enlazada de frutas disponibles.
	 * @param totalProductos Cantidad total de frutas en la lista.
	 * @param nodoActual     Nodo actual de la lista de frutas.
	 * @param cont           Contador para controlar la cantidad de frutas
	 *                       agregadas.
	 */
	public void agregarProductos(LinkedList<Fruta> listaFrutas, int totalProductos, Node<Fruta> nodoActual, int cont) {
		if (nodoActual == null || cont >= totalProductos - (totalProductos / 2)) {
			return;
		}
		Fruta fruta = nodoActual.getInfo();
		JPanel panelProducto = crearPanelProducto(fruta);
		add(panelProducto);
		agregarProductos(listaFrutas, totalProductos, nodoActual.getNext(), cont + 1);
	}

	/**
	 * Crea un panel individual para cada fruta. El panel incluye la imagen de la
	 * fruta, su información y un botón para agregarla al carrito.
	 *
	 * @param fruta La fruta para la cual se crea el panel.
	 * @return JPanel configurado con la información y el botón de la fruta.
	 */
	private JPanel crearPanelProducto(Fruta fruta) {
		JPanel panelProducto = new JPanel(new BorderLayout());
		panelProducto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelProducto.setBackground(new Color(255, 255, 255, 180));
		panelProducto.setPreferredSize(new java.awt.Dimension(180, 180));

		JLabel lblImagen = new JLabel(asignarImagen(fruta));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		panelProducto.add(lblImagen, BorderLayout.CENTER);

		JTextArea txtAreaInfo = new JTextArea(fruta.toString());
		txtAreaInfo.setEditable(false);
		txtAreaInfo.setFont(new Font("Baloo", Font.BOLD, 14));
		txtAreaInfo.setBackground(new Color(255, 255, 255, 0));
		panelProducto.add(txtAreaInfo, BorderLayout.SOUTH);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(new Color(235, 219, 79));
		btnAgregar.setFont(new Font("Baloo", Font.BOLD, 12));
		btnAgregar.setActionCommand(fruta.getNombre());
		botonesAnadir.addLastR(btnAgregar);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBotones.setOpaque(false);
		panelBotones.add(btnAgregar);
		panelProducto.add(panelBotones, BorderLayout.NORTH);

		return panelProducto;
	}

	/**
	 * Asigna una imagen redimensionada a una fruta. Carga la imagen desde la ruta
	 * especificada en el objeto Fruta.
	 *
	 * @param fruta La fruta de la cual se carga la imagen.
	 * @return ImageIcon con la imagen redimensionada de la fruta.
	 */
	private ImageIcon asignarImagen(Fruta fruta) {
		try {
			String ruta = fruta.getImagen().replace("\\", "/");
			BufferedImage imagen = ImageIO.read(new File(ruta));
			Image imagenRedimensionada = imagen.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			return new ImageIcon(imagenRedimensionada);
		} catch (Exception e) {
			System.err.println("Error cargando imagen: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Obtiene la lista de botones "Agregar" asociados a las frutas.
	 *
	 * @return Lista enlazada de botones "Agregar".
	 */
	public LinkedList<JButton> getBotonesAnadir() {
		return botonesAnadir;
	}

	/**
	 * Establece la lista de botones "Agregar" asociados a las frutas.
	 *
	 * @param botonesAnadir Lista enlazada de botones "Agregar" a establecer.
	 */
	public void setBotonesAnadir(LinkedList<JButton> botonesAnadir) {
		this.botonesAnadir = botonesAnadir;
	}
}
