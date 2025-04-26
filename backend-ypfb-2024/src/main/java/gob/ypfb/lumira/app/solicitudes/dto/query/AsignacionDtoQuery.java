package gob.ypfb.lumira.app.solicitudes.dto.query;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AsignacionDtoQuery {

    private Long id;
    private Long idSolicitud;
    private Long idUsuario;
    private Timestamp fechaRegistro;
    private Timestamp fechaCierre;
    private String observaciones;
    private Long idEstadoAsignacion;
    private Boolean eliminado;
    private String usuarioRegistro;

}
