package ezpay.io.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ezpay.io.security.entity.*;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByRefreshToken(String aRefreshToken);
    
    Optional<RefreshToken> findByUser(User aUser);
    
    void deleteAllByUser(User aUser);
}
