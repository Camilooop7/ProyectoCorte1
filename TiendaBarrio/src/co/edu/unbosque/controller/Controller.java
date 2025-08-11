package co.edu.unbosque.controller;

import co.edu.unbosque.view.ViewFacade;

public class Controller {
	private ViewFacade vf;

	public Controller() {
		vf = new ViewFacade();
	}

	public void run() {
		vf.getVp().setVisible(true);
	}

}
