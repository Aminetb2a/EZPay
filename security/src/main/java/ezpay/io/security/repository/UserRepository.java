package ezpay.io.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ezpay.io.security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    
}
