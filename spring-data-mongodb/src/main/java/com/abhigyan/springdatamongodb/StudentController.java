package com.abhigyan.springdatamongodb;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @GetMapping("/all")
    public List<Student> fetchAllStudents(){
        return studentService.gettAllStudents();
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody StudentDTO studentDTO){
        studentService.addStudent(studentDTO);

    }

    @PutMapping("/modify")
    public void modifyStudent (@RequestBody StudentDTO studentDTO){
        studentService.modifyStudent(studentDTO);
    }

    @DeleteMapping("/delete/{email}")
    public void deleteStudent(@PathVariable("email") String email){
        studentService.deleteStudent(email);

    }
}
