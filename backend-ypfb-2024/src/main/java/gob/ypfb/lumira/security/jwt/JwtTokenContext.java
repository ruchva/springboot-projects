package gob.ypfb.lumira.security.jwt;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtTokenContext implements Serializable {

    private String displayName;
    private String username;
    private String email;
    private String accessToken;

    /*private Long instancia;
    private String ci;
    private String empresaInstitucion;
    private String cliente;
    private HashMap<Long, String> roles;
    */
    @Override
    public String toString() {
        return "JwtTokenContext{" +
            "displayName='" + displayName + '\'' +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", accessToken='" + accessToken + '\'' +
            '}';
    }
}
