package co.edu.unbosque.service;

import java.util.ArrayList;

import co.edu.unbosque.model.*; // Entidades y DTOs
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class AddService {

    private ModelFacade mf;

    public AddService() { }

    @PostConstruct
    void init() {
        // Si luego ModelFacade es un bean, cámbialo a @Inject
        mf = new ModelFacade();
    }

    // =================== ELIMINAR (por nombre) ===================
    public boolean eliminarAudifono(String nombre) {
        if (nombre == null || nombre.isBlank()) return false;
        AudifonoDTO dto = new AudifonoDTO();
        dto.setNombre(nombre);
        return mf.getAudifonoDAO().delete(dto);
    }
    

    public boolean eliminarMovil(String nombre) {
        if (nombre == null || nombre.isBlank()) return false;
        MovilDTO dto = new MovilDTO();
        dto.setNombre(nombre);
        return mf.getMovilDAO().delete(dto);
    }

    public boolean eliminarLabial(String nombre) {
        if (nombre == null || nombre.isBlank()) return false;
        LabialDTO dto = new LabialDTO();
        dto.setNombre(nombre);
        return mf.getLabialDAO().delete(dto);
    }

    public boolean eliminarPestanina(String nombre) {
        if (nombre == null || nombre.isBlank()) return false;
        PestaninaDTO dto = new PestaninaDTO();
        dto.setNombre(nombre);
        return mf.getPestaninaDAO().delete(dto);
    }

    public boolean eliminarJuegoMesa(String nombre) {
        if (nombre == null || nombre.isBlank()) return false;
        JuegoMesaDTO dto = new JuegoMesaDTO();
        dto.setNombre(nombre);
        return mf.getJuegoMesaDAO().delete(dto);
    }

    public boolean eliminarColegio(String nombre) {
        if (nombre == null || nombre.isBlank()) return false;
        ColegioDTO dto = new ColegioDTO();
        dto.setNombre(nombre);
        return mf.getColegioDAO().delete(dto);
    }

    public boolean eliminarOficina(String nombre) {
        if (nombre == null || nombre.isBlank()) return false;
        OficinaDTO dto = new OficinaDTO();
        dto.setNombre(nombre);
        return mf.getOficinaDAO().delete(dto);
    }

    public boolean eliminarHombre(String nombre) {
        if (nombre == null || nombre.isBlank()) return false;
        HombreDTO dto = new HombreDTO();
        dto.setNombre(nombre);
        return mf.getHombreDAO().delete(dto);
    }

    public boolean eliminarMujer(String nombre) {
        if (nombre == null || nombre.isBlank()) return false;
        MujerDTO dto = new MujerDTO();
        dto.setNombre(nombre);
        return mf.getMujerDAO().delete(dto);
    }
    
    public boolean eliminarPelicula(String nombre) {
    	if (nombre == null || nombre.isBlank()) return false;
    	PeliculaDTO dto = new PeliculaDTO();
    	dto.setNombre(nombre);
    	return mf.getPeliculaDAO().delete(dto);
    }
    
    
    public boolean eliminarAnimal(String nombre) {
    	if (nombre == null || nombre.isBlank()) return false;
    	AnimalDTO dto = new AnimalDTO();
    	dto.setNombre(nombre);
    	return mf.getAnimalDAO().delete(dto);
    }
    
    
    
    

    // =================== CREAR ===================
    public void crearAu(AudifonoDTO dto) { mf.getAudifonoDAO().add(dto); }
    public void crearMo(MovilDTO dto)     { mf.getMovilDAO().add(dto); }
    public void crearLa(LabialDTO dto)    { mf.getLabialDAO().add(dto); }
    public void crearPes(PestaninaDTO dto){ mf.getPestaninaDAO().add(dto); }
    public void crearJue(JuegoMesaDTO dto){ mf.getJuegoMesaDAO().add(dto); }
    public void crearCo(ColegioDTO dto)   { mf.getColegioDAO().add(dto); }
    public void crearOfi(OficinaDTO dto)  { mf.getOficinaDAO().add(dto); }
    public void crearHom(HombreDTO dto)   { mf.getHombreDAO().add(dto); }
    public void crearMuj(MujerDTO dto)    { mf.getMujerDAO().add(dto); }
    public void crearPel(PeliculaDTO dto) {
    	mf.getPeliculaDAO().add(dto);
    }
    
    public void crearAni(AnimalDTO dto) {
    	mf.getAnimalDAO().add(dto);
    }
    

    // =================== LISTAR (LECTURA) ===================
    public ArrayList<Movil> listarMoviles()       { return mf.getMovilDAO().getListaMovil(); }
    public ArrayList<Labial> listarLabiales()     { return mf.getLabialDAO().getListaLabial(); }
    public ArrayList<Pestanina> listarPestaninas(){ return mf.getPestaninaDAO().getListaPestanina(); }
    public ArrayList<JuegoMesa> listarJuegoMesa() { return mf.getJuegoMesaDAO().getListaJuegoMesa(); }
    public ArrayList<Colegio> listarColegios()    { return mf.getColegioDAO().getListaColegio(); }
    public ArrayList<Oficina> listarOficinas()    { return mf.getOficinaDAO().getListaOficina(); }
    public ArrayList<Hombre> listarHombres()      { return mf.getHombreDAO().getListaHombre(); }
    public ArrayList<Mujer> listarMujeres()       { return mf.getMujerDAO().getListaMujer(); }
    public ArrayList<Audifono> listarAudifonos()  { return mf.getAudifonoDAO().getListaAudifono(); }
    
    public ArrayList<Pelicula> listarPeliculas()  { return mf.getPeliculaDAO().getListaPelicula(); }
    
    public ArrayList<Animal> listarAnimal()  { return mf.getAnimalDAO().getListaAnimal(); }
    

    public ModelFacade getModelFacade() {
        return mf;
    }
}
