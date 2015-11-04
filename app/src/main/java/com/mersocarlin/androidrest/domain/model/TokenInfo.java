package com.mersocarlin.androidrest.domain.model;

import android.text.TextUtils;

import java.util.Calendar;
import java.util.Date;

public class TokenInfo {

    public String access_token;
    public String token_type;
    public long expires_in;
    public String refresh_token;
    public long expiresAt;

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void calculateExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, (int) this.expires_in);
        calendar.add(Calendar.MINUTE, -5);

        this.setExpiresAt(calendar.getTimeInMillis());
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.access_token) &&
                TextUtils.isEmpty(this.token_type) &&
                TextUtils.isEmpty(this.refresh_token) &&
                this.expires_in == 0;
    }

    public boolean isValid() {
        if (this.isEmpty()) return false;

        Date expirationDate = new Date(this.expiresAt);

        return expirationDate.after(new Date());
    }
}
