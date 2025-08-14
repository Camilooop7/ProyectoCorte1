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

/**
 * Clase la cual es llamada como Ventana y extiende JPanel lo que permite
 * agregar botones y o componentes de interfaz grafica. y creación de las
 * variables con su nombre privadas.
 */
public class PanelPrincipal extends JPanel {
	private JLabel fondo;
	private JButton iniciar;
	private JButton registrar;

	/**
	 * Constructor del panel donde se ejecuta la logica en general de cada parametro
	 * que se encuentra en la ventana. además se declara la excepción de
	 * IOexception.
	 * 
	 * @throws IOException Si ocurre un error al cargar las imágenes.
	 */
	public PanelPrincipal() throws IOException {
		setBounds(0, 0, 1290, 750);
		setLayout(null);
		/**
		 * Inicialización del JLabel BufferedImage con el objetivo de establecer la
		 * ubicación del archivo de la imagen dentro de los archivos. Image Redim
		 * redimenzionar las medidas establecidas de la imagen.
		 */
		fondo = new JLabel();
		BufferedImage fd = ImageIO.read(new File("src/co/edu/unbosque/view/Inicio.png"));
		ImageIcon imagenFondo = new ImageIcon(fd);
		Image fdRedim = fd.getScaledInstance(1290, 750, Image.SCALE_SMOOTH);
		fondo.setIcon(new ImageIcon(fdRedim));
		fondo.setBounds(0, 0, 1290, 750);

		/**
		 * En este caso se inicializa el Jbutton para su uso .setbounds para definir el
		 * tamaño y posicion dentro del panel .setbackground se establece el color.
		 * .contentareafilled para que el area de boton sea transparente .borderpainted
		 * quitar el borde establecido preterminado del boton. .add añadir el boton.
		 */
		iniciar = new JButton();
		iniciar.setBounds(250, 548, 375, 100);
		iniciar.setFocusable(false);
		iniciar.setBackground(new Color(0, 0, 0));
		iniciar.setContentAreaFilled(false);
		iniciar.setOpaque(false);
		iniciar.setBorderPainted(true);
		iniciar.setVisible(true);
		add(iniciar);

		/**
		 * En este caso se inicializa el Jbutton para su uso .setbounds para definir el
		 * tamaño y posicion dentro del panel .setbackground se establece el color.
		 * .contentareafilled para que el area de boton sea transparente .borderpainted
		 * quitar el borde establecido preterminado del boton. .add añadir el boton.
		 */
		registrar = new JButton();
		registrar.setBounds(665, 548, 375, 100);
		registrar.setFocusable(false);
		registrar.setBackground(new Color(0, 0, 0));
		registrar.setContentAreaFilled(false);
		registrar.setOpaque(false);
		registrar.setBorderPainted(true);
		registrar.setVisible(true);
		add(registrar);

		add(fondo);
		// TODO Auto-generated constructor stub
	}

	public JLabel getFondo() {
		return fondo;
	}

	public void setFondo(JLabel fondo) {
		this.fondo = fondo;
	}

	public JButton getIniciar() {
		return iniciar;
	}

	public void setIniciar(JButton iniciar) {
		this.iniciar = iniciar;
	}

	public JButton getRegistrar() {
		return registrar;
	}

	public void setRegistrar(JButton registrar) {
		this.registrar = registrar;
	}

}
