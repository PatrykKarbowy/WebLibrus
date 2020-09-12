package com.WebLibrus.Subjects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    public Optional<Subject> findById(Long id){
        return subjectRepository.findById(id);
    }

    public Subject save(Subject stock){
        return subjectRepository.save(stock);
    }

    public void deleteById(Long id){
        subjectRepository.deleteById(id);
    }

    public void deleteAll(){
        subjectRepository.deleteAll();
    }
}
