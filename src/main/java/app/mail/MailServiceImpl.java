package app.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    public JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    @Override
    public void sendEmail(String to, String mess) throws SendFailedException {
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("isrp.zpi@gmail.com");
        javaMailSender.setPassword("wsiz#1234");

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom("isrp.zpi@gmail.com");
                mimeMessage.setRecipient( Message.RecipientType.TO, new InternetAddress( to ));
                mimeMessage.setSubject("Potwierdzenie zgłoszenia wizyty na portalu ISRP");
                mimeMessage.setHeader("Content-Type", "text/html; charset=UTF-8");
                mimeMessage.setText(mess);
            }
        };
        try {
            javaMailSender.send(preparator);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
