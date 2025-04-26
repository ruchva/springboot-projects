package gob.ypfb.lumira.app.valoraciones.dto.query;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResumenPreliminarDtoQuery {

    private Long id;
    private String concepto;
    private BigDecimal precioBase;


}
