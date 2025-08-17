package co.edu.unbosque.model.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * funcion especial de leer y guaradr archivos
 */
public class FileManager {
	/**
	 * Ruta de la carpeta donde se almacenarán los archivos.
	 */
	private static final String RUTA_CARPETA = "../apache-tomcat-10.1.36/archivos";
	/**
	 * estos 3 son para acrhivos de texto
	 */
	private static Scanner lectorArchivo;
	private static File archivo;
	private static PrintWriter escritorArchivo;

	/**
	 * atributos para archivos serealizado
	 */

	/**
	 * Variables para ubicar el archivo, escribir obejtos en el arvhivo, leer
	 * archivo, leer objetos desde el archivo
	 */
	private static FileOutputStream fos;
	private static ObjectOutputStream oos;
	private static FileInputStream fis;
	private static ObjectInputStream ois;

	/**
	 * Método que crea la carpeta donde se almacenarán los archivos. Si la carpeta
	 * no existe, la crea.
	 */
	public static void crearCarpeta() {
		archivo = new File(RUTA_CARPETA);
		if (!archivo.exists() || !archivo.isDirectory()) {
			archivo.mkdir();// cree la carpeta
		}

	}

	/**
	 * Método para escribir en un archivo de texto. Recibe el nombre del archivo y
	 * el contenido a escribir. Si el archivo no existe, lo crea antes de escribir
	 * en él.
	 */

	public static void escribirArchivoTexto(String nombreArchivo, String contenido) {
		try {
			archivo = new File(RUTA_CARPETA + "/" + nombreArchivo);
			if (!archivo.exists()) {
				archivo.createNewFile(); // crear el archivo si no existe
			}
			escritorArchivo = new PrintWriter(archivo);
			escritorArchivo.println(contenido); // escribe el contenido en el archivo
			escritorArchivo.close();// cierra el archivo después de escribir
			// cuando se habre un archivo despues de abrirlo ahi que cerrarlo
		} catch (IOException e) {
			// Si llegue hasta aqui es por que el aechivo no tiene permisos, la URL esta mal
			// o el acrchivo no existe
			System.out.println("Error al escribir el archivo de texto.(creacion del archivo).");
			e.printStackTrace();
		}
	}

	/**
	 * Método para leer un archivo de texto. Recibe el nombre del archivo a leer. Si
	 * el archivo no existe, lo crea. Devuelve el contenido del archivo como una
	 * cadena de texto.
	 */

	public static String leerArchivoTexto(String nombreArchivo) {
		try {
			archivo = new File(RUTA_CARPETA + "/" + nombreArchivo);
			if (!archivo.exists()) {
				archivo.createNewFile();// crea el archivo si no existe
			}
			lectorArchivo = new Scanner(archivo);
			String contenido = "";
			while (lectorArchivo.hasNext()) {
				contenido += lectorArchivo.nextLine() + "\n"; // lee línea por línea y las concatena
			}
			lectorArchivo.close();// cierra el archivo después de leer
			return contenido;
		} catch (IOException e) {
			System.out.println("Error al leer el archivo de texto.(creacion del archivo).");
			e.printStackTrace();
		}
		return null; // retorna null si hubo un error
	}

	/**
	 * Método para escribir un objeto en un archivo serializado. Recibe el nombre
	 * del archivo y el contenido (objeto) a escribir. Si el archivo no existe, lo
	 * crea antes de escribir en él.
	 */

	public static void escribirArchivoSerializado(String nombreArchivo, Object contenido) {
		try {
			archivo = new File(RUTA_CARPETA + "/" + nombreArchivo);
			if (!archivo.exists()) {
				archivo.createNewFile(); // crea el archivo si no existe
			}
			fos = new FileOutputStream(archivo);// abre el archivo para escritura
			oos = new ObjectOutputStream(fos);// crea un ObjectOutputStream para escribir objetos
			oos.writeObject(contenido);// escribe el objeto en el archivo
			oos.close();// cierra el ObjectOutputStream
			fos.close();// cierra el FileOutputStream
		} catch (IOException e) {
			System.out.println("Problemas al abrir el archivo serealizado (escritura)");
			e.printStackTrace();
		}

	}

	/**
	 * Método para leer un archivo serializado. Recibe el nombre del archivo a leer.
	 * Devuelve el objeto leído del archivo.
	 */

	public static Object leerArchivoSerializado(String nombreArchivo) {

		Object contenido = null;
		try {
			archivo = new File(RUTA_CARPETA + "/" + nombreArchivo);
			if (!archivo.exists()) {
				/**
				 * crea el archivo si no existe
				 */
				archivo.createNewFile();
			}
			/**
			 * abre el archivo para lectura
			 */
			fis = new FileInputStream(archivo);

			/**
			 * crea un ObjectInputStream para leer objetos
			 */
			ois = new ObjectInputStream(fis);
			/**
			 * lee el objeto desde el archivo
			 */
			contenido = ois.readObject();//
			ois.close();
			fis.close();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo serializado.");
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			System.out.println("Error en los datos del archivo serializado.");
			e.printStackTrace();
		}
		/**
		 * devuelve el objeto leído o null si hubo un error
		 */
		return contenido;
	}

	public static void guardarImagen(File origen, File destino) throws IOException {
		try (InputStream input = new FileInputStream(origen); OutputStream output = new FileOutputStream(destino)) {

			byte[] buf = new byte[1024];
			int a;
			while ((a = input.read(buf)) > 0) {
				output.write(buf, 0, a);
			}
		}
	}
}