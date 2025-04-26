package gob.ypfb.lumira.app.solicitudes.dto.query;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServicioDtoQuery {

    private Long numeroTarifa;
    private String descripcionTarifa;
    private String codigoServicio;
    private Long correlativoServicio;
    private String disclaimerServicio;
    private String codigo_busqueda;
    private String codigo;
    private String tipoInformacion;
    private String categoria;
    private String item;
    private String componenteDescripcion;
    private String unidadMedida;
    private BigDecimal precio;
    private String tipoCinta;
    private String detalleReferencia;

}
