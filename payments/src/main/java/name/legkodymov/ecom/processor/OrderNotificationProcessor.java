package name.legkodymov.ecom.processor;

import name.legkodymov.ecom.model.OrderNotification;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderNotificationProcessor {

    private static final Logger LOG = Logger.getLogger(OrderNotificationProcessor.class);

    @Incoming("order-notifications")
    public void process(OrderNotification notification) {
        LOG.info("Processing order notification");
        LOG.info(notification);
    }
}
