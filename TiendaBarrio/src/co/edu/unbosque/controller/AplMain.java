package co.edu.unbosque.controller;

import java.io.IOException;

/**
 * Clase principal de la aplicación. Contiene el método main que inicia la
 * ejecución del programa.
 */
public class AplMain {

	/**
	 * Método principal que inicia la aplicación. Crea una instancia de Controller y
	 * ejecuta su método run().
	 *
	 * @param args Argumentos de línea de comandos (no utilizados en esta
	 *             aplicación).
	 * @throws IOException Si ocurre un error de entrada/salida durante la
	 *                     ejecución.
	 */
	public static void main(String[] args) throws IOException {
		Controller c = new Controller();
		c.run();
	}
}
