package co.edu.unbosque.view;

import java.io.IOException;

import javax.swing.JFrame;
/**
 * Clase la cual es llamada como Ventana y extiende JPanel lo que permite
 * agregar botones y o componentes de interfaz grafica. y creación de las
 * variables con su nombre privadas.
 */
public class VentanaPrincipal extends JFrame {

	private PanelPrincipal pp;
	private PanelSesion ps;

	/**
	 * Constructor del panel donde se ejecuta la logica en general de cada parametro
	 * que se encuentra en la ventana. además se declara la excepción de
	 * IOexception.
	 */
	public VentanaPrincipal() throws IOException {
		setBounds(10, 10, 1290, 750);
		setTitle("Tienda de Barrio");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		pp = new PanelPrincipal();
		ps = new PanelSesion();
		
		add(pp).setVisible(true);
		add(ps).setVisible(false);
	}

	public PanelPrincipal getPp() {
		return pp;
	}

	public void setPp(PanelPrincipal pp) {
		this.pp = pp;
	}

	public PanelSesion getPs() {
		return ps;
	}

	public void setPs(PanelSesion ps) {
		this.ps = ps;
	}

}
