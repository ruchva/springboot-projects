package gob.ypfb.lumira.security.properties;

import gob.ypfb.lumira.security.utils.RSAUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

@Component
@ConfigurationProperties(prefix = "rsa.key")
public class RSAKeyConfigProperties {
    private String publicKeyFile;
    private PublicKey publicKey;

    @PostConstruct
    void createRSAKey() throws Exception {
        //  System.out.println(" >> RSAKeyConfigProperties > createRSAKey : " + getPublicKeyFile());
        this.setPublicKey(RSAUtils.getPublicKey(getPublicKeyFile()));
    }

    public String getPublicKeyFile() {
        return publicKeyFile;
    }

    public void setPublicKeyFile(String publicKeyFile) {
        this.publicKeyFile = publicKeyFile;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }


}
