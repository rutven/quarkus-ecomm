package name.legkodymov.ecom.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import name.legkodymov.ecom.model.Payment;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {
    public List<Payment> listByOrderId(Long orderId) {
        return list("order_id", orderId);
    }
}
