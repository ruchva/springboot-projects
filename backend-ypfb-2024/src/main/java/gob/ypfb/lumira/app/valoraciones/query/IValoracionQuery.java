package gob.ypfb.lumira.app.valoraciones.query;

import gob.ypfb.lumira.app.valoraciones.dto.query.DisposicionAdicionalDtoQuery;
import gob.ypfb.lumira.app.valoraciones.dto.query.ResumenPreliminarDtoQuery;
import gob.ypfb.lumira.app.valoraciones.dto.query.ValoracionPreliminarDtoQuery;

import java.util.List;

public interface IValoracionQuery {

    List<ResumenPreliminarDtoQuery> listarResumenPreliminar(Long id);

    List<ValoracionPreliminarDtoQuery> listarValoracionPreliminar(Long id);

    List<DisposicionAdicionalDtoQuery> listarDisposicionAdicional(Long id);
}
