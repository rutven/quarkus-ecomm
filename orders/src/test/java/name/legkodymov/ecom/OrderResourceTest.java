package name.legkodymov.ecom;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class OrderResourceTest {

    @Test
    public void testGetAllEndpoint() {
        given()
                .when().get("/orders")
                .then()
                .statusCode(200);
    }

}