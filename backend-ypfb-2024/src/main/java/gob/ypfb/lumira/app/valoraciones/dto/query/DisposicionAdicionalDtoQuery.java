package gob.ypfb.lumira.app.valoraciones.dto.query;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DisposicionAdicionalDtoQuery {

    private String glosaDisposicion;
    private BigDecimal descuentoTotal;
    private BigDecimal costoTotal;

}
