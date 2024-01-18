package raf.sk.sk_notification_service.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raf.sk.sk_notification_service.inter_service_comunication.EmailNotificationDto;
import raf.sk.sk_notification_service.service.api.EmailServiceApi;

@RestController
@RequestMapping("/mailing")
public class NotificationController {

    private final EmailServiceApi emailServiceApi;

    public NotificationController(EmailServiceApi emailServiceApi) {
        this.emailServiceApi = emailServiceApi;
    }

    @PostMapping(path = "/sendEmail")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailNotificationDto emailNotificationDto) {
        emailServiceApi.sendEmail(
                emailNotificationDto.getEmailToNotify(),
                emailNotificationDto.getSubject(),
                emailNotificationDto.getEmailToNotify()
        );
        return ResponseEntity.ok().build();
    }
}
