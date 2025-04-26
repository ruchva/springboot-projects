package gob.ypfb.lumira.commons.cliente.api.ypfb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.ClienteRestRequest;
import gob.ypfb.lumira.commons.rest.ResponseRest;
import gob.ypfb.lumira.exception.NotFoundException;
import gob.ypfb.lumira.exception.YpfbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Slf4j(topic = "YpfbCalendarioService")
public class YpfbCalendarioService extends YpfbService {

    @Value("${ypfb.rest.url-gestion-personal}")
    private String urlApiCalendario;

    @Autowired
    private YPFBClienteRest ypfbClienteRest;

    private final ObjectMapper objectMapper = new ObjectMapper();

/*    public ResponseRestGenegicDto<String> obtenerSumaDiasHabiles(String fecha, Integer dias) {

        String url = urlApiCalendario + "/sumaDiasHabiles?fecha={f}&dias={d}";

        ResponseRestGenegicDto<String> vRespuesta = restTemplate.getForObject(url, ResponseRestGenegicDto.class, fecha, dias);

        return vRespuesta;
    }*/

    /**
     * Obtiene los dias habiles entre dos fechas
     *
     * @param fechaIni - fecha inicial formato(dd/mm/yyyy)
     * @param fechaFin - fecha final formato(dd/mm/yyyy)
     * @return
     */
    public Integer obtenerDiasHabilesDelAl(String fechaIni, String fechaFin) {
        //https://testgestionpersonal.ypfb.gob.bo/api-gestion-personal/v1/diasHabilesDelAl?del=01/01/2023&al=31/12/2023
        String urlCalendario = urlApiCalendario + "/diasHabilesDelAl";
        try {
            ClienteRestRequest clienteRestRequest = new ClienteRestRequest();
            clienteRestRequest.setApiUrl(urlCalendario);
            clienteRestRequest.addParameter("del", fechaIni);
            clienteRestRequest.addParameter("al", fechaFin);
            ResponseRest responseRest = ypfbClienteRest.get(clienteRestRequest);

            JsonNode jsonNode = getJsonNode(responseRest);
            Integer dia = objectMapper.readValue(jsonNode.toString(), Integer.class);

            return dia;
        } catch (
            HttpClientErrorException he) {
            log.error(" > HttpClientErrorException : {} | {} ", he.getStatusText(), he.getMessage());
            throw new NotFoundException("[" + he.getStatusText() + "] Verificar > " + urlCalendario + "");
        } catch (Exception e) {
            log.error(" > YpfbCalendarioService: {} ", e.getMessage());
            throw new YpfbException(e.getMessage());
        }
    }
}
