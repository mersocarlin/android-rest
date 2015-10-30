package com.mersocarlin.androidrest.network.request;

import com.mersocarlin.androidrest.network.api.PersonApi;
import com.mersocarlin.androidrest.network.response.PersonResponse;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

public class PersonRequest extends RetrofitSpiceRequest<PersonResponse, PersonApi> {

    private String query;
    private int personType;
    private int personStatus;
    private int page;

    public PersonRequest(String query, int personType, int personStatus, int page) {
        super(PersonResponse.class, PersonApi.class);

        this.query = query;
        this.personType = personType;
        this.personStatus = personStatus;
        this.page = page;
    }

    @Override
    public PersonResponse loadDataFromNetwork() throws Exception {
        return getService().getPersons(this.query, this.personType, this.personStatus, this.page);
    }
}
