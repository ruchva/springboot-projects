package gob.ypfb.lumira.commons.cliente.api.ypfb.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class DmsResponse implements Serializable {
    private String id;
    private Boolean created;
    private String dms;
    /*    private Boolean success;
    private Integer code;
    private String message;
    private JsonNode data;*/
}
