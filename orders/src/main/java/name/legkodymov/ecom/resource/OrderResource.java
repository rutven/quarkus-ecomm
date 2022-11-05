package name.legkodymov.ecom.resource;

import name.legkodymov.ecom.model.Order;
import name.legkodymov.ecom.repository.OrderRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderRepository repository;


    @GET
    public List<Order> getAll() {
        return repository.findAll().stream().toList();
    }

    @GET
    @Path("/{id}")
    public Order getById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        repository.persist(order);
        return order;
    }

    @GET
    @Path("/test")
    @Transactional
    public Order createTestOrder() {
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setUserId(1L);
        order.setTotalPrice(10.0);
        repository.persist(order);
        return order;
    }
}
