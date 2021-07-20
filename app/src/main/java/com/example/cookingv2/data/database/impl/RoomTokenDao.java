package com.example.cookingv2.data.database.impl;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cookingv2.data.database.dao.TokenDao;
import com.example.cookingv2.data.database.impl.model.RoomToken;
import com.example.cookingv2.model.Token;

@Dao
public abstract class RoomTokenDao implements TokenDao {

    @Insert()
    abstract void roomInsert(RoomToken roomToken);

    @Query("SELECT * FROM token WHERE publicId LIKE :publicId")
    abstract RoomToken roomGetTokenWithPublicId(String publicId);


    @Override
    public void insert(String publicId, Token token) {
        roomInsert(new RoomToken(publicId, token.accessToken, token.refreshToken));
    }

    @Override
    public Token getTokenWithPublicId(String publicId) {
        RoomToken roomToken = roomGetTokenWithPublicId(publicId);
        return new Token(roomToken.accessToken, roomToken.refreshToken);
    }
}
