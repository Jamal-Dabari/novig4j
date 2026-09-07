package io.github.novig4j.http;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

final class RequestFactory {

    public static HttpRequest toHttpRequest(Request r){

        String url = r.environment().getRestUrl() + r.path();

        if(r.queryParams() != null && !r.queryParams().isEmpty()) {
            final String queryString = r.queryParams().entrySet().stream()
                    .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));

            url += "?" + queryString;
        }


        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(URI.create(url));
        HttpRequest.BodyPublisher publisher = r.body() == null ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(r.body());

        if (r.headers() != null){
            r.headers().forEach((key, val) -> {
                if (key != null && val != null){
                    if (key.equals("Authorization")){

                        // todo: Attach the token here
                    }

                    builder.headers(key, val);
                }
            });
        }

        return switch (r.method()){
            case HttpMethod.GET -> builder.GET().build();
            case HttpMethod.POST -> builder.POST(publisher).build();
            case HttpMethod.DELETE -> builder.DELETE().build();

        };
    }


}
