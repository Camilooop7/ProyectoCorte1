package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import co.edu.unbosque.model.*;
import co.edu.unbosque.model.persistence.*;
import co.edu.unbosque.util.exception.*;
import co.edu.unbosque.util.pdf.GenerarPDF;
import co.edu.unbosque.util.structure.*;
import co.edu.unbosque.view.ViewFacade;

/**
 * Controlador principal de la aplicación. Gestiona los eventos de la interfaz
 * gráfica y coordina las acciones entre la vista y el modelo.
 */
public class Controller implements ActionListener {

	private ViewFacade vf;
	private ModelFacade mf;
	private String nombreUsuarioActual;
	private String nombreCarritoActual;

	/**
	 * Constructor del controlador. Inicializa la fachada de la vista y del modelo,
	 * y asigna los listeners a los componentes de la interfaz.
	 * 
	 * @throws IOException Si ocurre un error al inicializar los componentes.
	 */
	public Controller() throws IOException {
		vf = new ViewFacade();
		mf = new ModelFacade();
		FileManager.crearCarpeta();
		asignarLectores();
	}

	/**
	 * Inicia la aplicación haciendo visible la ventana principal.
	 */
	public void run() {
		vf.getVp().setVisible(true);
	}

	/**
	 * Asigna los ActionListener a los botones de la interfaz gráfica.
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
		vf.getVp().getPpr().getVerdura().addActionListener(this);
		vf.getVp().getPpr().getVerdura().setActionCommand("verdura");
		vf.getVp().getPpr().getFruta().addActionListener(this);
		vf.getVp().getPpr().getFruta().setActionCommand("fruta");
		vf.getVp().getPpr().getJugo().addActionListener(this);
		vf.getVp().getPpr().getJugo().setActionCommand("jugo");
		vf.getVp().getPpr().getGaseosa().addActionListener(this);
		vf.getVp().getPpr().getGaseosa().setActionCommand("gaseosa");
		vf.getVp().getPpr().getPaquete().addActionListener(this);
		vf.getVp().getPpr().getPaquete().setActionCommand("paquete");
		vf.getVp().getPpr().getCarrito().addActionListener(this);
		vf.getVp().getPpr().getCarrito().setActionCommand("verCarrito");
		vf.getVp().getPpr().getHistorial().addActionListener(this);
		vf.getVp().getPpr().getHistorial().setActionCommand("verHistorial");
		vf.getVp().getPc().getAgregarCarrito().addActionListener(this);
		vf.getVp().getPc().getAgregarCarrito().setActionCommand("crearCarrito");
		vf.getVp().getPc().getVolver().addActionListener(this);
		vf.getVp().getPc().getVolver().setActionCommand("volverC");
		vf.getVp().getPc().getCombocarrito().addActionListener(this);
		vf.getVp().getPc().getCombocarrito().setActionCommand("seleccionCarrito");
		vf.getVp().getPc().getComprar().addActionListener(this);
		vf.getVp().getPc().getComprar().setActionCommand("comprar");
		vf.getVp().getPh().getBtnLimpiar().addActionListener(this);
		vf.getVp().getPh().getBtnLimpiar().setActionCommand("limpiarHistorial");
		vf.getVp().getPh().getBtnVolver().addActionListener(this);
		vf.getVp().getPh().getBtnVolver().setActionCommand("volverH");
		vf.getVp().getPc().getEliminar().addActionListener(this);
		vf.getVp().getPc().getEliminar().setActionCommand("eliminar");
	}

	/**
	 * Maneja los eventos generados por los componentes de la interfaz gráfica.
	 * 
	 * @param e Evento generado por un componente.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "iniciar":
			vf.getVp().getPp().setVisible(false);
			vf.getVp().getPs().setVisible(true);
			break;
		case "registrar":
			vf.getVp().getPp().setVisible(false);
			vf.getVp().getPcu().setVisible(true);
			break;
		case "registrarS":
			vf.getVp().getPs().setVisible(false);
			vf.getVp().getPcu().setVisible(true);
			break;
		case "entrar":
			int identificacion = vf.getVp().getPs().getIdentificacion();
			if (mf.getUsuarioDAO().find(new Usuario("", identificacion, null)) != null) {
				Usuario u = mf.getUsuarioDAO().find(new Usuario("", identificacion, null));
				nombreUsuarioActual = u.getNombre();
				vf.getVe().mostrar("Ingreso como: " + u.getNombre());
				vf.getVp().getPs().setVisible(false);
				vf.getVp().getPpr().setVisible(true);
				Carrito carritoUsuario = new Carrito("Mi Carrito", u.getNombre());
				Carrito carritoExistente = mf.getCarritoDAO().find(carritoUsuario);
				if (carritoExistente == null) {
					mf.getCarritoDAO().add(DataMapper.carritoToCarritoDTO(carritoUsuario));
				}
				VerduraDAO a = new VerduraDAO();
				GaseosaDAO b = new GaseosaDAO();
				PaquetePapaDAO c = new PaquetePapaDAO();
				FrutaDAO d = new FrutaDAO();
				JugoDAO eg = new JugoDAO();
				mf.crearStock(a.getListaVerduras(), b.getListaGaseosas(), eg.getListaJugos(), d.getListaFrutas(),
						c.getListaPaquetePapas());
			}
			break;
		case "crear":
			int identificacionCrear = vf.getVp().getPcu().getIdentificacion();
			String nombre = vf.getVp().getPcu().getNombre();
			try {
				ExceptionCheker.checkerIsBlank(nombre);
				ExceptionCheker.checkerText(nombre);
				if (mf.getUsuarioDAO().find(new Usuario("", identificacionCrear, null)) == null) {
					mf.getUsuarioDAO().add(new UsuarioDTO(nombre, identificacionCrear, null));
					Carrito nuevoCarrito = new Carrito("Mi Carrito", nombre);
					mf.getCarritoDAO().add(DataMapper.carritoToCarritoDTO(nuevoCarrito));
					vf.getVe().mostrar("Usuario creado exitosamente");
					vf.getVp().getPcu().setVisible(false);
					vf.getVp().getPs().setVisible(true);
				} else {
					vf.getVe().mostrarError("Identificación ya registrada, vuelva para ingresar");
				}
			} catch (IsBlackException e2) {
				vf.getVe().mostrar("Complete la información de nombre");
			} catch (TextException e2) {
				vf.getVe().mostrar("Únicamente se aceptan letras");
			}
			break;
		case "verdura":
			vf.getVp().getPpr().getEstInfV().setVisible(true);
			vf.getVp().getPpr().getEstSuV().setVisible(true);
			ocultarOtrosPaneles("Verdura");
			vf.getVp().getPpr().actualizarInfoVerdura(mf.generarVerdura());
			asignarFuncionesComponentesProducto("Verdura");
			break;
		case "gaseosa":
			vf.getVp().getPpr().getEstInfG().setVisible(true);
			vf.getVp().getPpr().getEstSuG().setVisible(true);
			ocultarOtrosPaneles("Gaseosa");
			vf.getVp().getPpr().actualizarInfoGaseosa(mf.generarGaseosa());
			asignarFuncionesComponentesProducto("Gaseosa");
			break;
		case "jugo":
			vf.getVp().getPpr().getEstInfJ().setVisible(true);
			vf.getVp().getPpr().getEstSuJ().setVisible(true);
			ocultarOtrosPaneles("Jugo");
			vf.getVp().getPpr().actualizarInfoJugo(mf.generarJugo());
			asignarFuncionesComponentesProducto("Jugo");
			break;
		case "fruta":
			vf.getVp().getPpr().getEstInfF().setVisible(true);
			vf.getVp().getPpr().getEstSuF().setVisible(true);
			ocultarOtrosPaneles("Fruta");
			vf.getVp().getPpr().actualizarInfoFruta(mf.generarFruta());
			asignarFuncionesComponentesProducto("Fruta");
			break;
		case "paquete":
			vf.getVp().getPpr().getEstInfP().setVisible(true);
			vf.getVp().getPpr().getEstSuP().setVisible(true);
			ocultarOtrosPaneles("Paquete");
			vf.getVp().getPpr().actualizarInfoPaquetePapa(mf.generarPaquetePapa());
			asignarFuncionesComponentesProducto("Paquete");
			break;
		case "verCarrito":
			vf.getVp().getPc().setUsuario(new UsuarioDTO(nombreUsuarioActual, 0, null));
			vf.getVp().getPc().setProductosAleatorios(mf.getListaProductos());
			vf.getVp().getPc().recargarComboBox();
			vf.getVp().getPc().cargarProductosDelCarrito();
			vf.getVp().getPc().setVisible(true);
			vf.getVp().getPpr().setVisible(false);
			break;
		case "volverC":
			vf.getVp().getPc().setVisible(false);
			vf.getVp().getPpr().setVisible(true);
			break;
		case "seleccionCarrito":
			nombreCarritoActual = vf.getVp().getPc().getCombocarritoS();
			vf.getVp().getPc().cargarProductosDelCarrito();
			break;
		case "crearCarrito":
			String nombreC = vf.getVe().leerTexto("Ingrese el nombre del nuevo Carrito");
			String nombreU = nombreUsuarioActual;
			CarritoDTO nuevoCarrito = new CarritoDTO(nombreC, nombreU);
			mf.getCarritoDAO().add(nuevoCarrito);
			vf.getVe().mostrar("Carrito creado exitosamente");
			vf.getVp().getPc().recargarComboBox();
			vf.getVp().getPc().cargarProductosDelCarrito();
			vf.getVp().getPc().getTablaCarrito().repaint();
			break;
		case "comprar":
		    if (nombreUsuarioActual == null) {
		        JOptionPane.showMessageDialog(null, "Debe iniciar sesión para generar la factura.");
		        break;
		    }
		    Usuario usuarioActual = mf.getUsuarioDAO().find(new Usuario(nombreUsuarioActual, 0, null));
		    if (usuarioActual == null) {
		        JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
		        break;
		    }
		    Carrito carritoSeleccionado = mf.getCarritoDAO()
		            .find(new Carrito(nombreCarritoActual, nombreUsuarioActual));
		    if (carritoSeleccionado == null) {
		        JOptionPane.showMessageDialog(null, "Carrito no encontrado.");
		        break;
		    }

		    // (opcional) La factura puede seguir usando todo el carrito:
		    GenerarPDF.generarFactura(usuarioActual, carritoSeleccionado, vf.getVp().getPc());

		    // === NUEVO: copiar solo los disponibles al historial ===
		    Carrito copiaCarrito = new Carrito(carritoSeleccionado.getNombre(), carritoSeleccionado.getNombreU());
		    copiarSoloDisponiblesRecursivo(
		            carritoSeleccionado.getListaNombresProductos().getFirst(),
		            copiaCarrito.getListaNombresProductos()
		    );

		    if (copiaCarrito.getListaNombresProductos().isEmpty()) {
		        JOptionPane.showMessageDialog(null,
		                "No hay productos disponibles para guardar en el historial.");
		    } else {
		        vf.getVp().getPh().agregarCompraAlHistorial(copiaCarrito);
		    }

		    // Limpia siempre el carrito de compra (como ya lo hacías)
		    carritoSeleccionado.getListaNombresProductos().clear();
		    vf.getVp().getPc().cargarProductosDelCarrito();
		    JOptionPane.showMessageDialog(null,
		            "Compra realizada. Solo los productos disponibles se guardaron en el historial.");
		    break;

		case "verHistorial":
			vf.getVp().getPpr().setVisible(false);
			vf.getVp().getPh().setVisible(true);
			break;
		case "volverH":
			vf.getVp().getPpr().setVisible(true);
			vf.getVp().getPh().setVisible(false);
			break;
		case "limpiarHistorial":
			vf.getVp().getPh().limpiarHistorial();
			JOptionPane.showMessageDialog(null, "Historial limpiado.");
			break;
		default:
			agregarProductoAlCarrito(command, nombreCarritoActual);
			break;
		case "eliminar":
			eliminarProductoDelCarrito();
			break;
		}
	}

	/**
	 * Oculta los paneles de productos que no corresponden al tipo seleccionado.
	 * 
	 * @param tipoProducto Tipo de producto seleccionado.
	 */
	private void ocultarOtrosPaneles(String tipoProducto) {
		ocultarPanelRecursivo("P", tipoProducto);
		ocultarPanelRecursivo("F", tipoProducto);
		ocultarPanelRecursivo("J", tipoProducto);
		ocultarPanelRecursivo("G", tipoProducto);
		ocultarPanelRecursivo("V", tipoProducto);
		mostrarPanel(tipoProducto);
	}

