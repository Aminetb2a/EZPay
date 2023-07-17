package ezpay.io.security.service;

import java.time.Instant;
import java.util.*;
import org.springframework.stereotype.Service;
import ezpay.io.security.entity.*;
import ezpay.io.security.repository.*;
import ezpay.io.securitycommon.utils.JwtProperties;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final JwtProperties mJwtProperties;
    private final UserRepository mUserRepository;
    private final RefreshTokenRepository mRefreshTokenRepository;
    
    public RefreshToken refreshToken(String email) {
        User user = mUserRepository.findByEmail(email).get();
        revokeToken(user);
        RefreshToken refreshToken = RefreshToken.builder()
                .refreshToken(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(Instant.now().plusMillis(mJwtProperties.getJwtExpirationMs()))
                .build();
        return mRefreshTokenRepository.save(refreshToken);
    }
    
    private void revokeToken(User user) {
        mRefreshTokenRepository.findByUser(user)
                .ifPresent(mRefreshTokenRepository::delete);
    }
    
    public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
        return mRefreshTokenRepository.findByRefreshToken(refreshToken);
    }
    
    public RefreshToken verifyExpiration(RefreshToken token) {
        if(token.getExpiryDate().compareTo(Instant.now()) < 0) {
            token.setExpiryDate(Instant.now().plusMillis(mJwtProperties.getJwtExpirationMs()));
            mRefreshTokenRepository.save(token);
        }
        return token;
    }
}
