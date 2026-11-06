package com.student.service;

import com.student.dao.StudentRepository;
import com.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repo;

    public List<Student> getStudents()
    {
        return repo.findAll();
    }


    public String addStudent(Student s1) {

        repo.save(s1);
        return "Success";
    }
}
