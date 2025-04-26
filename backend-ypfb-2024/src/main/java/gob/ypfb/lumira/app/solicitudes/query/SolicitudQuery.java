package gob.ypfb.lumira.app.solicitudes.query;

import gob.ypfb.lumira.app.solicitudes.dto.query.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class SolicitudQuery implements ISolicitudQuery {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private JdbcTemplate jdbcTemplateProc;

    /**
     *
     */
    @Override
    public List<ServicioDtoQuery> listarServicios(String term) {
        List<ServicioDtoQuery> vResultado = null;
        String vQuery ="select ct.numero_tarifa \n" +
                "\t,ct.descripcion_tarifa \n" +
                "\t,cs.codigo_servicio \n" +
                "\t,csd.correlativo_servicio \n" +
                "\t,cs.disclaimer_servicio \n" +
                "\t,csd.codigo ||' - '|| csd.categoria codigo_busqueda \n" +
                "\t,csd.codigo \n" +
                "\t,csd.tipo_informacion \n" +
                "\t,csd.categoria \n" +
                "\t,csd.item \n" +
                "\t,csd.componente_descripcion \n" +
                "\t,csd.unidad_medida \n" +
                "\t,csd.precio \n" +
                "\t,csd.tipo_cinta \n" +
                "\t,csd.detalle_referencia \n" +
                "from lumira.par_catalogo_tarifas ct\n" +
                "join lumira.par_catalogo_servicios cs on cs.fk_numero_tarifa =ct.id\n" +
                "join lumira.par_catalogo_servicios_detalle csd on csd.fk_codigo_servicio =cs.codigo_servicio \n" +
                "where ct.eliminado =false\n" +
                "\tand cs.eliminado =false\n" +
                "\tand csd.eliminado =false\n" +
                "\tand csd.codigo like '%"+ term +"%'\n" +
                "order by ct.id \n" +
                "\t,cs.id \n" +
                "\t,csd.id";
        Map<String, Object> parameter = new HashMap<>();
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(ServicioDtoQuery.class));
        return vResultado;
    }

    @Override
    public List<ItemDtoQuery> listarItemsSolicitud(Long id) {
        List<ItemDtoQuery> vResultado =null;
        String vQuery ="select csd.codigo \n" +
                "\t, csd.componente_descripcion \n" +
                "\t, ad.adicional1 \n" +
                "\t, ad.adicional2 \n" +
                "\t, ad.adicional3 \n" +
                "\t, ad.adicional4 \n" +
                "\t, ad.adicional5 \n" +
                "\t, ad.adicional6 \n" +
                "\t, ad.adicional7 \n" +
                "\t, case when csd.fk_formula =1 then i.precio_unitario\n" +
                "\t\t   when csd.fk_formula =2 then (case when i.fk_tipo_informacion =3 then i.precio_unitario * to_number(ad.adicional3, '0.999')\n" +
                "\t\t   \t\t\t\t\t\t\t\t\t when i.fk_tipo_informacion =5 then i.precio_unitario * to_number(ad.adicional2, '0.999')\n" +
                "\t\t   \t\t\t\t\t\t\t\t\t when i.fk_tipo_informacion =10 then i.precio_unitario * to_number(ad.adicional5, '0.999')\n" +
                "\t\t   \t\t\t\t\t\t\t\t\t when i.fk_tipo_informacion =11 then i.precio_unitario * to_number(ad.adicional1, '0.999') end)\n" +
                "  \t\t   when csd.fk_formula =3 then i.precio_unitario\n" +
                "  \t\t   when csd.fk_formula =4 then (case when i.fk_tipo_informacion =7 \n" +
                "  \t\t     \t\t\t\t\t\t\t\t then (i.precio_unitario * to_number(ad.adicional3, '0.999')) * to_number(ad.adicional6, '0.999') end)\n" +
                "\t  end as precio_base\n" +
                "\t, ad.comentario\n" +
                "\t, i.fk_tipo_informacion \n" +
                "from lumira.trs_solicitudes sol\n" +
                "\tjoin lumira.trs_items i on i.fk_solicitud =sol.id \t\n" +
                "\tjoin lumira.par_catalogo_servicios_detalle csd on csd.fk_codigo_servicio =i.fk_codigo_servicio \n" +
                "\tjoin lumira.trs_informacion_adicional ad on ad.fk_solicitud =sol.id and ad.fk_item =i.id\t \n" +
                "where sol.eliminado =false\n" +
                "\tand sol.id ="+id.toString()+"\n"+
                "order by i.fk_tipo_informacion";
        Map<String, Object> parameter = new HashMap<>();
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(ItemDtoQuery.class));
        return vResultado;
    }
    //todo: lista items solicitud + informacion adicional + precio base
    //todo: ver si no se necesita otro DTO
    @Override
    public List<InfAdicionalDtoQuery> listarInfAdicionalSolicitud(Long id) {
        List<InfAdicionalDtoQuery> vResultado =null;
        String vQuery ="";
        Map<String, Object> parameter = new HashMap<>();
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(InfAdicionalDtoQuery.class));
        return vResultado;
    }

    @Override
    public List<InfRequeridaDtoQuery> listarInfRequeridaSolicitud(Long id) {
        List<InfRequeridaDtoQuery> vResultado =null;
        String query ="select tip.descripcion tipo\n" +
                "\t, inf.nombre \n" +
                "\t, inf.descripcion \n" +
                "\t, inf.area \n" +
                "\t, inf.formato \n" +
                "from lumira.trs_informacion_requerida inf\n" +
                "\tjoin lumira.trs_solicitudes sol on inf.fk_solicitud =sol.id\t\n" +
                "\tjoin lumira.par_tipo_informacion tip on inf.fk_tipo_informacion =tip.id\n" +
                "where sol.eliminado =false\n" +
                "\tand sol.id ="+id.toString()+"\n"+
                "order by tip.id";
        Map<String, Object> parameter = new HashMap<>();
        vResultado = jdbcTemplate.query(query, parameter, new BeanPropertyRowMapper<>(InfRequeridaDtoQuery.class));
        return vResultado;
    }

    /**
     * BANDEJA DE SOLICITUDES
     */
    @Override
    public List<BandejaDtoQuery> listarBandejaTodos() {
        List<BandejaDtoQuery> vResultado = null;
        String vQuery ="select sol.id \n" +
                "\t,us.nombre_razon_social solicitante \n" +
                "\t,sol.descripcion \n" +
                "\t,es.descripcion estado_solicitud\n" +
                "\t,(select us1.nombre_razon_social from lumira.trs_usuarios us1 where sol.fk_usuario_responsable = us1.id ) responsable\n" +
                "from lumira.trs_solicitudes sol \n" +
                "\tjoin lumira.trs_usuarios us on sol.fk_usuario_solicitante = us.id\n" +
                "\tjoin lumira.trs_estados es on sol.fk_estado_actual =es.id\n" +
                "where sol.eliminado =false \n" +
                "order by sol.id asc";
        Map<String, Object> parameter = new HashMap<>();
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(BandejaDtoQuery.class));
        return vResultado;
    }
    @Override
    public List<BandejaDtoQuery> listarBandejaPendientes(Long id) {
        List<BandejaDtoQuery> vResultado = null;
        String vQuery ="select sol.id \n" +
                "\t,us.nombre_razon_social solicitante \n" +
                "\t,sol.descripcion \n" +
                "\t,es.descripcion estado_solicitud\n" +
                "\t,(select us1.nombre_razon_social from lumira.trs_usuarios us1 where sol.fk_usuario_responsable = us1.id ) responsable\n" +
                "from lumira.trs_solicitudes sol \n" +
                "\tjoin lumira.trs_usuarios us on sol.fk_usuario_responsable = us.id\n" +
                "\tjoin lumira.trs_estados es on sol.fk_estado_actual =es.id\n" +
                "where sol.eliminado =false \n" +
                "and sol.fk_usuario_responsable ="+id.toString()+"\n"+
                "order by sol.id asc";
        Map<String, Object> parameter = new HashMap<>();
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(BandejaDtoQuery.class));
        return vResultado;
    }
    @Override
    public List<BandejaDtoQuery> listarBandejaAsignacion(Long id) {
        List<BandejaDtoQuery> vResultado = null;
        String vQuery ="select sol.id \n" +
                "\t,us.nombre_razon_social solicitante \n" +
                "\t,sol.descripcion \n" +
                "\t,es.descripcion estado_solicitud\n" +
                "\t,(select us1.nombre_razon_social from lumira.trs_usuarios us1 where sol.fk_usuario_responsable = us1.id ) responsable\n" +
                "from lumira.trs_solicitudes sol \n" +
                "\tjoin lumira.trs_usuarios us on sol.fk_usuario_responsable = us.id\n" +
                "\tjoin lumira.trs_estados es on sol.fk_estado_actual =es.id\n" +
                "\tjoin lumira.trs_asignaciones asi on asi.fk_solicitud =sol.id\n" +
                "where sol.eliminado =false \n" +
                "and asi.fk_usuario ="+ id.toString()+"\n"+
                "order by sol.id asc";
        Map<String, Object> parameter = new HashMap<>();
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(BandejaDtoQuery.class));
        return vResultado;
    }
    @Override
    public List<BandejaDtoQuery> listarBandejaCliente(Long id) {
        List<BandejaDtoQuery> vResultado = null;
        String vQuery ="select sol.id \n" +
                "\t,us.nombre_razon_social solicitante \n" +
                "\t,sol.descripcion \n" +
                "\t,es.descripcion estado_solicitud\n" +
                "\t,(select us1.nombre_razon_social from lumira.trs_usuarios us1 where sol.fk_usuario_responsable = us1.id ) responsable\n" +
                "from lumira.trs_solicitudes sol \n" +
                "\tjoin lumira.trs_usuarios us on sol.fk_usuario_solicitante = us.id\n" +
                "\tjoin lumira.trs_estados es on sol.fk_estado_actual =es.id\n" +
                "where sol.eliminado =false \n" +
                "and sol.fk_usuario_solicitante ="+id.toString()+"\n"+
                "order by sol.id asc";
        Map<String, Object> parameter = new HashMap<>();
        vResultado = jdbcTemplate.query(vQuery, parameter, new BeanPropertyRowMapper<>(BandejaDtoQuery.class));
        return vResultado;
    }

    @Override
    public void completaHistorialAsignacion(String idSolicitud, String idAsignacion, String idUsuario,
                                       String estado, String observaciones) {

        jdbcTemplateProc.update((Connection conexion)  -> {
            CallableStatement procedimiento = conexion.prepareCall("call lumira.sp_estado_flujo(?, ?, ?, ?, ?)");
            procedimiento.setString(1, idSolicitud);
            procedimiento.setString(2, idAsignacion);
            procedimiento.setString(3, idUsuario);
            procedimiento.setString(4, estado);
            procedimiento.setString(5, observaciones);
            return procedimiento;
        });

    }


}
