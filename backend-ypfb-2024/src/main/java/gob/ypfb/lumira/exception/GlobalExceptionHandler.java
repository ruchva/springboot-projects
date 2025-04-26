package gob.ypfb.lumira.exception;

import gob.ypfb.lumira.commons.rest.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j(topic = "GlobalExceptionHandler")
public class GlobalExceptionHandler {//extends ResponseEntityExceptionHandler {

    // ---> BAD_REQUEST
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        System.out.println(" >> handleMethodArgumentNotValid <<");
        log.error("Error de validacion ", ex);

        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {

            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        System.out.println(" >>>> " + ex.getLocalizedMessage());
        System.out.println(" >>>> " + errors);
        return (ResponseEntity<Object>) ResponseMessage.solicitudIncorrecta(ex.getBindingResult());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    public ResponseEntity<?> handleValidationException(ValidationException exp) {
        System.out.println(" >> validationException <<");
        //exp.printStackTrace();

        log.error("validationException: ", exp);
        return ResponseMessage.solicitudIncorrecta(exp.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> handleBadRequestException(BadRequestException exp) {
        log.error("Error de validacion ", exp);
        System.out.println(" >> solicitudIncorrecta <<");
        return ResponseMessage.solicitudIncorrecta(exp.getMessage());
    }

    // ---> NOT_FOUND
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<?> handleNotFoundException(NotFoundException exp) {
        //log.error("Parametro Incorrecto");
        return ResponseMessage.noEncontrado(exp.getMessage());
    }

    // ---> CONFLICT

    @ExceptionHandler({ConstraintViolationException.class, DuplicateKeyException.class, DataIntegrityViolationException.class, SQLException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<?> handleConflictDataBase(Exception exception) {
        System.out.println(" >>>>>>>>>>>>>> conflict <<<<<<<<<<<<<<<<<<<<<<");
        String mensaje = exception.getMessage();
        if (exception.getClass().getSimpleName().equals("DataIntegrityViolationException")) {
            //System.out.println(" >>>>>>>>>>>>>> " + exception.getMessage().split(";")[2] );
            mensaje = "[DataIntegrityViolation] => " + exception.getMessage().split(";")[2];
        }

        System.out.println(" >>>>>>>>>>>>>> " + exception.getClass().getSimpleName() + " <<<<<<<<<<<<<<<<<<<<<<");
        //log.error(" GlobalExceptionHandler > conflict, ", exception.getCause());

        return ResponseMessage.conflicto(mensaje);
    }

    @ExceptionHandler({ConflictException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<?> handleConflict(ConflictException exception) {
        System.out.println(" >>>>>>>>>>>>>> ConflictException <<<<<<<<<<<<<<<<<<<<<<");
        return ResponseMessage.conflicto(exception.getMessage());
    }


    // ---> FORBIDDEN
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ResponseEntity<?> handleForbiddenException(ForbiddenException exception) {
        System.out.println("GlobalExceptionHandler > FORBIDDEN");
        return ResponseMessage.prohibido(exception.getMessage());
    }


    // ---> UNAUTHORIZED
    @ExceptionHandler({UnauthorizedException.class, AccessDeniedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<?> unauthorized(Exception e) {
        log.error(" GlobalExceptionHandler > unauthorized, ");
        System.out.println("GlobalExceptionHandler > unauthorized");
        e.printStackTrace();
        return ResponseMessage.noAutorizado();
    }


    // 50x
    // ---> INTERNAL_SERVER_ERROR
    @ExceptionHandler({Exception.class, YpfbException.class, YpfbServiceException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<?> fatalErrorUnexpectedException(Exception exception) {
        log.error("Ocurrio un error desconocido {}", exception.getMessage());
        //exception.printStackTrace();
        return ResponseMessage.errorEnServidor(exception);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public ResponseEntity<?> handleServiceUnavailableException(ServiceUnavailableException exception) {
        log.error("Ocurrio un error desconocido {}", exception.getMessage());
        return ResponseMessage.servicioNoDisponible(exception.getMessage());
    }


}
