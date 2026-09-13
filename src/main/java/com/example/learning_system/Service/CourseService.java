package com.example.learning_system.Service;


import com.example.learning_system.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {


    ArrayList<Course> courses = new ArrayList<>();


    public ArrayList<Course> get() {
        return courses;
    }


    public String add(Course course) {
        for (Course c : courses) {
            if (c.getId().equals(course.getId())) {
                return "false";
            }
            if(c.getClassRoomNumber().equals(course.getClassRoomNumber())){
                return "false";
            }
        }
        if (course.haveTeacher) {
            courses.add(course);
            return "true";
        }
        return "teacherFalse";
    }


    public String update(String id, Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (!courses.get(i).getId().equals(id) && courses.get(i).getId().equals(course.getId())) {
                return "false";
            }
            if(!courses.get(i).getId().equals(id)&&courses.get(i).classRoomNumber.equals(course.getClassRoomNumber())){
                return "false";
            }

            if (courses.get(i).getId().equals(id)) {
                if (!course.getHaveTeacher()) {
                    if (course.getStudentNumber() != 0) {
                        return "not Allow number";
                    }
                }
                courses.set(i, course);
            }
        }
        return "true";
    }


    public String delete(String id) {
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                if (c.getStudentNumber() > 0) {
                    return "has student";
                }
                courses.remove(c);
                return "true";
            }
        }
        return "not found";
    }




    public ArrayList<Course>search(){
        ArrayList<Course>c=new ArrayList<>();
        for(Course course:courses){
            if(course.getHaveTeacher()){
                c.add(course);
            }
        }
           return c;
        }

}
