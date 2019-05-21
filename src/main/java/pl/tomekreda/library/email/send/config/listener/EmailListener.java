package pl.tomekreda.library.email.send.config.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tomekreda.library.email.send.sender.EmailSender;

import java.util.Map;

@Component
public class EmailListener {

    @Autowired
    private EmailSender emailSender;


    public void receiveMessage(Map<String, String> message) {
        emailSender.sendEmail(message.get("to"),message.get("title"),message.get("body"));
    }
}