package com.mersocarlin.androidrest.network.request;

import com.mersocarlin.androidrest.domain.model.TokenInfo;
import com.mersocarlin.androidrest.network.api.AuthApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

public class AuthRequest extends RetrofitSpiceRequest<TokenInfo, AuthApi> {

    private final String username;
    private final String password;

    public AuthRequest(String username, String password) {
        super(TokenInfo.class, AuthApi.class);

        this.username = username;
        this.password = password;
    }

    @Override
    public TokenInfo loadDataFromNetwork() throws Exception {
        return getService().authenticate("password", this.username, this.password);
    }
}
