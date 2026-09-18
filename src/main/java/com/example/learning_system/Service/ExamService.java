package com.example.learning_system.Service;

import com.example.learning_system.Model.Exam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExamService {
    ArrayList<Exam> exams = new ArrayList<>();

    public ArrayList<Exam> get() {
        return exams;
    }


    public String add(Exam exam) {
        for (Exam e : exams) {
            if (e.getId().equals(exam.getId())) {
                return "false";
            }
        }

            if (exam.getExamType().equals("Quiz")) {
                if (exam.getTotalMarks() > 15) {
                    return "Quiz";
                }
            }

            if (exam.getExamType().equals("Midterm")) {
                if (exam.getTotalMarks() > 30) {
                    return "Midterm";
                }
            }

            if (exam.getExamType().equals("Final")) {
                if (exam.getTotalMarks() != 100) {
                    return "Final";
                }
            }
        exams.add(exam);
        return "true";
    }


        public String update (String id, Exam exam){
            for (int i = 0; i < exams.size(); i++) {
                if (!exams.get(i).getId().equals(id) && exams.get(i).getId().equals(exam.getId())) {
                    return "false";
                }

                if (exam.getExamType().equals("Quiz")) {
                    if (exam.getTotalMarks() > 15) {
                        return "Quiz";
                    }
                }

                if (exam.getExamType().equals("Midterm")) {
                    if (exam.getTotalMarks() > 30) {
                        return "Midterm";
                    }
                }

                if (exam.getExamType().equals("Final")) {
                    if (exam.getTotalMarks() != 100) {
                        return "Final";
                    }

                }
                if(exams.get(i).getId().equals(id)) {
                    exams.set(i, exam);
                    return "true";

                }
            }
            return "not found ";

        }



        public boolean delete(String id ){
        for(Exam e:exams){
            if(e.getId().equals(id)){
                exams.remove(e);
                return true;
            }
        }
        return false;
        }


        public Exam search(String id ){
        for(Exam e:exams){
            if(e.getId().equals(id)){
                return e;
            }
        }
        return null;
        }



        public ArrayList<Exam>getExam(String exam){
        ArrayList<Exam>getE=new ArrayList<>();
        for(Exam e:exams) {
            if (e.getExamType().equals(exam)){
                getE.add(e);
            }
        }return getE;
        }
}



