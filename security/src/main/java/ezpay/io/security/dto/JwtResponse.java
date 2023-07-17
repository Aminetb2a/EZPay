package ezpay.io.security.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtResponse {
    private String token;
    @JsonProperty("refresh_token")
    private String refreshToken;
}
