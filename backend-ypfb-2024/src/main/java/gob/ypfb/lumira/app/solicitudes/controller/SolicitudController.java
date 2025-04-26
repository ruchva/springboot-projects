package gob.ypfb.lumira.app.solicitudes.controller;

import gob.ypfb.lumira.app.solicitudes.dto.query.BandejaDtoQuery;
import gob.ypfb.lumira.app.solicitudes.dto.query.InfAdicionalDtoQuery;
import gob.ypfb.lumira.app.solicitudes.dto.query.InfRequeridaDtoQuery;
import gob.ypfb.lumira.app.solicitudes.dto.query.ServicioDtoQuery;
import gob.ypfb.lumira.app.solicitudes.dto.request.*;
import gob.ypfb.lumira.app.solicitudes.service.ISolicitudService;

import gob.ypfb.lumira.commons.rest.ResponseMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("lumira/api/v1/solicitudes")
@Slf4j
@Tag(name = "Solicitudes ", description = " Bandeja de solicitudes")
public class SolicitudController {

    @Autowired
    ISolicitudService vISolicitudService;

    /*##############################################################################################################*/

    @GetMapping(value = "/bandeja/all")
    public ResponseEntity<?> getBandejaTodos() {
        List<BandejaDtoQuery> listar = vISolicitudService.listarBandejaTodos();
        return ResponseMessage.ok(listar);
    }

    @GetMapping(value = "/bandeja/pendientes/{id}")
    public ResponseEntity<?> getBandejaPendientes(@PathVariable("id") Long id) {
        List<BandejaDtoQuery> listar = vISolicitudService.listarBandejaPendientes(id);
        return ResponseMessage.ok(listar);
    }

    @GetMapping(value = "/bandeja/asignacion/{id}")
    public ResponseEntity<?> getBandejaAsignacion(@PathVariable("id") Long id) {
        List<BandejaDtoQuery> listar = vISolicitudService.listarBandejaAsignacion(id);
        return ResponseMessage.ok(listar);
    }

    @GetMapping(value = "/bandeja/cliente/{id}")
    public ResponseEntity<?> getBandejaCliente(@PathVariable("id") Long id) {
        List<BandejaDtoQuery> listar = vISolicitudService.listarBandejaCliente(id);
        return ResponseMessage.ok(listar);
    }

    @GetMapping(value = "/servicios/{term}")
    public ResponseEntity<?> getServicios(@PathVariable("term") String term) {
        List<ServicioDtoQuery> listar = vISolicitudService.listarServicios(term);
        return ResponseMessage.ok(listar);
    }

    @GetMapping(value = "/items/{id}")
    public ResponseEntity<?> getItemsSolicitud(@PathVariable("id") Long id) {
        return null;//todo implementar segun dto items por tipo inf (13 tipos)
    }

    @GetMapping(value = "/requerida/{id}")
    public ResponseEntity<?> getInormacionRequerida(@PathVariable("id") Long id) {
        List<InfRequeridaDtoQuery> listar =vISolicitudService.listarInfRequeridaSolicitud(id);
        return ResponseMessage.ok(listar);
    }

    @GetMapping(value = "/adicional/{id}")
    public ResponseEntity<?> getInormacionAdicional(@PathVariable("id") Long id) {
        List<InfAdicionalDtoQuery> listar =vISolicitudService.listarInfAdicionalSolicitud(id);
        return ResponseMessage.ok(listar);
    }

    /*##############################################################################################################*/

    @PostMapping(value = "/registra/solicitud")
    public ResponseEntity<?> registraSolicitud(@RequestBody SolicitudDtoReq pSolicitudDtoReq) {
        vISolicitudService.registraSolicitud(pSolicitudDtoReq);
        return ResponseMessage.ok(null);
    }

    @PostMapping(value = "/modifica/solicitud")
    public ResponseEntity<?> modificaSolicitud(@RequestBody SolicitudDtoReq pSolicitudDtoReq) {
        vISolicitudService.modificaSolicitud(pSolicitudDtoReq);
        return ResponseMessage.ok(null);
    }

    @PostMapping(value = "/registra/requerida")
    public ResponseEntity<?> registraInformacionRequerida(@RequestBody InfRequeridaDtoReq vInfRequeridaDtoReq) {
        vISolicitudService.registraInformacionRequerida(vInfRequeridaDtoReq);//todo
        return ResponseMessage.ok(null);
    }

    @PostMapping(value = "/registra/adicional")
    public ResponseEntity<?> registraInformacionAdicional(@RequestBody InfAdicionalDtoReq vInfAdicionalDtoReq) {
        vISolicitudService.registraInformacionAdicional(vInfAdicionalDtoReq);//todo
        return ResponseMessage.ok(null);
    }

    @PostMapping(value = "/registra/asignacion")
    public ResponseEntity<?> registraAsignacion(@RequestBody AsignacionDtoReq vAsignacionDtoReq) {
        vISolicitudService.registraAsignacion(vAsignacionDtoReq);
        return ResponseMessage.ok(null);
    }

    @PostMapping(value = "/cambia/estado")
    public ResponseEntity<?> cambiaEstado(@RequestBody SolicitudDtoReq vSolicitudDtoReq) {
        vISolicitudService.cambiaEstado(vSolicitudDtoReq);
        return ResponseMessage.ok(null);
    }

    @PostMapping(value = "/agrega/item")
    public ResponseEntity<?> agregaItem(@RequestBody ItemDtoReq vItemDtoReq) {
        vISolicitudService.agregaItem(vItemDtoReq);
        return ResponseMessage.ok(null);
    }

    @PostMapping(value = "/retira/item")
    public ResponseEntity<?> retiraItem(@RequestBody ItemDtoReq vItemDtoReq) {
        vISolicitudService.retiraItem(vItemDtoReq);
        return ResponseMessage.ok(null);
    }

}
