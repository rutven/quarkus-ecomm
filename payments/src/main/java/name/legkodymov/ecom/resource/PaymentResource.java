package name.legkodymov.ecom.resource;

import name.legkodymov.ecom.model.Payment;
import name.legkodymov.ecom.repository.PaymentRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Inject
    PaymentRepository paymentRepository;

    @GET
    public List<Payment> getAll() {
        return paymentRepository.listAll();
    }

    @GET
    @Path("/order/{id}")
    public List<Payment> getByOrderId(@PathParam("id") Long orderId) {
        return paymentRepository.listByOrderId(orderId);
    }
}
