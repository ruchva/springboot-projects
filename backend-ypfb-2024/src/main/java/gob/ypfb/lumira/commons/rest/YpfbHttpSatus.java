/* ================================================================
 * El archivo "YpfbHttpSatus" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 26/5/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.commons.rest;

public enum YpfbHttpSatus {
    // 2xx: Success
    OK(200, "OK", "OK", true),
    CREATED(201, "Creado", "CREATED", true),
    ACCEPTED(202, "Aceptado", "ACCEPTED", true), //Accepted
    NO_CONTENT(204, "Sin contenido", "NO_CONTENT", true),
    // 3xx: Redirection

    // 4xx: Client Error
    BAD_REQUEST(400, "Petición incorrecta", "BAD_REQUEST", false),
    UNAUTHORIZED(401, "No autorizado", "UNAUTHORIZED", false),
    FORBIDDEN(403, "Prohibido", "FORBIDDEN", false),
    NOT_FOUND(404, "No encontrado", "NOT_FOUND", false),
    PROXY_AUTHENTICATION_REQUIRED(407, "Autentificación necesaria", "PROXY_AUTHENTICATION_REQUIRED", false),
    CONFLICT(409, "Conflicto", "CONFLICT", false),

    // 5xx: Server Error
    INTERNAL_SERVER_ERROR(500, "Error interno en el servidor", "INTERNAL_SERVER_ERROR", false),
    SERVICE_UNAVAILABLE(503, "Servicio No Disponible", "SERVICE_UNAVAILABLE", false);

    //default.response.delete.204=Operación éxitosa
    private int code;
    private String message;
    private String httpCodeMessage;
    private final boolean success;

    YpfbHttpSatus(int code, String message, String httpCodeMessage, boolean success) {
        this.code = code;
        this.message = message;
        this.httpCodeMessage = httpCodeMessage;
        this.success = success;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getHttpCodeMessage() {
        return httpCodeMessage;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setHttpCodeMessage(String httpCodeMessage) {
        this.httpCodeMessage = httpCodeMessage;
    }

}