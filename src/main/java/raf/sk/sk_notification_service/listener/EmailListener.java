package raf.sk.sk_notification_service.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import raf.sk.sk_notification_service.inter_service_comunication.EmailNotificationDto;
import raf.sk.sk_notification_service.listener.helper.MessageHelper;
import raf.sk.sk_notification_service.service.api.EmailServiceApi;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class EmailListener {

    private final MessageHelper messageHelper;
    private final EmailServiceApi emailService;

    public EmailListener(MessageHelper messageHelper, EmailServiceApi emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.sendNotification}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        // listen for messages on this que (you should receive the EmailNotificationDto)
        EmailNotificationDto notification = messageHelper.getMessage(message, EmailNotificationDto.class);
        // send the notification via MailSender and persist the messages
        emailService.sendEmail(notification.getEmailToNotify(), notification.getSubject(), notification.getMessage());
    }
}
