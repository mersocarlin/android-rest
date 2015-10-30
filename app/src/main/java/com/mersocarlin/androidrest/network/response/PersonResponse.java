package com.mersocarlin.androidrest.network.response;

import com.mersocarlin.androidrest.domain.model.Person;

import java.util.List;

public class PersonResponse {
    public int page;
    public int total;
    public List<Person> data;
}
