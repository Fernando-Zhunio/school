package com.fzhunio.school.adapters;

import com.fzhunio.school.domain.dtos.TeacherStoreDto;
import com.fzhunio.school.domain.entities.Teacher;
import com.fzhunio.school.domain.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/teacher")
public class TeacherController  {

    @Autowired
    TeacherService teacherService;
    @GetMapping()
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(teacherService.list());
    }

    @PostMapping()
    public ResponseEntity<?> store(@RequestBody @Valid Teacher teacherStoreDto) {
        var teacher =  teacherService.store(teacherStoreDto);
        return ResponseEntity.ok(teacher);
    }

//    @PutMapping("{id}")
//    public ResponseEntity<?> update(@PathVariable("id") Long id, Teacher teacher) {
//
//    }
}
