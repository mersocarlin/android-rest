package com.mersocarlin.androidrest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.inject.Inject;
import com.mersocarlin.androidrest.R;
import com.mersocarlin.androidrest.domain.helper.TokenInfoManager;
import com.mersocarlin.androidrest.domain.model.TokenInfo;
import com.mersocarlin.androidrest.network.request.AuthRequest;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @InjectView(R.id.textErrorMessage) private TextView textErrorMessage;
    @InjectView(R.id.username) private AutoCompleteTextView mUsernameView;
    @InjectView(R.id.password) private EditText mPasswordView;
    @InjectView(R.id.login_progress) private ProgressBar progressBar;

    @Inject
    private TokenInfoManager tokenInfoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    private void attemptLogin() {
        if (this.progressBar.getVisibility() == View.VISIBLE) {
            return;
        }

        this.textErrorMessage.setVisibility(View.INVISIBLE);
        this.mUsernameView.setError(null);
        this.mPasswordView.setError(null);
        this.progressBar.setVisibility(View.VISIBLE);

        String email = this.mUsernameView.getText().toString();
        String password = this.mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password)) {
            this.mPasswordView.setError(getString(R.string.error_field_required));
            focusView = this.mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            this.mUsernameView.setError(getString(R.string.error_field_required));
            focusView = this.mUsernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            this.progressBar.setVisibility(View.GONE);
            return;
        }

        AuthRequest authRequest = new AuthRequest(email, password);
        getManager().execute(authRequest, new RequestListener<TokenInfo>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                progressBar.setVisibility(View.GONE);
                textErrorMessage.setVisibility(View.VISIBLE);
                textErrorMessage.setText(getString(R.string.error_invalid_credentials));
            }

            @Override
            public void onRequestSuccess(TokenInfo tokenInfo) {
                progressBar.setVisibility(View.GONE);
                tokenInfoManager.loggedIn(tokenInfo);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

