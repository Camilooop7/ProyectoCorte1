package co.edu.unbosque.beans;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import co.edu.unbosque.model.AudifonoDTO;
import co.edu.unbosque.model.ColegioDTO;
import co.edu.unbosque.model.EducativoDTO;
import co.edu.unbosque.model.HombreDTO;
import co.edu.unbosque.model.JuegoMesaDTO;
import co.edu.unbosque.model.LabialDTO;
import co.edu.unbosque.model.MovilDTO;
import co.edu.unbosque.model.MujerDTO;
import co.edu.unbosque.model.OficinaDTO;
import co.edu.unbosque.model.PestaninaDTO;
import co.edu.unbosque.service.AddService;



@Named("addBean") // <--- ahora CDI, accesible en las páginas como #{addBean}
@SessionScoped // <--- versión moderna de JSF ViewScoped
public class AddBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String producto;
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

	// === Campos para la imagen ===
	private UploadedFile originalImageFile;
	private StreamedContent image; // imagen original
	private AddService aService;
	

	// ====== Constructor ======
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
		
		aService = new AddService();
	}
	

	// ========== MANEJAR UPLOAD ==========
	public void handleFileUpload(FileUploadEvent event) {
        try {
            byte[] fileContent = event.getFile().getContent();

            // Convertir a Base64
            this.imageBase64 = "data:" + event.getFile().getContentType() + ";base64," +
                               Base64.getEncoder().encodeToString(fileContent);

            System.out.println("Imagen cargada como Base64");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



	public void onProductoChange() {
		subtipos.clear();
		tipos.clear();
		subsubtipos.clear();
		subtipo = null;
		tipo = null;
		subsubtipo = null;

		if ("DispositivoElectronico".equals(producto)) {
			subtipos.add("Audifono");
			subtipos.add("Movil");
			tipos.add("MODELO: 5464");
			tipos.add("MODELO: 54655");
			tipos.add("MODELO: 5FS655");
			tipos.add("MODELO: 54SEF55");
		} else if ("Maquillaje".equals(producto)) {
			subtipos.add("Labial");
			subtipos.add("Pestanina");
			tipos.add("ES aprueba de agua SI");
			tipos.add("ES aprueba de agua NO");
		} else if ("Juguete".equals(producto)) {
			subtipos.add("JuegoMesa");
			subtipos.add("Educativo");
			tipos.add("EDAD: +5");
			tipos.add("EDAD: +8");
			tipos.add("EDAD: +12");
			tipos.add("EDAD: +18");
		} else if ("Peluche".equals(producto)) {
			subtipos.add("Pelicula");
			subtipos.add("Animal");
			tipos.add("TAMANO: PEQUEÑO");
			tipos.add("TAMANO: MEDIANO");
			tipos.add("TAMANO: GRANDE");
		} else if ("Papeleria".equals(producto)) {
			subtipos.add("Colegio");
			subtipos.add("Oficina");
			tipos.add("CANTIDAD: 5 UND");
			tipos.add("CANTIDAD: 10 UND");
			tipos.add("CANTIDAD: 15 UND");
			tipos.add("CANTIDAD: 20 UND");
		} else if ("Ropa".equals(producto)) {
			subtipos.add("Hombre");
			subtipos.add("Mujer");
			tipos.add("TALLA L - COLOR BLANCO");
			tipos.add("TALLA M - COLOR AZUL");
			tipos.add("TALLA S - ROJO");
		}
	}

	public void onSubtipoChange() {
		subsubtipos.clear();
		subsubtipo = null;

		if ("Audifono".equals(subtipo)) {
			subsubtipos.add("Con cable");
			subsubtipos.add("Bluetooth");
		} else if ("Movil".equals(subtipo)) {
			subsubtipos.add("64 GB");
			subsubtipos.add("128 GB");
			subsubtipos.add("256 GB");
		} else if ("Labial".equals(subtipo)) {
			subsubtipos.add("Rojo");
			subsubtipos.add("Morado");
			subsubtipos.add("Rosa");
		} else if ("Pestanina".equals(subtipo)) {
			subsubtipos.add("Duración 6 meses");
			subsubtipos.add("Duración 12 meses");
			subsubtipos.add("Duración 1 mes");
		} else if ("JuegoMesa".equals(subtipo)) {
			subsubtipos.add("4 personas");
			subsubtipos.add("8 personas");
			subsubtipos.add("2 personas");
		} else if ("Educativo".equals(subtipo)) {
			subsubtipos.add("Es didáctico SI");
			subsubtipos.add("Es didáctico NO");
		} else if ("Pelicula".equals(subtipo)) {
			subsubtipos.add("PERSONAJE PELICULA AVATAR");
			subsubtipos.add("PERSONAJE PELICULA UP");
			subsubtipos.add("PERSONAJE PELICULA STRANGER");
		} else if ("Animal".equals(subtipo)) {
			subsubtipos.add("PERRO");
			subsubtipos.add("GATO");
			subsubtipos.add("MONO");
			subsubtipos.add("PEZ");
			subsubtipos.add("OSO");
		} else if ("Colegio".equals(subtipo)) {
			subsubtipos.add("ES seguro SI");
			subsubtipos.add("ES seguro NO");
		} else if ("Oficina".equals(subtipo)) {
			subsubtipos.add("Es decorativo SI");
			subsubtipos.add("Es decorativo NO");
		} else if ("Hombre".equals(subtipo)) {
			subsubtipos.add("Es deportiva SI");
			subsubtipos.add("Es deportiva NO");
		} else if ("Mujer".equals(subtipo)) {
			subsubtipos.add("Es conjunto SI");
			subsubtipos.add("Es conjunto NO");
		}
	}

	// Guardar datos
	public void guardar() {
		System.out.println("Producto: " + producto);
		System.out.println("Tipo: " + tipo);
		System.out.println("Subtipo: " + subtipo);
		System.out.println("Sub-subtipo: " + subsubtipo);
		System.out.println("Nombre: " + nombre);
		System.out.println("Precio: " + precio);
	}
	
	public void guardarPro() {
        try {
            switch (producto) {
                case "DispositivoElectronico":
                    switch (subtipo) {
                        case "Audifono":
                            AudifonoDTO audifono = new AudifonoDTO("Jeisson", 88, "ok" , "imbecil","dsads");
                            aService.crearAu(audifono);
                            break;
                        case "Movil":
                            MovilDTO movil = new MovilDTO(nombre, precio,imageBase64 ,tipo, subsubtipo);
                            aService.crearMo(movil);
                            break;
                    }
                    break;
                case "Maquillaje":
                    switch (subtipo) {
                        case "Labial":
                            LabialDTO labial = new LabialDTO(nombre, precio, imageBase64,tipo, subsubtipo);
                            aService.crearLa(labial);
                            break;
                        case "Pestanina":
                            PestaninaDTO pestanina = new PestaninaDTO(nombre, precio,imageBase64, tipo, subsubtipo);
                            aService.crearPes(pestanina);
                            break;
                    }
                    break;
                case "Juguete":
                    switch (subtipo) {
                        case "JuegoMesa":
                            JuegoMesaDTO juegoMesa = new JuegoMesaDTO(nombre, precio,imageBase64, tipo, subsubtipo);
                            aService.crearJue(juegoMesa);
                            break;
                        case "Educativo":
                            EducativoDTO educativo = new EducativoDTO(nombre, precio,imageBase64, tipo, subsubtipo);
                            // Asegúrate de que el servicio tenga un método para EducativoDTO
                            break;
                    }
                    break;
                case "Papeleria":
                    switch (subtipo) {
                        case "Colegio":
                            ColegioDTO colegio = new ColegioDTO(nombre, precio,imageBase64, tipo, subsubtipo);
                            aService.crearCo(colegio);
                            break;
                        case "Oficina":
                            OficinaDTO oficina = new OficinaDTO(nombre, precio,imageBase64, tipo, subsubtipo);
                            aService.crearOfi(oficina);
                            break;
                    }
                    break;
                case "Ropa":
                    switch (subtipo) {
                        case "Hombre":
                            HombreDTO hombre = new HombreDTO(nombre, precio,imageBase64,tipo, subsubtipo);
                            aService.crearHom(hombre);
                            break;
                        case "Mujer":
                            MujerDTO mujer = new MujerDTO(nombre, precio,imageBase64, tipo, subsubtipo);
                            aService.crearMuj(mujer);
                            break;
                    }
                    break;
                default:
                    System.out.println("Tipo de producto no soportado.");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	// ====== GETTERS y SETTERS ======
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

	
	private String searchText;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
