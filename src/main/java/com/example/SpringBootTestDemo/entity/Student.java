package com.example.SpringBootTestDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    @Id
    @Column(name="ID")

    private Integer Id;
    @NotBlank(message="the column name should not blank")
    @Column(name="NAME")

    private String name;
    @Column(name="ADDRESS")
    private String address;
    @Column(name="ROLLNO")
    private String rollNo;
}
