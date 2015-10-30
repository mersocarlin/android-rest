package com.mersocarlin.androidrest.data.repository;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.inject.Inject;
import com.mersocarlin.androidrest.data.AppDbHelper;
import com.mersocarlin.androidrest.data.contracts.PersonReader;
import com.mersocarlin.androidrest.domain.contracts.IPersonRespository;
import com.mersocarlin.androidrest.domain.helper.DateTimeHelper;
import com.mersocarlin.androidrest.domain.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements IPersonRespository {

    @Inject
    private AppDbHelper appDbHelper;

    @Override
    public List<Person> get() {
        List<Person> response = new ArrayList<>();

        String query = "SELECT * FROM " + PersonReader.PersonEntry.TABLE_NAME + " ORDER BY NAME";

        Cursor cursor = this.appDbHelper.getReadableDatabase().rawQuery(query, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Person person = new Person();
            person.id = cursor.getInt(cursor.getColumnIndex(PersonReader.PersonEntry.COLUMN_NAME_PERSON_ID));
            person.name = cursor.getString(cursor.getColumnIndex(PersonReader.PersonEntry.COLUMN_NAME_NAME));
            person.email = cursor.getString(cursor.getColumnIndex(PersonReader.PersonEntry.COLUMN_NAME_EMAIL));
            person.createdAt = DateTimeHelper.stringToDate(cursor.getString(cursor.getColumnIndex(PersonReader.PersonEntry.COLUMN_NAME_CREATED_AT)));
            person.homePhone = cursor.getString(cursor.getColumnIndex(PersonReader.PersonEntry.COLUMN_NAME_HOME_PHONE));
            person.mobilePhone = cursor.getString(cursor.getColumnIndex(PersonReader.PersonEntry.COLUMN_NAME_MOBILE_PHONE));
            person.workPhone = cursor.getString(cursor.getColumnIndex(PersonReader.PersonEntry.COLUMN_NAME_WORK_PHONE));

            response.add(person);

            cursor.moveToNext();
        }

        return response;
    }

    @Override
    public Person getById(int id) {
        return null;
    }

    @Override
    public void save(Person person) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PersonReader.PersonEntry.COLUMN_NAME_PERSON_ID, person.id);
        contentValues.put(PersonReader.PersonEntry.COLUMN_NAME_NAME, person.name);
        contentValues.put(PersonReader.PersonEntry.COLUMN_NAME_EMAIL, person.email);
        contentValues.put(PersonReader.PersonEntry.COLUMN_NAME_CREATED_AT, DateTimeHelper.dateToString(person.createdAt));
        contentValues.put(PersonReader.PersonEntry.COLUMN_NAME_HOME_PHONE, person.homePhone);
        contentValues.put(PersonReader.PersonEntry.COLUMN_NAME_MOBILE_PHONE, person.mobilePhone);
        contentValues.put(PersonReader.PersonEntry.COLUMN_NAME_WORK_PHONE, person.workPhone);

        if (person.id > 0 && false) {
            this.update(person.id, contentValues);
            return;
        }

        this.create(contentValues);
    }

    @Override
    public void create(ContentValues contentValues) {
        this.appDbHelper.getWritableDatabase().insert(
                PersonReader.PersonEntry.TABLE_NAME,
                null,
                contentValues);
    }

    @Override
    public void update(int id, ContentValues contentValues) {
        this.appDbHelper.getWritableDatabase().update(
                PersonReader.PersonEntry.TABLE_NAME,
                contentValues,
                PersonReader.PersonEntry.COLUMN_NAME_PERSON_ID + " = " + id,
                null);
    }

    @Override
    public void delete(int id) {
        this.appDbHelper.getWritableDatabase().delete(
                PersonReader.PersonEntry.TABLE_NAME,
                PersonReader.PersonEntry.COLUMN_NAME_PERSON_ID + " = " + id,
                null
        );
    }
}
