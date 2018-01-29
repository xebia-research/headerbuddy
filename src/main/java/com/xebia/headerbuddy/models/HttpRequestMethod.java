package com.xebia.headerbuddy.models;

public enum HttpRequestMethod {
    GET("GET"), CONNECT("POST"), DELETE("DELETE"), HEAD("HEAD"), OPTIONS("OPTIONS"), PATCH("POST"), POST("POST"), PUT("PUT"), TRACE("TRACE");

    private final String requestMethod;

    HttpRequestMethod(final String method) {
        this.requestMethod = method;
    }

    public String requestMethod() {
        return requestMethod;
    }
}
