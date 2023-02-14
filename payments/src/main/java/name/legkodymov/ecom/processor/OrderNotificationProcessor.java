package name.legkodymov.ecom.processor;

import name.legkodymov.ecom.model.OrderNotificationEvent;
import name.legkodymov.ecom.model.PaymentCreateEvent;
import name.legkodymov.ecom.service.PaymentService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class OrderNotificationProcessor {

    private static final Logger LOG = Logger.getLogger(OrderNotificationProcessor.class);

    @Inject
    PaymentService paymentService;

    @Incoming("order-create")
    @Outgoing("payment-create")
    @Transactional
    public PaymentCreateEvent process(OrderNotificationEvent orderEvent) {
        LOG.info("Received order notification event - " + orderEvent.toString());
        PaymentCreateEvent paymentEvent = paymentService.createPayment(orderEvent);
        LOG.info("PaymentEvent = " + paymentEvent.toString());
        return paymentEvent;
    }
}
