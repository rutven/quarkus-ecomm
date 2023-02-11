package name.legkodymov.ecom.model;

public class OrderNotificationEvent {
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

    public OrderNotificationEvent() {
    }

    @Override
    public String toString() {
        return "OrderNotificationEvent{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
