package ezpay.io.security.controller;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ezpay.io.security.dto.*;
import ezpay.io.security.entity.User;
import ezpay.io.security.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService mAuthService;
    private final AuthenticationManager mAuthenticationManager;
    
    @PostMapping("register")
    public String addUser(@RequestBody UserDto user) {
        return mAuthService.saveUser(user);
    }
    
    @GetMapping("token")
    public String getToken(@RequestBody AuthRequest auth) {
        Authentication authentication = mAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
        if (authentication.isAuthenticated())
            return mAuthService.generateToken(auth.getEmail());
        else
            throw new RuntimeException("invalid access");
    }
    
    @GetMapping("validate")
    public boolean validateToken(@RequestParam String token) {
        return mAuthService.validateToken(token);
    }
    
    
}
