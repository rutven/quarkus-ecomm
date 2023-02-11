package name.legkodymov.ecom.producer;

import io.quarkus.scheduler.Scheduled;
import name.legkodymov.ecom.model.NotificationStatus;
import name.legkodymov.ecom.model.OrderNotification;
import name.legkodymov.ecom.model.OrderNotificationEvent;
import name.legkodymov.ecom.repository.OrderNotificationRepository;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class NotificationProducer {

    private static final Logger LOG = Logger.getLogger(NotificationProducer.class);

    @Inject
    OrderNotificationRepository notificationRepository;

    @Channel("order-notifications")
    Emitter<OrderNotificationEvent> orderNotificationEmitter;

    @Scheduled(every = "1s")
    @Transactional
    void getNewNotifications() {
        LOG.info("getNewNotifications");
        List<OrderNotification> notifications = notificationRepository.listNewNotifications();
        LOG.info("get " + notifications.size() + " notifications");
        for (OrderNotification notification : notifications) {
            processNotification(notification);
        }
    }

    @Transactional
    private void processNotification(OrderNotification notification) {
        LOG.info("Processing notification" + notification.toString());
        OrderNotificationEvent dao = new OrderNotificationEvent(notification);
        orderNotificationEmitter.send(dao);
        notification.setStatus(NotificationStatus.PROCESSED);
        notificationRepository.persistAndFlush(notification);
    }

}
