package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void send(
            @RequestParam(value = "mail") String mail,
            @RequestParam(value = "mess") String mess
    ) throws SendFailedException {
        mailService.sendEmail(mail, mess);
    }

}
