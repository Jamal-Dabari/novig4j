package netTests;
import io.github.novig4j.http.HttpMethod;
import io.github.novig4j.http.NovigCredentials;
import io.github.novig4j.http.NovigEnvironment;
import io.github.novig4j.http.NovigHttpClient;
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
