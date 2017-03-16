package com.example.guo.rxreview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_hx on 2017/3/16 15:01.
 */

public class Student {

    public String name;

    public List<Course> courseList = new ArrayList<>();

    public static class Course {

        public String courseName;
    }
}
