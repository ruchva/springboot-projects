package gob.ypfb.lumira.app.solicitudes.dto.query;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InfRequeridaDtoQuery {

    private Long id;
    private Long idSolicitud;
    private Long idTipoInformacion;
    private String tipo;
    private String nombre;
    private String descripcion;
    private String area;
    private String formato;
    private Boolean eliminado;
    private String usuarioRegistro;
    private Timestamp fechaRegistro;

}
