package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Animal;
import co.edu.unbosque.model.AnimalDTO;
import co.edu.unbosque.model.Audifono;
import co.edu.unbosque.model.AudifonoDTO;
import co.edu.unbosque.model.Colegio;
import co.edu.unbosque.model.ColegioDTO;
import co.edu.unbosque.model.Educativo;
import co.edu.unbosque.model.EducativoDTO;
import co.edu.unbosque.model.Hombre;
import co.edu.unbosque.model.HombreDTO;
import co.edu.unbosque.model.JuegoMesa;
import co.edu.unbosque.model.JuegoMesaDTO;
import co.edu.unbosque.model.Labial;
import co.edu.unbosque.model.LabialDTO;
import co.edu.unbosque.model.Movil;
import co.edu.unbosque.model.MovilDTO;
import co.edu.unbosque.model.Mujer;
import co.edu.unbosque.model.MujerDTO;
import co.edu.unbosque.model.Oficina;
import co.edu.unbosque.model.OficinaDTO;
import co.edu.unbosque.model.Pelicula;
import co.edu.unbosque.model.PeliculaDTO;
import co.edu.unbosque.model.Pestanina;
import co.edu.unbosque.model.PestaninaDTO;

/**
 * Clase en la cual se crean métodos para administrar datos entre los DTO y entidades.
 */
public class DataMapper {
	
	/**
	 * Convierte un AnimalDTO a un Animal.
	 *
	 * @param dto El AnimalDTO a convertir.
	 * @return El Animal resultante.
	 */
	public static Animal animalDTOToAnimal(AnimalDTO dto) {
	    Animal entity = new Animal(dto.getNombre(), dto.getPrecio(), dto.getImagen(),
	            dto.getTamano(), dto.getAnimal());
	    return entity;
	}

