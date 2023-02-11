package name.legkodymov.ecom.service;

import name.legkodymov.ecom.model.NotificationStatus;
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
        notification.setStatus(NotificationStatus.CREATED);
        notificationRepository.persist(notification);
    }

    public Order createTestOrder() {
        Order order = new Order();
        order.setUserId(generateLong(1, 100));
        int itemQuantity = generateInt(1, 5);
        for (int i = 0; i < itemQuantity; i++) {
            OrderItem item = createOrderItem();
            item.setOrder(order);
            order.getItems().add(item);
        }
        order.setTotalPrice(calculateTotal(order.getItems()));
        return createOrder(order);
    }

    private OrderItem createOrderItem() {
        OrderItem item = new OrderItem();
        item.setAmount(generateInt(1, 10));
        item.setPrice(generateDouble(1, 100));
        item.setProductId(generateLong(1, 100));
        return item;
    }

    private Double calculateTotal(List<OrderItem> items) {
        double total = 0.0;
        for (OrderItem item : items) {
            total = total + item.getPrice();
        }
        return total;
    }

    private Integer generateInt(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }

    private Double generateDouble(int min, int max) {
        return min + (Math.random() * (max - min));
    }

    private Long generateLong(long min, long max) {
        return min + (long) (Math.random() * (max - min));
    }
}
