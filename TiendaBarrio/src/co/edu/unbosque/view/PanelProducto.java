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
import co.edu.unbosque.model.Verdura;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;

public class PanelProducto extends JPanel {
	private JLabel fondo;
	private JButton verdura;
	private JButton fruta;
	private JButton bebida;
	private JButton paquete;
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

		verdura = new JButton();
		verdura.setBounds(190, 120, 205, 45);
		verdura.setFocusable(false);
		verdura.setBackground(new Color(0, 0, 0));
		verdura.setContentAreaFilled(false);
		verdura.setOpaque(false);
		verdura.setBorderPainted(true);
		verdura.setVisible(true);
		add(verdura);
		fruta = new JButton();
		fruta.setBounds(430, 120, 205, 45);
		fruta.setFocusable(false);
		fruta.setBackground(new Color(0, 0, 0));
		fruta.setContentAreaFilled(false);
		fruta.setOpaque(false);
		fruta.setBorderPainted(true);
		fruta.setVisible(true);
		add(fruta);
		bebida = new JButton();
		bebida.setBounds(670, 120, 205, 45);
		bebida.setFocusable(false);
		bebida.setBackground(new Color(0, 0, 0));
		bebida.setContentAreaFilled(false);
		bebida.setOpaque(false);
		bebida.setBorderPainted(true);
		bebida.setVisible(true);
		add(bebida);
		paquete = new JButton();
		paquete.setBounds(915, 120, 205, 45);
		paquete.setFocusable(false);
		paquete.setBackground(new Color(0, 0, 0));
		paquete.setContentAreaFilled(false);
		paquete.setOpaque(false);
		paquete.setBorderPainted(true);
		paquete.setVisible(true);
		add(paquete);
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

	public JButton getBebida() {
		return bebida;
	}

	public void setBebida(JButton bebida) {
		this.bebida = bebida;
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

}
