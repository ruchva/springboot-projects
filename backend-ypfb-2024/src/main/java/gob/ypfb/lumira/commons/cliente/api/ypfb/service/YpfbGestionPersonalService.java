/**
 * Project: lumira
 * Package: bo.gob.ypfb.nova
 * El archivo "YpfbGestionPersonalService.java" fue creado para el proyecto lumira
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 29/9/2023 15:17
 * @copyright: YPFB
 * @version: 1.0
 **/

package gob.ypfb.lumira.commons.cliente.api.ypfb.service;

import gob.ypfb.lumira.commons.cliente.api.ypfb.response.personal.MaeResponseDto;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.personal.PersonalResponseDto;
import gob.ypfb.lumira.commons.cliente.api.ypfb.response.personal.UnidadesResponseDto;
import gob.ypfb.lumira.commons.rest.ResponseRestGenerico;
import gob.ypfb.lumira.exception.YpfbServiceException;
import gob.ypfb.lumira.security.jwt.JwtServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Transactional
@Service
public class YpfbGestionPersonalService {
    @Value("${ypfb.rest.url-gestion-personal}")
    private String urlApiPersonal;

    @Autowired
    private JwtServiceContext jwtAuthenticationFilter;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<UnidadesResponseDto> obtenerUnidadesDependenteDeUnidad(String siglaUnidad) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtAuthenticationFilter.getToken());

            String ruta = urlApiPersonal + "/unidades/dependientes/" + siglaUnidad;

            HttpEntity<ResponseRestGenerico<List<UnidadesResponseDto>>> vEntidadSolicitud =
                new HttpEntity<>(null, headers);

            ResponseEntity<ResponseRestGenerico<List<UnidadesResponseDto>>> response =
                restTemplate.exchange(ruta, HttpMethod.GET, vEntidadSolicitud,
                    new ParameterizedTypeReference<ResponseRestGenerico<List<UnidadesResponseDto>>>() {
                    });

            ResponseRestGenerico<List<UnidadesResponseDto>> vRespuesta = response.getBody();
            return vRespuesta.getData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new YpfbServiceException("Serv. Personal -> " + e.getMessage());
        }
    }

    public List<PersonalResponseDto> obtenerPersonalDependenteDeUnidad(String sigla) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtAuthenticationFilter.getToken());

            String ruta = urlApiPersonal + "/empleado/buscar-por-area";

            String paramValue = sigla;
            UriComponentsBuilder builderUrl = UriComponentsBuilder.fromUriString(ruta)
                .queryParam("sigla", paramValue);


            HttpEntity<ResponseRestGenerico<List<PersonalResponseDto>>> vEntidadSolicitud =
                new HttpEntity<>(null, headers);

            ResponseEntity<ResponseRestGenerico<List<PersonalResponseDto>>> response =
                restTemplate.exchange(builderUrl.toUriString(), HttpMethod.GET, vEntidadSolicitud,
                    new ParameterizedTypeReference<ResponseRestGenerico<List<PersonalResponseDto>>>() {
                    });

            ResponseRestGenerico<List<PersonalResponseDto>> vRespuesta = response.getBody();
            return vRespuesta.getData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new YpfbServiceException("Serv. Personal -> " + e.getMessage());
        }
    }

    public MaeResponseDto obtenerInformacionDeLaMAEDeUnidad(String sigla) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtAuthenticationFilter.getToken());

            String ruta = urlApiPersonal + "/mae/" + sigla;

            HttpEntity<ResponseRestGenerico<MaeResponseDto>> vEntidadSolicitud =
                new HttpEntity<>(null, headers);

            ResponseEntity<ResponseRestGenerico<MaeResponseDto>> response =
                restTemplate.exchange(ruta, HttpMethod.GET, vEntidadSolicitud,
                    new ParameterizedTypeReference<ResponseRestGenerico<MaeResponseDto>>() {
                    });

            ResponseRestGenerico<MaeResponseDto> vRespuesta = response.getBody();
            return vRespuesta.getData();
        } catch (Exception e) {
            e.printStackTrace();
            throw new YpfbServiceException("Serv. Personal -> " + e.getMessage());
        }
    }

}
