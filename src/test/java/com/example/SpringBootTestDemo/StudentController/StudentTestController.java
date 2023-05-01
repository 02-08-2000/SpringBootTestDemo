package com.example.SpringBootTestDemo.StudentController;

import com.example.SpringBootTestDemo.controller.StudentController;
import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.service.StudentServices;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentTestController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentServices studentServices;
    private Student student;

    @BeforeEach
    public void Setup() {
        student = new Student(2, "Yashwanth", "Hyderabad", "245098");
    }

    @Test
    void testSaveStudent() throws Exception {
        Student student = new Student(2, "Yashwanth", "Hyderabad", "245098");
        Mockito.when(studentServices.addStudent(student)).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.post("/student/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\r\n" +
                                " \"id\" : 2, \r\n" +
                                " \"name\" : \"Yashwanth\", \r\n" +
                                " \"address\" :\"Hyderabad\", \r\n" +
                                " \"rollNo\" : \"245098\" \r\n" +
                                "}"))
                .andExpect(status().isOk());
    }
}

