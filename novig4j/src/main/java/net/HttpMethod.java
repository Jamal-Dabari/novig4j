package net;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    DELETE("DELETE");

    private final String methodName;

    private HttpMethod(String methodName){
        this.methodName = methodName;
    }
}
