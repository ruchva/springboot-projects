/* ================================================================
 * El archivo "DominioController" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.app.parametricas.controller;

import gob.ypfb.lumira.app.parametricas.domain.model.Dominio;
import gob.ypfb.lumira.app.parametricas.dto.request.DominioRequest;
import gob.ypfb.lumira.app.parametricas.dto.response.DominioResponse;
import gob.ypfb.lumira.app.parametricas.service.IDominioService;
import gob.ypfb.lumira.commons.rest.ResponseMessage;
import gob.ypfb.lumira.utils.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/dominios")
@Slf4j
@Tag(name = "- Parametricas: Dominio ", description = "")
public class DominioController {

    @Autowired
    private IDominioService dominioService;

    @Operation(
        summary = "- Obtiene el listado de Dominios.",
        description = "- Obtiene el listado de Dominios.")

    @GetMapping("")
    public ResponseEntity<?> obtenerListadoDominios(@RequestParam("term") Optional<String> term, @RequestParam("tipoDominio") Optional<String> tipoDominio) {
        List<DominioResponse> dominioResponseList = new ArrayList<>();
        if (tipoDominio.isPresent())
            dominioResponseList = dominioService.obtenerPorTipoDominio(tipoDominio.get());
        else
            dominioResponseList = dominioService.obtenerListadoDominios();

        return ResponseMessage.ok(dominioResponseList);
    }

    @Operation(
        summary = "- Obtiene un Dominio por su identificador único.",
        description = "- Obtiene un Dominio por su identificador único.")

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDominioPorId(@PathVariable(value = "id") Long id) throws Exception {

        Dominio dominio = dominioService.obtenerPorId(id);

        return ResponseMessage.ok(MapperUtil.convertToType(dominio, DominioResponse.class));
    }

    @PostMapping
    public ResponseEntity<?> guadar(@Valid @RequestBody DominioRequest dominioRequest, BindingResult result) {
        if (result.hasErrors())
            return ResponseMessage.solicitudIncorrecta(result);

        Dominio dominio = dominioService.guardar(dominioRequest);
        return ResponseMessage.creado(MapperUtil.convertToType(dominio, DominioResponse.class));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody DominioRequest dominioRequest, BindingResult result, @PathVariable(value = "id") Long id) {
        if (result.hasErrors())
            return ResponseMessage.solicitudIncorrecta(result);

        Dominio dominio = dominioService.actualizar(dominioRequest, id);
        return ResponseMessage.ok(MapperUtil.convertToType(dominio, DominioResponse.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id") Long id) {
        dominioService.eliminar(id);
        return ResponseMessage.sinContenido();
    }
}
