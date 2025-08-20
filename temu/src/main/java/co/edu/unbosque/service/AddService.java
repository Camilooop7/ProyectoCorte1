package co.edu.unbosque.service;

import java.util.ArrayList;

import co.edu.unbosque.model.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class AddService {

	private ModelFacade mf;

	public AddService() {
	}

	@PostConstruct
	void init() {
		mf = new ModelFacade(); // si luego vuelves ModelFacade un bean, cámbialo a @Inject
	}

	// ====== CREAR ======
	public void crearAu(AudifonoDTO dto) {
		mf.getAudifonoDAO().add(dto);
	}

	public void crearMo(MovilDTO dto) {
		mf.getMovilDAO().add(dto);
	}

	public void crearLa(LabialDTO dto) {
		mf.getLabialDAO().add(dto);
	}

	public void crearPes(PestaninaDTO dto) {
		mf.getPestaninaDAO().add(dto);
	}

	public void crearJue(JuegoMesaDTO dto) {
		mf.getJuegoMesaDAO().add(dto);
	}

	public void crearCo(ColegioDTO dto) {
		mf.getColegioDAO().add(dto);
	}

	public void crearOfi(OficinaDTO dto) {
		mf.getOficinaDAO().add(dto);
	}

	public void crearHom(HombreDTO dto) {
		mf.getHombreDAO().add(dto);
	}

	public void crearMuj(MujerDTO dto) {
		mf.getMujerDAO().add(dto);
	}

	// ====== LISTAR (LECTURA) ======
	public ArrayList<Movil> listarMoviles() {
		return mf.getMovilDAO().getListaMovil();
	}

	public ArrayList<Labial> listarLabiales() {
		return mf.getLabialDAO().getListaLabial();
	}

	public ArrayList<Pestanina> listarPestaninas() {
		return mf.getPestaninaDAO().getListaPestanina();
	}

	public ArrayList<JuegoMesa> listarJuegoMesa() {
		return mf.getJuegoMesaDAO().getListaJuegoMesa();
	}

	public ArrayList<Colegio> listarColegios() {
		return mf.getColegioDAO().getListaColegio();
	}

	public ArrayList<Oficina> listarOficinas() {
		return mf.getOficinaDAO().getListaOficina();
	}

	public ArrayList<Hombre> listarHombres() {
		return mf.getHombreDAO().getListaHombre();
	}

	public ArrayList<Mujer> listarMujeres() {
		return mf.getMujerDAO().getListaMujer();
	}

	public ArrayList<Audifono> listarAudifonos() {
		return mf.getAudifonoDAO().getListaAudifono();
	}

	public ModelFacade getModelFacade() {
		return mf;
	}
}
