package gob.ypfb.lumira.app.solicitudes.dto.query;

import lombok.Data;

@Data
public class BandejaDtoQuery {

    private Long id;
    private String solicitante;
    private String descripcion;
    private String estadoSolicitud;
    private String responsable;

}
