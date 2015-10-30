package com.mersocarlin.androidrest.network.request;

import com.mersocarlin.androidrest.domain.model.Course;
import com.mersocarlin.androidrest.network.api.CourseApi;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

public class CourseRequest extends RetrofitSpiceRequest<Course.List, CourseApi> {


    public CourseRequest() {
        super(Course.List.class, CourseApi.class);
    }

    @Override
    public Course.List loadDataFromNetwork() throws Exception {
        return getService().getCourses();
    }
}