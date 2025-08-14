package co.edu.unbosque.controller;

import java.io.IOException;

import co.edu.unbosque.view.ViewFacade;

public class Controller {
	private ViewFacade vf;

	public Controller() throws IOException {
		vf = new ViewFacade();
	}

	public void run() {
		vf.getVp().setVisible(true);
	}

}
