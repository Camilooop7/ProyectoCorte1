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

/**
 * Clase que representa un panel para mostrar el historial de compras en una
 * interfaz gráfica. Permite visualizar, agregar y limpiar el historial de
 * compras realizadas.
 */
public class PanelHistorial extends JPanel {

	/** Etiqueta para el fondo del panel. */
	private JLabel fondo;

	/** Botón para volver a la pantalla anterior. */
	private JButton btnVolver;

	/** Botón para limpiar el historial de compras. */
	private JButton btnLimpiar;

	/** Panel de desplazamiento para visualizar el contenido del historial. */
	private JScrollPane scrollPane;

	/** Panel que contiene las compras del historial. */
	private JPanel panelContenido;

	/** Lista enlazada que almacena el historial de compras. */
	private LinkedList<Carrito> historialCompras;

	/**
	 * Constructor de la clase PanelHistorial. Inicializa el panel con los
	 * componentes necesarios para mostrar el historial de compras.
	 *
	 * @throws IOException Si ocurre un error al cargar la imagen de fondo.
	 */
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
		btnLimpiar = new JButton();
		btnLimpiar.setBounds(735, 33, 280, 80);
		btnLimpiar.setFocusable(false);
		btnLimpiar.setBackground(new Color(0, 0, 0));
		btnLimpiar.setContentAreaFilled(false);
		btnLimpiar.setOpaque(false);
		btnLimpiar.setBorderPainted(false);
		btnLimpiar.setVisible(true);
		btnVolver = new JButton();
		btnVolver.setBounds(1110, 5, 100, 90);
		btnVolver.setFocusable(false);
		btnVolver.setBackground(new Color(0, 0, 0));
		btnVolver.setBorderPainted(false);
		btnVolver.setContentAreaFilled(false);
		btnVolver.setOpaque(false);
		JScrollPane scrollPrincipal = new JScrollPane(panelContenido);
		scrollPrincipal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPrincipal.setBounds(90, 140, 1100, 500);
		add(btnVolver);
		add(btnLimpiar);
		add(scrollPrincipal);
		add(fondo);
	}

	/**
	 * Agrega las compras del historial al panel de contenido. Limpia el panel
	 * actual y vuelve a agregar todas las compras.
	 */
	public void agregarCompras() {
		panelContenido.removeAll();
		agregarComprasRecursivo(historialCompras.getFirst(), 0);
		revalidate();
		repaint();
	}

	/**
	 * Agrega las compras al panel de contenido de forma recursiva.
	 *
	 * @param nodoCarrito Nodo actual de la lista de carritos.
	 * @param indice      Índice de la compra actual.
	 */
	private void agregarComprasRecursivo(Node<Carrito> nodoCarrito, int indice) {
		if (nodoCarrito == null) {
			return;
		}
		Carrito carrito = nodoCarrito.getInfo();
		JLabel nProducto = new JLabel(" Compra #" + (indice + 1));
		JPanel panelCarrito = new JPanel();
		panelCarrito.setLayout(new BorderLayout());
		panelCarrito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panelCarrito.setBackground(new Color(198, 195, 195));
		panelCarrito.add(nProducto, BorderLayout.NORTH);
		JPanel panelProductos = new JPanel();
		panelProductos.setLayout(new GridLayout(0, 1, 5, 5));
		agregarProductosRecursivo(carrito.getListaNombresProductos().getFirst(), panelProductos);
		JScrollPane scrollProductos = new JScrollPane(panelProductos);
		scrollProductos.setPreferredSize(new Dimension(950, 450));
		scrollProductos.setBorder(BorderFactory.createEmptyBorder());
		panelCarrito.add(scrollProductos, BorderLayout.CENTER);
		panelContenido.add(panelCarrito);
		agregarComprasRecursivo(nodoCarrito.getNext(), indice + 1);
	}

	/**
	 * Agrega los productos de una compra al panel de productos de forma recursiva.
	 *
	 * @param nodoProducto   Nodo actual de la lista de nombres de productos.
	 * @param panelProductos Panel donde se agregarán los productos.
	 */
	private void agregarProductosRecursivo(Node<String> nodoProducto, JPanel panelProductos) {
		if (nodoProducto == null) {
			return;
		}
		String nombreProducto = nodoProducto.getInfo();
		JTextArea txtAreaInfo = new JTextArea(nombreProducto);
		txtAreaInfo.setEditable(false);
		txtAreaInfo.setFont(new Font("Baloo", Font.BOLD, 18));
		txtAreaInfo.setBackground(new Color(230, 230, 230));
		panelProductos.add(txtAreaInfo);
		agregarProductosRecursivo(nodoProducto.getNext(), panelProductos);
	}

	/**
	 * Actualiza la información mostrada en el panel de contenido.
	 */
	public void actualizarInfo() {
		panelContenido.removeAll();
		agregarCompras();
		revalidate();
		repaint();
	}

	/**
	 * Agrega una compra al historial de compras.
	 *
	 * @param carrito Carrito que contiene la información de la compra a agregar.
	 */
	public void agregarCompraAlHistorial(Carrito carrito) {
		historialCompras.addLastR(carrito);
		actualizarInfo();
	}

	/**
	 * Limpia el historial de compras.
	 */
	public void limpiarHistorial() {
		historialCompras = new LinkedList<>();
		actualizarInfo();
	}

	/**
	 * Obtiene la etiqueta de fondo del panel.
	 *
	 * @return JLabel Etiqueta de fondo.
	 */
	public JLabel getFondo() {
		return fondo;
	}

	/**
	 * Establece la etiqueta de fondo del panel.
	 *
	 * @param fondo Nueva etiqueta de fondo.
	 */
	public void setFondo(JLabel fondo) {
		this.fondo = fondo;
	}

	/**
	 * Obtiene el botón para volver a la pantalla anterior.
	 *
	 * @return JButton Botón para volver.
	 */
	public JButton getBtnVolver() {
		return btnVolver;
	}

	/**
	 * Establece el botón para volver a la pantalla anterior.
	 *
	 * @param btnVolver Nuevo botón para volver.
	 */
	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

	/**
	 * Obtiene el botón para limpiar el historial de compras.
	 *
	 * @return JButton Botón para limpiar el historial.
	 */
	public JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	/**
	 * Establece el botón para limpiar el historial de compras.
	 *
	 * @param btnLimpiar Nuevo botón para limpiar el historial.
	 */
	public void setBtnLimpiar(JButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	/**
	 * Obtiene el panel de desplazamiento del contenido.
	 *
	 * @return JScrollPane Panel de desplazamiento.
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * Establece el panel de desplazamiento del contenido.
	 *
	 * @param scrollPane Nuevo panel de desplazamiento.
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	/**
	 * Obtiene el panel que contiene las compras del historial.
	 *
	 * @return JPanel Panel de contenido.
	 */
	public JPanel getPanelContenido() {
		return panelContenido;
	}

	/**
	 * Establece el panel que contiene las compras del historial.
	 *
	 * @param panelContenido Nuevo panel de contenido.
	 */
	public void setPanelContenido(JPanel panelContenido) {
		this.panelContenido = panelContenido;
	}

	/**
	 * Obtiene el historial de compras.
	 *
	 * @return LinkedList<Carrito> Historial de compras.
	 */
	public LinkedList<Carrito> getHistorialCompras() {
		return historialCompras;
	}

	/**
	 * Establece el historial de compras.
	 *
	 * @param historialCompras Nuevo historial de compras.
	 */
	public void setHistorialCompras(LinkedList<Carrito> historialCompras) {
		this.historialCompras = historialCompras;
	}
}
