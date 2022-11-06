package name.legkodymov.ecom.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import name.legkodymov.ecom.model.OrderNotification;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderNotificationRepository implements PanacheRepository<OrderNotification> {
}
