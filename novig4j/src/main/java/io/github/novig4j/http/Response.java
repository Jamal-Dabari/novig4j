package io.github.novig4j.http;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public record Response(int statusCode, InputStream body, Map<String, String> headers, String url, String version) {

    public Response{
        headers = Map.copyOf(headers);
    }


    public Builder builder() {
        return new Builder();
    }

    public static final class Builder{
       private int statusCode;
       private InputStream body;
       private Map<String, String> headers = new HashMap<>();
       private String url;
       private String version;

       public Builder statusCode(int statusCode){
           this.statusCode = statusCode;
           return this;
       }

       public Builder body(InputStream body){
           this.body = body;
           return this;
       }

       public Builder header(String name, String value){

           if (name == null || value == null){
               throw new IllegalArgumentException("query cannot be null");
           }

           headers.put(name.toLowerCase(), value.toLowerCase());

           return this;
       }

       public Builder url(String url){
           this.url = url;
           return this;
       }

        public Builder version(String version){
            this.version = version;
            return this;
        }

        public Response build(){
           return new Response(statusCode,body, headers, url, version);
        }

    }


}
