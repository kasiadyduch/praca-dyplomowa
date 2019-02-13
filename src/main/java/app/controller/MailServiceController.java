package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.SendFailedException;

import app.mail.MailService;
import app.mail.MailServiceImpl;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true", allowedHeaders = "GET, POST, PUT, OPTION, DELETE")
public class MailServiceController {

    @Autowired
    MailService mailService;

    public MailServiceController() {
        mailService = new MailServiceImpl();
    }

    @RequestMapping(value = "/send/{type}/{bookingId}", method = RequestMethod.GET)
    public void send(
            @PathVariable(value = "type") String type,
            @PathVariable(value = "bookingId") Integer bookingId
    ) throws SendFailedException {
        mailService.sendEmail(bookingId, type);
    }

}
