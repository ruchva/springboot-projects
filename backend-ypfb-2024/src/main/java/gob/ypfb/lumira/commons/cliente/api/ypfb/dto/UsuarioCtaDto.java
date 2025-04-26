package gob.ypfb.lumira.commons.cliente.api.ypfb.dto;

import lombok.Data;

@Data
public class UsuarioCtaDto {
    private Long id;
    private String username;
    private String email;
    private String cargo;
    private String empresa;
}
