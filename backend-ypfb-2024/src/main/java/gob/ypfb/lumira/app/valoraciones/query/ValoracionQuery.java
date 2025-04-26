package gob.ypfb.lumira.app.valoraciones.query;

import gob.ypfb.lumira.app.valoraciones.dto.query.DisposicionAdicionalDtoQuery;
import gob.ypfb.lumira.app.valoraciones.dto.query.ResumenPreliminarDtoQuery;
import gob.ypfb.lumira.app.valoraciones.dto.query.ValoracionPreliminarDtoQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ValoracionQuery implements IValoracionQuery{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<ResumenPreliminarDtoQuery> listarResumenPreliminar(Long id) {
        List<ResumenPreliminarDtoQuery> resumen =null;
        String vQuery ="select sol.id\n" +
                ", 'SUB-TOTAL COSTO ' || tip.descripcion || ' SEGÚN TARIFARIO VIGENTE (USD):' concepto\n" +
                ", sum(i.precio_base) precio_base\n" +
                "from lumira.trs_solicitudes sol\n" +
                "\tjoin lumira.trs_usuarios u on sol.fk_usuario_solicitante =u.id\n" +
                "\tjoin lumira.trs_items i on i.fk_solicitud =sol.id\n" +
                "\tjoin lumira.par_catalogo_Servicios_detalle det on i.fk_numero_tarifa =det.fk_numero_tarifa \n" +
                "\t\tand i.fk_codigo_servicio =det.fk_codigo_servicio and i.fk_correlativo_servicio =det.correlativo_servicio \n" +
                "\tjoin lumira.par_tipo_informacion tip on i.fk_tipo_informacion =tip.id \n" +
                "where sol.id =" +id.toString()+"\n"+
                "group by sol.id\n" +
                ", tip.descripcion";
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        resumen = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(ResumenPreliminarDtoQuery.class));
        instanciaValoracionesPreliminar(resumen);
        return resumen;
    }

    public void instanciaValoracionesPreliminar(List<ResumenPreliminarDtoQuery> resumen){
        //todo: implementar carga de valoracion preliminar en trs_valoraciones mediante SP
    }

    @Override
    public List<ValoracionPreliminarDtoQuery> listarValoracionPreliminar(Long id) {
        List<ValoracionPreliminarDtoQuery> valoraciones =null;
        String vQuery ="select sol.id\n" +
                ", 'SUB-TOTAL COSTO ' || tip.descripcion || ' SEGÚN TARIFARIO VIGENTE (USD):' concepto\n" +
                ", val.calculo_base \n" +
                ", des.descuento ||'%' descuento\n" +
                "--,val.calculo_descuento\n" +
                ", (val.calculo_base * to_number(des.porcentual, '0.999')) calculo_descuento \n" +
                "from lumira.trs_solicitudes sol\n" +
                "join lumira.trs_valoraciones val on val.fk_solicitud =sol.id\n" +
                "join lumira.par_tipo_informacion tip on val.fk_tipo_informacion =tip.id\n" +
                "join lumira.par_disposiciones_descuentos des on val.fk_descuento =des.id \n" +
                "where sol.id = " +id.toString();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        valoraciones = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(ValoracionPreliminarDtoQuery.class));
        return valoraciones;
    }

    @Override
    public List<DisposicionAdicionalDtoQuery> listarDisposicionAdicional(Long id) {
        List<DisposicionAdicionalDtoQuery> descuento =null;
        String vQuery ="select des.glosa_disposicion \n" +
                ", sum((val.calculo_base * to_number(des.porcentual, '0.999'))) descuento_total\n" +
                ", (sum((val.calculo_base)) - sum((val.calculo_base * to_number(des.porcentual, '0.999')))) costo_total\n" +
                "from lumira.trs_solicitudes sol\n" +
                "join lumira.trs_valoraciones val on val.fk_solicitud =sol.id\n" +
                "join lumira.par_tipo_informacion tip on val.fk_tipo_informacion =tip.id\n" +
                "join lumira.par_disposiciones_descuentos des on val.fk_descuento =des.id \n" +
                "where sol.id =" +id.toString()+"\n"+
                "group by des.glosa_disposicion";
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        descuento = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(DisposicionAdicionalDtoQuery.class));
        return descuento;
    }
}
