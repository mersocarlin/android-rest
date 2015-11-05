package com.mersocarlin.androidrest.network.api;

import com.mersocarlin.androidrest.domain.model.TokenInfo;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface AuthApi {

    @FormUrlEncoded
    @POST("/api/auth")
    TokenInfo authenticate(@Field("grant_type") String grantType,
                      @Field("username") String username,
                      @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/auth")
    TokenInfo refreshToken(@Field("grant_type") String grantType,
                      @Field("refresh_token") String refreshToken);
}
