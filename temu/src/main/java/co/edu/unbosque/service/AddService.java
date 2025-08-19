package co.edu.unbosque.service;

import co.edu.unbosque.model.AudifonoDTO;
import co.edu.unbosque.model.ColegioDTO;
import co.edu.unbosque.model.EducativoDTO;
import co.edu.unbosque.model.HombreDTO;
import co.edu.unbosque.model.JuegoMesaDTO;
import co.edu.unbosque.model.LabialDTO;
import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.MovilDTO;
import co.edu.unbosque.model.MujerDTO;
import co.edu.unbosque.model.OficinaDTO;
import co.edu.unbosque.model.PestaninaDTO;

public class AddService {

    public AddService() {
        // TODO Auto-generated constructor stub
    }

    public void crearAu(AudifonoDTO nuevo) {
        ModelFacade.audifonoDAO.add(nuevo);
    }

    public void crearMo(MovilDTO nuevo) {
        ModelFacade.movilDAO.add(nuevo);
    }

    public void crearLa(LabialDTO nuevo) {
        ModelFacade.labialDAO.add(nuevo);
    }

    public void crearPes(PestaninaDTO nuevo) {
        ModelFacade.pestaninaDAO.add(nuevo);
    }

    public void crearJue(JuegoMesaDTO nuevo) {
        ModelFacade.juegoMesaDAO.add(nuevo);
    }

    public void crearCo(ColegioDTO nuevo) {
        ModelFacade.colegioDAO.add(nuevo);
    }

    public void crearOfi(OficinaDTO nuevo) {
        ModelFacade.oficinaDAO.add(nuevo);
    }

    public void crearHom(HombreDTO nuevo) {
        ModelFacade.hombreDAO.add(nuevo);
    }

    public void crearMuj(MujerDTO nuevo) {
        ModelFacade.mujerDAO.add(nuevo);
    }
}
