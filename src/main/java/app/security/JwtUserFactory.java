package app.security;

import app.model.Authority;
import app.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {

    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getPesel(),
                user.getPhone(),
                user.getStreet(),
                user.getSnumber(),
                user.getBnumber(),
                user.getPostcode(),
                user.getCity(),
                user.getEmail(),
                user.getPassword(),
                user.getWorkplaceid(),
                user.getEnabled(),
                user.getLastpasswordresetdate(),
                mapToGrantedAuthorities(user.getAuthorities())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
