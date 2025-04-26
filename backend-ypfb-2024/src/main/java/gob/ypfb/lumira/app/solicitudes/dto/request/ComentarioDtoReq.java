package gob.ypfb.lumira.app.solicitudes.dto.request;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ComentarioDtoReq {

    private Long id;
    private Long idSolicitud;
    private Long idUsuario;
    private String idAdjunto;
    private String glosaComentario;
    private String linkComentario;
    private String nombreAdjunto;
    private String clasificacion;
    private Boolean eliminado;
    private String usuarioRegistro;
    private Timestamp fechaRegistro;

}
