package ezpay.io.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {
    @JsonProperty("refresh_token")
    private String refreshToken;
}
