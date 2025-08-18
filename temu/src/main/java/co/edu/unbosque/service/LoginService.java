package co.edu.unbosque.service;

import co.edu.unbosque.model.PersonaDTO;
import java.util.ArrayList;
import java.util.List;

public class LoginService {
    private List<PersonaDTO> usuarios;

    public LoginService() {
        this.usuarios = new ArrayList<>();
        // Datos de prueba
        usuarios.add(new PersonaDTO("admin", "1234", "admin@example.com"));
    }

    public boolean encontrar(PersonaDTO dto) {
        for (PersonaDTO usuario : usuarios) {
            if (usuario.getCorreo().equals(dto.getCorreo()) && usuario.getContrasena().equals(dto.getContrasena())) {
                return true;
            }
        }
        return false;
    }

    public void crear(PersonaDTO nuevo) {
        usuarios.add(nuevo);
    }
}
