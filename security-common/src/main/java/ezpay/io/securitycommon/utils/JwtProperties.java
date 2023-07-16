package ezpay.io.securitycommon.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    
    private String jwtSecret;
    private Integer jwtExpirationMs;
    
    public String getJwtSecret() {
        return jwtSecret;
    }
    
    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }
    
    public Integer getJwtExpirationMs() {
        return jwtExpirationMs;
    }
    
    public void setJwtExpirationMs(Integer jwtExpirationMs) {
        this.jwtExpirationMs = jwtExpirationMs;
    }
}
