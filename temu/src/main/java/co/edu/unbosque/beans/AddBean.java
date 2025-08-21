package co.edu.unbosque.beans;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import co.edu.unbosque.model.AnimalDTO;
import co.edu.unbosque.model.AudifonoDTO;
import co.edu.unbosque.model.ColegioDTO;
import co.edu.unbosque.model.EducativoDTO;
import co.edu.unbosque.model.HombreDTO;
import co.edu.unbosque.model.JuegoMesaDTO;
import co.edu.unbosque.model.LabialDTO;
import co.edu.unbosque.model.MovilDTO;
import co.edu.unbosque.model.MujerDTO;
import co.edu.unbosque.model.OficinaDTO;
import co.edu.unbosque.model.PeliculaDTO;
import co.edu.unbosque.model.PestaninaDTO;
import co.edu.unbosque.service.AddService;
import co.edu.unbosque.util.exception.ExceptionCheker;

/**
 * Bean para gestionar la adición de nuevos productos al sistema. Permite
 * seleccionar el tipo de producto, subir imágenes y guardar la información.
 */
@Named("addBean")
@ViewScoped
public class AddBean implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Servicio para agregar productos. */
	@Inject
	private AddService aService;

	/** Bean de la página principal. */
	@Inject
	private PrincipalBean paginaprincipalbean;

	private String producto; // DispositivoElectronico, Maquillaje, Juguete, Peluche, Papeleria, Ropa
	private String tipo;
	private String subtipo;
	private String subsubtipo;
	private String nombre;
	private int precio;

	private List<String> productos;
	private List<String> tipos;
	private List<String> subtipos;
	private List<String> subsubtipos;

	private String imageBase64;
	private UploadedFile originalImageFile;
	private StreamedContent image;

	private String searchText;

	/**
	 * Constructor por defecto. Inicializa las listas de productos, tipos, subtipos
	 * y subsubtipos.
	 */
	public AddBean() {
		productos = new ArrayList<>();
		productos.add("DispositivoElectronico");
		productos.add("Maquillaje");
		productos.add("Juguete");
		productos.add("Peluche");
		productos.add("Papeleria");
		productos.add("Ropa");

		tipos = new ArrayList<>();
		subtipos = new ArrayList<>();
		subsubtipos = new ArrayList<>();
	}

	/**
	 * Maneja la subida de una imagen y la convierte a Base64.
	 * 
	 * @param event Evento de subida de archivo.
	 */
	public void handleFileUpload(FileUploadEvent event) {
		try {
			byte[] fileContent = event.getFile().getContent();
			this.imageBase64 = "data:" + event.getFile().getContentType() + ";base64,"
					+ Base64.getEncoder().encodeToString(fileContent);
			this.originalImageFile = event.getFile();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo cargar la imagen"));
		}
	}

	/**
	 * Actualiza la lista de subtipos según el producto seleccionado.
	 */
	public void onProductoChange() {
		subtipos.clear();
		tipos.clear();
		subsubtipos.clear();
		subtipo = null;
		tipo = null;
		subsubtipo = null;
		switch (producto == null ? "" : producto) {
		case "DispositivoElectronico":
			subtipos.add("Audifono");
			subtipos.add("Movil");
			tipos.add("MODELO: 5464");
			tipos.add("MODELO: 54655");
			tipos.add("MODELO: 5FS655");
			tipos.add("MODELO: 54SEF55");
			break;
		case "Maquillaje":
			subtipos.add("Labial");
			subtipos.add("Pestanina");
			tipos.add("ES aprueba de agua SI");
			tipos.add("ES aprueba de agua NO");
			break;
		case "Juguete":
			subtipos.add("JuegoMesa");
			subtipos.add("Educativo");
			tipos.add("EDAD: +5");
			tipos.add("EDAD: +8");
			tipos.add("EDAD: +12");
			tipos.add("EDAD: +18");
			break;
		case "Peluche":
			subtipos.add("Pelicula");
			subtipos.add("Animal");
			tipos.add("TAMANO: PEQUEÑO");
			tipos.add("TAMANO: MEDIANO");
			tipos.add("TAMANO: GRANDE");
			break;
		case "Papeleria":
			subtipos.add("Colegio");
			subtipos.add("Oficina");
			tipos.add("CANTIDAD: 5 UND");
			tipos.add("CANTIDAD: 10 UND");
			tipos.add("CANTIDAD: 15 UND");
			tipos.add("CANTIDAD: 20 UND");
			break;
		case "Ropa":
			subtipos.add("Hombre");
			subtipos.add("Mujer");
			tipos.add("TALLA L - COLOR BLANCO");
			tipos.add("TALLA M - COLOR AZUL");
			tipos.add("TALLA S - ROJO");
			break;
		default:
			break;
		}
	}

	/**
	 * Actualiza la lista de subsubtipos según el subtipo seleccionado.
	 */
	public void onSubtipoChange() {
		subsubtipos.clear();
		subsubtipo = null;
		switch (subtipo == null ? "" : subtipo) {
		case "Audifono":
			subsubtipos.add("Con cable");
			subsubtipos.add("Bluetooth");
			break;
		case "Movil":
			subsubtipos.add("64 GB");
			subsubtipos.add("128 GB");
			subsubtipos.add("256 GB");
			break;
		case "Labial":
			subsubtipos.add("Rojo");
			subsubtipos.add("Morado");
			subsubtipos.add("Rosa");
			break;
		case "Pestanina":
			subsubtipos.add("Duración 6 meses");
			subsubtipos.add("Duración 12 meses");
			subsubtipos.add("Duración 1 mes");
			break;
		case "JuegoMesa":
			subsubtipos.add("4 personas");
			subsubtipos.add("8 personas");
			subsubtipos.add("2 personas");
			break;
		case "Educativo":
			subsubtipos.add("Es didáctico SI");
			subsubtipos.add("Es didáctico NO");
			break;
		case "Pelicula":
			subsubtipos.add("PERSONAJE PELICULA AVATAR");
			subsubtipos.add("PERSONAJE PELICULA UP");
			subsubtipos.add("PERSONAJE PELICULA STRANGER");
			break;
		case "Animal":
			subsubtipos.add("PERRO");
			subsubtipos.add("GATO");
			subsubtipos.add("MONO");
			subsubtipos.add("PEZ");
			subsubtipos.add("OSO");
			break;
		case "Colegio":
			subsubtipos.add("ES seguro SI");
			subsubtipos.add("ES seguro NO");
			break;
		case "Oficina":
			subsubtipos.add("Es decorativo SI");
			subsubtipos.add("Es decorativo NO");
			break;
		case "Hombre":
			subsubtipos.add("Es deportiva SI");
			subsubtipos.add("Es deportiva NO");
			break;
		case "Mujer":
			subsubtipos.add("Es conjunto SI");
			subsubtipos.add("Es conjunto NO");
			break;
		default:
			break;
		}
	}

	/**
	 * Guarda el producto y navega a la página de compra.
	 * 
	 * @return Ruta de redirección.
	 */
	public String guardarYNavegar() {
		try {
			if (producto == null || subtipo == null || nombre == null || nombre.isBlank()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Validación", "Completa producto, subtipo y nombre"));
				return null;
			}
			ExceptionCheker.checkerNegativeNumber(precio);
			switch (producto) {
			case "DispositivoElectronico":
				switch (subtipo) {
				case "Audifono": {
					AudifonoDTO audifono = new AudifonoDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					aService.crearAu(audifono);
					break;
				}
				case "Movil": {
					MovilDTO movil = new MovilDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					aService.crearMo(movil);
					break;
				}
				}
				break;
			case "Maquillaje":
				switch (subtipo) {
				case "Labial": {
					LabialDTO labial = new LabialDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					aService.crearLa(labial);
					break;
				}
				case "Pestanina": {
					PestaninaDTO pestanina = new PestaninaDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					aService.crearPes(pestanina);
					break;
				}
				}
				break;
			case "Juguete":
				switch (subtipo) {
				case "JuegoMesa": {
					JuegoMesaDTO juegoMesa = new JuegoMesaDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					aService.crearJue(juegoMesa);
					break;
				}
				case "Educativo": {
					EducativoDTO educativo = new EducativoDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					break;
				}
				}
				break;
			case "Papeleria":
				switch (subtipo) {
				case "Colegio": {
					ColegioDTO colegio = new ColegioDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					aService.crearCo(colegio);
					break;
				}
				case "Oficina": {
					OficinaDTO oficina = new OficinaDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					aService.crearOfi(oficina);
					break;
				}
				}
				break;
			case "Ropa":
				switch (subtipo) {
				case "Hombre": {
					HombreDTO hombre = new HombreDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					aService.crearHom(hombre);
					break;
				}
				case "Mujer": {
					MujerDTO mujer = new MujerDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					aService.crearMuj(mujer);
					break;
				}
				}
				break;
			case "Peluche":
				switch (subtipo) {
				case "Pelicula": {
					PeliculaDTO pelicula = new PeliculaDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					aService.crearPel(pelicula);
					break;
				}
				case "Animal": {
					AnimalDTO animal = new AnimalDTO(nombre, precio, imageBase64, tipo, subsubtipo);
					aService.crearAni(animal);
					break;
				}
				}
				break;
			default:
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención", "Tipo de producto no soportado"));
				return null;
			}
			if (paginaprincipalbean != null) {
				paginaprincipalbean.cargarProductos();
			}
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Producto agregado"));
			return "comprar?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
			return null;
		}
	}

	// Getters y Setters
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	public String getSubsubtipo() {
		return subsubtipo;
	}

	public void setSubsubtipo(String subsubtipo) {
		this.subsubtipo = subsubtipo;
	}

	public List<String> getProductos() {
		return productos;
	}

	public void setProductos(List<String> productos) {
		this.productos = productos;
	}

	public List<String> getTipos() {
		return tipos;
	}

	public void setTipos(List<String> tipos) {
		this.tipos = tipos;
	}

	public List<String> getSubtipos() {
		return subtipos;
	}

	public void setSubtipos(List<String> subtipos) {
		this.subtipos = subtipos;
	}

	public List<String> getSubsubtipos() {
		return subsubtipos;
	}

	public void setSubsubtipos(List<String> subsubtipos) {
		this.subsubtipos = subsubtipos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public UploadedFile getOriginalImageFile() {
		return originalImageFile;
	}

	public void setOriginalImageFile(UploadedFile originalImageFile) {
		this.originalImageFile = originalImageFile;
	}

	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}
}
