package com.WebLibrus.TeacherOpinion;

import com.WebLibrus.Student.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/opinions")
@Slf4j
@RequiredArgsConstructor
public class TeacherOpinionAPI {
    private final TeacherOpinionService teacherOpinionService;

    @GetMapping
    public ResponseEntity<List<TeacherOpinion>> findAll() {
        return ResponseEntity.ok(teacherOpinionService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody TeacherOpinion teacherOpinion) {
        return ResponseEntity.ok(teacherOpinionService.save(teacherOpinion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherOpinion> findById(@PathVariable Long id) {
        Optional<TeacherOpinion> stock = teacherOpinionService.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }


    @PutMapping("/{id}")
    public ResponseEntity<TeacherOpinion> update(@PathVariable Long id, @Valid @RequestBody TeacherOpinion teacherOpinion) {
        Optional<TeacherOpinion> stock = teacherOpinionService.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        teacherOpinion.setId(id);
        teacherOpinionService.save(teacherOpinion);
        return ResponseEntity.ok().build();
}

    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherOpinion> delete(@PathVariable Long id) {
        if (!teacherOpinionService.findById(id).isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        teacherOpinionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<List<Student>> deleteAll(){
        teacherOpinionService.deleteAll();
        return ResponseEntity.ok().build();
    }

}
