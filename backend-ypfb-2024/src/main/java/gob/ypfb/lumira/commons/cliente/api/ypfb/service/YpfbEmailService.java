package gob.ypfb.lumira.commons.cliente.api.ypfb.service;

import gob.ypfb.lumira.commons.cliente.api.ypfb.request.ClienteRestRequest;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.EmailRequest;
import gob.ypfb.lumira.commons.rest.ResponseRest;
import gob.ypfb.lumira.config.ConstantesConfig;
import gob.ypfb.lumira.exception.NotFoundException;
import gob.ypfb.lumira.exception.ServiceUnavailableException;
import gob.ypfb.lumira.exception.YpfbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j(topic = "YpfbEmailService")
public class YpfbEmailService {

    @Value("${ypfb.rest.url-email}")
    private String urlEmail;

    @Value("${ypfb.app.ambiente}")
    private String ambiente;

    @Value("${ypfb.app.codigo}")
    private String codigo;

    @Value("${ypfb.app.email.enable}")
    private boolean enableEmail;

    @Autowired
    private YPFBClienteRest ypfbClienteRest;

    public ResponseRest enviarSync(EmailRequest email) {
        if (!enableEmail) {
            throw new ServiceUnavailableException("El envío de email NO habilitado");
        }

        return enviar(email);
    }

    public void enviarAsync(EmailRequest email) {
        if (!enableEmail) {
            log.info("El envío de email NO habilitado");
            return;
        }

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            enviar(email);
        });
    }

    private ResponseRest enviar(EmailRequest email) {
        try {
            formatSubject(email);
            ClienteRestRequest clienteRestRequest = new ClienteRestRequest();
            clienteRestRequest.setApiUrl(urlEmail);
            clienteRestRequest.setData(email);

            ResponseRest responseRest = ypfbClienteRest.post(clienteRestRequest);
            log.info(" Respuesta: {}", responseRest.getData());
            return responseRest;

        } catch (HttpClientErrorException he) {
            log.error(" > HttpClientErrorException : {} | {} ", he.getStatusText(), he.getMessage());
            throw new NotFoundException("[" + he.getStatusText() + "] Verificar > " + urlEmail + "");
        } catch (Exception e) {
            log.error(" > Exception: {} ", e.getMessage());
            throw new YpfbException(e.getMessage());
        }
    }

    private void formatSubject(EmailRequest email) {
        if (!this.ambiente.equals(ConstantesConfig.PRODUCCION)) {
            email.setSubject("[YPFB-" + codigo + "]" + email.getSubject());
        } else {
            email.setSubject(
                "[" + codigo + "- " + this.ambiente.toUpperCase() + "]" + email.getSubject());
            //request.put("to", emailConfig.emailDefaultTo)
        }
    }
}
