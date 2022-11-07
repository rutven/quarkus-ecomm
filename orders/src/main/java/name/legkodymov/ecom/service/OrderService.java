package name.legkodymov.ecom.service;

import name.legkodymov.ecom.model.Order;
import name.legkodymov.ecom.model.OrderItem;
import name.legkodymov.ecom.model.OrderNotification;
import name.legkodymov.ecom.repository.OrderRepository;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.*;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class OrderService {

    private final static Logger LOG = Logger.getLogger(OrderService.class);

    @Channel("order-notifications")
    Emitter<OrderNotification> orderNotificationEmitter;

    @Inject
    OrderRepository orderRepository;

    @Inject
    UserTransaction transaction;

    public List<Order> getAll() {
        return orderRepository.findAll().stream().toList();
    }

    public Order getById(Long id) {
        return orderRepository.findById(id);
    }

    private Order createOrder(Order order) throws SystemException, NotSupportedException {
        transaction.begin();
        try {
            order.setCreatedAt(LocalDateTime.now());
            orderRepository.persist(order);
            transaction.commit();
        } catch (HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            transaction.rollback();
        }
        return order;
    }

    public Order createOrderAndSendNotification(Order order) {
        try {
            Order savedOrder = createOrder(order);
            sendOrderNotification(savedOrder);
            return savedOrder;
        } catch (SystemException | NotSupportedException e) {
            LOG.error("Order creation failed - " + e.getMessage(), e);
            return order;
        }
    }

    public void sendOrderNotification(Order order) {
        OrderNotification notification = new OrderNotification();
        notification.setOrderId(order.getId());
        notification.setUserId(order.getUserId());
        notification.setTotalPrice(order.getTotalPrice());
        orderNotificationEmitter.send(notification);
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
        return createOrderAndSendNotification(order);
    }
}
