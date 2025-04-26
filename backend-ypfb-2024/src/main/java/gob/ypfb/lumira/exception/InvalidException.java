package gob.ypfb.lumira.exception;

import gob.ypfb.lumira.config.MensajesResponseRestGeneral;

public class InvalidException extends RuntimeException {

    public InvalidException(String mensaje) {
        super(MensajesResponseRestGeneral.TEXTO_INVALID_EXCEPTION + mensaje);
    }
}
