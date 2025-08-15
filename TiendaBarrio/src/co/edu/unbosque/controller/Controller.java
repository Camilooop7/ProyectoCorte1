package co.edu.unbosque.controller;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.UsuarioDTO;
import co.edu.unbosque.model.persistence.FileManager;
import co.edu.unbosque.model.persistence.UsuarioDAO;
import co.edu.unbosque.util.exception.IsBlackException;
import co.edu.unbosque.util.exception.TextException;
import co.edu.unbosque.view.ViewFacade;

public class Controller implements ActionListener {
	private ViewFacade vf;
	private ModelFacade mf;

	public Controller() throws IOException {
		vf = new ViewFacade();
		mf = new ModelFacade();
		FileManager.crearCarpeta();
		asignarLectores();

	}

	/**
	 * Método que inicia la ejecución de la aplicación. Hace visible la ventana
	 * principal.
	 */
	public void run() {
		vf.getVp().setVisible(true);
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
			int identificacion = vf.getVp().getPs().getIdentificacion();
			if (mf.getUsuarioDAO().find(new Usuario("", identificacion)) != null) {
				Usuario u = mf.getUsuarioDAO().find(new Usuario("", identificacion));
				
				vf.getVe().mostrar("Ingreso como:  "+ u.getNombre());
				vf.getVp().getPs().setVisible(false);
				vf.getVp().getPpr().setVisible(true);
//TODO aca debo poner que cargue el stock
			}
			break;
		}
		case "crear": {
			int identificacion = vf.getVp().getPcu().getIdentificacion();
			String nombre = vf.getVp().getPcu().getNombre();
			try {
				ExceptionCheker.checkerIsBlank(nombre);
				ExceptionCheker.checkerText(nombre);
				if (mf.getUsuarioDAO().find(new Usuario("", identificacion)) == null) {
					mf.getUsuarioDAO().add(new UsuarioDTO(nombre, identificacion));
					vf.getVe().mostrar("Usuario creado exitosamente");
					vf.getVp().getPcu().setVisible(false);
					vf.getVp().getPs().setVisible(true);

				} else {

					vf.getVe().mostrarError("Identificacion ya registrada, vuelva para ingresar");

				}

			} catch (IsBlackException e2) {

				vf.getVe().mostrar("Complete la informacion de nombre");
			} catch (TextException e2) {
				vf.getVe().mostrar("Unicamente se aceptan letras");
			}
			break;
		}
		}
	}
}
