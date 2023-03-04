package com.example.hateoasDemo.init;

import com.example.hateoasDemo.model.entity.CourseEntity;
import com.example.hateoasDemo.model.entity.OrderEntity;
import com.example.hateoasDemo.model.entity.StudentEntity;
import com.example.hateoasDemo.repository.CourseRepository;
import com.example.hateoasDemo.repository.OrderRepository;
import com.example.hateoasDemo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final OrderRepository orderRepository;

    public Init(StudentRepository studentRepository, CourseRepository courseRepository, OrderRepository orderRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        StudentEntity pesho = new StudentEntity();
        pesho.setAge(21);
        pesho.setDeleted(false);
        pesho.setName("Pesho");

        StudentEntity gosho = new StudentEntity();
        gosho.setAge(22);
        gosho.setDeleted(false);
        gosho.setName("Gosho");

        StudentEntity sasho = new StudentEntity();
        sasho.setAge(23);
        sasho.setDeleted(true);
        sasho.setName("Sasho");

        this.studentRepository.save(pesho);
        this.studentRepository.save(gosho);
        this.studentRepository.save(sasho);

        CourseEntity spring = new CourseEntity();
        spring.setName("Spring");
        spring.setPrice(100);

        CourseEntity javaScript = new CourseEntity();
        javaScript.setName("JavaScript");
        javaScript.setPrice(5);

        this.courseRepository.save(spring);
        this.courseRepository.save(javaScript);

        OrderEntity firstOrder = new OrderEntity();
        firstOrder.setStudent(pesho);
        firstOrder.setCourse(spring);

        OrderEntity secondOrder = new OrderEntity();
        secondOrder.setStudent(pesho);
        secondOrder.setCourse(javaScript);

        OrderEntity thirdOrder = new OrderEntity();
        thirdOrder.setStudent(gosho);
        thirdOrder.setCourse(javaScript);

        this.orderRepository.save(firstOrder);
        this.orderRepository.save(secondOrder);
        this.orderRepository.save(thirdOrder);

    }

    // INSERT INTO students (id, age, deleted, name) VALUES (1, "21", 0, "Pesho");
    //INSERT INTO students (id, age, deleted, name) VALUES (2, "22", 0, "Gosho");
    //INSERT INTO students (id, age, deleted, name) VALUES (3, "23", 1, "Sasho");
    //
    //INSERT INTO courses (id, name, price) VALUES (1, "Spring", 100);
    //INSERT INTO courses (id, name, price) VALUES (2, "JavaScript", 5);
    //
    //INSERT INTO orders (id, course_id, student_id) VALUES (1, 1, 1);
    //INSERT INTO orders (id, course_id, student_id) VALUES (2, 1, 2);
    //INSERT INTO orders (id, course_id, student_id) VALUES (3, 2, 2);
}
