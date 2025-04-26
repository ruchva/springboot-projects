package gob.ypfb.lumira.app.administracion.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "trs_usuarios_externos", schema = "sumira")
public class UsuariosExternos {
    @Id
    @Column(name = "id")
    private Long id;
}
