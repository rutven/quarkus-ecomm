package name.legkodymov.ecom.processor;

import name.legkodymov.ecom.model.OrderNotificationEvent;
import name.legkodymov.ecom.model.Payment;
import name.legkodymov.ecom.service.PaymentService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class OrderNotificationProcessor {

    private static final Logger LOG = Logger.getLogger(OrderNotificationProcessor.class);

    @Inject
    PaymentService paymentService;

    @Incoming("order-notifications")
    @Transactional
    public void process(OrderNotificationEvent event) {
        LOG.info("Received order notification event - " + event.toString());
        Payment payment = paymentService.processOrder(event);
        LOG.info("Payment = " + payment.toString());
    }
}
