package io.github.novig4j.http;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

final class RequestFactory {

    public static HttpRequest toHttpRequest(Request r){
        final HttpRequest req;
        String url = r.environment().getRestUrl() + r.path();

        if(r.queryParams() != null && !r.queryParams().isEmpty()) {
            final String queryString = r.queryParams().entrySet().stream()
                    .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));

            url += "?" + queryString;
        }

        if (r.headers() != null){
            r.headers().forEach((key, val) -> {
                if (key != null && val != null){

                }
            });
        }



        return switch (r.method()){
            case HttpMethod.GET -> req = HttpRequest.newBuilder().uri(URI.create(url)).build();
            case HttpMethod.POST -> req = HttpRequest.newBuilder().uri(URI.create(url)).POST(HttpRequest.BodyPublishers.ofString(r.body())).build();
            case HttpMethod.DELETE -> req = HttpRequest.newBuilder().uri(URI.create(url)).DELETE().build();
        };
    }


}
