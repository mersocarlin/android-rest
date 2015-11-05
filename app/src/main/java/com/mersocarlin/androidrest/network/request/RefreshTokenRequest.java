package com.mersocarlin.androidrest.network.request;

import com.mersocarlin.androidrest.domain.model.TokenInfo;
import com.mersocarlin.androidrest.network.api.AuthApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

public class RefreshTokenRequest extends RetrofitSpiceRequest<TokenInfo, AuthApi> {

    private final String refreshToken;

    public RefreshTokenRequest(String refreshToken) {
        super(TokenInfo.class, AuthApi.class);

        this.refreshToken = refreshToken;
    }

    @Override
    public TokenInfo loadDataFromNetwork() throws Exception {
        return getService().refreshToken("refresh_token", this.refreshToken);
    }
}