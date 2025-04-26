package gob.ypfb.lumira.commons.cliente.api.ypfb.response;

import gob.ypfb.lumira.commons.cliente.api.ypfb.dto.RolDto;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioResponse {

    private int id;
    private String username;
    private String nombre_completo;
    private String correo_electronico;
    private String documento_identidad;
    private String cargo;
    // private String sigla_unidad_dependiente; private String unidad_dependiente;
    // private String empresa_institucion; private String fecha_registro;
    private List<RolDto> roles;
}
