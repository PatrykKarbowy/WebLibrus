package com.WebLibrus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@Slf4j
@RequiredArgsConstructor
public class StudentAPI {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.save(student));
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
}
