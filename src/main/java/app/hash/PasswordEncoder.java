package app.hash;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String hashPassword(String pass) {
        String hash = encoder.encode(pass);
        return hash;
    }

    public boolean checkPassword(String pass, String hash) {
        boolean doesMatch = encoder.matches(pass, hash);
        return doesMatch;
    }
}
