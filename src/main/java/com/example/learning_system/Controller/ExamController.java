package com.example.learning_system.Controller;

import com.example.learning_system.Api.ApiResponse;
import com.example.learning_system.Model.Exam;
import com.example.learning_system.Service.ExamService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/exam")
public class ExamController {

    private final ExamService examService;


    @GetMapping("/get")
    public ResponseEntity<?> get() {
        ArrayList<Exam> e = examService.get();
        return ResponseEntity.status(200).body(e);
    }


    @PutMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Exam exam, Errors err) {
        if (err.hasErrors()) {
            String message = err.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String check = examService.add(exam);
        return switch (check) {
            case "false" -> ResponseEntity.status(400).body(new ApiResponse("ID already used"));
            case "Quiz" -> ResponseEntity.status(400).body(new ApiResponse("Quiz total marks must be 15 or less"));
            case "Midterm" -> ResponseEntity.status(400).body(new ApiResponse("Midterm total marks must be 30 or less"));
            case "Final" -> ResponseEntity.status(400).body(new ApiResponse("Final total marks must be 100"));
            case "true" -> ResponseEntity.status(200).body(new ApiResponse("Add success"));

            default -> ResponseEntity.status(400).body(new ApiResponse("Unexpected error"));
        };
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody @Valid Exam exam,
            Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        String check = examService.update(id, exam);

        return switch (check) {
            case "false" -> ResponseEntity.status(400).body(new ApiResponse("ID already used"));
            case "Quiz" -> ResponseEntity.status(400).body(new ApiResponse("Quiz total marks must be 15 or less"));
            case "Midterm" -> ResponseEntity.status(400).body(new ApiResponse("Midterm total marks must be 30 or less"));
            case "Final" -> ResponseEntity.status(400).body(new ApiResponse("Final total marks must be 100"));
            case "true" -> ResponseEntity.status(200).body(new ApiResponse("Update success"));
            default -> ResponseEntity.status(404).body(new ApiResponse("Exam not found"));
        };
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (examService.delete(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Remove success"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("ID not found"));
    }


    @GetMapping("/search/{id}")
    public ResponseEntity<?> search(@PathVariable String id) {
        Exam exam = examService.search(id);
        if (exam == null) {
            return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
        }
        return ResponseEntity.status(200).body(exam);
    }

    @GetMapping("/getExam/{exam}")
    public ResponseEntity<?> getExam(@PathVariable String exam) {
        ArrayList<Exam> e = examService.getExam(exam);
        if (e.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No exam found"));
        }
        return ResponseEntity.status(200).body(e);
    }

}