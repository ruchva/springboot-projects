package gob.ypfb.lumira.commons.cliente.api.ypfb.request;

import lombok.Data;

import java.util.List;

@Data
public class ParamAsignaRolRequest {
    private String username;
    private String usuario_aplicacion;
    private List<Integer> roles;
}
