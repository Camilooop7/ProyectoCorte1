package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import co.edu.unbosque.model.*;
import co.edu.unbosque.model.persistence.*;
import co.edu.unbosque.util.exception.*;
import co.edu.unbosque.util.structure.*;
import co.edu.unbosque.view.ViewFacade;

public class Controller implements ActionListener {
	private ViewFacade vf;
	private ModelFacade mf;
	private String nombreUsuarioActual;

	public Controller() throws IOException {
		vf = new ViewFacade();
		mf = new ModelFacade();
		FileManager.crearCarpeta();
		asignarLectores();
	}

	public void run() {
		vf.getVp().setVisible(true);
	}

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
	}

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
			//TODO
			if (mf.getUsuarioDAO().find(new Usuario("", identificacion,null)) != null) {
				Usuario u = mf.getUsuarioDAO().find(new Usuario("", identificacion, null));
				nombreUsuarioActual = u.getNombre();
				vf.getVe().mostrar("Ingreso como: " + u.getNombre());
				vf.getVp().getPs().setVisible(false);
				vf.getVp().getPpr().setVisible(true);

				// Crear un carrito para el usuario si no existe
				Carrito carritoUsuario = new Carrito(u.getNombre());
				if (mf.getCarritoDAO().find(carritoUsuario) == null) {
					mf.getCarritoDAO().add(DataMapper.carritoToCarritoDTO(carritoUsuario));
				}

				// Cargar los productos
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
				//TODO
				if (mf.getUsuarioDAO().find(new Usuario("", identificacionCrear,null)) == null) {
					mf.getUsuarioDAO().add(new UsuarioDTO(nombre, identificacionCrear,null));

					// Crear un carrito para el nuevo usuario
					Carrito nuevoCarrito = new Carrito(nombre);
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
			int identificacionC = vf.getVp().getPs().getIdentificacion();
			vf.getVp().getPc().recargarComboBox();
			vf.getVp().getPc().setVisible(true);
			vf.getVp().getPpr().setVisible(false);
			
			mostrarCarrito();
			break;

		case "verHistorial":
			// mostrarHistorial();
			break;

		default:
			// Agregar producto al carrito
			agregarProductoAlCarrito(command);
			break;
		}
	}

	private void ocultarOtrosPaneles(String tipoProducto) {
		// Ocultar todos los paneles de productos
		vf.getVp().getPpr().getEstInfP().setVisible(false);
		vf.getVp().getPpr().getEstSuP().setVisible(false);
		vf.getVp().getPpr().getEstInfF().setVisible(false);
		vf.getVp().getPpr().getEstSuF().setVisible(false);
		vf.getVp().getPpr().getEstInfJ().setVisible(false);
		vf.getVp().getPpr().getEstSuJ().setVisible(false);
		vf.getVp().getPpr().getEstInfG().setVisible(false);
		vf.getVp().getPpr().getEstSuG().setVisible(false);
		vf.getVp().getPpr().getEstInfV().setVisible(false);
		vf.getVp().getPpr().getEstSuV().setVisible(false);

		// Mostrar solo los paneles del tipo de producto seleccionado
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

	private void asignarFuncionesComponentesProducto(String tipoProducto) {
		switch (tipoProducto) {
		case "Verdura":
			asignarActionListeners(vf.getVp().getPpr().getEstSuV().getBotonesAnadir());
			asignarActionListeners(vf.getVp().getPpr().getEstInfV().getBotonesAnadir());
			break;
		case "Fruta":
			asignarActionListeners(vf.getVp().getPpr().getEstSuF().getBotonesAnadir());
			asignarActionListeners(vf.getVp().getPpr().getEstInfF().getBotonesAnadir());
			break;
		case "Gaseosa":
			asignarActionListeners(vf.getVp().getPpr().getEstSuG().getBotonesAnadir());
			asignarActionListeners(vf.getVp().getPpr().getEstInfG().getBotonesAnadir());
			break;
		case "Jugo":
			asignarActionListeners(vf.getVp().getPpr().getEstSuJ().getBotonesAnadir());
			asignarActionListeners(vf.getVp().getPpr().getEstInfJ().getBotonesAnadir());
			break;
		case "Paquete":
			asignarActionListeners(vf.getVp().getPpr().getEstSuP().getBotonesAnadir());
			asignarActionListeners(vf.getVp().getPpr().getEstInfP().getBotonesAnadir());
			break;
		}
	}

	private void asignarActionListeners(LinkedList<JButton> botones) {
		Node<JButton> nodoActual = botones.getFirst();
		while (nodoActual != null) {
			nodoActual.getInfo().addActionListener(this);
			nodoActual = nodoActual.getNext();
		}
	}

	private void agregarProductoAlCarrito(String nombreProducto) {
		if (nombreUsuarioActual != null) {
			if (mf.getCarritoDAO().agregarNombreProductoACarrito(nombreUsuarioActual, nombreProducto)) {
				JOptionPane.showMessageDialog(null, "Producto agregado al carrito: " + nombreProducto);
			} else {
				JOptionPane.showMessageDialog(null, "Error al agregar el producto al carrito.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe iniciar sesión para agregar productos al carrito.");
		}
	}

	private void mostrarCarrito() {
		if (nombreUsuarioActual != null) {
			Carrito carritoUsuario = new Carrito(nombreUsuarioActual);
			Carrito carritoEncontrado = mf.getCarritoDAO().find(carritoUsuario);

			if (carritoEncontrado == null || carritoEncontrado.getListaNombresProductos().isEmpty()) {
				JOptionPane.showMessageDialog(null, "El carrito está vacío.");
			} else {
				StringBuilder mensaje = new StringBuilder("Productos en el carrito:\n");
				double total = 0;

				Node<String> nodoNombreProducto = carritoEncontrado.getListaNombresProductos().getFirst();

				while (nodoNombreProducto != null) {
					String nombreProducto = nodoNombreProducto.getInfo();
					Object productoEncontrado = buscarProductoPorNombreEnNodos(nombreProducto);

					if (productoEncontrado != null) {
						String nombre = "";
						double precio = 0;

						if (productoEncontrado instanceof Fruta) {
							Fruta fruta = (Fruta) productoEncontrado;
							nombre = fruta.getNombre();
							precio = fruta.getPrecio();
						} else if (productoEncontrado instanceof Verdura) {
							Verdura verdura = (Verdura) productoEncontrado;
							nombre = verdura.getNombre();
							precio = verdura.getPrecio();
						} else if (productoEncontrado instanceof Gaseosa) {
							Gaseosa gaseosa = (Gaseosa) productoEncontrado;
							nombre = gaseosa.getNombre();
							precio = gaseosa.getPrecio();
						} else if (productoEncontrado instanceof Jugo) {
							Jugo jugo = (Jugo) productoEncontrado;
							nombre = jugo.getNombre();
							precio = jugo.getPrecio();
						} else if (productoEncontrado instanceof PaquetePapa) {
							PaquetePapa paquete = (PaquetePapa) productoEncontrado;
							nombre = paquete.getNombre();
							precio = paquete.getPrecio();
						}

						mensaje.append("- ").append(nombre).append(" ($").append(precio).append(")\n");
						total += precio;
					}

					nodoNombreProducto = nodoNombreProducto.getNext();
				}

				mensaje.append("\nTotal: $").append(total);
				JOptionPane.showMessageDialog(null, mensaje.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe iniciar sesión para ver el carrito.");
		}
	}

	private Object buscarProductoPorNombreEnNodos(String nombre) {
		// Buscar en frutas
		Object producto = buscarFrutaPorNombreEnNodos(nombre);
		if (producto != null)
			return producto;

		// Buscar en verduras
		producto = buscarVerduraPorNombreEnNodos(nombre);
		if (producto != null)
			return producto;

		// Buscar en gaseosas
		producto = buscarGaseosaPorNombreEnNodos(nombre);
		if (producto != null)
			return producto;

		// Buscar en jugos
		producto = buscarJugoPorNombreEnNodos(nombre);
		if (producto != null)
			return producto;

		// Buscar en paquetes de papa
		producto = buscarPaquetePapaPorNombreEnNodos(nombre);
		if (producto != null)
			return producto;

		return null;
	}

	private Fruta buscarFrutaPorNombreEnNodos(String nombre) {
		LinkedList<Fruta> listaFrutas = mf.getFrutaDAO().getListaFrutas();
		Node<Fruta> nodoActual = listaFrutas.getFirst();
		while (nodoActual != null) {
			if (nodoActual.getInfo().getNombre().equals(nombre)) {
				return nodoActual.getInfo();
			}
			nodoActual = nodoActual.getNext();
		}
		return null;
	}

	private Verdura buscarVerduraPorNombreEnNodos(String nombre) {
		LinkedList<Verdura> listaVerduras = mf.getVerduraDAO().getListaVerduras();
		Node<Verdura> nodoActual = listaVerduras.getFirst();
		while (nodoActual != null) {
			if (nodoActual.getInfo().getNombre().equals(nombre)) {
				return nodoActual.getInfo();
			}
			nodoActual = nodoActual.getNext();
		}
		return null;
	}

	private Gaseosa buscarGaseosaPorNombreEnNodos(String nombre) {
		LinkedList<Gaseosa> listaGaseosas = mf.getGaseosaDAO().getListaGaseosas();
		Node<Gaseosa> nodoActual = listaGaseosas.getFirst();
		while (nodoActual != null) {
			if (nodoActual.getInfo().getNombre().equals(nombre)) {
				return nodoActual.getInfo();
			}
			nodoActual = nodoActual.getNext();
		}
		return null;
	}

	private Jugo buscarJugoPorNombreEnNodos(String nombre) {
		LinkedList<Jugo> listaJugos = mf.getJugoDAO().getListaJugos();
		Node<Jugo> nodoActual = listaJugos.getFirst();
		while (nodoActual != null) {
			if (nodoActual.getInfo().getNombre().equals(nombre)) {
				return nodoActual.getInfo();
			}
			nodoActual = nodoActual.getNext();
		}
		return null;
	}

	private PaquetePapa buscarPaquetePapaPorNombreEnNodos(String nombre) {
		LinkedList<PaquetePapa> listaPaquetesPapa = mf.getPaquetePapaDAO().getListaPaquetePapas();
		Node<PaquetePapa> nodoActual = listaPaquetesPapa.getFirst();
		while (nodoActual != null) {
			if (nodoActual.getInfo().getNombre().equals(nombre)) {
				return nodoActual.getInfo();
			}
			nodoActual = nodoActual.getNext();
		}
		return null;
	}

}
