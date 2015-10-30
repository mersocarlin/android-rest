package com.mersocarlin.androidrest.network.api;

import com.mersocarlin.androidrest.domain.model.Course;

import retrofit.http.GET;

public interface CourseApi {
    @GET("/api/v1/course")
    Course.List getCourses();
}
