package gob.ypfb.lumira.app.solicitudes.service;

import gob.ypfb.lumira.app.solicitudes.domain.*;
import gob.ypfb.lumira.app.solicitudes.dto.query.*;
import gob.ypfb.lumira.app.solicitudes.dto.request.*;
import gob.ypfb.lumira.app.solicitudes.query.ISolicitudQuery;
import gob.ypfb.lumira.app.solicitudes.repository.*;
import gob.ypfb.lumira.security.jwt.JwtServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class SolicitudService implements ISolicitudService {

    @Autowired
    ISolicitudQuery vISolicitudQuery;

    @Autowired
    private JwtServiceContext jwtAuthenticationFilter;

    @Autowired
    private IInfRequeridaRepository vIInfRequeridaRepository;

    @Autowired
    private IInfAdicionalRepository vIInfAdicionalRepository;

    @Autowired
    private ISolicitudRepository vISolicitudRepository;

    @Autowired
    private IAsignacionRepository vIAsignacionRepository;

    @Autowired
    private IItemRepository vIItemRepository;


    /*##############################################################################################################*/

    @Override
    public List<BandejaDtoQuery> listarBandejaTodos() { return vISolicitudQuery.listarBandejaTodos(); }

    @Override
    public List<BandejaDtoQuery> listarBandejaPendientes(Long id) { return vISolicitudQuery.listarBandejaPendientes(id); }

    @Override
    public List<BandejaDtoQuery> listarBandejaAsignacion(Long id) { return vISolicitudQuery.listarBandejaAsignacion(id); }

    @Override
    public List<BandejaDtoQuery> listarBandejaCliente(Long id) { return vISolicitudQuery.listarBandejaCliente(id); }

    @Override
    public List<ServicioDtoQuery> listarServicios(String term) { return vISolicitudQuery.listarServicios(term); }

    @Override
    public List<InfRequeridaDtoQuery> listarInfRequeridaSolicitud(Long id) { return vISolicitudQuery.listarInfRequeridaSolicitud(id); }

    @Override
    public List<InfAdicionalDtoQuery> listarInfAdicionalSolicitud(Long id) { return vISolicitudQuery.listarInfAdicionalSolicitud(id); }

    @Override
    public List<ItemDtoQuery> listarItemsSolicitud(Long id) { return vISolicitudQuery.listarItemsSolicitud(id); }

    /*##############################################################################################################*/

    @Transactional
    @Override
    public Long registraSolicitud(SolicitudDtoReq solicitudDtoReq) {
        Solicitudes solicitud =new Solicitudes();
        Long idSolicitud =null;
        try {

            solicitud.setIdUsuarioSolicitante(solicitudDtoReq.getIdUsuarioSolicitante());
            solicitud.setDescripcion(solicitudDtoReq.getDescripcion());
            solicitud.setIdEstadoActual(solicitudDtoReq.getIdEstadoActual());
            solicitud.setIdUsuarioResponsable(solicitudDtoReq.getIdUsuarioResponsable());
            solicitud.setEliminado(false);
            solicitud.setFechaRegistro(Timestamp.from(Instant.now()));
            solicitud.setUsuarioRegistro(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername());
            vISolicitudRepository.save(solicitud);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return idSolicitud;
    }

    @Transactional
    @Override
    public void modificaSolicitud(SolicitudDtoReq solicitudDtoReq) {
        try {

            Solicitudes solicitud =vISolicitudRepository.findById(solicitudDtoReq.getId()).get();
            solicitud.setId(solicitudDtoReq.getId());
            solicitud.setIdUsuarioSolicitante(solicitudDtoReq.getIdUsuarioSolicitante());
            solicitud.setDescripcion(solicitudDtoReq.getDescripcion());
            solicitud.setIdEstadoActual(solicitudDtoReq.getIdEstadoActual());
            solicitud.setIdUsuarioResponsable(solicitudDtoReq.getIdUsuarioResponsable());
            solicitud.setEliminado(false);
            solicitud.setFechaRegistro(Timestamp.from(Instant.now()));
            solicitud.setUsuarioRegistro(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername());
            vISolicitudRepository.save(solicitud);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cambiaEstado(SolicitudDtoReq solicitudDtoReq) {
        try {
            Solicitudes solicitud =vISolicitudRepository.findById(solicitudDtoReq.getId()).get();
            solicitud.setIdEstadoActual(solicitudDtoReq.getIdEstadoActual());
            solicitud.setIdUsuarioResponsable(solicitudDtoReq.getIdUsuarioResponsable());
            solicitud.setEliminado(false);
            solicitud.setFechaRegistro(Timestamp.from(Instant.now()));
            solicitud.setUsuarioRegistro(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername());
            vISolicitudRepository.save(solicitud);
            vISolicitudQuery.completaHistorialAsignacion(solicitudDtoReq.getId().toString(),"","","","");
            //TODO: completar parametros cuando se termine de implementar la valoracion
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    @Override
    public void registraInformacionRequerida(InfRequeridaDtoReq infRequeridaDtoReq) {

        InfRequerida infReq =new InfRequerida();
        try {

            infReq.setIdSolicitud(infRequeridaDtoReq.getIdSolicitud());
            infReq.setIdTipoInformacion(infRequeridaDtoReq.getIdTipoInformacion());
            infReq.setNombre(infRequeridaDtoReq.getNombre());
            infReq.setDescripcion(infRequeridaDtoReq.getDescripcion());
            infReq.setArea(infRequeridaDtoReq.getArea());
            infReq.setFormato(infRequeridaDtoReq.getFormato());
            infReq.setEliminado(false);
            infReq.setFechaRegistro(Timestamp.from(Instant.now()));
            infReq.setUsuarioRegistro(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername());
            vIInfRequeridaRepository.save(infReq);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void registraInformacionAdicional(InfAdicionalDtoReq infAdicionalDtoReq) {

        InfAdicional infAd =new InfAdicional();
        try {

            infAd.setIdSolicitud(infAdicionalDtoReq.getIdSolicitud());
            infAd.setIdTipoInformacion(infAdicionalDtoReq.getIdTipoInformacion());
            infAd.setAdicional1(infAdicionalDtoReq.getAdicional1());
            infAd.setAdicional2(infAdicionalDtoReq.getAdicional2());
            infAd.setAdicional3(infAdicionalDtoReq.getAdicional3());
            infAd.setAdicional4(infAdicionalDtoReq.getAdicional4());
            infAd.setAdicional5(infAdicionalDtoReq.getAdicional5());
            infAd.setAdicional6(infAdicionalDtoReq.getAdicional6());
            infAd.setAdicional7(infAdicionalDtoReq.getAdicional7());
            infAd.setComentario(infAdicionalDtoReq.getComentario());
            infAd.setEliminado(false);
            infAd.setFechaRegistro(Timestamp.from(Instant.now()));
            infAd.setUsuarioRegistro(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername());
            infAd.setIdItem(infAdicionalDtoReq.getIdItem());
            vIInfAdicionalRepository.save(infAd);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registraAsignacion(AsignacionDtoReq asignacionDtoReq) {
        Asignaciones asignacion = new Asignaciones();
        try {

            asignacion.setIdSolicitud(asignacionDtoReq.getIdSolicitud());
            asignacion.setIdUsuario(asignacionDtoReq.getIdUsuario());
            asignacion.setFechaRegistro(Timestamp.from(Instant.now()));
            asignacion.setObservaciones(asignacionDtoReq.getObservaciones());
            asignacion.setIdEstadoAsignacion(asignacionDtoReq.getIdEstadoAsignacion());
            asignacion.setEliminado(false);
            asignacion.setUsuarioRegistro(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername());
            vIAsignacionRepository.save(asignacion);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void agregaItem(ItemDtoReq itemDtoReq) {
        Items item =new Items();
        try {

            item.setIdSolicitud(itemDtoReq.getIdSolicitud());
            item.setNumeroTarifa(itemDtoReq.getNumeroTarifa());
            item.setCodigoServicio(itemDtoReq.getCodigoServicio());
            item.setCorrelativoServicio(itemDtoReq.getCorrelativoServicio());
            item.setPrecioUnitario(itemDtoReq.getPrecioUnitario());

            item.setEliminado(false);
            item.setFechaRegistro(Timestamp.from(Instant.now()));
            item.setUsuarioRegistro(jwtAuthenticationFilter.getJwtTokenContextAplicacion().getUsername());

            item.setComentarios(itemDtoReq.getComentarios());
            item.setPrecioBase(itemDtoReq.getPrecioUnitario());
            item.setIdTipoInformacion(itemDtoReq.getIdTipoInformacion());
            vIItemRepository.save(item);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void retiraItem(ItemDtoReq itemDtoReq) {
        Items item =new Items();
        try {

            item.setIdSolicitud(itemDtoReq.getIdSolicitud());
            item.setNumeroTarifa(itemDtoReq.getNumeroTarifa());
            item.setCodigoServicio(itemDtoReq.getCodigoServicio());
            item.setCorrelativoServicio(itemDtoReq.getCorrelativoServicio());
            item.setPrecioUnitario(itemDtoReq.getPrecioUnitario());
            item.setComentarios(itemDtoReq.getComentarios());
            item.setPrecioBase(itemDtoReq.getPrecioBase());
            item.setIdTipoInformacion(itemDtoReq.getIdTipoInformacion());
            vIItemRepository.delete(item);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
