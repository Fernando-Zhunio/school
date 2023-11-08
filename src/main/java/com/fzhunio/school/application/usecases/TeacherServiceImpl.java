package com.fzhunio.school.application.usecases;

import com.fzhunio.school.domain.dtos.TeacherStoreDto;
import com.fzhunio.school.domain.entities.Teacher;
import com.fzhunio.school.domain.services.TeacherService;
import com.fzhunio.school.frameworks.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;
    @Override
    public Teacher find(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Teacher update(Long id, Teacher teacherU) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher != null) {
            teacherU.setId(id);
            teacherRepository.save(teacherU);
            return teacherU;
        }
        return null;
    }

    @Override
    public Teacher store(Teacher teacherDto) {

        return teacherRepository.save(teacherDto);
    }

    @Override
    public Teacher delete(Long id) {
         teacherRepository.deleteById(id);
         return null;
    }

    @Override
    public List<Teacher> list() {
        return teacherRepository.findAll();
    }
}