	/**
	 * Oculta los paneles de un tipo específico de producto.
	 * 
	 * @param prefijo      Prefijo que identifica el tipo de producto.
	 * @param tipoProducto Tipo de producto seleccionado.
	 */
	private void ocultarPanelRecursivo(String prefijo, String tipoProducto) {
		switch (prefijo) {
		case "P":
			vf.getVp().getPpr().getEstInfP().setVisible(false);
			vf.getVp().getPpr().getEstSuP().setVisible(false);
			break;
		case "F":
			vf.getVp().getPpr().getEstInfF().setVisible(false);
			vf.getVp().getPpr().getEstSuF().setVisible(false);
			break;
		case "J":
			vf.getVp().getPpr().getEstInfJ().setVisible(false);
			vf.getVp().getPpr().getEstSuJ().setVisible(false);
			break;
		case "G":
			vf.getVp().getPpr().getEstInfG().setVisible(false);
			vf.getVp().getPpr().getEstSuG().setVisible(false);
			break;
		case "V":
			vf.getVp().getPpr().getEstInfV().setVisible(false);
			vf.getVp().getPpr().getEstSuV().setVisible(false);
			break;
		}
	}

	/**
	 * Muestra los paneles del tipo de producto seleccionado.
	 * 
	 * @param tipoProducto Tipo de producto seleccionado.
	 */
	private void mostrarPanel(String tipoProducto) {
		switch (tipoProducto) {
		case "Verdura":
			vf.getVp().getPpr().getEstInfV().setVisible(true);
			vf.getVp().getPpr().getEstSuV().setVisible(true);
			break;
		case "Gaseosa":
			vf.getVp().getPpr().getEstInfG().setVisible(true);
			vf.getVp().getPpr().getEstSuG().setVisible(true);
			break;
		case "Jugo":
			vf.getVp().getPpr().getEstInfJ().setVisible(true);
			vf.getVp().getPpr().getEstSuJ().setVisible(true);
			break;
		case "Fruta":
			vf.getVp().getPpr().getEstInfF().setVisible(true);
			vf.getVp().getPpr().getEstSuF().setVisible(true);
			break;
		case "Paquete":
			vf.getVp().getPpr().getEstInfP().setVisible(true);
			vf.getVp().getPpr().getEstSuP().setVisible(true);
			break;
		}
	}

