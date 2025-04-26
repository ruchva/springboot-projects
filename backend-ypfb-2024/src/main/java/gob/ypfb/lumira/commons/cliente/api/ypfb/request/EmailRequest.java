package gob.ypfb.lumira.commons.cliente.api.ypfb.request;

import gob.ypfb.lumira.commons.cliente.api.ypfb.entities.EmailAdjunto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailRequest {

    private String idAplicacion;
    private String to;
    private String subject;
    private String body;
    private String cc;

    private List<EmailAdjunto> attach;
}
