/**
 * Project: sistema
 * Package: bo.gob.ypfb
 * El archivo "JwtServiceContext.java" fue creado para el proyecto sistema
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 29/9/2023 12:12
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.ypfb.lumira.security.jwt;


import gob.ypfb.lumira.security.properties.RSAKeyConfigProperties;
import gob.ypfb.lumira.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class JwtServiceContext {
    private JwtTokenContext jwtTokenContextAplicacion;
    @Autowired
    RSAKeyConfigProperties rsaKeyConfigProperties;

    @Autowired
    private HttpServletRequest request;

    private String ip;
    private String token;

    public JwtTokenContext getJwtTokenContextAplicacion() {
        getAuthentication();
        return jwtTokenContextAplicacion;
    }

    private void getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
            // Obtener el token
            String token = (String) authToken.getCredentials();
            jwtTokenContextAplicacion = JwtTokenUtil.getJwtTokenContext(token, rsaKeyConfigProperties.getPublicKey());
            this.token = token;
            this.ip = request.getRemoteAddr();
        }
    }

    public String getIp() {
        getJwtTokenContextAplicacion();
        return this.ip;
    }

    public String getToken() {
        getAuthentication();
        return this.token;
    }
}
