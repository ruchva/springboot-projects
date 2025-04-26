package gob.ypfb.lumira.commons.cliente.api.ypfb.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class DocumentoDmsResponse implements Serializable {
    private String id;
    private String mimeType;
    @JsonIgnore
    private String length;
    private String path;
    private String name;
    private String dominio;
    private String file;

    @JsonIgnore
    private String fechaRegistro;
}
