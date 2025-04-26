package gob.ypfb.lumira.utils.excel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {

    int posicionColumna() default 0;
}
