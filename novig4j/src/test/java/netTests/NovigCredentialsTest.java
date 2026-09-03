package netTests;

import io.github.novig4j.http.NovigCredentials;
import io.github.novig4j.http.NovigEnvironment;
import io.github.novig4j.http.NovigHttpClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NovigCredentialsTest {


    @Test
    void testHttpClientBuilder(){

        NovigCredentials credentials = new NovigCredentials("TEST", "BEST");

        NovigHttpClient client = new NovigHttpClient.Builder()
                .credentials(credentials)
                .environment(NovigEnvironment.QA)
                .build();

        assertAll(
    //            () -> assertEquals("TEST", client.getCredentials().client_id()),
                () -> assertEquals(NovigEnvironment.QA, client.getEnvironment())
        );
    }

    @Test
    void testHttpClientBuilderWithoutClientId(){

        assertThrows(IllegalStateException.class,
                () -> {
                    NovigCredentials credentials = new NovigCredentials("", "TEST");
                    NovigHttpClient client = new NovigHttpClient.Builder().credentials(credentials).environment(NovigEnvironment.QA).build();
            }, "Client id must not be null");
    }

    @Test
    void testHttpClientBuilderWithoutSecret(){

        assertThrows(IllegalStateException.class,
                () -> {
                    NovigCredentials credentials = new NovigCredentials("asdf", "");
                    NovigHttpClient client = new NovigHttpClient.Builder().credentials(credentials).environment(NovigEnvironment.QA).build();
            }, "Client Secret must not be null");
    }

}