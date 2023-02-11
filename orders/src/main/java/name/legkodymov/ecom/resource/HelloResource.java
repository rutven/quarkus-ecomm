package name.legkodymov.ecom.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class HelloResource {

    @GET
    public String hello() {
        return "Hello, world!";
    }

    @GET
    @Path("/user/{name}")
    public String helloUser(@PathParam("name") String name) {
        return "Hello, " + name + "!";
    }
}
