package name.legkodymov.ecom.processor;

import name.legkodymov.ecom.model.PaymentCreateEvent;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentProcessor {

    private static final Logger LOG = Logger.getLogger(PaymentProcessor.class);

    @Incoming("payment-create")
    public void processPaymentCreateEvent(PaymentCreateEvent event) {
        LOG.info("Payment create event = " + event.toString());
    }
}
