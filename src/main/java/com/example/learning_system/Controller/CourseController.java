package com.example.learning_system.Controller;

import com.example.learning_system.Api.ApiResponse;
import com.example.learning_system.Model.Course;
import com.example.learning_system.Service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;



    @GetMapping("/get")
    public ResponseEntity<?>get(){
        ArrayList<Course>c=courseService.get();
        return ResponseEntity.status(200).body(c);
    }



@PutMapping("/add")
    public ResponseEntity<?>add(@RequestBody@Valid Course course,Errors err){
        if(err.hasErrors()){
            String message=err.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        String check=courseService.add(course);
        return switch (check){
            case "false"->ResponseEntity.status(400).body(new ApiResponse("ID or Class Rome already use "));
            case "true"->ResponseEntity.status(200).body(new ApiResponse("Add success"));
            case "teacherFalse"->ResponseEntity.status(400).body(new ApiResponse("most have teacher"));
            default -> ResponseEntity.status(400).body(new ApiResponse("unsuspected errors"));
        };
    }




@PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable String id , @RequestBody@Valid Course course, Errors errors){

    if(errors.hasErrors()){
        String message=errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
        String check=courseService.update(id,course);
        return switch (check){
            case "false"->ResponseEntity.status(400).body(new ApiResponse("ID or Class Rome already use"));
            case "not Allow number"->ResponseEntity.status(400).body(new ApiResponse("if not have teacher not can enter student "));
            case "true" -> ResponseEntity.status(200).body(new ApiResponse("Update success"));
            default ->ResponseEntity.status(404).body(new ApiResponse("Course not found"));


        };
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable String id ){
        String check=courseService.delete(id);
        if(check.equals("true")){
            return ResponseEntity.status(200).body(new ApiResponse("Remove success"));
        }
        if(check.equals("has student")) {
            return ResponseEntity.status(400).body(new ApiResponse("Not Allow to remove "));
        }
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }



    @GetMapping("/search")
    public ResponseEntity<?>search(){
        ArrayList<Course>c=courseService.search();

        if(c.isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("No course with teacher found"));
        }
        return ResponseEntity.status(200).body(c);
    }






}
