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
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class NotificationProducer {

    private static final Logger LOG = Logger.getLogger(NotificationProducer.class);

    private final AtomicInteger notificationWorkersCount = new AtomicInteger(0);

    @Inject
    OrderNotificationRepository notificationRepository;

    @Channel("order-create")
    Emitter<OrderNotificationEvent> orderNotificationEmitter;

    @Scheduled(every = "1s")
    @Transactional
    void getNewNotifications() {
        if (notificationWorkersCount.compareAndSet(0, 1)) {
            LOG.info("check new notifications");
            List<OrderNotification> notifications = notificationRepository.listNewNotifications();
            if (notifications.size() > 0) {
                LOG.info("get " + notifications.size() + " notifications");
                for (OrderNotification notification : notifications) {
                    try {
                        processNotification(notification);
                    } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
            notificationWorkersCount.set(0);
        } else {
            LOG.info("worker already running");
        }
    }

    private void processNotification(OrderNotification notification) {
        LOG.info("Processing notification" + notification.toString());
        OrderNotificationEvent dao = new OrderNotificationEvent(notification);
        orderNotificationEmitter.send(dao);
        notification.setStatus(NotificationStatus.PROCESSED);
        notificationRepository.persistAndFlush(notification);
    }

}
