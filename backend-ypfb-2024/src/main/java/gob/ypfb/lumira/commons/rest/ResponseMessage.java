/* ================================================================
 * El archivo "ResponseMessage" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.commons.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ResponseMessage {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    //200
    public static ResponseEntity<?> ok(Object data) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.OK, data);

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    //201
    public static ResponseEntity<?> creado(Object obj) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.CREATED, obj);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    //202
    public static ResponseEntity<?> aceptado(Object obj) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.ACCEPTED, obj);
        return new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED);
    }

    //204
    public static ResponseEntity<?> sinContenido(Object obj) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.NO_CONTENT, obj);
        return new ResponseEntity<>(respuesta, HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<?> sinContenido() {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.NO_CONTENT, null);
        return new ResponseEntity<>(respuesta, HttpStatus.NO_CONTENT);
    }

    //400
    public static ResponseEntity<?> solicitudIncorrecta(Object errorList) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.BAD_REQUEST, null);

        System.out.println(errorList.getClass());

        respuesta.put("errorList", errorList);

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    //400
    public static ResponseEntity<?> solicitudIncorrecta(BindingResult result) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.BAD_REQUEST, null);

        Map<String, String> errorList = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errorList.put(err.getField(), "El atributo " + err.getField() + " " + err.getDefaultMessage());
        });

        respuesta.put("errorList", errorList);

        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    //401
    public static ResponseEntity<?> noAutorizado() {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.UNAUTHORIZED, null);
        return new ResponseEntity<>(respuesta, HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity<?> noAutorizado(String message) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.UNAUTHORIZED, null);
        respuesta.replace("message", message);
        return new ResponseEntity<>(respuesta, HttpStatus.UNAUTHORIZED);
    }

    // 403
    public static ResponseEntity<?> prohibido() {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.FORBIDDEN, null);

        return new ResponseEntity<>(respuesta, HttpStatus.FORBIDDEN);
    }

    public static ResponseEntity<?> prohibido(String message) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.FORBIDDEN, null);
        respuesta.replace("message", message);
        return new ResponseEntity<>(respuesta, HttpStatus.FORBIDDEN);
    }

    //404
    public static ResponseEntity<?> noEncontrado() {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.NOT_FOUND, null);
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<?> noEncontrado(String message) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.NOT_FOUND, null);
        respuesta.replace("message", message);
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    // 407
    public static ResponseEntity<?> requiereAuthentificacion() {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.PROXY_AUTHENTICATION_REQUIRED, null);
        return new ResponseEntity<>(respuesta, HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
    }

    public static ResponseEntity<?> requiereAuthentificacion(String message) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.PROXY_AUTHENTICATION_REQUIRED, null);
        respuesta.replace("message", message);
        return new ResponseEntity<>(respuesta, HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
    }

    // 409
    public static ResponseEntity<?> conflicto() {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.CONFLICT, null);
        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
    }

    public static ResponseEntity<?> conflicto(String message) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.CONFLICT, null);
        respuesta.replace("message", message);
        return new ResponseEntity<>(respuesta, HttpStatus.CONFLICT);
    }


    // 500
    public static ResponseEntity<?> errorEnServidor(Exception e) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.INTERNAL_SERVER_ERROR, null);
        respuesta.put("exception", e.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<?> errorEnServidor(String excepcion) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.INTERNAL_SERVER_ERROR, null);
        respuesta.put("excepcion", excepcion);
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 503
    public static ResponseEntity<?> servicioNoDisponible() {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.SERVICE_UNAVAILABLE, null);
        return new ResponseEntity<>(respuesta, HttpStatus.SERVICE_UNAVAILABLE);
    }

    public static ResponseEntity<?> servicioNoDisponible(String message) {
        Map<String, Object> respuesta = createMapResponse(YpfbHttpSatus.SERVICE_UNAVAILABLE, null);
        respuesta.replace("message", message);
        return new ResponseEntity<>(respuesta, HttpStatus.SERVICE_UNAVAILABLE);
    }

    private static Map<String, Object> createMapResponse(YpfbHttpSatus ypfbHttpSatus, Object data) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("success", ypfbHttpSatus.getSuccess());
        respuesta.put("code", ypfbHttpSatus.getCode());
        respuesta.put("message", ypfbHttpSatus.getMessage());
        if (ypfbHttpSatus.getSuccess())
            respuesta.put("data", data);
        respuesta.put("timestamp", LocalDateTime.now().format(formatter));

        return respuesta; //new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
