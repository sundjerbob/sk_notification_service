package raf.sk.sk_notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.sk.sk_notification_service.entity_model.Notification;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


}
