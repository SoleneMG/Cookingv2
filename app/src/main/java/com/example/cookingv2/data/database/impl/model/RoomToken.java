package com.example.cookingv2.data.database.impl.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "token")
public class RoomToken {
    @PrimaryKey
    @NonNull
    public final String publicId;
    @NonNull
    public final String accessToken;
    @NonNull
    public final String refreshToken;

    public RoomToken(@NonNull String publicId, @NonNull String accessToken, @NonNull String refreshToken) {
        this.publicId = publicId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
