package com.example.learning_system.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Course {


    @NotEmpty(message = "Enter id please")
    private String id;


    @NotEmpty(message = "Please Enter course name ")
    public String courseName;


    @NotEmpty(message = "Enter class Rome number")
    @Pattern(regexp = "^[A-Z]{2}-[0-9]{3}+$")
    public String classRoomNumber;



    @NotNull(message = "Enter number of student inside class")
    @Max(value = 30,message = "The max number 30 , sorry ")
//    @Min(value = 15,message = "The min number 15 , sorry")
    public Integer StudentNumber;


    @NotNull(message = "Please specify if the course has a teacher")
    public Boolean haveTeacher;












}
