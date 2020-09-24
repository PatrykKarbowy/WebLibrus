package com.WebLibrus.Student;

import com.WebLibrus.Subjects.Subject;
import com.WebLibrus.Subjects.SubjectRepository;
import com.WebLibrus.commands.AssignSubjectToStudentCommand;
import com.WebLibrus.commands.CreateStudentCommand;
import com.google.common.collect.ImmutableSet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private  StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

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

    public CreateStudentDTO createNewStudent(CreateStudentCommand student) {
        Student newStudent = Student.builder().studentAge(student.getStudentAge()).studentFirstName(student.getStudentFirstName()).studentLastName(student.getStudentLastName()).build();
        studentRepository.save(newStudent);
        return CreateStudentDTO.from(newStudent);
    }

    public void assignSubjectTOStudent(AssignSubjectToStudentCommand command) {
        Optional<Subject> subjectOptional = subjectRepository.findById((long) command.getSubjectId());
        Subject subject = null;
        if (subjectOptional.isPresent()){
             subject = subjectOptional.get();
        }
        Optional<Student> studentOptional = studentRepository.findById((long) command.getStudentId());
        Student student = null;
        if (studentOptional.isPresent()) {
             student = studentOptional.get();
        }

        // Mamy przedmiot + studenta, teraz przypiszemy ten przedmiot studentowi
            assignSubjectToStudent(student, subject);
    }


    private void assignSubjectToStudent (Student student, Subject subject) {
        if (student != null && subject != null) {
            subject.setStudent(ImmutableSet.of(student));
            subjectRepository.save(subject);
        }

    }
}
