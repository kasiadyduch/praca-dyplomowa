package app.mail;

import javax.mail.SendFailedException;

public interface MailService {
    void sendEmail(Integer bookindId) throws SendFailedException;
}
