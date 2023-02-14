package name.legkodymov.ecom.model;

public class PaymentCreateEvent {

    private Long orderId;

    private Long paymentId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "PaymentCreateEvent{" +
                "orderId=" + orderId +
                ", paymentId=" + paymentId +
                '}';
    }
}
