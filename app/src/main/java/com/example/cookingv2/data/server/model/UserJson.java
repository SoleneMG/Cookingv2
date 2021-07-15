package com.example.cookingv2.data.server.model;

public class UserJson {
    public final String publicId, email, id;

    public UserJson(String publicId, String email, String id) {
        this.publicId = publicId;
        this.email = email;
        this.id = id;
    }
}
