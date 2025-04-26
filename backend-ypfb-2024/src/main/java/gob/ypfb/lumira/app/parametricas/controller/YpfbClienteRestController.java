/* ================================================================
 * El archivo "ClienteController" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 12/7/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.app.parametricas.controller;

import gob.ypfb.lumira.app.apicliente.domain.model.Documento;
import gob.ypfb.lumira.app.apicliente.service.IDocumentoService;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.DocumentoDmsRequest;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.EmailRequest;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.DmsResponse;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.DocumentoDmsResponse;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.LogResponse;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.personal.MaeResponseDto;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.personal.PersonalResponseDto;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.personal.UnidadesResponseDto;
import gob.ypfb.lumira.commons.cliente.api.ypfb.service.YpfbDocumentoService;
import gob.ypfb.lumira.commons.cliente.api.ypfb.service.YpfbEmailService;
import gob.ypfb.lumira.commons.cliente.api.ypfb.service.YpfbGestionPersonalService;
import gob.ypfb.lumira.commons.cliente.api.ypfb.service.YpfbLoggerService;
import gob.ypfb.lumira.commons.rest.ResponseMessage;
import gob.ypfb.lumira.commons.rest.ResponseRest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/clientes")
@Slf4j
@Tag(name = "- Configuracion: Cliente ", description = "Para verificar los endpoints de los servicios de YPFB")
public class YpfbClienteRestController {
    @Autowired
    private YpfbEmailService ypfbEmailService;
    @Autowired
    private YpfbLoggerService ypfbLoggerService;

    @Autowired
    private YpfbDocumentoService ypfbDocumentoService;

    @Autowired
    private IDocumentoService documentoService;

    @Autowired
    private YpfbGestionPersonalService ypfbGestionPersonalService;

    //----------- Email
    @PostMapping("/notificar-sync")
    public ResponseEntity<?> enviarSync(@RequestBody EmailRequest email) {
        ResponseRest responseRest = ypfbEmailService.enviarSync(email);
        if (responseRest.getSuccess())
            return ResponseMessage.ok(responseRest.getMessage());

        //if(responseRest.getHttpStatus() == 500)
        return ResponseMessage.errorEnServidor(responseRest.getMessage());
    }

    @PostMapping("/notificar-async")
    public ResponseEntity<?> enviarAsync(@RequestBody EmailRequest email) {
        ypfbEmailService.enviarAsync(email);
        return ResponseMessage.ok("Correo enviado de asíncronamente");
    }

    //-----------
    @GetMapping("/logger")
    public ResponseEntity<?> obtenerBitacoras() {
        List<LogResponse> logResponseList = ypfbLoggerService.obtenerBitacoras();
        return ResponseMessage.ok(logResponseList);
    }

    @PostMapping("/logger")
    public ResponseEntity<?> logger(@RequestBody String log) {
        ypfbLoggerService.log(log);
        return ResponseMessage.ok("Log enviado");
    }

    //----------- Documentos
    @GetMapping("/documentos")
    public ResponseEntity<?> listarDocumento(@RequestParam("query") Optional<String> query) {
        List<Documento> documentoLista = documentoService.listar(query);
        return ResponseMessage.ok(documentoLista);
    }

    @GetMapping("/documentos/{id}")
    public ResponseEntity<?> obtenerDocumento(@PathVariable(value = "id") Long id) {
        Documento documento = documentoService.obtenerPorId(id);
        return ResponseMessage.ok(documento);
    }

    @GetMapping("/documentos/dms/{dmsId}")
    public ResponseEntity<?> obtenerDocumentoDms(@PathVariable(value = "dmsId") String dmsId) {
        DocumentoDmsResponse documentoDmsResponse = ypfbDocumentoService.obtenerDocumentoPorId(dmsId);

        return ResponseMessage.ok(documentoDmsResponse);
    }

    @GetMapping("/documentos/dms/{dmsId}/download")
    public ResponseEntity<Resource> descargarDocumentoDms(@PathVariable(value = "dmsId") String dmsId) {
        DocumentoDmsResponse documentoDmsResponse = ypfbDocumentoService.descargar(dmsId);

        ByteArrayResource resource = new ByteArrayResource(Base64.getDecoder().decode(documentoDmsResponse.getFile()));

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(documentoDmsResponse.getMimeType()))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + documentoDmsResponse.getName() + "\"")
            .body(resource);
    }

    @PostMapping("/documentos")
    public ResponseEntity<?> guardarDocumento(@RequestBody DocumentoDmsRequest dmsRequest) {
/*
        System.out.println(" >> name: " + dmsRequest.getName());
        System.out.println(" >> has: " + dmsRequest.getHash());
        System.out.println(" >> file: " + dmsRequest.getFile());
        dmsRequest.getMetadato().forEach((metadato) -> {
            System.out.println(metadato.getGrupo());
            metadato.getPropiedades().forEach((p) -> {
                System.out.println("   - " + p.getNombre() + " : " + p.getValor());
            });
        });
*/

        DmsResponse dmsResponse = ypfbDocumentoService.guardarDocumento(dmsRequest);

        Documento documento = documentoService.guardar(dmsRequest, dmsResponse);

        System.out.println(" >>> dmsId: " + dmsResponse.getId());
        System.out.println(" >>> Id: " + documento.getId());

        return ResponseMessage.ok(documento);
    }


    //--Gestion de Personal
    @GetMapping("/gestion-personal/unidades/dependientes/{sigla}")
    public ResponseEntity<?> obtenerUnidadesDependenteDeUnidad(@PathVariable(value = "sigla") String sigla) {
        List<UnidadesResponseDto> respuesta =
            ypfbGestionPersonalService.obtenerUnidadesDependenteDeUnidad(sigla);
        return ResponseMessage.ok(respuesta);
    }

    @GetMapping("/gestion-personal/personal/dependientes/{sigla}")
    public ResponseEntity<?> obtenerPersonalDependenteDeUnidad(@PathVariable(value = "sigla") String sigla) {
        List<PersonalResponseDto> respuesta =
            ypfbGestionPersonalService.obtenerPersonalDependenteDeUnidad(sigla);
        return ResponseMessage.ok(respuesta);
    }

    @GetMapping("/gestion-personal/mae/{sigla}")
    public ResponseEntity<?> obtenerInformacionDeLaMAEDeUnidad(@PathVariable(value = "sigla") String sigla) {
        MaeResponseDto respuesta =
            ypfbGestionPersonalService.obtenerInformacionDeLaMAEDeUnidad(sigla);
        return ResponseMessage.ok(respuesta);
    }

}
