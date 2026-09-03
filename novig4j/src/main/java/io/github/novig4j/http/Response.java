package io.github.novig4j.http;

import java.util.List;
import java.util.Map;

public record Response(int statusCode, String body, Map<String, List<String>> headers) {

    public Response{
        headers = Map.copyOf(headers);
    }

}
