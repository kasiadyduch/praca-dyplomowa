package app.mail;

import app.model.BookingDetails;
import app.model.User;
import app.repository.BookingDetailsRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    @Autowired
    public BookingDetailsRepository bookingDetailsRepository;

    @Autowired
    public UserRepository userRepository;

    @Override
    public void sendEmail(Integer bookingId) throws SendFailedException {
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("isrp.zpi@gmail.com");
        javaMailSender.setPassword("wsiz#1234");
        String to = "karolinakozak9@gmail.com";
        BookingDetails bookingDetails = bookingDetailsRepository.findOne(bookingId);
        User patient = userRepository.getOne(bookingDetails.getUser_id());
        String mess = "Nowa rezerwacja\nImię i nazwisko pacjenta: " + bookingDetails.getPatient() +
                "\nTelefon kontaktowy: " + patient.getPhone() +
                "\nTyp badania: " + bookingDetails.getDescription() +
                "\nData: " + bookingDetails.getDate();

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom("isrp.zpi@gmail.com");
                helper.setTo(to);
                helper.setSubject("Nowa rezerwacja");
//                helper.setHeader("Content-Type", "text/html; charset=UTF-8");
                FileSystemResource file = new FileSystemResource(bookingDetails.getAttachmentpath());
                helper.addAttachment(file.getFilename(), file);
                helper.setText(mess);
            }
        };
        try {
            javaMailSender.send(preparator);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
