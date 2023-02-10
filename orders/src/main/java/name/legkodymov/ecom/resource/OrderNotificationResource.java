package name.legkodymov.ecom.resource;

import name.legkodymov.ecom.model.OrderNotification;
import name.legkodymov.ecom.repository.OrderNotificationRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/notifications")
@Produces(MediaType.APPLICATION_JSON)
public class OrderNotificationResource {

    @Inject
    OrderNotificationRepository notificationRepository;
    @GET
    public List<OrderNotification> getALl() {
        return notificationRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public List<OrderNotification> getByOrderId(@PathParam("id") Long id) {
        return notificationRepository.listByOrderId(id);
    }

    @GET
    @Path("user/{id}")
    public List<OrderNotification> getByUserId(@PathParam("id")Long userId) {
        return notificationRepository.listByUserId(userId);
    }
}
