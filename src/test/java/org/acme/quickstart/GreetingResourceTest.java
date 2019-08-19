package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        TestObject testObject = new TestObject("TestString", true, 42);
        TestObject result = given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .extract().as(TestObject.class);

        assertEquals(testObject, result);
    }

}
