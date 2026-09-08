package io.github.novig4j.http;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RequestTests {
    final String path = "Test";

    @Test
    void buildsGetRequestWithMethodAndPath() {
        Request request = Request.builder().method(HttpMethod.GET).path("TESTING").queryParams("Test", "Test").headers("Testing", "Testing").build();

        assertAll("GET request",
                () -> assertEquals(HttpMethod.GET, request.method()),
                () -> assertEquals("TESTING", request.path()));
    }

    @Test
    void buildsPostRequestWithMethodPathAndBody(){
        Request request = Request.builder().method(HttpMethod.POST).path("Testing").body("THIS IS A TEST BODY").build();

        assertAll("POST request", () -> assertEquals(HttpMethod.POST, request.method()),
                () -> assertEquals("Testing", request.path()), () -> assertEquals("THIS IS A TEST BODY", request.body()));
    }

    @Test
    void builderStoresHeaderByName(){
        Request request = Request.builder().method(HttpMethod.GET).path(path).headers("accept", "application/json").build();
        assertEquals("application/json", request.headers().get("accept"));
    }

    @Test
    void builderStoresQueryParamByName(){
        Request request = Request.builder().method(HttpMethod.GET).path(path).queryParams("Dogs", "limit=500").build();
        assertEquals("limit=500", request.queryParams().get("Dogs"));
    }

    @Test
    void builderStoresBody(){
        Request request = Request.builder().method(HttpMethod.POST).path(path).body("THIS IS TEST").build();
        assertEquals("THIS IS TEST", request.body());
    }

    @Test
    void buildThrowsWhenMethodIsMissing(){
        assertThrowsExactly(IllegalArgumentException.class, () -> Request.builder().path("test").build());
    }

    @Test
    void buildThrowsWhenPathIsMissing(){
        assertThrowsExactly(IllegalArgumentException.class, () -> Request.builder().method(HttpMethod.POST).build());
    }


    @Test
    void toHttpRequestAssemblesUriAndEncodesSpaces(){
        // todo remove null token manager once auth is done
        RequestFactory factory = new RequestFactory(NovigEnvironment.QA, null);
        Request request = Request.builder().method(HttpMethod.GET).path("markets").queryParams("q", "two words").build();
        HttpRequest httpRequest = factory.toHttpRequest(request);
        assertEquals("https://api-qa.novig.us/nbx/v2/markets?q=two%20words", httpRequest.uri().toString());
    }



    @Test
    void toHttpRequestAssemblesUriAndEncodesCommas(){
        // todo remove null token manager once auth is done
        RequestFactory factory = new RequestFactory(NovigEnvironment.QA, null);
        Request request = Request.builder().method(HttpMethod.GET).path("markets").queryParams("outcomeIds", "1,2,3,4,5").build();
        HttpRequest httpRequest = factory.toHttpRequest(request);
        assertEquals("https://api-qa.novig.us/nbx/v2/markets?outcomeIds=1,2,3,4,5", httpRequest.uri().toString());
    }

    @Test
    void toHttpRequestAssemblesUriWithMultipleQueryParams(){
        // todo remove null token manager once auth is done
        RequestFactory factory = new RequestFactory(NovigEnvironment.QA, null);
        Request request = Request.builder().method(HttpMethod.GET).path("markets").queryParams("a", "b").queryParams("c", "d").build();
        URI uri = factory.toHttpRequest(request).uri();

        // Map.copyOf iteration order is randomised per JVM run, so assert the
        // pairs rather than a fixed ordering of the query string.
        assertEquals("https://api-qa.novig.us/nbx/v2/markets", uri.toString().split("\\?")[0]);
        assertEquals(Set.of("a=b", "c=d"), Set.of(uri.getQuery().split("&")));
    }



    @Test
    void testRequestImmutability(){
        Request.Builder b = Request.builder().method(HttpMethod.POST).path("test").queryParams("test1", "test2");
        Request req = b.build();
        b.queryParams("change1", "change2");
        assertNull(req.queryParams().get("change1"));

    }




}
