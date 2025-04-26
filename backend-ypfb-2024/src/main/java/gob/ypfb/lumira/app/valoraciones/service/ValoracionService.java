package gob.ypfb.lumira.app.valoraciones.service;

import gob.ypfb.lumira.app.valoraciones.dto.query.DisposicionAdicionalDtoQuery;
import gob.ypfb.lumira.app.valoraciones.dto.query.ResumenPreliminarDtoQuery;
import gob.ypfb.lumira.app.valoraciones.dto.query.ValoracionPreliminarDtoQuery;
import gob.ypfb.lumira.app.valoraciones.query.IValoracionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracionService implements IValoracionService{

    @Autowired
    IValoracionQuery vIValoracionQuery;

    @Override
    public List<ResumenPreliminarDtoQuery> listarResumenPreliminar(Long id) {
        return vIValoracionQuery.listarResumenPreliminar(id);
    }

    @Override
    public List<ValoracionPreliminarDtoQuery> listarValoracionPreliminar(Long id) {
        return vIValoracionQuery.listarValoracionPreliminar(id);
    }

    @Override
    public List<DisposicionAdicionalDtoQuery> listarDisposicionAdicional(Long id) {
        return vIValoracionQuery.listarDisposicionAdicional(id);
    }

}
