package com.mersocarlin.androidrest.domain.model;

import java.util.Date;

public class Person {
    public int id;
    public String name;
    public String email;
    public Date createdAt;
    public String homePhone;
    public String mobilePhone;
    public String workPhone;

    public Person() {
        this.createdAt = new Date();
    }

    @Override
    public String toString() {
        return String.format("\nName: %s, email: %s, homePhone: %s, mobilePhone: %s, workPhone: %s, createdAt: %s",
                this.name,
                this.email,
                this.homePhone,
                this.mobilePhone,
                this.workPhone,
                this.createdAt);
    }
}
