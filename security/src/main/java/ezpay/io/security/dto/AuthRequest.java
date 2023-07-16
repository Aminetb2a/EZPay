package ezpay.io.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthRequest {
    private String email;
    private String password;
}
