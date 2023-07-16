package ezpay.io.security.service;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ezpay.io.security.entity.User;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    
    
    private String name;
    
    private String email;
    
    @JsonIgnore
    private String password;
    
    
    public UserDetailsImpl(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getUsername() {
        return name;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}
