package gob.ypfb.lumira.app.solicitudes.query;

import gob.ypfb.lumira.app.solicitudes.dto.query.*;
import gob.ypfb.lumira.app.solicitudes.dto.request.ItemDtoReq;

import java.util.List;

public interface ISolicitudQuery {


    /**
     * BANDEJA DE SOLICITUDES
     */
    List<BandejaDtoQuery> listarBandejaTodos();
    List<BandejaDtoQuery> listarBandejaPendientes(Long id);
    List<BandejaDtoQuery> listarBandejaAsignacion(Long id);
    List<BandejaDtoQuery> listarBandejaCliente(Long id);
    /**
     *
     */
    List<ServicioDtoQuery> listarServicios(String term);
    List<InfRequeridaDtoQuery> listarInfRequeridaSolicitud(Long id);
    List<InfAdicionalDtoQuery> listarInfAdicionalSolicitud(Long id);
    List<ItemDtoQuery> listarItemsSolicitud(Long id);

    /**
     *
     * @param idSolicitud
     * @param idAsignacion
     * @param idUsuario
     * @param estado
     * @param observaciones
     */
    void completaHistorialAsignacion(String idSolicitud, String idAsignacion, String idUsuario,
                                       String estado, String observaciones);




}
