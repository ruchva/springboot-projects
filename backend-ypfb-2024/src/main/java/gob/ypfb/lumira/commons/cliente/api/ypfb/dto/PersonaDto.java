package gob.ypfb.lumira.commons.cliente.api.ypfb.dto;

import lombok.Data;

@Data
public class PersonaDto {
    private Long id;
    private String ci;
    private String extensionCi;
    private String nombreCompleto;
    private UsuarioCtaDto usuario;

}
