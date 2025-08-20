package co.edu.unbosque.util.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.util.structure.Node;
import co.edu.unbosque.view.PanelCarrito;

/**
 * Clase utilitaria para generar facturas en formato PDF. Permite crear y
 * guardar una factura con los detalles de compra de un usuario y su carrito.
 */
public class GenerarPDF {

	/**
	 * Genera una factura en formato PDF para un usuario y carrito específicos.
	 * Muestra un diálogo para seleccionar la ubicación donde guardar el archivo
	 * PDF.
	 *
	 * @param usuario      El usuario asociado a la factura.
	 * @param carrito      El carrito de compras del usuario.
	 * @param panelCarrito El panel que contiene la lógica para buscar y obtener
	 *                     información de los productos.
	 */
	public static void generarFactura(Usuario usuario, Carrito carrito, PanelCarrito panelCarrito) {
		Document document = new Document();
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Guardar Factura");
			fileChooser.setSelectedFile(new File(usuario.getNombre() + "_" + carrito.getNombre() + ".pdf"));
			int userSelection = fileChooser.showSaveDialog(null);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				// Asegurarse de que el archivo tenga extensión .pdf
				if (!fileToSave.getName().toLowerCase().endsWith(".pdf")) {
					fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
				}

				PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
				document.open();

				Paragraph titulo = new Paragraph("Factura de Compra",
						FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD));
				titulo.setAlignment(Element.ALIGN_CENTER);
				document.add(titulo);
				document.add(new Paragraph(" "));

				Paragraph empresaInfo = new Paragraph("Tienda: DONDE EL CHATO",
						FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
				empresaInfo.setAlignment(Element.ALIGN_LEFT);
				document.add(empresaInfo);

				Paragraph nitInfo = new Paragraph("NIT: 123456789-0", FontFactory.getFont(FontFactory.HELVETICA, 12));
				nitInfo.setAlignment(Element.ALIGN_LEFT);
				document.add(nitInfo);

				Paragraph actividadInfo = new Paragraph("Actividad Económica: 44771",
						FontFactory.getFont(FontFactory.HELVETICA, 12));
				actividadInfo.setAlignment(Element.ALIGN_LEFT);
				document.add(actividadInfo);

				Paragraph direccionInfo = new Paragraph("Dirección: Ak 9 # 131a 2",
						FontFactory.getFont(FontFactory.HELVETICA, 12));
				direccionInfo.setAlignment(Element.ALIGN_LEFT);
				document.add(direccionInfo);

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String fechaActual = sdf.format(new Date());
				Paragraph fechaInfo = new Paragraph("Fecha: " + fechaActual,
						FontFactory.getFont(FontFactory.HELVETICA, 12));
				fechaInfo.setAlignment(Element.ALIGN_LEFT);
				document.add(fechaInfo);

				Paragraph vendedor = new Paragraph("Vendedor: Juan Diego Alvira",
						FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
				vendedor.setAlignment(Element.ALIGN_LEFT);
				document.add(vendedor);
				document.add(new Paragraph(" "));

				Paragraph cliente = new Paragraph("Cliente", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
				cliente.setAlignment(Element.ALIGN_LEFT);
				document.add(cliente);

				Paragraph usuarioInfo = new Paragraph("Usuario: " + usuario.getNombre(),
						FontFactory.getFont(FontFactory.HELVETICA, 12));
				document.add(usuarioInfo);

				document.add(new Paragraph("Carrito: " + carrito.getNombre(),
						FontFactory.getFont(FontFactory.HELVETICA, 12)));
				document.add(new Paragraph(" "));

				PdfPTable table = new PdfPTable(2); // Solo 2 columnas: Producto y Precio
				table.setWidthPercentage(100);
				table.addCell("Producto");
				table.addCell("Precio");

				double total = 0.0;
				Node<String> nodoProducto = carrito.getListaNombresProductos().getFirst();

				while (nodoProducto != null) {
					String nombreProducto = nodoProducto.getInfo();
					if (panelCarrito.estaEnProductosAleatorios(nombreProducto)) {
						Object producto = panelCarrito.buscarProductoPorNombre(nombreProducto);
						if (producto != null) {
							String nombre = panelCarrito.obtenerNombreProducto(producto);
							double precio = panelCarrito.obtenerPrecioProducto(producto);
							table.addCell(nombre);
							table.addCell("$" + String.format("%.2f", precio));
							total += precio;
						}
					}
					nodoProducto = nodoProducto.getNext();
				}

				document.add(table);
				document.add(new Paragraph(" "));

				Paragraph totalParagraph = new Paragraph("Total: $" + String.format("%.2f", total),
						FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.BOLD));
				totalParagraph.setAlignment(Element.ALIGN_RIGHT);
				document.add(totalParagraph);

				document.close();
				JOptionPane.showMessageDialog(null,
						"Factura guardada correctamente en: " + fileToSave.getAbsolutePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al generar la factura.");
		}
	}
}
