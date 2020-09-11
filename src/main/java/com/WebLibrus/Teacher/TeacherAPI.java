package com.WebLibrus.Teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
@Slf4j
@RequiredArgsConstructor
public class TeacherAPI {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> findAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Teacher student) {
        return ResponseEntity.ok(teacherService.save(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable Long id) {
        Optional<Teacher> stock = teacherService.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable Long id, @Valid @RequestBody Teacher student) {
        Optional<Teacher> stock = teacherService.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        student.setId(id);
        teacherService.save(student);
        return ResponseEntity.ok().build();
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> delete(@PathVariable Long id) {
        if (!teacherService.findById(id).isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        teacherService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
