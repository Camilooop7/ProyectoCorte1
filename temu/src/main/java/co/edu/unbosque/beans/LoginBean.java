package co.edu.unbosque.beans;


import co.edu.unbosque.model.Persona;
import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.service.LoginService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
//indica que es un bean y nos da un alias
@Named(value = "loginbean")
//indica los ciclos de vida del bean
@RequestScoped
public class LoginBean {
	private String correo;
	private String contrasena;
	private String nombre;
	private String correoC;
	private String contrasenaC;
	private String confiContrasenaC;
	private LoginService loginService;
	public LoginBean() {
		// TODO Auto-generated constructor stub
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreoC() {
		return correoC;
	}

	public void setCorreoC(String correoC) {
		this.correoC = correoC;
	}

	public String getContrasenaC() {
		return contrasenaC;
	}

	public void setContrasenaC(String contrasenaC) {
		this.contrasenaC = contrasenaC;
	}

	public String getConfiContrasenaC() {
		return confiContrasenaC;
	}

	public void setConfiContrasenaC(String confiContrasenaC) {
		this.confiContrasenaC = confiContrasenaC;
	}
	public void mostrarIniciar() {
		System.out.println("correo: " + correo);
		System.out.println("contrasena: " + contrasena);
	}
	public void mostrarCrear() {
		System.out.println("nombre: " + nombre);
		System.out.println("correo: " + correoC);
		System.out.println("contrasena: " + contrasenaC);
		System.out.println("contrasena confi: " + confiContrasenaC);
	}
	public void Crear() {

		PersonaDTO nuevo = new PersonaDTO(nombre, contrasenaC, correo, "", "", "", 0, 0, null,null, null);
		loginService.crear(nuevo);
	}
	public void iniciar() {
		Persona encontrar = new Persona(nombre, contrasena, correo);
		loginService.encontrar(encontrar);
	
	}
	
}
