package com.example.hateoasDemo.service;

import com.example.hateoasDemo.model.dto.OrderDTO;
import com.example.hateoasDemo.model.dto.StudentDTO;
import com.example.hateoasDemo.model.entity.OrderEntity;
import com.example.hateoasDemo.model.entity.StudentEntity;
import com.example.hateoasDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<StudentDTO> getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .map(this::studentToDTOMapper);
    }

    private StudentDTO studentToDTOMapper(StudentEntity entity) {
        List<OrderDTO> orderDTOs = entity
                .getOrders()
                .stream()
                .map(this::orderMapper)
                .toList();

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setId(entity.getId());
        studentDTO.setName(entity.getName());
        studentDTO.setAge(entity.getAge());
        studentDTO.setDeleted(entity.isDeleted());

        studentDTO.setOrders(orderDTOs);

        return studentDTO;
    }

    private OrderDTO orderMapper(OrderEntity entity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCourseId(entity.getCourse().getId());
        orderDTO.setStudentId(entity.getStudent().getId());

        return orderDTO;
    }

    public List<StudentDTO> getAllStudents() {
      return this.studentRepository.findAll()
              .stream().map(this::studentToDTOMapper)
              .toList();
    }

    public List<OrderDTO> getStudentOrders(Long studentId) {
        return getStudentById(studentId)
                .map(s -> s.getOrders())
                .orElseGet(ArrayList::new);
    }

}
