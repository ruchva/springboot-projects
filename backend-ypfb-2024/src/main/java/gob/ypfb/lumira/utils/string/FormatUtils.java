/* ================================================================
 * El archivo "FormatUtils" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 25/5/23
 * @copyright: YPFB
 * ==============================================================
 */

package gob.ypfb.lumira.utils.string;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;


public class FormatUtils {
    static final String FORMATO_FECHA = "dd/MM/yyyy";
    static final String FORMATO_FECHA_HTML5 = "yyyy-MM-dd";
    static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm:ss";
    static final String FORMATO_FECHA_HORA_JSON = "dd-MM-yyyy hh:mm:ss";
    static final String FORMATO_FECHA_JSON = "dd-MM-yyyy";
    static final String FORMATO_FECHA_HORA_REP = "dd/MM/yyyy - HH:mm";
    static final String FORMATO_FECHA_HORA_AM_PM = "dd/MM/yyyy hh:mm aa";
    static final String FORMATO_HORA_AM_PM = "HH:mm aa";
    static final String FORMATO_HORA = "HH:mm";
    static final String HORA_FIN = "23:59:59";

    public static int dateGetYear(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        fecha = fecha == null ? new Date() : fecha;
        calendar.setTime(fecha);

        return calendar.get(Calendar.YEAR);
    }

    public static String limpiar(String txt) {
        return limpiar(txt, false);
    }

    public static String limpiar(String txt, boolean upperCase) {
        String result = "";
        if (StringUtils.isNotEmpty(txt)) {
            result = txt.trim().replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\t", "");
        }
        return upperCase ? result.toUpperCase() : result;
    }

    public static String likeString(String txt) {
        return "%" + txt.toLowerCase().replaceAll(" ", "%") + "%";
    }

    public static String quitarTildes(String txt) {
        String txtLimpio = "";
        if (StringUtils.isNotEmpty(txt)) {
            txtLimpio = txt.trim().toUpperCase().replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U");
        }
        return txtLimpio;
    }

    public static String cifrar(String txt) throws NoSuchAlgorithmException {
        return cifrar(txt, "SHA-256");
    }

    public static String cifrar(String txt, String metodo) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(metodo);
        byte[] hash = md.digest(txt.getBytes(StandardCharsets.UTF_8));

        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32) {
            hexString.insert(0, "0");
        }

        return hexString.toString();
    }
}
