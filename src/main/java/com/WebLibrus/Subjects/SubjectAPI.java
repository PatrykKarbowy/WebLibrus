package com.WebLibrus.Subjects;

import com.WebLibrus.commands.CreateSubjectCommand;
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
@RequestMapping("/api/subjects")
@Slf4j
@RequiredArgsConstructor
public class SubjectAPI {
    @Autowired
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> findAll() {
        return ResponseEntity.ok(subjectService.findAll());
    }

    @PostMapping
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid @RequestBody CreateSubjectCommand subject) throws Exception {
        Response result;
        try {
            CreateSubjectDTO dto = subjectService.createNewSubject(subject);
            result = Response.status(Response.Status.OK).entity(dto).build();
        }catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> findById(@PathVariable Long id) {
        Optional<Subject> stock = subjectService.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> update(@PathVariable Long id, @Valid @RequestBody Subject subject) {
        Optional<Subject> stock = subjectService.findById(id);
        if (!stock.isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        subject.setId(id);
        subjectService.save(subject);
        return ResponseEntity.ok().build();
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> delete(@PathVariable Long id) {
        if (!subjectService.findById(id).isPresent()) {
            log.error("Id " + id + " is not in the list");
            return ResponseEntity.badRequest().build();
        }
        subjectService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<List<Subject>> deleteAll(){
        subjectService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
