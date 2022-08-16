package com.alkemy.challenge.dto;

public class Response {
    private String token;

    public Response()
    {

    }

    public Response(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
