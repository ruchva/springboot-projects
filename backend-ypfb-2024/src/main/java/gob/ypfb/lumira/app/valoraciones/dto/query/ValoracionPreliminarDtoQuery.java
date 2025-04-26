package gob.ypfb.lumira.app.valoraciones.dto.query;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ValoracionPreliminarDtoQuery {

    private Long id;
    private String concepto;
    private BigDecimal calculoBase;
    private String descuento;
    private BigDecimal calculoDescuento;

}
