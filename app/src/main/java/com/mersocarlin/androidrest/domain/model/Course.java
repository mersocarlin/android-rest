package com.mersocarlin.androidrest.domain.model;

import java.util.ArrayList;

public class Course {
    public int id;
    public String name;

    public static class List extends ArrayList<Course> {
    }
}
