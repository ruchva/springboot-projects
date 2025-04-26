package gob.ypfb.lumira.utils.excel;

import gob.ypfb.lumira.exception.YpfbServiceException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

public class DocumentoExcelUtil {

    XSSFWorkbook archivoExcel;

    XSSFSheet sheet;

    protected final String HOJA = "Hoja1";

    public void obtenerArchivoXlsx(String pathExcel) {
        try {
            ClassPathResource classPathResource = new ClassPathResource(pathExcel);
            InputStream in = classPathResource.getInputStream();
            archivoExcel = new XSSFWorkbook(in);

            sheet = archivoExcel.getSheet(HOJA);


        } catch (IOException e) {
            throw new YpfbServiceException("Plantilla inexistente");
        }
    }

    public byte[] generarExcel() {

        byte[] vResultado = null;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
         /*   archivoExcel.write(bos);

            bos.close();
            archivoExcel.close();*/
            File archivo = new File("ejemplo.xlsx");
            FileOutputStream out = new FileOutputStream(archivo);
            archivoExcel.write(out);
            out.close();
        } catch (IOException e) {
            throw new YpfbServiceException("Plantilla inexistente");
        }
        // vResultado = bos.toByteArray();

        return vResultado;
    }

    public <T extends Object> void escribirListaEnExcel(List<T> lista, int filaInicio, int colInicio, Class<T> clase) {

        try {
            for (T vObject : lista) {
                Field[] campos = vObject.getClass().getDeclaredFields();

                for (Field campoPropiedad : campos) {
                    campoPropiedad.setAccessible(true);
                    Object campo = campoPropiedad.get(vObject);

                    //  campo.setAccessible(true);
                    ExcelAnnotation propiedadAnotacion = campoPropiedad.getAnnotation(ExcelAnnotation.class);
                    System.out.println(campo);
                }

            }
        } catch (IllegalAccessException e) {
            throw new YpfbServiceException("Error: Acceso propiedad");
        }

    }


}
