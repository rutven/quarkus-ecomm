package name.legkodymov.ecom.service;

import name.legkodymov.ecom.model.Order;
import name.legkodymov.ecom.model.OrderItem;
import name.legkodymov.ecom.model.OrderNotification;
import name.legkodymov.ecom.repository.OrderNotificationRepository;
import name.legkodymov.ecom.repository.OrderRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class OrderService {

    private final static Logger LOG = Logger.getLogger(OrderService.class);

    @Inject
    OrderRepository orderRepository;

    @Inject
    OrderNotificationRepository notificationRepository;

    public List<Order> getAll() {
        return orderRepository.findAll().stream().toList();
    }

    public Order getById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        orderRepository.persist(order);
        saveOrderNotification(order);
        return order;
    }

    private void saveOrderNotification(Order order) {
        OrderNotification notification = new OrderNotification();
        notification.setOrderId(order.getId());
        notification.setUserId(order.getUserId());
        notification.setTotalPrice(order.getTotalPrice());
        notificationRepository.persist(notification);
    }

    public Order createTestOrder() {
        Order order = new Order();
        order.setUserId(1L);
        order.setTotalPrice(10.0);
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setAmount(1);
        item.setPrice(10.0);
        item.setProductId(2L);
        order.getItems().add(item);
        return createOrder(order);
    }
}
