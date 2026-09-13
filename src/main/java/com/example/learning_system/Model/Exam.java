package com.example.learning_system.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Exam {


    @NotEmpty(message = "Enter id please")
    private String id;

    @NotEmpty(message = "please Enter subject Name ! ")
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String subjectName;

    @NotEmpty(message = "Please Enter Teacher Name")
    @Pattern(regexp = ("^[a-zA-Z ]+$"))
    private String teacherName;

    @NotEmpty(message = "Please Select Exam Type ")
    @Pattern(regexp = "^(Quiz|Midterm|Final)$",message = "Please Select one of this Quiz or Midterm or Final")
    private String examType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Jest Allow Present or Future")
    private LocalDate examDate;

    @NotNull(message = "Enter correct Total Marks")
    @Min(10)
    @Max(100)
    private int totalMarks;

}
