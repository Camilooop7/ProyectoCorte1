package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.unbosque.model.Fruta;
import co.edu.unbosque.model.Gaseosa;
import co.edu.unbosque.model.Jugo;
import co.edu.unbosque.model.PaquetePapa;
import co.edu.unbosque.model.Verdura;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class PanelProducto extends JPanel {
	private JLabel fondo;
	private JButton verdura;
	private JButton fruta;
	private JButton jugo;
	private JButton gaseosa;
	private JButton paquete;
	private JButton carrito;
	private JButton historial;
	private PanelEstanteSuperiorV estSuV;
	private PanelEstanteSuperiorG estSuG;
	private PanelEstanteSuperiorJ estSuJ;
	private PanelEstanteSuperiorF estSuF;
	private PanelEstanteSuperiorP estSuP;
	private PanelEstanteInferiorV estInfV;
	private PanelEstanteInferiorG estInfG;
	private PanelEstanteInferiorJ estInfJ;
	private PanelEstanteInferiorF estInfF;
	private PanelEstanteInferiorP estInfP;

	public PanelProducto() throws IOException {
		setBounds(0, 0, 1290, 750);
		setLayout(null);
		fondo = new JLabel();
		BufferedImage fd = ImageIO.read(new File("src/co/edu/unbosque/view/Producto.png"));
		ImageIcon imagenFondo = new ImageIcon(fd);
		Image fdRedim = fd.getScaledInstance(1290, 750, Image.SCALE_SMOOTH);
		fondo.setIcon(new ImageIcon(fdRedim));
		fondo.setBounds(0, 0, 1290, 750);

		estSuV = new PanelEstanteSuperiorV();
		estSuV.setBounds(160, 180, 970, 200);
		estInfV = new PanelEstanteInferiorV();
		estInfV.setBounds(160, 390, 970, 200);

		estSuG = new PanelEstanteSuperiorG();
		estSuG.setBounds(160, 180, 970, 200);
		estInfG = new PanelEstanteInferiorG();
		estInfG.setBounds(160, 390, 970, 200);

		estSuJ = new PanelEstanteSuperiorJ();
		estSuJ.setBounds(160, 180, 970, 200);
		estInfJ = new PanelEstanteInferiorJ();
		estInfJ.setBounds(160, 390, 970, 200);

		estSuF = new PanelEstanteSuperiorF();
		estSuF.setBounds(160, 180, 970, 200);
		estInfF = new PanelEstanteInferiorF();
		estInfF.setBounds(160, 390, 970, 200);

		estSuP = new PanelEstanteSuperiorP();
		estSuP.setBounds(160, 180, 970, 200);
		estInfP = new PanelEstanteInferiorP();
		estInfP.setBounds(160, 390, 970, 200);

		add(estSuV);
		add(estInfV);

		add(estSuG);
		add(estInfG);

		add(estSuJ);
		add(estInfJ);

		add(estSuF);
		add(estInfF);

		add(estSuP);
		add(estInfP);

		carrito = new JButton();
		carrito.setBounds(930, 26, 87, 69);
		carrito.setFocusable(false);
		carrito.setBackground(new Color(0, 0, 0));
		carrito.setContentAreaFilled(false);
		carrito.setOpaque(false);
		carrito.setBorderPainted(true);
		carrito.setVisible(true);
		add(carrito);
		
		historial = new JButton();
		historial.setBounds(1048, 28, 92, 65);
		historial.setFocusable(false);
		historial.setBackground(new Color(0, 0, 0));
		historial.setContentAreaFilled(false);
		historial.setOpaque(false);
		historial.setBorderPainted(true);
		historial.setVisible(true);
		add(historial);
		
		verdura = new JButton();
		verdura.setBounds(95, 115, 185, 40);
		verdura.setFocusable(false);
		verdura.setBackground(new Color(0, 0, 0));
		verdura.setContentAreaFilled(false);
		verdura.setOpaque(false);
		verdura.setBorderPainted(true);
		verdura.setVisible(true);
		add(verdura);
		fruta = new JButton();
		fruta.setBounds(313, 115, 185, 40);
		fruta.setFocusable(false);
		fruta.setBackground(new Color(0, 0, 0));
		fruta.setContentAreaFilled(false);
		fruta.setOpaque(false);
		fruta.setBorderPainted(true);
		fruta.setVisible(true);
		add(fruta);


		gaseosa = new JButton();
		gaseosa.setBounds(535, 115, 185, 40);
		gaseosa.setFocusable(false);
		gaseosa.setBackground(new Color(0, 0, 0));
		gaseosa.setContentAreaFilled(false);
		gaseosa.setOpaque(false);
		gaseosa.setBorderPainted(true);
		gaseosa.setVisible(true);
		add(gaseosa);

		paquete = new JButton();
		paquete.setBounds(759, 115, 185, 40);
		paquete.setFocusable(false);
		paquete.setBackground(new Color(0, 0, 0));
		paquete.setContentAreaFilled(false);
		paquete.setOpaque(false);
		paquete.setBorderPainted(true);
		paquete.setVisible(true);
		add(paquete);
		jugo = new JButton();
		jugo.setBounds(982, 115, 185, 40);
		jugo.setFocusable(false);
		jugo.setBackground(new Color(0, 0, 0));
		jugo.setContentAreaFilled(false);
		jugo.setOpaque(false);
		jugo.setBorderPainted(true);
		jugo.setVisible(true);
		add(jugo);
		add(fondo);
	}

	public void actualizarInfoVerdura(LinkedList<Verdura> listaVerduras) {
		int totalProductos = contarNodos(listaVerduras.getFirst(), 0);
		int mitad = totalProductos / 2;

		// Estante superior: primera mitad
		estSuV.removeAll();
		estSuV.agregarProductos(listaVerduras, totalProductos, listaVerduras.getFirst(), 0);

		// Estante inferior: segunda mitad
		Node<Verdura> nodoMitad = obtenerNodoEnPosicion(listaVerduras.getFirst(), mitad);
		estInfV.removeAll();
		estInfV.agregarProductos(listaVerduras, totalProductos, nodoMitad, 0);

		revalidate();
		repaint();
	}

	private int contarNodos(Node<Verdura> nodo, int cont) {
		if (nodo == null)
			return cont;
		return contarNodos(nodo.getNext(), cont + 1);
	}

	private Node<Verdura> obtenerNodoEnPosicion(Node<Verdura> nodoActual, int pos) {
		if (pos == 0 || nodoActual == null) {
			return nodoActual;
		}
		return obtenerNodoEnPosicion(nodoActual.getNext(), pos - 1);
	}

	public void actualizarInfoFruta(LinkedList<Fruta> listaFrutas) {
		int totalProductos = contarNodosF(listaFrutas.getFirst(), 0);
		int mitad = totalProductos / 2;
		// Estante superior: primera mitad
		estSuF.removeAll();
		estSuF.agregarProductos(listaFrutas, totalProductos, listaFrutas.getFirst(), 0);
		// Estante inferior: segunda mitad
		Node<Fruta> nodoMitad = obtenerNodoEnPosicionF(listaFrutas.getFirst(), mitad);
		estInfF.removeAll();
		estInfF.agregarProductos(listaFrutas, totalProductos, nodoMitad, 0);
		revalidate();
		repaint();
	}

	private int contarNodosF(Node<Fruta> nodo, int cont) {
		if (nodo == null)
			return cont;
		return contarNodosF(nodo.getNext(), cont + 1);
	}

	private Node<Fruta> obtenerNodoEnPosicionF(Node<Fruta> nodoActual, int pos) {
		if (pos == 0 || nodoActual == null) {
			return nodoActual;
		}
		return obtenerNodoEnPosicionF(nodoActual.getNext(), pos - 1);
	}

	public void actualizarInfoPaquetePapa(LinkedList<PaquetePapa> listaPaquetesPapa) {
		int totalProductos = contarNodosP(listaPaquetesPapa.getFirst(), 0);
		int mitad = totalProductos / 2;
		estSuP.removeAll();
		estSuP.agregarProductos(listaPaquetesPapa, totalProductos, listaPaquetesPapa.getFirst(), 0);
		Node<PaquetePapa> nodoMitad = obtenerNodoEnPosicionP(listaPaquetesPapa.getFirst(), mitad);
		estInfP.removeAll();
		estInfP.agregarProductos(listaPaquetesPapa, totalProductos, nodoMitad, 0);
		revalidate();
		repaint();
	}

	private int contarNodosP(Node<PaquetePapa> nodo, int cont) {
		if (nodo == null)
			return cont;
		return contarNodosP(nodo.getNext(), cont + 1);
	}

	private Node<PaquetePapa> obtenerNodoEnPosicionP(Node<PaquetePapa> nodoActual, int pos) {
		if (pos == 0 || nodoActual == null) {
			return nodoActual;
		}
		return obtenerNodoEnPosicionP(nodoActual.getNext(), pos - 1);
	}

	public void actualizarInfoGaseosa(LinkedList<Gaseosa> listaGaseosas) {
		int totalProductos = contarNodosG(listaGaseosas.getFirst(), 0);
		int mitad = totalProductos / 2;
		// Estante superior: primera mitad
		estSuG.removeAll();
		estSuG.agregarProductos(listaGaseosas, totalProductos, listaGaseosas.getFirst(), 0);
		// Estante inferior: segunda mitad
		Node<Gaseosa> nodoMitad = obtenerNodoEnPosicionG(listaGaseosas.getFirst(), mitad);
		estInfG.removeAll();
		estInfG.agregarProductos(listaGaseosas, totalProductos, nodoMitad, 0);
		revalidate();
		repaint();
	}

	private int contarNodosG(Node<Gaseosa> nodo, int cont) {
		if (nodo == null)
			return cont;
		return contarNodosG(nodo.getNext(), cont + 1);
	}

	private Node<Gaseosa> obtenerNodoEnPosicionG(Node<Gaseosa> nodoActual, int pos) {
		if (pos == 0 || nodoActual == null) {
			return nodoActual;
		}
		return obtenerNodoEnPosicionG(nodoActual.getNext(), pos - 1);
	}

	public void actualizarInfoJugo(LinkedList<Jugo> listaJugos) {
		int totalProductos = contarNodosJ(listaJugos.getFirst(), 0);
		int mitad = totalProductos / 2;
		// Estante superior: primera mitad
		estSuJ.removeAll();
		estSuJ.agregarProductos(listaJugos, totalProductos, listaJugos.getFirst(), 0);
		// Estante inferior: segunda mitad
		Node<Jugo> nodoMitad = obtenerNodoEnPosicionJ(listaJugos.getFirst(), mitad);
		estInfJ.removeAll();
		estInfJ.agregarProductos(listaJugos, totalProductos, nodoMitad, 0);
		revalidate();
		repaint();
	}

	private int contarNodosJ(Node<Jugo> nodo, int cont) {
		if (nodo == null)
			return cont;
		return contarNodosJ(nodo.getNext(), cont + 1);
	}

	private Node<Jugo> obtenerNodoEnPosicionJ(Node<Jugo> nodoActual, int pos) {
		if (pos == 0 || nodoActual == null) {
			return nodoActual;
		}
		return obtenerNodoEnPosicionJ(nodoActual.getNext(), pos - 1);
	}

	// Getters y setters...

	public JLabel getFondo() {
		return fondo;
	}

	public void setFondo(JLabel fondo) {
		this.fondo = fondo;
	}

	public JButton getVerdura() {
		return verdura;
	}

	public void setVerdura(JButton verdura) {
		this.verdura = verdura;
	}

	public JButton getFruta() {
		return fruta;
	}

	public void setFruta(JButton fruta) {
		this.fruta = fruta;
	}

	public JButton getJugo() {
		return jugo;
	}

	public void setJugo(JButton jugo) {
		this.jugo = jugo;
	}

	public JButton getGaseosa() {
		return gaseosa;
	}

	public void setGaseosa(JButton gaseosa) {
		this.gaseosa = gaseosa;
	}

	public JButton getPaquete() {
		return paquete;
	}

	public void setPaquete(JButton paquete) {
		this.paquete = paquete;
	}

	public PanelEstanteSuperiorV getEstSuV() {
		return estSuV;
	}

	public void setEstSuV(PanelEstanteSuperiorV estSuV) {
		this.estSuV = estSuV;
	}

	public PanelEstanteSuperiorG getEstSuG() {
		return estSuG;
	}

	public void setEstSuG(PanelEstanteSuperiorG estSuG) {
		this.estSuG = estSuG;
	}

	public PanelEstanteSuperiorJ getEstSuJ() {
		return estSuJ;
	}

	public void setEstSuJ(PanelEstanteSuperiorJ estSuJ) {
		this.estSuJ = estSuJ;
	}

	public PanelEstanteSuperiorF getEstSuF() {
		return estSuF;
	}

	public void setEstSuF(PanelEstanteSuperiorF estSuF) {
		this.estSuF = estSuF;
	}

	public PanelEstanteSuperiorP getEstSuP() {
		return estSuP;
	}

	public void setEstSuP(PanelEstanteSuperiorP estSuP) {
		this.estSuP = estSuP;
	}

	public PanelEstanteInferiorV getEstInfV() {
		return estInfV;
	}

	public void setEstInfV(PanelEstanteInferiorV estInfV) {
		this.estInfV = estInfV;
	}

	public PanelEstanteInferiorG getEstInfG() {
		return estInfG;
	}

	public void setEstInfG(PanelEstanteInferiorG estInfG) {
		this.estInfG = estInfG;
	}

	public PanelEstanteInferiorJ getEstInfJ() {
		return estInfJ;
	}

	public void setEstInfJ(PanelEstanteInferiorJ estInfJ) {
		this.estInfJ = estInfJ;
	}

	public PanelEstanteInferiorF getEstInfF() {
		return estInfF;
	}

	public void setEstInfF(PanelEstanteInferiorF estInfF) {
		this.estInfF = estInfF;
	}

	public PanelEstanteInferiorP getEstInfP() {
		return estInfP;
	}

	public void setEstInfP(PanelEstanteInferiorP estInfP) {
		this.estInfP = estInfP;
	}

	public JButton getCarrito() {
		return carrito;
	}

	public void setCarrito(JButton carrito) {
		this.carrito = carrito;
	}

	public JButton getHistorial() {
		return historial;
	}

	public void setHistorial(JButton historial) {
		this.historial = historial;
	}

}
