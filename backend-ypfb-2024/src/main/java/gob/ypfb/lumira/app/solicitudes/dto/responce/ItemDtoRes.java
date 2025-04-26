package gob.ypfb.lumira.app.solicitudes.dto.responce;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class ItemDtoRes {

    private Long id;
    private Long idSolicitud;

    private Long numeroTarifa;
    private String codigoServicio;
    private Long correlativoServicio;
    private BigDecimal precioUnitario;

    private Boolean eliminado;
    private String usuarioRegistro;
    private Timestamp fechaRegistro;

    private String comentarios;
    private BigDecimal precioBase;
    private Long idTipoInformacion;

}
