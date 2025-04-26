package gob.ypfb.lumira.app.solicitudes.dto.query;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class ItemDtoQuery {

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
