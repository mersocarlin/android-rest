package com.mersocarlin.androidrest.data.contracts;

import android.provider.BaseColumns;

public class PersonReader {

    public PersonReader() {

    }

    public static abstract class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME_PERSON_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_CREATED_AT = "createdAt";
        public static final String COLUMN_NAME_HOME_PHONE = "homePhone";
        public static final String COLUMN_NAME_MOBILE_PHONE = "mobilePhone";
        public static final String COLUMN_NAME_WORK_PHONE = "workPhone";
    }
}
