package com.mersocarlin.androidrest.network.api;

import com.mersocarlin.androidrest.network.response.PersonResponse;

import retrofit.http.GET;
import retrofit.http.Query;

public interface PersonApi {
    @GET("/api/v1/person")
    PersonResponse getPersons(@Query("query") String query, @Query("personType") int personType, @Query("personStatus") int personStatus, @Query("page") int page);
}

