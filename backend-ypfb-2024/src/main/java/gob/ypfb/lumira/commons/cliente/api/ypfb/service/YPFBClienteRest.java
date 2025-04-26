/* ================================================================
 * El archivo "ClienteRest" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 13/7/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.commons.cliente.api.ypfb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gob.ypfb.lumira.commons.cliente.api.ypfb.request.ClienteRestRequest;
import gob.ypfb.lumira.commons.rest.ResponseRest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j(topic = "YPFBClienteRest")
@Service
public class YPFBClienteRest {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String CHARTSET = "UTF-8";
    private static final int HTTP_CONNECT_TIMEOUT = 30000;
    private static final int HTTP_READ_TIMEOUT = 20000;

    private final ObjectMapper objectMapper = new ObjectMapper();

    //TODO: falta la implementacion

    public ResponseRest get(ClienteRestRequest clienteRestRequest) {
        clienteRestRequest.setMethod("GET");
        return sendRequest(clienteRestRequest);
    }

    public ResponseRest post(ClienteRestRequest clienteRestRequest) {
        clienteRestRequest.setMethod("POST");
        return sendRequest(clienteRestRequest);
    }

    public ResponseRest put(ClienteRestRequest clienteRestRequest) {
        clienteRestRequest.setMethod("PUT");
        return sendRequest(clienteRestRequest);
    }

    public ResponseRest delete(ClienteRestRequest clienteRestRequest) {
        clienteRestRequest.setMethod("DELETE");
        return sendRequest(clienteRestRequest);
    }

    private ResponseRest sendRequest(ClienteRestRequest clienteRestRequest) {
        String host = "";
        try {
            String apiUrl = clienteRestRequest.getApiUrl();

            if (!StringUtils.isNotEmpty(apiUrl)) {
                throw new MalformedURLException("El Dominio del servicio de API es nulo");
            }
            List<String> values = Arrays.asList("GET", "PUT", "DELETE");

            log.info("apiUrl: {}", apiUrl);

            //GET: https://api.ypfb.gob.bo/v1/1222
            if (values.contains(clienteRestRequest.getMethod()) && StringUtils.isNotEmpty(clienteRestRequest.getId()))
                apiUrl = apiUrl + "/" + clienteRestRequest.getId();

            if (clienteRestRequest.getMethod().equals("GET") && !clienteRestRequest.getParameters().isEmpty())
                apiUrl = apiUrl + "" + formatURL(clienteRestRequest.getParameters());

            URL urlRest = new URL(apiUrl);
            HttpURLConnection http = (HttpURLConnection) urlRest.openConnection();

            http.setRequestMethod(clienteRestRequest.getMethod());
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("User-Agent", USER_AGENT);
            http.setRequestProperty("Accept-Charset", CHARTSET);
            http.setConnectTimeout(HTTP_CONNECT_TIMEOUT);
            http.setReadTimeout(HTTP_READ_TIMEOUT);
            http.setRequestProperty("Connection", "Keep-Alive");

            if (StringUtils.isNotEmpty(clienteRestRequest.getAccessToken()))
                http.setRequestProperty("YPFB-Access-Token", clienteRestRequest.getAccessToken());

            if (StringUtils.isNotEmpty(clienteRestRequest.getBearerToken()))
                http.setRequestProperty("Authorization", "Bearer " + clienteRestRequest.getBearerToken());

            clienteRestRequest.getHeaders().forEach((key, value) -> {
                http.setRequestProperty(String.valueOf(key), String.valueOf(value));
            });

            if (clienteRestRequest.getMethod().equals("GET") || clienteRestRequest.getMethod().equals("DELETE")) {
                http.setDoOutput(true);
            } else if (clienteRestRequest.getMethod().equals("POST") || clienteRestRequest.getMethod().equals("PUT")) {
                http.setUseCaches(false);
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream outStream = http.getOutputStream();

                String data = objectMapper.writeValueAsString(clienteRestRequest.getData());

                outStream.write(data.getBytes());
                outStream.flush();
            }

            int code = http.getResponseCode();

            log.info("code: {}", code);
            JSONObject response;
            if (code == HttpURLConnection.HTTP_BAD_REQUEST
                || code == HttpURLConnection.HTTP_NOT_FOUND
                || code == HttpURLConnection.HTTP_FORBIDDEN
                || code == HttpURLConnection.HTTP_INTERNAL_ERROR
                || code == HttpURLConnection.HTTP_BAD_GATEWAY
                || code == HttpURLConnection.HTTP_UNAUTHORIZED
            ) {
                response = formatResponse(http.getErrorStream());
                http.disconnect();
                log.info(" >>> response: {}", response);
                ResponseRest restResponse = createResponse(response, false);
                restResponse.setError(response.has("error") ? response.get("error").toString() : "");
                if (restResponse.getCode() == 400) { //httpStatus
                    restResponse.setErrorList(response.has("errorList") ? objectMapper.readTree(response.get("errorList").toString()) : null);
                }

                return restResponse;

            }

            if (!(code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED || code == HttpURLConnection.HTTP_ACCEPTED || code == HttpURLConnection.HTTP_NO_CONTENT)) {
                throw new RuntimeException("Failed : HTTP error code : " + http.getResponseCode());
            }

            response = formatResponse(http.getInputStream());

            http.disconnect();

            return createResponse(response, true);

        } catch (UnknownHostException une) {
            //println "ERROR: Cannot access ${host}"
            return ResponseRest.builder().success(false).code(404).message(une.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseRest.builder().success(false).code(500).message(e.getMessage()).build();
        }
    }

    private JSONObject formatResponse(InputStream inputStream) throws JSONException, IOException {
        String output = "";

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuffer response = new StringBuffer();

        while ((output = bufferedReader.readLine()) != null) {
            response.append(output);
        }
        //log.info("response : {}", response);

        if (isValidJson(response.toString()))
            return new JSONObject(String.valueOf(response));

        JSONObject json = new JSONObject();
        json.put("status", 404);
        json.put("message", response);

        return json;
    }

    private String formatURL(HashMap parameters) {
        String query = "";
        try {
            if (!parameters.isEmpty() && paramsString(parameters).length() > 0)
                query = "?" + paramsString(parameters);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return query;
    }

    private String paramsString(HashMap params) throws UnsupportedEncodingException {

        StringBuilder result = new StringBuilder();

        params.forEach((key, value) -> {
            result.append(URLEncoder.encode(key.toString(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), StandardCharsets.UTF_8));
            result.append("&");
        });

        String resultString = result.toString();

        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }

    private ResponseRest createResponse(JSONObject response, boolean success) throws JSONException, JsonProcessingException {
        int httpStatus = success ? 200 : 500;

        int code = (int) (response.has("code") ? response.get("code") : (response.has("status") ? response.get("status") : httpStatus));

        JsonNode body = response.has("data") ? objectMapper.readTree(response.get("data").toString()) : null;

        return ResponseRest.builder()
            .success(response.has("success") ? (boolean) response.get("success") : success)
            .code(Integer.parseInt(String.valueOf(code)))
            .message(response.has("message") ? response.get("message").toString() : " S/M")
            .data(body)
            .error(response.has("error") ? response.get("error").toString() : "")
            .build();
    }

    protected boolean isValidJson(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException je) {
            return false;
        }

        return true;
    }
}
