package name.legkodymov.ecom.model;

import javax.persistence.*;

@Entity
@Table(name = "order_notifications")
public class OrderNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name ="order_id")
    private Long orderId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "total_price")
    private Double totalPrice;

    @Enumerated
    private NotificationStatus status;

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

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OrderNotification{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                '}';
    }
}
