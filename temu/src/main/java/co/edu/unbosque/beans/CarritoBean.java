package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import co.edu.unbosque.service.CarritoService;
import co.edu.unbosque.service.CarritoService.ItemView;
import co.edu.unbosque.util.mail.EnvioCorreo;

/**
 * Bean para gestionar el carrito de compras. Permite agregar, eliminar y pagar
 * productos en el carrito.
 */
@Named("carritoBean")
@ViewScoped
public class CarritoBean implements Serializable {

	/** Versión para la serialización. */
	private static final long serialVersionUID = 1L;

	/** Nombre del carrito. */
	private String nombreCarrito = "CarritoGlobal";

	/** Servicio de carrito. */
	private transient CarritoService carritoService = new CarritoService();

	/** Ítem seleccionado en el carrito. */
	private String itemSeleccionado;

	/** Bean para enviar correos. */
	@Inject
	private EnvioCorreo envioCorreo;

	/** Bean de login para obtener datos del usuario. */
	@Inject
	private LoginBean loginbean;

	/**
	 * Obtiene los ítems del carrito.
	 * 
	 * @return Lista de ítems en el carrito.
	 */
	public List<ItemView> getItems() {
		return carritoService.listarItemsView(nombreCarrito);
	}

	/**
	 * Obtiene el total del carrito.
	 * 
	 * @return Total del carrito.
	 */
	public int getTotal() {
		return carritoService.total(nombreCarrito);
	}

	/**
	 * Elimina un ítem del carrito.
	 */
	public void eliminarItem() {
		if (itemSeleccionado == null || itemSeleccionado.isBlank()) {
			addMsg(FacesMessage.SEVERITY_WARN, "Aviso", "No se seleccionó ítem.");
			return;
		}
		boolean ok = carritoService.eliminarItemPorToken(nombreCarrito, itemSeleccionado);
		addMsg(ok ? FacesMessage.SEVERITY_INFO : FacesMessage.SEVERITY_ERROR, ok ? "Eliminado" : "Error",
				ok ? "Ítem removido del carrito" : "No se pudo eliminar.");
		itemSeleccionado = null;
	}

	/**
	 * Vacía el carrito.
	 */
	public void vaciar() {
		boolean ok = carritoService.vaciar(nombreCarrito);
		addMsg(ok ? FacesMessage.SEVERITY_INFO : FacesMessage.SEVERITY_ERROR, ok ? "OK" : "Error",
				ok ? "Carrito vaciado." : "No se pudo vaciar.");
	}

	/**
	 * Procesa el pago del carrito y envía un correo de confirmación.
	 * 
	 * @return Ruta de redirección.
	 */
	public String pagar() {
		if (getItems().isEmpty()) {
			addMsg(FacesMessage.SEVERITY_WARN, "Aviso", "El carrito está vacío.");
			return null;
		}
		String correo = (loginbean != null) ? loginbean.getCorreo() : null;
		if (correo == null || correo.isBlank()) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> ses = ec.getSessionMap();
			Object c = ses.get("correo");
			if (c instanceof String s && !s.isBlank())
				correo = s;
		}
		if (correo == null || correo.isBlank()) {
			addMsg(FacesMessage.SEVERITY_ERROR, "Correo", "No se encontró el correo del usuario en sesión.");
			return null;
		}
		String direccion = null;
		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			Object d = ec.getSessionMap().get("direccion");
			if (d instanceof String s && !s.isBlank())
				direccion = s;
		} catch (Exception ignored) {
		}
		if (direccion == null || direccion.isBlank())
			direccion = "No registrada";
		String asunto = "Compra exitosa - TEMU SHOP";
		String content = buildEmailHtml(direccion);
		try {
			envioCorreo.createEmail(correo, asunto, content);
			envioCorreo.sendEmail();
		} catch (RuntimeException ex) {
			addMsg(FacesMessage.SEVERITY_ERROR, "Correo", "No se pudo enviar el correo: " + ex.getMessage());
			return null;
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("mensajeConfirmacion",
				"¡Compra realizada! Enviamos el detalle a " + correo + ". Dirección de envío: " + direccion + ".");
		carritoService.vaciar(nombreCarrito);
		return "principal.xhtml";
	}

	/**
	 * Construye el contenido HTML del correo de confirmación.
	 * 
	 * @param direccionEnvio Dirección de envío.
	 * @return Contenido HTML del correo.
	 */
	private String buildEmailHtml(String direccionEnvio) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div style='font-family:Arial,Helvetica,sans-serif'>").append("<h2>TEMU SHOP - Compra exitosa</h2>")
				.append("<p>Gracias por tu compra. Aquí está el detalle:</p>")
				.append("<table style='border-collapse:collapse;width:100%'>").append("<thead><tr>")
				.append("<th style='text-align:left;border-bottom:1px solid #ddd;padding:8px'>Producto</th>")
				.append("<th style='text-align:right;border-bottom:1px solid #ddd;padding:8px'>Precio</th>")
				.append("</tr></thead><tbody>");
		for (ItemView iv : getItems()) {
			sb.append("<tr>").append("<td style='padding:8px;border-bottom:1px solid #f0f0f0'>")
					.append(escape(iv.getNombre())).append("</td>")
					.append("<td style='padding:8px;border-bottom:1px solid #f0f0f0;text-align:right'>$")
					.append(iv.getPrecio()).append("</td>").append("</tr>");
		}
		sb.append("</tbody>").append("<tfoot><tr>")
				.append("<td style='padding:8px;text-align:right'><strong>Total:</strong></td>")
				.append("<td style='padding:8px;text-align:right'><strong>$ ").append(getTotal())
				.append("</strong></td>").append("</tr></tfoot>").append("</table>")
				.append("<p style='margin-top:16px'><strong>Dirección de envío:</strong> ")
				.append(escape(direccionEnvio)).append("</p>")
				.append("<p>Este es un correo automático, por favor no responder.</p>").append("</div>");
		return sb.toString();
	}

	/**
	 * Escapa caracteres especiales en una cadena.
	 * 
	 * @param s Cadena a escapar.
	 * @return Cadena escapada.
	 */
	private String escape(String s) {
		if (s == null)
			return "";
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
	}

	/**
	 * Agrega un mensaje a FacesContext.
	 * 
	 * @param sev     Severidad del mensaje.
	 * @param summary Resumen del mensaje.
	 * @param detail  Detalle del mensaje.
	 */
	private void addMsg(FacesMessage.Severity sev, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(sev, summary, detail));
	}

	/**
	 * Obtiene el script para aplicar el tema.
	 * 
	 * @return Script para aplicar el tema.
	 */
	public String getApplyThemeScript() {
		return "function applyThemeByInputId(id){" + "var el=document.getElementById(id);" + "if(!el)return;"
				+ "document.documentElement.setAttribute('data-theme',el.value);"
				+ "document.body.setAttribute('data-theme',el.value);" + "}";
	}

	// Getters y Setters
	public String getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(String itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}
}
