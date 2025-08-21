package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import co.edu.unbosque.model.ModelFacade;

// ENTIDADES para la UI
import co.edu.unbosque.model.Movil;
import co.edu.unbosque.model.Audifono;
import co.edu.unbosque.model.Pelicula;
import co.edu.unbosque.model.Animal;
import co.edu.unbosque.model.Labial;
import co.edu.unbosque.model.Pestanina;
import co.edu.unbosque.model.JuegoMesa;
import co.edu.unbosque.model.Colegio;
import co.edu.unbosque.model.Oficina;
import co.edu.unbosque.model.Hombre;
import co.edu.unbosque.model.Mujer;

// DTOs (capa de persistencia)
import co.edu.unbosque.model.MovilDTO;
import co.edu.unbosque.model.AudifonoDTO;
import co.edu.unbosque.model.PeliculaDTO;
import co.edu.unbosque.model.AnimalDTO;
import co.edu.unbosque.model.LabialDTO;
import co.edu.unbosque.model.PestaninaDTO;
import co.edu.unbosque.model.JuegoMesaDTO;
import co.edu.unbosque.model.ColegioDTO;
import co.edu.unbosque.model.OficinaDTO;
import co.edu.unbosque.model.HombreDTO;
import co.edu.unbosque.model.MujerDTO;

/**
 * Capa de datos: expone “información” a la UI.
 * - Lista entidades ligeras para pintar.
 * - Ejecuta operaciones de datos (eliminar por nombre).
 * No contiene lógica de UI.
 */
@Named("principalService")
@ApplicationScoped
public class PrincipalService {

    private final ModelFacade mf = new ModelFacade();

    private static String nvl(String s) { return s == null ? "" : s; }
    private static int nvi(Number n)    { return n == null ? 0 : n.intValue(); }

    // ===== LISTAR (map DTO -> entidad ligera para la UI) =====
    public List<Movil> listarMoviles() {
        List<Movil> out = new ArrayList<>();
        for (MovilDTO d : mf.getMovilDAO().getAll()) {
            Movil e = new Movil();
            e.setNombre(nvl(d.getNombre()));
            e.setImagen(nvl(d.getImagen()));
            e.setPrecio(nvi(d.getPrecio()));
            out.add(e);
        }
        return out;
    }

    public List<Audifono> listarAudifonos() {
        List<Audifono> out = new ArrayList<>();
        for (AudifonoDTO d : mf.getAudifonoDAO().getAll()) {
            Audifono e = new Audifono();
            e.setNombre(nvl(d.getNombre()));
            e.setImagen(nvl(d.getImagen()));
            e.setPrecio(nvi(d.getPrecio()));
            out.add(e);
        }
        return out;
    }

    public List<Pelicula> listarPeliculas() {
        List<Pelicula> out = new ArrayList<>();
        for (PeliculaDTO d : mf.getPeliculaDAO().getAll()) {
            Pelicula e = new Pelicula();
            e.setNombre(nvl(d.getNombre()));
            e.setImagen(nvl(d.getImagen()));
            e.setPrecio(nvi(d.getPrecio()));
            out.add(e);
        }
        return out;
    }

    public List<Animal> listarAnimal() {
        List<Animal> out = new ArrayList<>();
        for (AnimalDTO d : mf.getAnimalDAO().getAll()) {
            Animal e = new Animal();
            e.setNombre(nvl(d.getNombre()));
            e.setImagen(nvl(d.getImagen()));
            e.setPrecio(nvi(d.getPrecio()));
            out.add(e);
        }
        return out;
    }

    public List<Labial> listarLabiales() {
        List<Labial> out = new ArrayList<>();
        for (LabialDTO d : mf.getLabialDAO().getAll()) {
            Labial e = new Labial();
            e.setNombre(nvl(d.getNombre()));
            e.setImagen(nvl(d.getImagen()));
            e.setPrecio(nvi(d.getPrecio()));
            out.add(e);
        }
        return out;
    }

    public List<Pestanina> listarPestaninas() {
        List<Pestanina> out = new ArrayList<>();
        for (PestaninaDTO d : mf.getPestaninaDAO().getAll()) {
            Pestanina e = new Pestanina();
            e.setNombre(nvl(d.getNombre()));
            e.setImagen(nvl(d.getImagen()));
            e.setPrecio(nvi(d.getPrecio()));
            out.add(e);
        }
        return out;
    }

