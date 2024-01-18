package raf.sk.sk_notification_service.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import raf.sk.sk_notification_service.entity_model.Notification;
import raf.sk.sk_notification_service.repository.NotificationRepository;
import raf.sk.sk_notification_service.service.api.EmailServiceApi;

@Service
public class EmailService implements EmailServiceApi {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    public EmailService(NotificationRepository notificationRepository, JavaMailSender mailSender) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        Notification notification = new Notification();
        notification.setReceiver(to);
        notification.setSubject(subject);
        notification.setText(body);
        notificationRepository.save(notification);
    }
}
