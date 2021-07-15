package com.example.cookingv2.data.server.model;

public class ErrorJson {
    public final int httpCode;
    public final String reasonCode;
    public final String reasonStatus;

    public ErrorJson(int httpCode, String reasonCode, String reasonStatus) {
        this.httpCode = httpCode;
        this.reasonCode = reasonCode;
        this.reasonStatus = reasonStatus;
    }
}
