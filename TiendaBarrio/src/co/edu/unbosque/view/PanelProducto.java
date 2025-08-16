package co.edu.unbosque.view;

import java.awt.Button;
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

/**
 * Clase la cual es llamada como Ventana y extiende JPanel lo que permite
 * agregar botones y o componentes de interfaz grafica. y creación de las
 * variables con su nombre privadas.
 */
public class PanelProducto extends JPanel {

	private JLabel fondo;
	private JButton verdura;
	private JButton fruta;
	private JButton bebida;
	private JButton paquete;
	private PanelVerdura pv;

	public PanelProducto() throws IOException {
		setBounds(0, 0, 1290, 750);
		setLayout(null);
		/**
		 * Inicialización del JLabel BufferedImage con el objetivo de establecer la
		 * ubicación del archivo de la imagen dentro de los archivos. Image Redim
		 * redimenzionar las medidas establecidas de la imagen.
		 */
		fondo = new JLabel();
		BufferedImage fd = ImageIO.read(new File("src/co/edu/unbosque/view/Producto.png"));
		ImageIcon imagenFondo = new ImageIcon(fd);
		Image fdRedim = fd.getScaledInstance(1290, 750, Image.SCALE_SMOOTH);
		fondo.setIcon(new ImageIcon(fdRedim));
		fondo.setBounds(0, 0, 1290, 750);
		
		
		pv = new PanelVerdura();
		pv.setVisible(false);
		add(pv);
		/**
		 * En este caso se inicializa el Jbutton para su uso .setbounds para definir el
		 * tamaño y posicion dentro del panel .setbackground se establece el color.
		 * .contentareafilled para que el area de boton sea transparente .borderpainted
		 * quitar el borde establecido preterminado del boton. .add añadir el boton.
		 */
		verdura = new JButton();
		verdura.setBounds(190,120, 205, 45);
		verdura.setFocusable(false);
		verdura.setBackground(new Color(0, 0, 0));
		verdura.setContentAreaFilled(false);
		verdura.setOpaque(false);
		verdura.setBorderPainted(true);
		verdura.setVisible(true);
		add(verdura);
		
		fruta = new JButton();
		fruta.setBounds(430,120, 205, 45);
		fruta.setFocusable(false);
		fruta.setBackground(new Color(0, 0, 0));
		fruta.setContentAreaFilled(false);
		fruta.setOpaque(false);
		fruta.setBorderPainted(true);
		fruta.setVisible(true);
		add(fruta);
		
		bebida = new JButton();
		bebida.setBounds(670,120, 205, 45);
		bebida.setFocusable(false);
		bebida.setBackground(new Color(0, 0, 0));
		bebida.setContentAreaFilled(false);
		bebida.setOpaque(false);
		bebida.setBorderPainted(true);
		bebida.setVisible(true);
		add(bebida);
		
		paquete = new JButton();
		paquete.setBounds(915,120, 205, 45);
		paquete.setFocusable(false);
		paquete.setBackground(new Color(0, 0, 0));
		paquete.setContentAreaFilled(false);
		paquete.setOpaque(false);
		paquete.setBorderPainted(true);
		paquete.setVisible(true);
		add(paquete);

		
		add(fondo);

	}
	
    public void actualizarComp() throws IOException {
    	verdura = new JButton("Verduras");
    	fruta = new JButton("Fruta");
    	bebida = new JButton("Bebida");
    	paquete = new JButton("Paquete");
    }


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

	public PanelVerdura getPv() {
		return pv;
	}

	public void setPv(PanelVerdura pv) {
		this.pv = pv;
	}
	

}
