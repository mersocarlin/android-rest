package com.mersocarlin.androidrest.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.inject.Inject;
import com.mersocarlin.androidrest.R;
import com.mersocarlin.androidrest.domain.helper.TokenInfoManager;
import com.mersocarlin.androidrest.domain.model.TokenInfo;
import com.mersocarlin.androidrest.network.request.RefreshTokenRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class SplashActivity extends BaseActivity {

    @Inject
    private TokenInfoManager tokenInfoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.checkIfUserIsLoggedIn();
    }

    private void checkIfUserIsLoggedIn() {
        TokenInfo tokenInfo = this.tokenInfoManager.getTokenInfo();

        if (tokenInfo == null || tokenInfo.isEmpty()) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);

            return;
        }

        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest(tokenInfo.refresh_token);

        getManager().execute(refreshTokenRequest, new RequestListener<TokenInfo>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRequestSuccess(TokenInfo tokenInfo) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
