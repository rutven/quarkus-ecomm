package name.legkodymov.ecom.resource;

import name.legkodymov.ecom.model.Order;
import name.legkodymov.ecom.service.OrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    OrderService orderService;

    @GET
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @GET
    @Path("/{id}")
    public Order getById(@PathParam("id") Long id) {
        return orderService.getById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Order createOrder(Order order) {
        return orderService.createOrderAndSendNotification(order);
    }

    @GET
    @Path("/test")
    public Order createTestOrder() {
        return orderService.createTestOrder();
    }
}
