package gob.ypfb.lumira.app;

import gob.ypfb.lumira.config.AppProperties;
import gob.ypfb.lumira.exception.GlobalExceptionHandler;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"gob.ypfb.lumira.app", "gob.ypfb.lumira.commons.cliente", "gob.ypfb.lumira.security"})
@EnableConfigurationProperties(AppProperties.class)
@OpenAPIDefinition(info = @Info(title = "Sistema de Venta de Informacion - CNIH", version = "1.0", description = "Endpoints del Sistema de Venta de Informacion - CNIH (backend)"))
@Import(value = {GlobalExceptionHandler.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
