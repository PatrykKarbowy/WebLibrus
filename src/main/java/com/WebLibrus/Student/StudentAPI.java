package com.WebLibrus.Student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Response create(@Valid @RequestBody Student student) {
        return Response.status(Response.Status.ACCEPTED).entity("OK").build();
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
