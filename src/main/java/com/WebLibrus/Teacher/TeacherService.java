package com.WebLibrus.Teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository studentRepository;

    public List<Teacher> findAll(){
        return studentRepository.findAll();
    }

    public Optional<Teacher> findById(Long id){
        return studentRepository.findById(id);
    }

    public Teacher save(Teacher stock){
        return studentRepository.save(stock);
    }

    public void deleteById(Long id){
        studentRepository.deleteById(id);
    }

    public void deleteAll(){
        studentRepository.deleteAll();
    }
}
