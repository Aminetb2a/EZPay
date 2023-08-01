package ezpay.io.gatewayApi.filter;

import java.util.List;
import java.util.function.Predicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
    
    public static final List<String> openEndpoints =  List.of(
            "/eureka",
            "/auth/token",
            "/auth/register",
            "/actuator/*",
            "/webjars/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/swagger-ui/**",
            "/api/public/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/configuration/ui",
            "/swagger-resources",
            "/auth/v3/api-docs/**",
            "/swagger-resources/**",
            "/configuration/security",
            "/api/public/authenticate"
            );
    
    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
