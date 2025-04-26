package gob.ypfb.lumira.commons.cliente.api.ypfb.request;

import lombok.Data;

import java.util.List;

@Data
public class ParamPersonaUsuarioResquest {

    private String nombre_completo;
    private String email;
    private String password;
    private String ci;
    private String extension_ci;
    private String cargo;
    private String empresa_institucion;
    private Boolean habilitar_roles_default;
    private List<Integer> roles;
    private String usuario_aplicacion;
}
