package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

/**
 * Clase la cual es llamada como Ventana y extiende JPanel lo que permite
 * agregar botones y o componentes de interfaz grafica. y creación de las
 * variables con su nombre privadas.
 */
public class PanelSesion extends JPanel {

	private JLabel fondo;
	private JButton entrar;
	private JButton registrar;
	/**
	 * Text field for entering the specialization.
	 */
	private JSpinner identificacion;

	/**
	 * Constructor del panel donde se ejecuta la logica en general de cada parametro
	 * que se encuentra en la ventana. además se declara la excepción de
	 * IOexception.
	 * 
	 * @throws IOException Si ocurre un error al cargar las imágenes.
	 */
	public PanelSesion() throws IOException {
		setBounds(0, 0, 1290, 750);
		setLayout(null);
		/**
		 * Inicialización del JLabel BufferedImage con el objetivo de establecer la
		 * ubicación del archivo de la imagen dentro de los archivos. Image Redim
		 * redimenzionar las medidas establecidas de la imagen.
		 */
		fondo = new JLabel();
		BufferedImage fd = ImageIO.read(new File("src/co/edu/unbosque/view/Identificacion.png"));
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
		entrar = new JButton();
		entrar.setBounds(250, 570, 375, 100);
		entrar.setFocusable(false);
		entrar.setBackground(new Color(0, 0, 0));
		entrar.setContentAreaFilled(false);
		entrar.setOpaque(false);
		entrar.setBorderPainted(false);
		entrar.setVisible(true);
		add(entrar);

		/**
		 * En este caso se inicializa el Jbutton para su uso .setbounds para definir el
		 * tamaño y posicion dentro del panel .setbackground se establece el color.
		 * .contentareafilled para que el area de boton sea transparente .borderpainted
		 * quitar el borde establecido preterminado del boton. .add añadir el boton.
		 */
		registrar = new JButton();
		registrar.setBounds(665, 570, 375, 100);
		registrar.setFocusable(false);
		registrar.setBackground(new Color(0, 0, 0));
		registrar.setContentAreaFilled(false);
		registrar.setOpaque(false);
		registrar.setBorderPainted(false);
		registrar.setVisible(true);
		add(registrar);

		SpinnerNumberModel model = new SpinnerNumberModel(1000000, 1000000, 999999999, 1);
		identificacion = new JSpinner(model);

		JFormattedTextField txt = ((JSpinner.NumberEditor) identificacion.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);

		txt.setOpaque(false);
		txt.setBackground(new Color(0, 0, 0, 0));
		txt.setBorder(null);

		identificacion.setBounds(445, 390, 380, 110);
		identificacion.setFont(new Font("Baloo", Font.BOLD, 24));
		identificacion.setOpaque(false);
		identificacion.setBackground(new Color(0, 0, 0, 0));
		identificacion.setBorder(null);

		add(identificacion);

		add(fondo);
		// TODO Auto-generated constructor stub
	}

	public JLabel getFondo() {
		return fondo;
	}

	public void setFondo(JLabel fondo) {
		this.fondo = fondo;
	}

	public JButton getRegistrar() {
		return registrar;
	}

	public void setRegistrar(JButton registrar) {
		this.registrar = registrar;
	}

	public JButton getEntrar() {
		return entrar;
	}

	public void setEntrar(JButton entrar) {
		this.entrar = entrar;
	}

	public int getIdentificacion() {
		return (int) identificacion.getValue();
	}

	public void setIdentificacion(JSpinner identificacion) {
		this.identificacion = identificacion;
	}

}
