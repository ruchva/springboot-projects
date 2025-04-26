package gob.ypfb.lumira.commons.cliente.api.ypfb.request;

import lombok.Data;

import java.util.List;

@Data
public class ParamCuentaUsuarioResquest {
    private Long idPersona;
    private String email;
    private String password;
    private String cargo;
    private String empresa_institucion;
    private Boolean habilitar_roles_default;
    private String usuario_aplicacion;
    private List<Integer> roles;
}
