package com.WebLibrus.Student;

import com.WebLibrus.commands.AssignSubjectToStudentCommand;
import com.WebLibrus.commands.CreateStudentCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@Slf4j
@RequiredArgsConstructor
public class StudentAPI {
    @Autowired
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid @RequestBody CreateStudentCommand student) throws Exception {
        Response result = null;
        try {
            CreateStudentDTO dto = studentService.createNewStudent(student);
            result = Response.status(Response.Status.OK).entity(dto).build();
        }catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    @PostMapping("/assign")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignSubjectToStudent(@Valid @RequestBody AssignSubjectToStudentCommand command) throws Exception {
        // TODO: Kazdy przedmiot jest wpisane w bazie i ma unikalne ID
        Response result = null;
        try {
            studentService.assignSubjectTOStudent(command);
            result = Response.status(Response.Status.OK).build();
        }catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        Optional<Student> stock = studentService.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }

    @GetMapping("findByName/{studentFirstName}")
    public ResponseEntity<Student> findByStudentFirstName(@PathVariable String studentFirstName){
        Optional<Student> stock = studentService.findByStudentFirstName(studentFirstName);
        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @Valid @RequestBody Student student) {
        Optional<Student> stock = studentService.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        student.setId(id);
        studentService.save(student);
        return ResponseEntity.ok().build();
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id) {
        if (!studentService.findById(id).isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        studentService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<List<Student>> deleteAll(){
        studentService.deleteAll();
        return ResponseEntity.ok().build();
    }

}
