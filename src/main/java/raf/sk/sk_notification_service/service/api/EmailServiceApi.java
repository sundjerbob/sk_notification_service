package raf.sk.sk_notification_service.service.api;

public interface EmailServiceApi {


    void sendEmail(String to, String subject, String body);
}
