package name.legkodymov.ecom;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class PaymentResourceTest {

    @Test
    public void testGetAllEndpoint() {
        given()
                .when().get("/payments")
                .then()
                .statusCode(200);
    }

}