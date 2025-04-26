package gob.ypfb.lumira.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "config.app")
public class AppProperties {
    private Boolean aplicacionDebug;
}
