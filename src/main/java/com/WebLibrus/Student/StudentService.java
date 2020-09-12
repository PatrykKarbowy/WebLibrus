package com.WebLibrus.Student;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id){
        return studentRepository.findById(id);
    }

    public Student save(Student stock){
        return studentRepository.save(stock);
    }

    public void deleteById(Long id){
        studentRepository.deleteById(id);
    }

    public void deleteAll(){
        studentRepository.deleteAll();
    }

    public Optional<Student> findByStudentFirstName(String name){
        return studentRepository.findByStudentFirstName(name);
    }

}
