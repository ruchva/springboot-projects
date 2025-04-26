package gob.ypfb.lumira.exception;

import org.apache.commons.lang3.StringUtils;

public class YpfbServiceException extends RuntimeException {

    private static final String DESCRIPTION = "YPFB Service Exception (500)";

    public YpfbServiceException(String message) {
        super(StringUtils.isEmpty(message) ? DESCRIPTION : message);
    }
}
