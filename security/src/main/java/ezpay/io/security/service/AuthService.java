package ezpay.io.security.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ezpay.io.security.dto.*;
import ezpay.io.security.entity.User;
import ezpay.io.security.repository.UserRepository;
import ezpay.io.securitycommon.utils.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserDTOMapper mapper;
    private final JwtService mJwtService;
    private final UserRepository mRepository;
    private final PasswordEncoder mPasswordEncoder;
    
    public String saveUser(UserDto userDto) {
        User user = mapper.mapToUser(userDto);
        user.setPassword(mPasswordEncoder.encode(user.getPassword()));
        mRepository.save(user);
        return "User created";
    }
    
    public boolean validateToken(String token) {
        return mJwtService.validateToken(token);
    }
    
    public String generateToken(String email) {
        return mJwtService.generateToken(email);
    }
    
}