	/**
	 * Asigna ActionListeners a los botones de los paneles de productos.
	 * 
	 * @param nodoActual Nodo actual de la lista de botones.
	 */
	private void asignarActionListeners(Node<JButton> nodoActual) {
		if (nodoActual != null) {
			nodoActual.getInfo().addActionListener(this);
			asignarActionListeners(nodoActual.getNext());
		}
	}

	/**
	 * Asigna funciones a los componentes de producto según el tipo de producto.
	 * 
	 * @param tipoProducto Tipo de producto seleccionado.
	 */
	private void asignarFuncionesComponentesProducto(String tipoProducto) {
		switch (tipoProducto) {
		case "Verdura":
			asignarActionListeners(vf.getVp().getPpr().getEstSuV().getBotonesAnadir().getFirst());
			asignarActionListeners(vf.getVp().getPpr().getEstInfV().getBotonesAnadir().getFirst());
			break;
		case "Fruta":
			asignarActionListeners(vf.getVp().getPpr().getEstSuF().getBotonesAnadir().getFirst());
			asignarActionListeners(vf.getVp().getPpr().getEstInfF().getBotonesAnadir().getFirst());
			break;
		case "Gaseosa":
			asignarActionListeners(vf.getVp().getPpr().getEstSuG().getBotonesAnadir().getFirst());
			asignarActionListeners(vf.getVp().getPpr().getEstInfG().getBotonesAnadir().getFirst());
			break;
		case "Jugo":
			asignarActionListeners(vf.getVp().getPpr().getEstSuJ().getBotonesAnadir().getFirst());
			asignarActionListeners(vf.getVp().getPpr().getEstInfJ().getBotonesAnadir().getFirst());
			break;
		case "Paquete":
			asignarActionListeners(vf.getVp().getPpr().getEstSuP().getBotonesAnadir().getFirst());
			asignarActionListeners(vf.getVp().getPpr().getEstInfP().getBotonesAnadir().getFirst());
			break;
		}
	}

