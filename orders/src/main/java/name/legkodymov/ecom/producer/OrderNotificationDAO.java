package name.legkodymov.ecom.producer;

import name.legkodymov.ecom.model.OrderNotification;

import javax.persistence.Column;

public class OrderNotificationDAO {
    private Long id;
    private Long orderId;
    private Long userId;
    private Double totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderNotificationDAO() {
    }
    public OrderNotificationDAO(OrderNotification notification) {
        this.id = notification.getId();
        this.orderId = notification.getOrderId();
        this.userId = notification.getUserId();
        this.totalPrice = notification.getTotalPrice();
    }
}
