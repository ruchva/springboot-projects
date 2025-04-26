package gob.ypfb.lumira.commons.cliente.api.ypfb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.ParamCuentaUsuarioResquest;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.ParamPersonaUsuarioResquest;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.PersonaUsuarioResponse;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.UsuarioResponse;
import gob.ypfb.lumira.commons.rest.ResponseRestGenerico;
import gob.ypfb.lumira.exception.InvalidException;
import gob.ypfb.lumira.exception.YpfbException;
import gob.ypfb.lumira.exception.YpfbServiceException;
import gob.ypfb.lumira.security.jwt.JwtServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Transactional
@Service
public class YpfbCerberoService {

    @Value("${ypfb.rest.url-cebero}")
    private String urlApiCerbero;

    @Autowired
    private JwtServiceContext jwtServiceContextr;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<UsuarioResponse> obtenerUsuarios() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtServiceContextr.getToken());

            String ruta = urlApiCerbero + "/usuarios";

            HttpEntity<ResponseRestGenerico<List<UsuarioResponse>>> vEntidadSolicitud = new HttpEntity<>(null, headers);

            ResponseEntity<ResponseRestGenerico<List<UsuarioResponse>>> response = restTemplate.exchange(ruta, HttpMethod.GET, vEntidadSolicitud,
                new ParameterizedTypeReference<ResponseRestGenerico<List<UsuarioResponse>>>() {
                });

            ResponseRestGenerico<List<UsuarioResponse>> vRespuesta = response.getBody();
            return vRespuesta.getData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new YpfbException("Serv. Cerbero -> " + e.getMessage());
        }
    }

    public PersonaUsuarioResponse crearCuentaUsuario(ParamCuentaUsuarioResquest cuentaUsuarioResquest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtServiceContextr.getToken());

            String ruta = urlApiCerbero + "/personas/" + cuentaUsuarioResquest.getIdPersona() + "/cuentaUsuario";

            String json = "";
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(cuentaUsuarioResquest);

            HttpEntity<String> vEntidadSolicitud = new HttpEntity<String>(json, headers);

            ResponseEntity<ResponseRestGenerico<PersonaUsuarioResponse>> response = restTemplate.exchange(ruta, HttpMethod.POST, vEntidadSolicitud,
                new ParameterizedTypeReference<ResponseRestGenerico<PersonaUsuarioResponse>>() {
                });
            ResponseRestGenerico<PersonaUsuarioResponse> vRespuesta = response.getBody();
            return vRespuesta.getData();
        } catch (Exception e) {
            throw new YpfbServiceException("Serv. Cerbero -> " + e.getMessage());
        }
    }

    public PersonaUsuarioResponse crearPersonaUsuario(ParamPersonaUsuarioResquest personaUsuarioResquest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtServiceContextr.getToken());

        String ruta = urlApiCerbero + "/cuentaUsuario/externo";
        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(personaUsuarioResquest);
        } catch (JsonProcessingException e) {
            throw new InvalidException("Parametro invalido");
        }
        HttpEntity<String> vEntidadSolicitud = new HttpEntity<>(json, headers);

        ResponseEntity<ResponseRestGenerico<PersonaUsuarioResponse>> response = restTemplate.exchange(ruta, HttpMethod.POST, vEntidadSolicitud,
            new ParameterizedTypeReference<ResponseRestGenerico<PersonaUsuarioResponse>>() {
            });

        ResponseRestGenerico<PersonaUsuarioResponse> vRespuesta = response.getBody();

        return vRespuesta.getData();
    }
}
