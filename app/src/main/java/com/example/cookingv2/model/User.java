package com.example.cookingv2.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
todo ton model utilisé dans toute l'appli est dédié à Room =>
 si tu dois l'envoyer ou récupérer à un serveur et que tu mets la gestion json
 dedans ça fera un modèle fourre tout. De même si tu changes room, ça va impacter toute l'appli.
 Faire un model RoomUser dans database.impl, que tu converti en ce modèle user neutre pour l'utiliser ensuite
 */

@Entity(tableName = "user")
public class User {

    @NonNull
    public final String publicId;
    @PrimaryKey
    @NonNull
    public final String id;
    @NonNull
    public final String email;

    public User(@NonNull String publicId, @NonNull String id, @NonNull String email) {
        this.publicId = publicId;
        this.id = id;
        this.email = email;
    }
}
