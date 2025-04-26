/* ================================================================
 * El archivo "ClientRestTemplate" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 13/7/23
 * @copyright: YPFB
 * ==============================================================
 */

package gob.ypfb.lumira.commons.cliente.api.ypfb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

@Service
@Slf4j(topic = "ClienteRestTemplate")
public class ClienteRestTemplate {

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> get(String url) throws JsonProcessingException {

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        log.info(response.getBody());

        Gson g = new Gson();
        Properties properties = g.fromJson(response.getBody(), Properties.class);
        boolean success = Boolean.parseBoolean(properties.get("success").toString());

        return response;
    }

    public ResponseEntity<String> post(String url, Object value) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(value);

        HttpEntity<String> request = new HttpEntity<>(json, createHttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        log.info(response.getBody());

        return response;
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setAcceptCharset(List.of(StandardCharsets.UTF_8));

        return headers;
    }
}
