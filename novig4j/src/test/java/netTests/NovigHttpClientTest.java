package netTests;

import net.NovigEnvironment;
import net.NovigHttpClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NovigHttpClientTest {


    @Test
    void testHttpClientBuilder(){

        NovigHttpClient client = new NovigHttpClient.Builder()
                .clientId("test")
                .clientSecret("test")
                .environment(NovigEnvironment.QA)
                .build();

        assertAll(
                () -> assertEquals("test", client.getClientId()),
                () -> assertEquals("test", client.getClientSecret()),
                () -> assertEquals(NovigEnvironment.QA, client.getEnvironment())
        );
    }

}