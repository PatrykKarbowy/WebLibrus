package com.WebLibrus.Teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public List<Teacher> findAll(){
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long id){
        return teacherRepository.findById(id);
    }

    public Teacher save(Teacher stock){
        return teacherRepository.save(stock);
    }

    public void deleteById(Long id){
        teacherRepository.deleteById(id);
    }

    public void deleteAll(){
        teacherRepository.deleteAll();
    }

    public Optional<Teacher> findByTeacherFirstName(String name){
        return teacherRepository.findByTeacherFirstName(name);
    }
}
