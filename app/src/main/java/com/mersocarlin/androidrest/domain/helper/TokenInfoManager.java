package com.mersocarlin.androidrest.domain.helper;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.mersocarlin.androidrest.domain.model.TokenInfo;

@Singleton
public class TokenInfoManager {

    private static final String TOKEN_INFO = "TOKEN_INFO";

    private final SharedPreferences preferences;

    private final Gson gson = new Gson();
    private TokenInfo tokenInfo;

    @Inject
    public TokenInfoManager(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void reset() {
        final SharedPreferences.Editor edit = this.preferences.edit();
        edit.remove(TOKEN_INFO);
        edit.commit();
    }

    public void loggedIn(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
        this.tokenInfo.calculateExpirationTime();

        final SharedPreferences.Editor edit = preferences.edit();
        edit.putString(TOKEN_INFO, gson.toJson(this.tokenInfo));
        edit.commit();
    }

    public TokenInfo getTokenInfo() {
        if (tokenInfo == null) {
            final String tokenInfoStr = preferences.getString(TOKEN_INFO, "");

            if (tokenInfoStr.isEmpty()) {
                return null;
            }

            tokenInfo = gson.fromJson(tokenInfoStr, TokenInfo.class);
        }

        return tokenInfo;
    }

}
