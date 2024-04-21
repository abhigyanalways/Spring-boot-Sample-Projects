package com.abhigyan.springdatamongodb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public List<Student> gettAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(StudentDTO studentDTO) {
        Student student=new Student(studentDTO.getFirstName(), studentDTO.getLastName(),
                studentDTO.getEmail(), studentDTO.getGender(),studentDTO.getAddress(),
                studentDTO.getFavouriteSubjects(),studentDTO.getTotalSpentInBooks(),null);
        studentRepository.insert(student);

    }

    public void modifyStudent(StudentDTO studentDTO) {

        Optional<Student> student = studentRepository.findStudentByEmail(studentDTO.getEmail());
        Student studentOrThrow = student.orElseThrow(() -> new RuntimeException("Student not found"));

        studentOrThrow.setFirstName(studentDTO.getFirstName());
        studentOrThrow.setLastName(studentDTO.getLastName());
        studentOrThrow.setEmail(studentDTO.getEmail());
        studentOrThrow.setGender(studentDTO.getGender());
        studentOrThrow.setAddress(studentDTO.getAddress());
        studentOrThrow.setFavouriteSubjects(studentDTO.getFavouriteSubjects());
        studentOrThrow.setTotalSpentInBooks(studentDTO.getTotalSpentInBooks());

        studentRepository.save(studentOrThrow); //save method updates the document whereas insert adds a new document
    }

    public void deleteStudent(String email) {
        studentRepository.deleteByEmail(email);
    }
}
