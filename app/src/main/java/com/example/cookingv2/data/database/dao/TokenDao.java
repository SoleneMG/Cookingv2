package com.example.cookingv2.data.database.dao;

import com.example.cookingv2.model.Token;

public interface TokenDao {

    void insert(String userId, Token token);

    Token getTokenWithPublicId(String publicId);
}