	/**
	 * Convierte un Animal a un AnimalDTO.
	 *
	 * @param entity El Animal a convertir.
	 * @return El AnimalDTO resultante.
	 */
	public static AnimalDTO animalToAnimalDTO(Animal entity) {
	    AnimalDTO dto = new AnimalDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(),
	            entity.getTamano(), entity.getAnimal());
	    return dto;
	}

	/**
	 * Convierte una lista de Animal a una lista de AnimalDTO.
	 *
	 * @param entityList La lista de Animal a convertir.
	 * @return La lista de AnimalDTO resultante.
	 */
	public static ArrayList<AnimalDTO> listaAnimalToListaAnimalDTO(ArrayList<Animal> entityList) {
	    ArrayList<AnimalDTO> dtoList = new ArrayList<>();
	    for (Animal m : entityList) {
	        dtoList.add(new AnimalDTO(m.getNombre(), m.getPrecio(), m.getImagen(),
	                m.getTamano(), m.getAnimal()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de AnimalDTO a una lista de Animal.
	 *
	 * @param dtoList La lista de AnimalDTO a convertir.
	 * @return La lista de Animal resultante.
	 */
	public static ArrayList<Animal> listaAnimalDTOToListaAnimal(ArrayList<AnimalDTO> dtoList) {
	    ArrayList<Animal> entityList = new ArrayList<>();
	    for (AnimalDTO d : dtoList) {
	        entityList.add(new Animal(d.getNombre(), d.getPrecio(), d.getImagen(),
	                d.getTamano(), d.getAnimal()));
	    }
	    return entityList;
	}
	
	/**
	 * Convierte un AudifonoDTO a un Audifono.
	 *
	 * @param dto El AudifonoDTO a convertir.
	 * @return El Audifono resultante.
	 */
	public static Audifono audifonoDTOToAudifono(AudifonoDTO dto) {
	    Audifono entity = new Audifono(dto.getNombre(), dto.getPrecio(), dto.getImagen(),
	            dto.getModelo(), dto.getTipoConexion());
	    return entity;
	}

	/**
	 * Convierte un Audifono a un AudifonoDTO.
	 *
	 * @param entity El Audifono a convertir.
	 * @return El AudifonoDTO resultante.
	 */
	public static AudifonoDTO audifonoToAudifonoDTO(Audifono entity) {
	    AudifonoDTO dto = new AudifonoDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(),
	            entity.getModelo(), entity.getTipoConexion());
	    return dto;
	}

	/**
	 * Convierte una lista de Audifono a una lista de AudifonoDTO.
	 *
	 * @param entityList La lista de Audifono a convertir.
	 * @return La lista de AudifonoDTO resultante.
	 */
	public static ArrayList<AudifonoDTO> listaAudifonoToListaAudifonoDTO(ArrayList<Audifono> entityList) {
	    ArrayList<AudifonoDTO> dtoList = new ArrayList<>();
	    for (Audifono m : entityList) {
	        dtoList.add(new AudifonoDTO(m.getNombre(), m.getPrecio(), m.getImagen(),
	                m.getModelo(), m.getTipoConexion()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de AudifonoDTO a una lista de Audifono.
	 *
	 * @param dtoList La lista de AudifonoDTO a convertir.
	 * @return La lista de Audifono resultante.
	 */
	public static ArrayList<Audifono> listaAudifonoDTOToListaAudifono(ArrayList<AudifonoDTO> dtoList) {
	    ArrayList<Audifono> entityList = new ArrayList<>();
	    for (AudifonoDTO d : dtoList) {
	        entityList.add(new Audifono(d.getNombre(), d.getPrecio(), d.getImagen(),
	                d.getModelo(), d.getTipoConexion()));
	    }
	    return entityList;
	}
	/**
	 * Convierte un ColegioDTO a un Colegio.
	 *
	 * @param dto El ColegioDTO a convertir.
	 * @return El Colegio resultante.
	 */
	public static Colegio colegioDTOToColegio(ColegioDTO dto) {
	    Colegio entity = new Colegio(dto.getNombre(), dto.getPrecio(), dto.getImagen(),
	            dto.getCantidadPaquete(), dto.isEsSeguro());
	    return entity;
	}

	/**
	 * Convierte un Colegio a un ColegioDTO.
	 *
	 * @param entity El Colegio a convertir.
	 * @return El ColegioDTO resultante.
	 */
	public static ColegioDTO colegioToColegioDTO(Colegio entity) {
	    ColegioDTO dto = new ColegioDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(),
	            entity.getCantidadPaquete(), entity.isEsSeguro());
	    return dto;
	}

	/**
	 * Convierte una lista de Colegio a una lista de ColegioDTO.
	 *
	 * @param entityList La lista de Colegio a convertir.
	 * @return La lista de ColegioDTO resultante.
	 */
	public static ArrayList<ColegioDTO> listaColegioToListaColegioDTO(ArrayList<Colegio> entityList) {
	    ArrayList<ColegioDTO> dtoList = new ArrayList<>();
	    for (Colegio m : entityList) {
	        dtoList.add(new ColegioDTO(m.getNombre(), m.getPrecio(), m.getImagen(),
	                m.getCantidadPaquete(), m.isEsSeguro()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de ColegioDTO a una lista de Colegio.
	 *
	 * @param dtoList La lista de ColegioDTO a convertir.
	 * @return La lista de Colegio resultante.
	 */
	public static ArrayList<Colegio> listaColegioDTOToListaColegio(ArrayList<ColegioDTO> dtoList) {
	    ArrayList<Colegio> entityList = new ArrayList<>();
	    for (ColegioDTO d : dtoList) {
	        entityList.add(new Colegio(d.getNombre(), d.getPrecio(), d.getImagen(),
	                d.getCantidadPaquete(), d.isEsSeguro()));
	    }
	    return entityList;
	}
	/**
	 * Convierte un EducativoDTO a un Educativo.
	 *
	 * @param dto El EducativoDTO a convertir.
	 * @return El Educativo resultante.
	 */
	public static Educativo educativoDTOToEducativo(EducativoDTO dto) {
	    Educativo entity = new Educativo(dto.getNombre(), dto.getPrecio(), dto.getImagen(),
	            dto.getEdadRecomendada(), dto.isEsDidactico());
	    return entity;
	}

	/**
	 * Convierte un Educativo a un EducativoDTO.
	 *
	 * @param entity El Educativo a convertir.
	 * @return El EducativoDTO resultante.
	 */
	public static EducativoDTO educativoToEducativoDTO(Educativo entity) {
	    EducativoDTO dto = new EducativoDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(),
	            entity.getEdadRecomendada(), entity.isEsDidactico());
	    return dto;
	}

	/**
	 * Convierte una lista de Educativo a una lista de EducativoDTO.
	 *
	 * @param entityList La lista de Educativo a convertir.
	 * @return La lista de EducativoDTO resultante.
	 */
	public static ArrayList<EducativoDTO> listaEducativoToListaEducativoDTO(ArrayList<Educativo> entityList) {
	    ArrayList<EducativoDTO> dtoList = new ArrayList<>();
	    for (Educativo e : entityList) {
	        dtoList.add(new EducativoDTO(e.getNombre(), e.getPrecio(), e.getImagen(),
	                e.getEdadRecomendada(), e.isEsDidactico()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de EducativoDTO a una lista de Educativo.
	 *
	 * @param dtoList La lista de EducativoDTO a convertir.
	 * @return La lista de Educativo resultante.
	 */
	public static ArrayList<Educativo> listaEducativoDTOToListaEducativo(ArrayList<EducativoDTO> dtoList) {
	    ArrayList<Educativo> entityList = new ArrayList<>();
	    for (EducativoDTO d : dtoList) {
	        entityList.add(new Educativo(d.getNombre(), d.getPrecio(), d.getImagen(),
	                d.getEdadRecomendada(), d.isEsDidactico()));
	    }
	    return entityList;
	}
	
	/**
	 * Convierte un HombreDTO a un Hombre.
	 *
	 * @param dto El HombreDTO a convertir.
	 * @return El Hombre resultante.
	 */
	public static Hombre hombreDTOToHombre(HombreDTO dto) {
	    Hombre entity = new Hombre(dto.getNombre(), dto.getPrecio(),
	            dto.getImagen(), dto.getTalla(), dto.getColor(), dto.isEsDeportiva());
	    return entity;
	}

	/**
	 * Convierte un Hombre a un HombreDTO.
	 *
	 * @param entity El Hombre a convertir.
	 * @return El HombreDTO resultante.
	 */
	public static HombreDTO hombreToHombreDTO(Hombre entity) {
	    HombreDTO dto = new HombreDTO(entity.getNombre(), entity.getPrecio(),
	            entity.getImagen(), entity.getTalla(), entity.getColor(), entity.isEsDeportiva());
	    return dto;
	}

	/**
	 * Convierte una lista de Hombre a una lista de HombreDTO.
	 *
	 * @param entityList La lista de Hombre a convertir.
	 * @return La lista de HombreDTO resultante.
	 */
	public static ArrayList<HombreDTO> listaHombreToListaHombreDTO(ArrayList<Hombre> entityList) {
	    ArrayList<HombreDTO> dtoList = new ArrayList<>();
	    for (Hombre e : entityList) {
	        dtoList.add(new HombreDTO(e.getNombre(), e.getPrecio(),
	                e.getImagen(), e.getTalla(), e.getColor(), e.isEsDeportiva()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de HombreDTO a una lista de Hombre.
	 *
	 * @param dtoList La lista de HombreDTO a convertir.
	 * @return La lista de Hombre resultante.
	 */
	public static ArrayList<Hombre> listaHombreDTOToListaHombre(ArrayList<HombreDTO> dtoList) {
	    ArrayList<Hombre> entityList = new ArrayList<>();
	    for (HombreDTO d : dtoList) {
	        entityList.add(new Hombre(d.getNombre(), d.getPrecio(),
	                d.getImagen(), d.getTalla(), d.getColor(), d.isEsDeportiva()));
	    }
	    return entityList;
	}

	/**
	 * Convierte un JuegoMesaDTO a un JuegoMesa.
	 *
	 * @param dto El JuegoMesaDTO a convertir.
	 * @return El JuegoMesa resultante.
	 */
	public static JuegoMesa juegoMesaDTOToJuegoMesa(JuegoMesaDTO dto) {
	    JuegoMesa entity = new JuegoMesa(dto.getNombre(), dto.getPrecio(), dto.getImagen(),
	            dto.getEdadRecomendada(), dto.getCantidadPersona());
	    return entity;
	}

	/**
	 * Convierte un JuegoMesa a un JuegoMesaDTO.
	 *
	 * @param entity El JuegoMesa a convertir.
	 * @return El JuegoMesaDTO resultante.
	 */
	public static JuegoMesaDTO juegoMesaToJuegoMesaDTO(JuegoMesa entity) {
	    JuegoMesaDTO dto = new JuegoMesaDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(),
	            entity.getEdadRecomendada(), entity.getCantidadPersona());
	    return dto;
	}

	/**
	 * Convierte una lista de JuegoMesa a una lista de JuegoMesaDTO.
	 *
	 * @param entityList La lista de JuegoMesa a convertir.
	 * @return La lista de JuegoMesaDTO resultante.
	 */
	public static ArrayList<JuegoMesaDTO> listaJuegoMesaToListaJuegoMesaDTO(ArrayList<JuegoMesa> entityList) {
	    ArrayList<JuegoMesaDTO> dtoList = new ArrayList<>();
	    for (JuegoMesa e : entityList) {
	        dtoList.add(new JuegoMesaDTO(e.getNombre(), e.getPrecio(), e.getImagen(),
	                e.getEdadRecomendada(), e.getCantidadPersona()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de JuegoMesaDTO a una lista de JuegoMesa.
	 *
	 * @param dtoList La lista de JuegoMesaDTO a convertir.
	 * @return La lista de JuegoMesa resultante.
	 */
	public static ArrayList<JuegoMesa> listaJuegoMesaDTOToListaJuegoMesa(ArrayList<JuegoMesaDTO> dtoList) {
	    ArrayList<JuegoMesa> entityList = new ArrayList<>();
	    for (JuegoMesaDTO d : dtoList) {
	        entityList.add(new JuegoMesa(d.getNombre(), d.getPrecio(), d.getImagen(),
	                d.getEdadRecomendada(), d.getCantidadPersona()));
	    }
	    return entityList;
	}

	/**
	 * Convierte un LabialDTO a un Labial.
	 *
	 * @param dto El LabialDTO a convertir.
	 * @return El Labial resultante.
	 */
	public static Labial labialDTOToLabial(LabialDTO dto) {
	    Labial entity = new Labial(dto.getNombre(), dto.getPrecio(), dto.getImagen(),
	            dto.isEsApruebaDeAgua(), dto.getTono());
	    return entity;
	}

	/**
	 * Convierte un Labial a un LabialDTO.
	 *
	 * @param entity El Labial a convertir.
	 * @return El LabialDTO resultante.
	 */
	public static LabialDTO labialToLabialDTO(Labial entity) {
	    LabialDTO dto = new LabialDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(),
	            entity.isEsApruebaDeAgua(), entity.getTono());
	    return dto;
	}

	/**
	 * Convierte una lista de Labial a una lista de LabialDTO.
	 *
	 * @param entityList La lista de Labial a convertir.
	 * @return La lista de LabialDTO resultante.
	 */
	public static ArrayList<LabialDTO> listaLabialToListaLabialDTO(ArrayList<Labial> entityList) {
	    ArrayList<LabialDTO> dtoList = new ArrayList<>();
	    for (Labial e : entityList) {
	        dtoList.add(new LabialDTO(e.getNombre(), e.getPrecio(), e.getImagen(),
	                e.isEsApruebaDeAgua(), e.getTono()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de LabialDTO a una lista de Labial.
	 *
	 * @param dtoList La lista de LabialDTO a convertir.
	 * @return La lista de Labial resultante.
	 */
	public static ArrayList<Labial> listaLabialDTOToListaLabial(ArrayList<LabialDTO> dtoList) {
	    ArrayList<Labial> entityList = new ArrayList<>();
	    for (LabialDTO d : dtoList) {
	        entityList.add(new Labial(d.getNombre(), d.getPrecio(), d.getImagen(),
	                d.isEsApruebaDeAgua(), d.getTono()));
	    }
	    return entityList;
	}
	/**
	 * Convierte un MovilDTO a un Movil.
	 *
	 * @param dto El MovilDTO a convertir.
	 * @return El Movil resultante.
	 */
	public static Movil movilDTOToMovil(MovilDTO dto) {
	    Movil entity = new Movil(dto.getNombre(), dto.getPrecio(), dto.getImagen(),
	            dto.getModelo(), dto.getAlmacenamiento());
	    return entity;
	}

	/**
	 * Convierte un Movil a un MovilDTO.
	 *
	 * @param entity El Movil a convertir.
	 * @return El MovilDTO resultante.
	 */
	public static MovilDTO movilToMovilDTO(Movil entity) {
	    MovilDTO dto = new MovilDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(),
	            entity.getModelo(), entity.getAlmacenamiento());
	    return dto;
	}

	/**
	 * Convierte una lista de Movil a una lista de MovilDTO.
	 *
	 * @param entityList La lista de Movil a convertir.
	 * @return La lista de MovilDTO resultante.
	 */
	public static ArrayList<MovilDTO> listaMovilToListaMovilDTO(ArrayList<Movil> entityList) {
	    ArrayList<MovilDTO> dtoList = new ArrayList<>();
	    for (Movil e : entityList) {
	        dtoList.add(new MovilDTO(e.getNombre(), e.getPrecio(), e.getImagen(),
	                e.getModelo(), e.getAlmacenamiento()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de MovilDTO a una lista de Movil.
	 *
	 * @param dtoList La lista de MovilDTO a convertir.
	 * @return La lista de Movil resultante.
	 */
	public static ArrayList<Movil> listaMovilDTOToListaMovil(ArrayList<MovilDTO> dtoList) {
	    ArrayList<Movil> entityList = new ArrayList<>();
	    for (MovilDTO d : dtoList) {
	        entityList.add(new Movil(d.getNombre(), d.getPrecio(), d.getImagen(),
	                d.getModelo(), d.getAlmacenamiento()));
	    }
	    return entityList;
	}

	/**
	 * Convierte un MujerDTO a un Mujer.
	 *
	 * @param dto El MujerDTO a convertir.
	 * @return El Mujer resultante.
	 */
	public static Mujer mujerDTOToMujer(MujerDTO dto) {
	    Mujer entity = new Mujer(dto.getNombre(), dto.getPrecio(), dto.getImagen(),
	            dto.getTalla(), dto.getColor(), dto.isEsConjunto());
	    return entity;
	}

	/**
	 * Convierte un Mujer a un MujerDTO.
	 *
	 * @param entity El Mujer a convertir.
	 * @return El MujerDTO resultante.
	 */
	public static MujerDTO mujerToMujerDTO(Mujer entity) {
	    MujerDTO dto = new MujerDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(),
	            entity.getTalla(), entity.getColor(), entity.isEsConjunto());
	    return dto;
	}

	/**
	 * Convierte una lista de Mujer a una lista de MujerDTO.
	 *
	 * @param entityList La lista de Mujer a convertir.
	 * @return La lista de MujerDTO resultante.
	 */
	public static ArrayList<MujerDTO> listaMujerToListaMujerDTO(ArrayList<Mujer> entityList) {
	    ArrayList<MujerDTO> dtoList = new ArrayList<>();
	    for (Mujer e : entityList) {
	        dtoList.add(new MujerDTO(e.getNombre(), e.getPrecio(), e.getImagen(),
	                e.getTalla(), e.getColor(), e.isEsConjunto()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de MujerDTO a una lista de Mujer.
	 *
	 * @param dtoList La lista de MujerDTO a convertir.
	 * @return La lista de Mujer resultante.
	 */
	public static ArrayList<Mujer> listaMujerDTOToListaMujer(ArrayList<MujerDTO> dtoList) {
	    ArrayList<Mujer> entityList = new ArrayList<>();
	    for (MujerDTO d : dtoList) {
	        entityList.add(new Mujer(d.getNombre(), d.getPrecio(), d.getImagen(),
	                d.getTalla(), d.getColor(), d.isEsConjunto()));
	    }
	    return entityList;
	}
	/**
	 * Convierte un OficinaDTO a un Oficina.
	 *
	 * @param dto El OficinaDTO a convertir.
	 * @return El Oficina resultante.
	 */
	public static Oficina oficinaDTOToOficina(OficinaDTO dto) {
	    Oficina entity = new Oficina(dto.getNombre(), dto.getPrecio(), dto.getImagen(),
	            dto.getCantidadPaquete(), dto.isEsDecorativo());
	    return entity;
	}

	/**
	 * Convierte un Oficina a un OficinaDTO.
	 *
	 * @param entity El Oficina a convertir.
	 * @return El OficinaDTO resultante.
	 */
	public static OficinaDTO oficinaToOficinaDTO(Oficina entity) {
	    OficinaDTO dto = new OficinaDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(),
	            entity.getCantidadPaquete(), entity.isEsDecorativo());
	    return dto;
	}

	/**
	 * Convierte una lista de Oficina a una lista de OficinaDTO.
	 *
	 * @param entityList La lista de Oficina a convertir.
	 * @return La lista de OficinaDTO resultante.
	 */
	public static ArrayList<OficinaDTO> listaOficinaToListaOficinaDTO(ArrayList<Oficina> entityList) {
	    ArrayList<OficinaDTO> dtoList = new ArrayList<>();
	    for (Oficina e : entityList) {
	        dtoList.add(new OficinaDTO(e.getNombre(), e.getPrecio(), e.getImagen(),
	                e.getCantidadPaquete(), e.isEsDecorativo()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de OficinaDTO a una lista de Oficina.
	 *
	 * @param dtoList La lista de OficinaDTO a convertir.
	 * @return La lista de Oficina resultante.
	 */
	public static ArrayList<Oficina> listaOficinaDTOToListaOficina(ArrayList<OficinaDTO> dtoList) {
	    ArrayList<Oficina> entityList = new ArrayList<>();
	    for (OficinaDTO d : dtoList) {
	        entityList.add(new Oficina(d.getNombre(), d.getPrecio(), d.getImagen(),
	                d.getCantidadPaquete(), d.isEsDecorativo()));
	    }
	    return entityList;
	}
	
	/**
	 * Convierte un PeliculaDTO a un Pelicula.
	 *
	 * @param dto El PeliculaDTO a convertir.
	 * @return El Pelicula resultante.
	 */
	public static Pelicula peliculaDTOToPelicula(PeliculaDTO dto) {
	    Pelicula entity = new Pelicula(dto.getNombre(), dto.getPrecio(), dto.getImagen(),
	            dto.getTamano(), dto.getPersonaje());
	    return entity;
	}

	/**
	 * Convierte un Pelicula a un PeliculaDTO.
	 *
	 * @param entity El Pelicula a convertir.
	 * @return El PeliculaDTO resultante.
	 */
	public static PeliculaDTO peliculaToPeliculaDTO(Pelicula entity) {
	    PeliculaDTO dto = new PeliculaDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(),
	            entity.getTamano(), entity.getPersonaje());
	    return dto;
	}

	/**
	 * Convierte una lista de Pelicula a una lista de PeliculaDTO.
	 *
	 * @param entityList La lista de Pelicula a convertir.
	 * @return La lista de PeliculaDTO resultante.
	 */
	public static ArrayList<PeliculaDTO> listaPeliculaToListaPeliculaDTO(ArrayList<Pelicula> entityList) {
	    ArrayList<PeliculaDTO> dtoList = new ArrayList<>();
	    for (Pelicula e : entityList) {
	        dtoList.add(new PeliculaDTO(e.getNombre(), e.getPrecio(), e.getImagen(),
	                e.getTamano(), e.getPersonaje()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de PeliculaDTO a una lista de Pelicula.
	 *
	 * @param dtoList La lista de PeliculaDTO a convertir.
	 * @return La lista de Pelicula resultante.
	 */
	public static ArrayList<Pelicula> listaPeliculaDTOToListaPelicula(ArrayList<PeliculaDTO> dtoList) {
	    ArrayList<Pelicula> entityList = new ArrayList<>();
	    for (PeliculaDTO d : dtoList) {
	        entityList.add(new Pelicula(d.getNombre(), d.getPrecio(), d.getImagen(),
	                d.getTamano(), d.getPersonaje()));
	    }
	    return entityList;
	}

	/**
	 * Convierte un PestaninaDTO a un Pestanina.
	 *
	 * @param dto El PestaninaDTO a convertir.
	 * @return El Pestanina resultante.
	 */
	public static Pestanina pestaninaDTOToPestanina(PestaninaDTO dto) {
	    Pestanina entity = new Pestanina(dto.getNombre(), dto.getPrecio(), dto.getImagen(),
	            dto.isEsApruebaDeAgua(), dto.getDuracion());
	    return entity;
	}

	/**
	 * Convierte un Pestanina a un PestaninaDTO.
	 *
	 * @param entity El Pestanina a convertir.
	 * @return El PestaninaDTO resultante.
	 */
	public static PestaninaDTO pestaninaToPestaninaDTO(Pestanina entity) {
	    PestaninaDTO dto = new PestaninaDTO(entity.getNombre(), entity.getPrecio(), entity.getImagen(),
	            entity.isEsApruebaDeAgua(), entity.getDuracion());
	    return dto;
	}

	/**
	 * Convierte una lista de Pestanina a una lista de PestaninaDTO.
	 *
	 * @param entityList La lista de Pestanina a convertir.
	 * @return La lista de PestaninaDTO resultante.
	 */
	public static ArrayList<PestaninaDTO> listaPestaninaToListaPestaninaDTO(ArrayList<Pestanina> entityList) {
	    ArrayList<PestaninaDTO> dtoList = new ArrayList<>();
	    for (Pestanina e : entityList) {
	        dtoList.add(new PestaninaDTO(e.getNombre(), e.getPrecio(), e.getImagen(),
	                e.isEsApruebaDeAgua(), e.getDuracion()));
	    }
	    return dtoList;
	}

	/**
	 * Convierte una lista de PestaninaDTO a una lista de Pestanina.
	 *
	 * @param dtoList La lista de PestaninaDTO a convertir.
	 * @return La lista de Pestanina resultante.
	 */
	public static ArrayList<Pestanina> listaPestaninaDTOToListaPestanina(ArrayList<PestaninaDTO> dtoList) {
	    ArrayList<Pestanina> entityList = new ArrayList<>();
	    for (PestaninaDTO d : dtoList) {
	        entityList.add(new Pestanina(d.getNombre(), d.getPrecio(), d.getImagen(),
	                d.isEsApruebaDeAgua(), d.getDuracion()));
	    }
	    return entityList;
	}



}
