package app.config;

import org.springframework.stereotype.Component;
import app.model.User;
import app.model.Workplace;

@Component
public class Validator {
    public boolean isUserValid(User u) {
        return !(isEmptyString(u.getLastname()) || isEmptyString(u.getPesel()));
    }
    public boolean isWorkplaceValid(Workplace w) {
        return !isEmptyString(w.getName());
    }
    public boolean isEmptyString(String s) {
        return (s == null || s.trim().length() == 0);
    }
}
