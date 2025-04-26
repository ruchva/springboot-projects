package gob.ypfb.lumira.app.valoraciones.dto.query;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class ValoracionDtoQuery {
    private Long id;

    private Long idSolicitud;
    private String estadoValoracion;
    private Long idTipoInformacion;
    private Long idDescuento;
    private BigDecimal calculoBase;
    private BigDecimal calculoDescuento;

    private Boolean eliminado;
    private String usuarioRegistro;
    private Timestamp fechaRegistro;
}
