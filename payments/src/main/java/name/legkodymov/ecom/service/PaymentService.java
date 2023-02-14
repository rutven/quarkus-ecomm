package name.legkodymov.ecom.service;

import name.legkodymov.ecom.model.OrderNotificationEvent;
import name.legkodymov.ecom.model.Payment;
import name.legkodymov.ecom.model.PaymentCreateEvent;
import name.legkodymov.ecom.model.PaymentStatus;
import name.legkodymov.ecom.repository.PaymentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Random;

@ApplicationScoped
public class PaymentService {

    @Inject
    PaymentRepository paymentRepository;

    @Transactional
    public PaymentCreateEvent createPayment(OrderNotificationEvent orderNotificationEvent) {
        Payment payment = new Payment();
        payment.setOrderId(orderNotificationEvent.getOrderId());
        payment.setAmount(orderNotificationEvent.getTotalPrice());
        payment.setCreated(LocalDateTime.now());
        payment.setStatus(PaymentStatus.CREATED);
        paymentRepository.persist(payment);
        PaymentCreateEvent paymentCreateEvent = new PaymentCreateEvent();
        paymentCreateEvent.setPaymentId(payment.getId());
        paymentCreateEvent.setOrderId(payment.getOrderId());
        return paymentCreateEvent;
    }

    private void processPayment(Payment payment) {
        payment.setPaymentDate(LocalDateTime.now());
        payment.setTransactionId(new Random().nextLong());
    }
}
