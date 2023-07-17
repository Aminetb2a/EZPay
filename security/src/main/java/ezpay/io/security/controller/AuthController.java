package ezpay.io.security.controller;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ezpay.io.security.dto.*;
import ezpay.io.security.entity.*;
import ezpay.io.security.service.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService mAuthService;
    private final RefreshTokenService mRefreshTokenService;
    private final AuthenticationManager mAuthenticationManager;
    
    @PostMapping("register")
    public String register(@RequestBody UserDto user) {
        return mAuthService.saveUser(user);
    }
    
    @GetMapping("token")
    public JwtResponse getToken(@RequestBody AuthRequest auth) {
        Authentication authentication = mAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = mRefreshTokenService.refreshToken(auth.getEmail());
            return JwtResponse.builder()
                    .token(mAuthService.generateToken(auth.getEmail()))
                    .refreshToken(refreshToken.getRefreshToken())
                    .build();
        }
        else
            throw new RuntimeException("Invalid access");
    }
    
    @GetMapping("validate")
    public boolean validateToken(@RequestParam String token) {
        return mAuthService.validateToken(token);
    }
    
    @PostMapping("refresh/token")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return mRefreshTokenService.findByRefreshToken(refreshTokenRequest.getRefreshToken())
                .map(mRefreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(usr -> {
                    String token = mAuthService.generateToken(usr.getEmail());
                    return JwtResponse.builder()
                            .token(token)
                            .refreshToken(refreshTokenRequest.getRefreshToken())
                            .build();
                })
                .orElseThrow(() -> new RuntimeException("Refresh Token Not Found"));
    }
}
