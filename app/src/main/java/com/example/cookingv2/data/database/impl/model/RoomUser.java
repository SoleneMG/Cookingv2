package com.example.cookingv2.data.database.impl.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity(tableName = "user")
    public class RoomUser {

        @NonNull
        public final String publicId;
        @PrimaryKey
        @NonNull
        public final String id;
        @NonNull
        public final String email;

        public RoomUser(@NonNull String publicId, @NonNull String id, @NonNull String email) {
            this.publicId = publicId;
            this.id = id;
            this.email = email;
        }
    }
