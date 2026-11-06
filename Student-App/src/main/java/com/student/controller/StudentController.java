package com.student.controller;

import com.student.model.Student;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping("/getStudents")
    public List<Student> getStudents()
    {
        return service.getStudents();
    }

    @PostMapping("/add")
    public String addStudents(@RequestBody Student student)
    {

       return service.addStudent(student);


    }


}
