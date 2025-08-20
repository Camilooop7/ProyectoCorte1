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

/**
 * Panel que representa el estante inferior de paquetes de papa en la interfaz
 * gráfica. Muestra una lista de paquetes de papa disponibles y proporciona
 * botones para agregarlos al carrito.
 */
public class PanelEstanteInferiorP extends JPanel {

	/**
	 * Lista de botones "Agregar" asociados a cada paquete de papa en el estante.
	 */
	private LinkedList<JButton> botonesAnadir;

	/**
	 * Constructor del panel del estante inferior de paquetes de papa. Configura el
	 * panel con un diseño de flujo a la izquierda y transparencia.
	 */
	public PanelEstanteInferiorP() {
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		botonesAnadir = new LinkedList<>();
	}

	/**
	 * Agrega los productos (paquetes de papa) al panel de manera recursiva. Solo
	 * agrega la mitad inferior de la lista de paquetes de papa.
	 *
	 * @param listaPaquetePapas Lista enlazada de paquetes de papa disponibles.
	 * @param totalProductos    Cantidad total de paquetes de papa en la lista.
	 * @param nodoActual        Nodo actual de la lista de paquetes de papa.
	 * @param cont              Contador para controlar la cantidad de paquetes de
	 *                          papa agregados.
	 */
	public void agregarProductos(LinkedList<PaquetePapa> listaPaquetePapas, int totalProductos,
			Node<PaquetePapa> nodoActual, int cont) {
		if (nodoActual == null || cont >= totalProductos - (totalProductos / 2)) {
			return;
		}
		PaquetePapa paquetePapa = nodoActual.getInfo();
		JPanel panelProducto = crearPanelProducto(paquetePapa);
		add(panelProducto);
		agregarProductos(listaPaquetePapas, totalProductos, nodoActual.getNext(), cont + 1);
	}

	/**
	 * Crea un panel individual para cada paquete de papa. El panel incluye la
	 * imagen del paquete de papa, su información y un botón para agregarlo al
	 * carrito.
	 *
	 * @param paquetePapa El paquete de papa para el cual se crea el panel.
	 * @return JPanel configurado con la información y el botón del paquete de papa.
	 */
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

	/**
	 * Asigna una imagen redimensionada a un paquete de papa. Carga la imagen desde
	 * la ruta especificada en el objeto PaquetePapa.
	 *
	 * @param paquetePapa El paquete de papa del cual se carga la imagen.
	 * @return ImageIcon con la imagen redimensionada del paquete de papa.
	 */
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

	/**
	 * Obtiene la lista de botones "Agregar" asociados a los paquetes de papa.
	 *
	 * @return Lista enlazada de botones "Agregar".
	 */
	public LinkedList<JButton> getBotonesAnadir() {
		return botonesAnadir;
	}

	/**
	 * Establece la lista de botones "Agregar" asociados a los paquetes de papa.
	 *
	 * @param botonesAnadir Lista enlazada de botones "Agregar" a establecer.
	 */
	public void setBotonesAnadir(LinkedList<JButton> botonesAnadir) {
		this.botonesAnadir = botonesAnadir;
	}
}
