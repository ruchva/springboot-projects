package gob.ypfb.lumira.app.solicitudes.dto.request;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class SolicitudDtoReq {

    private Long id;
    private Long idUsuarioSolicitante;
    private String descripcion;
    private Long idEstadoActual;
    private Long idUsuarioResponsable;
    private Boolean eliminado;
    private String usuarioRegistro;
    private Timestamp fechaRegistro;

}
