package com.mersocarlin.androidrest.network.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.mersocarlin.androidrest.R;
import com.mersocarlin.androidrest.domain.helper.TokenInfoManager;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import roboguice.RoboGuice;

public class BaseService extends RetrofitGsonSpiceService {

    private final int TIME_OUT = 10;

    @Inject
    private TokenInfoManager tokenInfoManager;

    @Override
    public void onCreate() {
        super.onCreate();
        RoboGuice.getInjector(this.getApplicationContext()).injectMembers(this);
    }

    @Override
    protected String getServerUrl() {
        return getString(R.string.api_url);
    }

    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(TIME_OUT, TimeUnit.SECONDS);

        return new RestAdapter.Builder()
                .setEndpoint(getServerUrl())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(this.errorHandler)
                .setRequestInterceptor(this.requestInterceptor)
                .setClient(new OkClient(okHttpClient))
                .setConverter(new GsonConverter(gson));
    }

    private ErrorHandler errorHandler = new ErrorHandler() {
        @Override
        public Throwable handleError(RetrofitError cause) {
            return cause;
        }
    };

    private RequestInterceptor requestInterceptor = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            final String accessToken = "";

            request.addHeader("Authorization", "Bearer " + accessToken);

//            request.addQueryParam("client_id", "");
//            request.addQueryParam("client_secret", "");
//            request.addQueryParam("v", "123456");
        }
    };
}
