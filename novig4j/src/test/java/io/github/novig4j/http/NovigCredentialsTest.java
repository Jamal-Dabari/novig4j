package io.github.novig4j.http;

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