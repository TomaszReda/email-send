package pl.tomekreda.library.email.send.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class EmailSender {

    @Qualifier("getJavaMailSender")
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String emailAddressFrom;

    public void sendEmail(String to, String title, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setFrom(emailAddressFrom);
            helper.setSubject(title);
            helper.setText(content, true);
            log.info(("Send email to " + to + " with title " + title + " and content " + content));
        } catch (MessagingException e) {
        }
        Executors.newSingleThreadExecutor().execute(() -> javaMailSender.send(mail));
    }
}