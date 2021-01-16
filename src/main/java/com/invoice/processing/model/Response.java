package com.invoice.processing.model;

import org.springframework.http.HttpStatus;

public class Response {

    private String message;
    private String format;
    private HttpStatus httpStatus;

    public Response() {}

    public Response(String message, String format, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.format = format;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
