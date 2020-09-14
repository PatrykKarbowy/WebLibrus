package com.WebLibrus.TeacherOpinion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherOpinionService {
    private final TeacherOpinionRepository teacherOpinionRepository;

    public List<TeacherOpinion> findAll(){
        return teacherOpinionRepository.findAll();
    }

    public Optional<TeacherOpinion> findById(Long id){
        return teacherOpinionRepository.findById(id);
    }

    public TeacherOpinion save(TeacherOpinion stock){
        return teacherOpinionRepository.save(stock);
    }

    public void deleteById(Long id){
        teacherOpinionRepository.deleteById(id);
    }

    public void deleteAll(){
        teacherOpinionRepository.deleteAll();
    }

}
