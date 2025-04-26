package gob.ypfb.lumira.commons.cliente.api.ypfb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.ClienteRestRequest;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.DocumentoDmsRequest;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.DmsResponse;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.DocumentoDmsResponse;
import gob.ypfb.lumira.commons.rest.ResponseRest;
import gob.ypfb.lumira.exception.YpfbServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j(topic = "YpfbDocumentoService")
@Service
public class YpfbDocumentoService extends YpfbService {

    @Value("${ypfb.rest.url-documento}")
    private String urlApiDocumento;

    @Autowired
    private YPFBClienteRest ypfbClienteRest;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public DmsResponse guardarDocumento(DocumentoDmsRequest dmsRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        try {

            ClienteRestRequest clienteRestRequest = new ClienteRestRequest();
            clienteRestRequest.setApiUrl(urlApiDocumento);
            clienteRestRequest.setData(dmsRequest);

            ResponseRest responseRest = ypfbClienteRest.post(clienteRestRequest);

            JsonNode jsonNode = getJsonNode(responseRest);
            DmsResponse dmsResponse = objectMapper.treeToValue(jsonNode, DmsResponse.class);

            return dmsResponse;

        } catch (Exception e) {
            e.printStackTrace();
            throw new YpfbServiceException("Serv. Documentos -> " + e.getMessage());
        }
    }

    public DocumentoDmsResponse obtenerDocumentoPorId(String dmsId) {
        return obtenerDocumento(dmsId, false);
    }

    public DocumentoDmsResponse descargar(String dmsId) {
        return obtenerDocumento(dmsId, true);
    }

    private DocumentoDmsResponse obtenerDocumento(String dmsId, boolean withFile) {
        try {
            ClienteRestRequest clienteRestRequest = new ClienteRestRequest();

            clienteRestRequest.setApiUrl(urlApiDocumento);
            clienteRestRequest.setId(dmsId);

            if (withFile)
                clienteRestRequest.addHeader("withFile", "true");

            ResponseRest responseRest = ypfbClienteRest.get(clienteRestRequest);

            JsonNode jsonNode = getJsonNode(responseRest);
            DocumentoDmsResponse documentoDmsResponse = objectMapper.treeToValue(jsonNode, DocumentoDmsResponse.class);

            return documentoDmsResponse;

        } catch (Exception e) {
            e.printStackTrace();
            throw new YpfbServiceException("Serv. Documento -> " + e.getMessage());
        }
    }
}