	/**
	 * Agrega un producto al carrito seleccionado.
	 * 
	 * @param nombreProducto Nombre del producto a agregar.
	 * @param nombreCarrito  Nombre del carrito al que se agregará el producto.
	 */
	private void agregarProductoAlCarrito(String nombreProducto, String nombreCarrito) {
		if (nombreUsuarioActual != null) {
			Carrito c = mf.getCarritoDAO().find(new Carrito(nombreCarrito, nombreUsuarioActual));
			boolean agregado = mf.getCarritoDAO().agregarNombreProductoACarrito(nombreUsuarioActual, nombreCarrito,
					nombreProducto);
			if (agregado) {
				JOptionPane.showMessageDialog(null, "Producto agregado al carrito: " + nombreProducto);
			}
		}
	}

	/**
	 * Elimina un producto del carrito seleccionado.
	 */
	private void eliminarProductoDelCarrito() {
		if (nombreUsuarioActual == null || nombreCarritoActual == null) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un carrito primero.");
			return;
		}
		String input = JOptionPane.showInputDialog(null, "Ingrese la posición del producto a eliminar (1, 2, 3, ...):",
				"Eliminar Producto", JOptionPane.QUESTION_MESSAGE);
		if (input == null) {
			return;
		}
		try {
			int posicion = Integer.parseInt(input) - 1;
			CarritoDAO carritoDAO = mf.getCarritoDAO();
			Carrito carrito = carritoDAO.find(new Carrito(nombreCarritoActual, nombreUsuarioActual));
			if (carrito == null) {
				JOptionPane.showMessageDialog(null, "Carrito no encontrado.");
				return;
			}
			LinkedList<String> listaProductos = carrito.getListaNombresProductos();
			Node<String> nodoAnterior = encontrarNodoEnPosicion(listaProductos.getFirst(), null, 0, posicion);
			if (nodoAnterior == null && posicion == 0) {
				listaProductos.setFirst(listaProductos.getFirst().getNext());
			} else if (nodoAnterior != null) {
				Node<String> nodoAEliminar = nodoAnterior.getNext();
				nodoAnterior.setNext(nodoAEliminar.getNext());
			}
			CarritoDTO carritoDTO = DataMapper.carritoToCarritoDTO(carrito);
			carritoDAO.update(carritoDTO, carritoDTO);
			vf.getVp().getPc().cargarProductosDelCarrito();
			JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
		}
	}

	/**
	 * Encuentra el nodo en una posición específica de una lista enlazada.
	 * 
	 * @param nodoActual       Nodo actual de la lista.
	 * @param nodoAnterior     Nodo anterior al actual.
	 * @param posicionActual   Posición actual en la lista.
	 * @param posicionObjetivo Posición objetivo en la lista.
	 * @return Nodo anterior al nodo en la posición objetivo.
	 */
	private Node<String> encontrarNodoEnPosicion(Node<String> nodoActual, Node<String> nodoAnterior, int posicionActual,
			int posicionObjetivo) {
		if (nodoActual == null || posicionActual == posicionObjetivo) {
			return nodoAnterior;
		}
		return encontrarNodoEnPosicion(nodoActual.getNext(), nodoActual, posicionActual + 1, posicionObjetivo);
	}

	/**
	 * Copia los productos de un carrito a otro de forma recursiva.
	 * 
	 * @param nodoOrigen   Nodo actual de la lista de productos origen.
	 * @param listaDestino Lista de productos destino.
	 */
	private void copiarProductosRecursivo(Node<String> nodoOrigen, LinkedList<String> listaDestino) {
		if (nodoOrigen != null) {
			listaDestino.addLastR(nodoOrigen.getInfo());
			copiarProductosRecursivo(nodoOrigen.getNext(), listaDestino);
		}
	}
	
	/**
	 * Copia solo los nombres de productos DISPONIBLES a la lista destino.
	 * La disponibilidad se valida contra PanelCarrito (productosAleatorios).
	 */
	private void copiarSoloDisponiblesRecursivo(Node<String> nodoOrigen, LinkedList<String> listaDestino) {
	    if (nodoOrigen == null) return;

	    String nombre = nodoOrigen.getInfo();
	    boolean disponible = false;

	    // Protección por si productosAleatorios no está seteado aún:
	    try {
	        disponible = vf.getVp().getPc().estaEnProductosAleatorios(nombre);
	    } catch (Exception ignore) {
	        disponible = false; // si no podemos verificar, lo consideramos NO disponible
	    }

	    if (disponible) {
	        listaDestino.addLastR(nombre);
	    }

	    copiarSoloDisponiblesRecursivo(nodoOrigen.getNext(), listaDestino);
	}

}
