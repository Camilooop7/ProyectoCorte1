package co.edu.unbosque.util.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.util.structure.LinkedList;
import co.edu.unbosque.util.structure.Node;
import co.edu.unbosque.view.PanelCarrito;

public class GenerarPDF {
    /**
     * Genera un PDF de factura para un usuario y su carrito de compras.
     *
     * @param usuario El usuario que realiza la compra.
     * @param carrito El carrito con los nombres de los productos a facturar.
     * @param panelCarrito Instancia de PanelCarrito para buscar los productos por nombre.
     */
    public static void generarFactura(Usuario usuario, Carrito carrito, PanelCarrito panelCarrito) {
        if (usuario == null || carrito == null || panelCarrito == null) {
            JOptionPane.showMessageDialog(null, "Datos incompletos para generar la factura.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Factura PDF");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "No se seleccionó una ubicación.");
            return;
        }

        File carpeta = fileChooser.getSelectedFile();
        String filePath = carpeta.getAbsolutePath() + "/Factura_" + usuario.getNombre() + ".pdf";
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Título
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            document.add(new Paragraph("FACTURA DE VENTA", titleFont));
            document.add(Chunk.NEWLINE);

            // Datos del chat/empresa
            document.add(new Paragraph("Nombre del Chat: DONDE EL CHATO", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            document.add(new Paragraph("NIT: 123456789-0"));
            document.add(new Paragraph("Actividad Económica: 44771"));
            document.add(new Paragraph("Dirección: Ak 9 # 131a 2"));
            document.add(new Paragraph("Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            document.add(Chunk.NEWLINE);

            // Datos del cliente
            document.add(new Paragraph("CLIENTE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            document.add(new Paragraph("Nombre: " + usuario.getNombre()));
            document.add(new Paragraph("Identificación: " + usuario.getIdentificacion()));
            document.add(Chunk.NEWLINE);

            // Lista de productos
            document.add(new Paragraph("LISTA DE PRODUCTOS", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));

            LinkedList<String> listaNombresProductos = carrito.getListaNombresProductos();
            if (listaNombresProductos == null || listaNombresProductos.isEmpty()) {
                document.add(new Paragraph("No hay productos en el carrito."));
            } else {
                Node<String> nodoProducto = listaNombresProductos.getFirst();
                double total = 0;

                while (nodoProducto != null) {
                    String nombreProducto = nodoProducto.getInfo();
                    Object producto = panelCarrito.buscarProductoPorNombre(nombreProducto);

                    if (producto != null) {
                        String nombre = panelCarrito.obtenerNombreProducto(producto);
                        double precio = panelCarrito.obtenerPrecioProducto(producto);

                        // Agregar nombre y precio al PDF
                        document.add(new Paragraph(
                            "- " + nombre +
                            " | Precio: $" + precio
                        ));

                        total += precio;
                    } else {
                        System.out.println("Producto no encontrado: " + nombreProducto);
                    }

                    nodoProducto = nodoProducto.getNext();
                }

                document.add(Chunk.NEWLINE);
                document.add(new Paragraph("TOTAL: $" + total, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            }

            JOptionPane.showMessageDialog(null, "Factura generada: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar la factura: " + e.getMessage());
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }
}
