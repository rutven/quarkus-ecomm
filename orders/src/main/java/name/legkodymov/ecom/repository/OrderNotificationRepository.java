package name.legkodymov.ecom.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import name.legkodymov.ecom.model.NotificationStatus;
import name.legkodymov.ecom.model.OrderNotification;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class OrderNotificationRepository implements PanacheRepository<OrderNotification> {
    public List<OrderNotification> listByOrderId(Long id) {
        return list("order_id", id);
    }

    public List<OrderNotification> listByUserId(Long userId) {
        return list("user_id", userId);
    }

    public List<OrderNotification> listNewNotifications() {
        return list("status", NotificationStatus.CREATED);
    }
}