    public List<JuegoMesa> listarJuegoMesa() {
        List<JuegoMesa> out = new ArrayList<>();
        for (JuegoMesaDTO d : mf.getJuegoMesaDAO().getAll()) {
            JuegoMesa e = new JuegoMesa();
            e.setNombre(nvl(d.getNombre()));
            e.setImagen(nvl(d.getImagen()));
            e.setPrecio(nvi(d.getPrecio()));
            out.add(e);
        }
        return out;
    }

    public List<Colegio> listarColegios() {
        List<Colegio> out = new ArrayList<>();
        for (ColegioDTO d : mf.getColegioDAO().getAll()) {
            Colegio e = new Colegio();
            e.setNombre(nvl(d.getNombre()));
            e.setImagen(nvl(d.getImagen()));
            e.setPrecio(nvi(d.getPrecio()));
            out.add(e);
        }
        return out;
    }

    public List<Oficina> listarOficinas() {
        List<Oficina> out = new ArrayList<>();
        for (OficinaDTO d : mf.getOficinaDAO().getAll()) {
            Oficina e = new Oficina();
            e.setNombre(nvl(d.getNombre()));
            e.setImagen(nvl(d.getImagen()));
            e.setPrecio(nvi(d.getPrecio()));
            out.add(e);
        }
        return out;
    }

    public List<Hombre> listarHombres() {
        List<Hombre> out = new ArrayList<>();
        for (HombreDTO d : mf.getHombreDAO().getAll()) {
            Hombre e = new Hombre();
            e.setNombre(nvl(d.getNombre()));
            e.setImagen(nvl(d.getImagen()));
            e.setPrecio(nvi(d.getPrecio()));
            out.add(e);
        }
        return out;
    }

    public List<Mujer> listarMujeres() {
        List<Mujer> out = new ArrayList<>();
        for (MujerDTO d : mf.getMujerDAO().getAll()) {
            Mujer e = new Mujer();
            e.setNombre(nvl(d.getNombre()));
            e.setImagen(nvl(d.getImagen()));
            e.setPrecio(nvi(d.getPrecio()));
            out.add(e);
        }
        return out;
    }

    // ===== OPERACIONES DE DATOS (borrar por nombre) =====
    public boolean eliminarMovil(String nombre)      { MovilDTO dto = new MovilDTO(); dto.setNombre(nvl(nombre)); return mf.getMovilDAO().delete(dto); }
    public boolean eliminarAudifono(String nombre)   { AudifonoDTO dto = new AudifonoDTO(); dto.setNombre(nvl(nombre)); return mf.getAudifonoDAO().delete(dto); }
    public boolean eliminarPelicula(String nombre)   { PeliculaDTO dto = new PeliculaDTO(); dto.setNombre(nvl(nombre)); return mf.getPeliculaDAO().delete(dto); }
    public boolean eliminarAnimal(String nombre)     { AnimalDTO dto = new AnimalDTO(); dto.setNombre(nvl(nombre)); return mf.getAnimalDAO().delete(dto); }
    public boolean eliminarLabial(String nombre)     { LabialDTO dto = new LabialDTO(); dto.setNombre(nvl(nombre)); return mf.getLabialDAO().delete(dto); }
    public boolean eliminarPestanina(String nombre)  { PestaninaDTO dto = new PestaninaDTO(); dto.setNombre(nvl(nombre)); return mf.getPestaninaDAO().delete(dto); }
    public boolean eliminarJuegoMesa(String nombre)  { JuegoMesaDTO dto = new JuegoMesaDTO(); dto.setNombre(nvl(nombre)); return mf.getJuegoMesaDAO().delete(dto); }
    public boolean eliminarColegio(String nombre)    { ColegioDTO dto = new ColegioDTO(); dto.setNombre(nvl(nombre)); return mf.getColegioDAO().delete(dto); }
    public boolean eliminarOficina(String nombre)    { OficinaDTO dto = new OficinaDTO(); dto.setNombre(nvl(nombre)); return mf.getOficinaDAO().delete(dto); }
    public boolean eliminarHombre(String nombre)     { HombreDTO dto = new HombreDTO(); dto.setNombre(nvl(nombre)); return mf.getHombreDAO().delete(dto); }
    public boolean eliminarMujer(String nombre)      { MujerDTO dto = new MujerDTO(); dto.setNombre(nvl(nombre)); return mf.getMujerDAO().delete(dto); }
}
