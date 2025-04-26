package gob.ypfb.lumira.commons.cliente.api.ypfb.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.ClienteRestRequest;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.LoggerRequest;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.LogResponse;
import gob.ypfb.lumira.commons.rest.ResponseRest;
import gob.ypfb.lumira.exception.NotFoundException;
import gob.ypfb.lumira.exception.YpfbException;
import gob.ypfb.lumira.security.jwt.JwtServiceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.List;

@Service
@Slf4j(topic = "YpfbLoggerService")
public class YpfbLoggerService extends YpfbService {

    @Value("${ypfb.rest.url-logger}")
    private String urlLogger;

    @Value("${ypfb.app.logger.enable}")
    private boolean enableLogger;

    @Autowired
    private JwtServiceContext jwtServiceContext;
    @Autowired
    private YPFBClienteRest ypfbClienteRest;
    private final ObjectMapper objectMapper = new ObjectMapper();

    //ERROR("ERROR"), WARN("WARN"), INFO("INFO"), DEBUG("DEBUG"), LOG("LOG");

    public List<LogResponse> obtenerBitacoras() {
        try {
            ClienteRestRequest clienteRestRequest = new ClienteRestRequest();
            clienteRestRequest.setApiUrl(urlLogger);
            ResponseRest responseRest = ypfbClienteRest.get(clienteRestRequest);
            JsonNode jsonNode = getJsonNode(responseRest);

            TypeReference<List<LogResponse>> typeReference = new TypeReference<List<LogResponse>>() {
            };

            List<LogResponse> loggerResponses = objectMapper.readValue(jsonNode.toString(), typeReference);

            return loggerResponses;

        } catch (Exception e) {
            log.error(" > Serv. Logger: {} ", e.getMessage());
            throw new YpfbException(e.getMessage());
        }
    }

    public void log(String bitacora, String dominio) {
        trace(createLogger("LOG", dominio, bitacora));
    }

    public void log(String bitacora) {
        trace(createLogger("LOG", "BITACORA", bitacora));
    }

/*    public void info(String log, String dominio) {
        trace(createLogger("INFO",dominio, log));
    }

    public void info(String log) {
        trace(createLogger("INFO","INFO", log));
    }*/

    public void error(String mensajeError, String dominio) {
        trace(createLogger("ERROR", dominio, mensajeError));
    }

    public void error(String mensajeError) {
        trace(createLogger("ERROR", "ERROR", mensajeError));
    }

    private void trace(LoggerRequest logger) {
        if (!enableLogger) {
            log.info("El envío de logger NO habilitado");
            return;
        }
        try {
            ClienteRestRequest clienteRestRequest = new ClienteRestRequest();
            clienteRestRequest.setApiUrl(urlLogger);
            clienteRestRequest.setData(logger);
            ResponseRest responseRest = ypfbClienteRest.post(clienteRestRequest);

            log.info(" Respuesta: {}", responseRest.getData());

        } catch (HttpClientErrorException he) {
            log.error(" > HttpClientErrorException : {} | {} ", he.getStatusText(), he.getMessage());
            throw new NotFoundException("[" + he.getStatusText() + "] Verificar > " + urlLogger + "");
        } catch (Exception e) {
            log.error(" > Serv. Logger: {} ", e.getMessage());
            throw new YpfbException(e.getMessage());
        }
    }

    private LoggerRequest createLogger(String level, String dominio, String log) {
        return LoggerRequest.builder()
            .fecha(new Date())
            .dominio(dominio)
            .level(level)
            .host(obtenerIpCliente())
            .usuario(obtenerUsuarioToken())
            .objetoId("0")
            .log(log)
            .build();
    }

    private String obtenerUsuarioToken() {
        return jwtServiceContext.getJwtTokenContextAplicacion().getUsername();
    }

    private String obtenerIpCliente() {
        //Todo: obtener del token o del header
        return "0.0.0.0";
    }
}
