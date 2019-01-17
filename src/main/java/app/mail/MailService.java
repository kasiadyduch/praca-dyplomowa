package app.mail;

import javax.mail.SendFailedException;

public interface MailService {
    void sendEmail(String to, String mess) throws SendFailedException;
}
