package gob.ypfb.lumira.app.solicitudes.dto.responce;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InfAdicionalDtoRes {

    private Long id;
    private Long idSolicitud;
    private Long idTipoInformacion;

    private String adicional1;
    private String adicional2;
    private String adicional3;
    private String adicional4;
    private String adicional5;
    private String adicional6;
    private String adicional7;
    private String comentario;

    private Boolean eliminado;
    private String usuarioRegistro;
    private Timestamp fechaRegistro;

    private Long idItem;

}
