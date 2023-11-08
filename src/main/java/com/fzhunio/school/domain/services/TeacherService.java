package com.fzhunio.school.domain.services;

import com.fzhunio.school.domain.dtos.TeacherStoreDto;
import com.fzhunio.school.domain.entities.Teacher;
import com.fzhunio.school.frameworks.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeacherService {

    Teacher find(Long id);
    Teacher update(Long id, Teacher teacher);
    Teacher store(Teacher teacher);
    Teacher delete(Long id);
    List<Teacher> list();
}
