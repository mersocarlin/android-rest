package com.mersocarlin.androidrest.domain.contracts;

import android.content.ContentValues;

import com.mersocarlin.androidrest.domain.model.Person;

import java.util.List;

public interface IPersonRespository {
    List<Person> get();

    Person getById(int id);

    void save(Person person);

    void create(ContentValues contentValues);

    void update(int id, ContentValues contentValues);

    void delete(int id);
}
