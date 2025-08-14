package co.edu.unbosque.controller;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import co.edu.unbosque.model.VerduraDTO;
import co.edu.unbosque.model.persistence.FileManager;
import co.edu.unbosque.model.persistence.VerduraDAO;
import co.edu.unbosque.view.ViewFacade;

public class Controller implements ActionListener {
	private ViewFacade vf;

	public Controller() throws IOException {
		vf = new ViewFacade();
		FileManager.crearCarpeta();
		asignarLectores();

	}

	/**
	 * Método que inicia la ejecución de la aplicación. Hace visible la ventana
	 * principal.
	 */
	public void run() {
		vf.getVp().setVisible(true);
		VerduraDAO adsa = new VerduraDAO();
		adsa.add(new VerduraDTO("nose", 0, "", "", ""));
	}

	/**
	 * Método que asigna los listeners a los botones de la interfaz gráfica.
	 * Configura los comandos de acción para cada botón.
	 */

	public void asignarLectores() {
		vf.getVp().getPp().getIniciar().addActionListener(this);
		vf.getVp().getPp().getIniciar().setActionCommand("iniciar");
		vf.getVp().getPp().getRegistrar().addActionListener(this);
		vf.getVp().getPp().getRegistrar().setActionCommand("registrar");
		vf.getVp().getPs().getRegistrar().addActionListener(this);
		vf.getVp().getPs().getRegistrar().setActionCommand("registrarS");
		vf.getVp().getPs().getEntrar().addActionListener(this);
		vf.getVp().getPs().getEntrar().setActionCommand("entrar");
		vf.getVp().getPcu().getCrear().addActionListener(this);
		vf.getVp().getPcu().getCrear().setActionCommand("crear");
	}

	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "iniciar": {
			vf.getVp().getPp().setVisible(false);
			vf.getVp().getPs().setVisible(true);
			break;
		}
		case "registrar": {
			vf.getVp().getPp().setVisible(false);
			vf.getVp().getPcu().setVisible(true);
			break;
		}
		case "registrarS": {
			vf.getVp().getPs().setVisible(false);
			vf.getVp().getPcu().setVisible(true);
			break;
		}
		case "entrar": {
			vf.getVp().getPs().setVisible(false);
			vf.getVp().getPpr().setVisible(true);
			break;
		}
		case "crear": {
			vf.getVp().getPcu().setVisible(false);
			vf.getVp().getPs().setVisible(true);
			break;
		}
		}
	}
}
