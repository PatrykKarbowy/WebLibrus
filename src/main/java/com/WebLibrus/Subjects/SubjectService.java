package com.WebLibrus.Subjects;


import com.WebLibrus.commands.CreateSubjectCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {
    @Autowired
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

    public CreateSubjectDTO createNewSubject(CreateSubjectCommand subject) {
        Subject newSubject = Subject.builder().subjectName(subject.getSubjectName()).build();
        subjectRepository.save(newSubject);
        return CreateSubjectDTO.from(newSubject);
    }
}
