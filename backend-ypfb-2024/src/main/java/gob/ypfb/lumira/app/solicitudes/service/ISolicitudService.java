package gob.ypfb.lumira.app.solicitudes.service;

import gob.ypfb.lumira.app.solicitudes.dto.query.*;
import gob.ypfb.lumira.app.solicitudes.dto.request.*;

import javax.transaction.Transactional;
import java.util.List;

public interface ISolicitudService {

    /**
     * BANDEJA DE SOLICITUDES
     */
    List<BandejaDtoQuery> listarBandejaTodos();
    List<BandejaDtoQuery> listarBandejaPendientes(Long id);
    List<BandejaDtoQuery> listarBandejaAsignacion(Long id);
    List<BandejaDtoQuery> listarBandejaCliente(Long id);

    /**
     * SERVICIOS - ITEMS
     */
    List<ServicioDtoQuery> listarServicios(String term);
    List<InfRequeridaDtoQuery> listarInfRequeridaSolicitud(Long id);
    List<InfAdicionalDtoQuery> listarInfAdicionalSolicitud(Long id);
    List<ItemDtoQuery> listarItemsSolicitud(Long id);//todo: por tipo inf (13 tipos)

    /**
     *
     */
    @Transactional
    Long registraSolicitud(SolicitudDtoReq solicitudDtoReq);
    @Transactional
    void modificaSolicitud(SolicitudDtoReq solicitudDtoReq);
    @Transactional
    void cambiaEstado(SolicitudDtoReq solicitudDtoReq);
    @Transactional
    void registraInformacionRequerida(InfRequeridaDtoReq infRequeridaDtoReq);
    @Transactional
    void registraInformacionAdicional(InfAdicionalDtoReq infAdicionalDtoReq);
    @Transactional
    void registraAsignacion(AsignacionDtoReq asignacionDtoReq);
    @Transactional
    void agregaItem(ItemDtoReq itemDtoReq);
    @Transactional
    void retiraItem(ItemDtoReq itemDtoReq);


}
