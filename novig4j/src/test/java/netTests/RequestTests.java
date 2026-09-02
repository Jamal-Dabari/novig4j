package netTests;

import net.HttpMethod;
import net.Request;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTests {
    final String path = "Test";

    @Test
    void testGetRequestBuilder() {
        Request request = Request.builder().method(HttpMethod.GET).path("TESTING").queryParams("Test", "Test").headers("Testing", "Testing").build();

        assertAll("Request properties",
                () -> assertEquals(HttpMethod.GET, request.method()),
                () -> assertEquals("TESTING", request.path()));
    }

    @Test
    void testPostRequestBuilder(){
        Request request = Request.builder().method(HttpMethod.POST).path("Testing").body("THIS IS A TEST BODY").build();

        assertAll("POST request properties", () -> assertEquals(HttpMethod.POST, request.method()),
                () -> assertEquals("Testing", request.path()), () -> assertEquals("THIS IS A TEST BODY", request.body()));
    }

    @Test
    void testRequestHeaders(){
        Request request = Request.builder().method(HttpMethod.GET).path(path).headers("accept", "application/json").build();
        assertEquals("application/json", request.headers().get("accept"));
    }

    @Test
    void testRequestQueryParams(){
        Request request = Request.builder().method(HttpMethod.GET).path(path).queryParams("Dogs", "limit=500").build();
        assertEquals("limit=500", request.queryParams().get("Dogs"));
    }

    @Test
    void testRequestBody(){
        Request request = Request.builder().method(HttpMethod.POST).path(path).body("THIS IS TEST").build();
        assertEquals("THIS IS TEST", request.body());
    }

    @Test
    void testFailedRequestBuild(){
        assertThrowsExactly(IllegalStateException.class, () -> Request.builder().build());
    }




}
