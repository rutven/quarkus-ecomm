package name.legkodymov.ecom.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import name.legkodymov.ecom.model.Order;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {
}
