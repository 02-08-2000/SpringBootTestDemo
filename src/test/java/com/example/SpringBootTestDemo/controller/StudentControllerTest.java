package com.example.SpringBootTestDemo.controller;

import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.service.StudentServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServices mockStudentservices;

    @Test
    void testAddStudent() throws Exception {
        // Setup
        // Configure StudentServices.addStudent(...).
        final Student student = new Student(0, "name", "address", "rollNo");
        when(mockStudentservices.addStudent(new Student(0, "name", "address", "rollNo"))).thenReturn(student);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/student/")
                        .content(asJsonString(student)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    private String asJsonString(Student student) {
        try{
            return new ObjectMapper().writeValueAsString(student);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return e;
    }

    @Test
    void testGetAllStudent() throws Exception {
        // Setup
        // Configure StudentServices.getAllStudent(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollNo"));
        when(mockStudentservices.getAllStudent()).thenReturn(students);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testGetAllStudent_StudentServicesReturnsNoItems() throws Exception {
        // Setup
        when(mockStudentservices.getAllStudent()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testGetAllStudentById() throws Exception {
        // Setup
        // Configure StudentServices.getStudentById(...).
        final Student student = new Student(0, "name", "address", "rollNo");
        when(mockStudentservices.getStudentById(0)).thenReturn(student);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/{student-id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testDeleteStudentById() throws Exception {
        // Setup
        // Configure StudentServices.deleteStudentById(...).
        final Student student = new Student(0, "name", "address", "rollNo");
        when(mockStudentservices.deleteStudentById(0)).thenReturn(student);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/student/{student-id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testGetStudentByName() throws Exception {
        // Setup
        // Configure StudentServices.getStudentByName(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollNo"));
        when(mockStudentservices.getStudentByName("name")).thenReturn(students);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/name/{student-id}", "name")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void testGetStudentByName_StudentServicesReturnsNoItems() throws Exception {
        // Setup
        when(mockStudentservices.getStudentByName("name")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/student/name/{student-id}", "name")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}
