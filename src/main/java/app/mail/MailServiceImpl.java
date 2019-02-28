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
    public void sendEmail(Integer bookingId, String type) throws SendFailedException {
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("isrp.zpi@gmail.com");
        javaMailSender.setPassword("wsiz#123");
        String to = "katarzyna.dyduch@ultramedic.com.pl";
        BookingDetails bookingDetails = bookingDetailsRepository.findOne(bookingId);
        User patient = userRepository.getOne(bookingDetails.getUser_id());
        String mess;
        if (type.equals("book")) {
            mess = "Nowa rezerwacja\nImię i nazwisko pacjenta: " + bookingDetails.getPatient() +
                    "\nTelefon kontaktowy: " + patient.getPhone() +
                    "\nTyp badania: " + bookingDetails.getDescription() +
                    "\nData: " + bookingDetails.getDate();
        } else {
            mess = "Anulowanie rezerwacji\nImię i nazwisko pacjenta: " + bookingDetails.getPatient() +
                    "\nTelefon kontaktowy: " + patient.getPhone() +
                    "\nTyp badania: " + bookingDetails.getDescription() +
                    "\nData: " + bookingDetails.getDate();
        }

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom("isrp.zpi@gmail.com", "Portal Pacjenta");
                helper.setTo(to);
                if (type.equals("book")) {

                    helper.setSubject("Nowa rezerwacja");
                    String rootPath = System.getProperty("user.dir");
                    FileSystemResource file = new FileSystemResource(rootPath +  "\\attachments\\" + bookingDetails.getAttachmentpath());
                    helper.addAttachment(file.getFilename(), file);
                } else {
                    helper.setSubject("Anulowanie rezerwacji");
                }
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
