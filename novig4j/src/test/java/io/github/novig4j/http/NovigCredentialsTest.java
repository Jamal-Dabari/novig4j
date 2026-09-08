package io.github.novig4j.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NovigCredentialsTest {


    @Test
    void testCredentialsDoesntLeak(){
        NovigCredentials credentials = new NovigCredentials("test", "testSecret");
        assertEquals("Credentials[clientId=***" + ", clientSecret=***]", credentials.toString());
    }

    @Test
    void testCredentialsThrowsWhenNullOrEmpty(){

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new NovigCredentials("", "")),
                () -> assertThrows(NullPointerException.class, () -> new NovigCredentials(null ,null))
        );

    }




}