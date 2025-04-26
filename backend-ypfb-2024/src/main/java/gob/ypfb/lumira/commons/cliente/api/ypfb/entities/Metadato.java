package gob.ypfb.lumira.commons.cliente.api.ypfb.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Metadato implements Serializable {
    private String grupo;
    private List<Propiedad> propiedades;
}
