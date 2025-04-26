package gob.ypfb.lumira.commons.cliente.api.ypfb.request;

import gob.ypfb.lumira.commons.cliente.api.ypfb.entities.Metadato;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DocumentoDmsRequest implements Serializable {
    private String mimeType;
    private String length;
    private String path;
    private String name;
    private String hash;
    private String dominio;
    private String typeFile;
    private String file;
    private List<Metadato> metadato;
}
