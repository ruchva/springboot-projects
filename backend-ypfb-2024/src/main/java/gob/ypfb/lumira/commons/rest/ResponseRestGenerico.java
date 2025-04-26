package gob.ypfb.lumira.commons.rest;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseRestGenerico<T> implements Serializable {
    private Long id;
    private Boolean success;
    private Integer code;
    private String message;
    private T data;
}
