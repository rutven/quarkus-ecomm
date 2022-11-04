package name.legkodymov.ecom;

import name.legkodymov.ecom.model.Order;
import name.legkodymov.ecom.model.OrderItem;
import name.legkodymov.ecom.repository.OrderRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    OrderRepository orderRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        createTestOrder();

        return "Hello from RESTEasy Reactive";
    }

//    @Transactional
//    private void createTestOrder() {
//        Order order = new Order();
//        OrderItem item1 = new OrderItem();
//        item1.setProductId(1L);
//        item1.setAmount(2);
//        item1.setPrice(2.0);
//        order.addOrderItem(item1);
//
//        orderRepository.persist(order);
//    }
}