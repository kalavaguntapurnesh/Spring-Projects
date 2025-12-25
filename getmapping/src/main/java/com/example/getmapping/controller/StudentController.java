package com.example.getmapping.controller;


import com.example.getmapping.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(
                1,
                "Purnesh",
                "Kalavagungta"
        );
//        return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok().header("custom-header", "Purnesh").body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(2, "Vasu", "Shamba"));
        students.add(new Student(3, "Yash", "Kalavagungta"));
//        return students;
        return ResponseEntity.ok().body(students);
    }

    //Spring Boot REST API with Path Variable
    //{id} is called ----- "URI TEMPLATE VARIABLE"
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
//        return new Student(studentId, firstName, lastName);
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok().body(student);
    }


    //Spring Boot Rest API with Request Params
    //  http://localhost:8080/students/query?id=1&firstName=Remo&lastName=Ramam
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName) {
//        return new Student(id, firstName, lastName);
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok().body(student);

    }

    // Spring Boot Rest API that handles HTTP POST Requests
    @PostMapping("create")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        //@RequestBody is used to convert response(JSON) body into Java Object
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
//        return student;
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // Spring Boot Rest API that handles HTTP PUT Requests
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
//        return student;
        return ResponseEntity.ok().body(student);
    }

    // Spring Boot Rest API that handles HTTP DELETE Requests
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
//        return "Successfully Deleted given student";
        return ResponseEntity.ok("Successfully deleted given student");
    }


}
