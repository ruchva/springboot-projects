package gob.ypfb.lumira.app.valoraciones.controller;

import gob.ypfb.lumira.app.valoraciones.dto.query.DisposicionAdicionalDtoQuery;
import gob.ypfb.lumira.app.valoraciones.dto.query.ResumenPreliminarDtoQuery;
import gob.ypfb.lumira.app.valoraciones.dto.query.ValoracionPreliminarDtoQuery;
import gob.ypfb.lumira.app.valoraciones.service.IValoracionService;

import gob.ypfb.lumira.commons.rest.ResponseMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("lumira/api/v1/valoracion")
@Slf4j
@Tag(name = "Valoracion ", description = " Valoracion de solicitudes")
public class ValoracionController {

    @Autowired
    IValoracionService IValoracionService;

    /*##############################################################################################################*/

    @GetMapping(value = "/solicitud/{id}")
    public ResponseEntity<?> getResumenPreliminar(@PathVariable("id") Long id) {
        List<ResumenPreliminarDtoQuery> listar = IValoracionService.listarResumenPreliminar(id);
        return ResponseMessage.ok(listar);
    }

    @GetMapping(value = "/preliminar/{id}")
    public ResponseEntity<?> getValoracionPreliminar(@PathVariable("id") Long id) {
        List<ValoracionPreliminarDtoQuery> listar = IValoracionService.listarValoracionPreliminar(id);
        return ResponseMessage.ok(listar);
    }

    @GetMapping(value = "/disposicion/{id}")
    public ResponseEntity<?> getDisposicionAdicional(@PathVariable("id") Long id) {
        List<DisposicionAdicionalDtoQuery> listar = IValoracionService.listarDisposicionAdicional(id);
        return ResponseMessage.ok(listar);
    }

    /*##############################################################################################################*/


}
