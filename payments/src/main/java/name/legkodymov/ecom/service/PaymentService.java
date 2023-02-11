package name.legkodymov.ecom.service;

import name.legkodymov.ecom.model.OrderNotificationEvent;
import name.legkodymov.ecom.model.Payment;
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
    public Payment processOrder(OrderNotificationEvent event) {
        Payment payment = new Payment();
        payment.setOrderId(event.getOrderId());
        payment.setAmount(event.getTotalPrice());
        processPayment(payment);
        paymentRepository.persist(payment);
        return payment;
    }

    @Transactional
    private void processPayment(Payment payment) {
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentId(new Random().nextLong());
    }
}
