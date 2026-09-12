package io.github.novig4j.http;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

final class RequestFactory {
    private final URI base;

    public RequestFactory(URI base) {
        this.base = base;
    }

    HttpRequest toHttpRequest(Request r, String bearer){

        String url = base + r.path();

        if(r.queryParams() != null && !r.queryParams().isEmpty()) {
            final String queryString = r.queryParams().entrySet().stream()
                    .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8)
                            .replace("+", "%20")
                            .replace("%2c", ",") + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8)
                            .replace("+", "%20")
                            .replace("%2C", ","))
                    .collect(Collectors.joining("&"));

            url += "?" + queryString;
        }


        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(URI.create(url)).timeout(Duration.of(5, ChronoUnit.SECONDS));
        HttpRequest.BodyPublisher publisher = r.body() == null ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(r.body());

        if (r.headers() != null){
            r.headers().forEach((key, val) -> {
                    builder.headers(key, val);
            });

            if (bearer != null) builder.header("Authorization", "Bearer " + bearer);
        }

        return switch (r.method()){
            case HttpMethod.GET -> builder.GET().build();
            case HttpMethod.POST -> builder.POST(publisher).build();
            case HttpMethod.DELETE -> builder.method("DELETE", publisher).build();

        };
    }


}
