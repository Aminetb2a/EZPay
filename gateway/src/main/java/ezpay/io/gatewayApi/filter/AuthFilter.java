package ezpay.io.gatewayApi.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import ezpay.io.securitycommon.utils.JwtService;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    
    private final JwtService mJwtService;
    private final RouteValidator routeValidator;
    public AuthFilter(JwtService aJwtService, RouteValidator aRouteValidator) {
        super(Config.class);
        mJwtService = aJwtService;
        routeValidator = aRouteValidator;
    }
    
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = null;
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                    throw new RuntimeException("Missing Authorization header");
                String token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (token != null && token.startsWith("Bearer"))
                    token = token.substring(7);
                try {
                    mJwtService.validateToken(token);
                    request = exchange.getRequest().mutate().header("email", mJwtService.extractUserEmail(token)).build();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Unauthorized access, please check your token.");
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }
    
    public static class Config {
    
    }
}
