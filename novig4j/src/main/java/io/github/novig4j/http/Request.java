package io.github.novig4j.http;

import java.util.HashMap;
import java.util.Map;

public record Request(HttpMethod method, String path, Map<String, String> headers, Map<String, String> queryParams,
                      String body) {

    public Request {
        if (method == null) {
            throw new IllegalStateException("Request needs a method");
        }

        if (path == null) {
            throw new IllegalStateException("Request needs a path");
        }

        headers = Map.copyOf(headers);
        queryParams = Map.copyOf(queryParams);

    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder {
        private HttpMethod method;
        private String path;
        private final Map<String, String> headers = new HashMap<>();
        private final Map<String, String> queryParams = new HashMap<>();
        private String body;

        public Builder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        public Builder path(String uri) {
            this.path = uri;
            return this;
        }

        public Builder headers(String name, String value) {
            headers.put(name, value);
            return this;
        }

        public Builder queryParams(String name, String value) {
            queryParams.put(name, value);
            return this;
        }

        public Builder body(String bodyText) {
            this.body = bodyText;
            return this;
        }

        public Request build() {
            if (method == null) {
                throw new IllegalStateException("Request needs Method");
            }
            if (path == null) {
                throw new IllegalStateException("Request needs a Path");
            }

            return new Request(method, path, headers, queryParams, body);
        }


    }

}

