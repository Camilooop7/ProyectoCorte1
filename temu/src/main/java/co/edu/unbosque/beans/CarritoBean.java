package co.edu.unbosque.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import co.edu.unbosque.service.CarritoService;
import co.edu.unbosque.service.CarritoService.ItemView;
import co.edu.unbosque.util.mail.EnvioCorreo;
import co.edu.unbosque.service.HistorialService;

@Named("carritoBean")
@ViewScoped
public class CarritoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombreCarrito = "CarritoGlobal";
	private transient CarritoService carritoService = new CarritoService();

	private String itemSeleccionado;

	@Inject
	private EnvioCorreo envioCorreo;
	@Inject
	private LoginBean loginbean; // tomamos datos de sesión aquí
	@Inject
	private HistorialService historialService;

	// ======== Items / Total ========
	public List<ItemView> getItems() {
		return carritoService.listarItemsView(nombreCarrito);
	}

	public int getTotal() {
		return carritoService.total(nombreCarrito);
	}

	// ======== Acciones ========
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

	public void vaciar() {
		boolean ok = carritoService.vaciar(nombreCarrito);
		addMsg(ok ? FacesMessage.SEVERITY_INFO : FacesMessage.SEVERITY_ERROR, ok ? "OK" : "Error",
				ok ? "Carrito vaciado." : "No se pudo vaciar.");
	}

	public String pagar() {
		if (getItems().isEmpty()) {
			addMsg(FacesMessage.SEVERITY_WARN, "Aviso", "El carrito está vacío.");
			return null;
		}

		// 1) Correo desde LoginBean (campo 'correo' del login)
		String correo = (loginbean != null) ? loginbean.getCorreo() : null;

		// 2) Fallback: SessionMap -> "correo"
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

		// Dirección opcional desde sesión
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

		// Mensaje flash para la siguiente vista
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("mensajeConfirmacion",
				"¡Compra realizada! Enviamos el detalle a " + correo + ". Dirección de envío: " + direccion + ".");

		// === Registrar en historial ===
		try {
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String fecha = LocalDateTime.now().format(fmt);

			StringBuilder line = new StringBuilder();
			line.append("[").append(fecha).append("] ").append("Compra por $ ").append(getTotal()).append(" | Items: ");

			boolean first = true;
			for (ItemView iv : getItems()) {
				if (!first)
					line.append(", ");
				line.append(escape(iv.getNombre())).append(" ($").append(iv.getPrecio()).append(")");
				first = false;
			}

			if (historialService != null) {
				historialService.agregarEntrada(nombreCarrito, line.toString());
			} else {
				// Fallback si no se inyecta el servicio
				new co.edu.unbosque.service.HistorialService().agregarEntrada(nombreCarrito, line.toString());
			}
		} catch (Exception e) {
			addMsg(FacesMessage.SEVERITY_WARN, "Historial",
					"La compra se realizó, pero no se pudo registrar en el historial.");
		}

		// vaciar carrito y navegar
		carritoService.vaciar(nombreCarrito);
		return "principal.xhtml";
	}

	// ======== Helpers ========
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

	private String escape(String s) {
		if (s == null)
			return "";
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
	}

	private void addMsg(FacesMessage.Severity sev, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(sev, summary, detail));
	}

	// ======== Script de tema expuesto desde el bean ========
	/** Devuelve el JS que aplica el tema leyendo el valor de un input por id */
	public String getApplyThemeScript() {
		return "function applyThemeByInputId(id){" + "var el=document.getElementById(id);" + "if(!el)return;"
				+ "document.documentElement.setAttribute('data-theme',el.value);"
				+ "document.body.setAttribute('data-theme',el.value);" + "}";
	}

	// Getters/Setters
	public String getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(String itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}
}
