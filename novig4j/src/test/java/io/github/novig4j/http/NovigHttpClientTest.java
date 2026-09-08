package io.github.novig4j.http;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.*;

class NovigHttpClientTest {

    @Test
    void testClientBuilder(){
        NovigCredentials creds = new NovigCredentials("TEST", "TESTING");
        NovigHttpClient client = NovigHttpClient.builder().credentials(creds).environment(NovigEnvironment.QA).build() ;

        assertAll(

                () -> assertEquals(client.getEnvironment(), NovigEnvironment.QA),
                () -> assertEquals(client.clientId(), "TEST")
        );

    }


    @Test
    void testClientBuilderWithProvidedClient(){
        HttpClient cl = HttpClient.newHttpClient();
        NovigCredentials creds = new NovigCredentials("TEST", "TESTING");
        NovigHttpClient client = NovigHttpClient.builder().client(cl).credentials(creds).environment(NovigEnvironment.QA).build() ;

        assertAll(
                () -> assertSame(cl, client.client())
                );

    }




}
