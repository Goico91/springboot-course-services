package com.course.springboot.services.controllers.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

public class EmployeeDTO implements Serializable {

    @NotNull(message = "Name can not be null")
    private String name;

    @NotBlank(message = "Surname can not be null")
    private String surname;

    @NotBlank(message = "Password can not be null")
    private String password;

    @Min(value = 18, message = "Min age is 18 years")
    @Max(value = 100, message = "Max age is 100 years")
    private Integer age;

    @Pattern(regexp = "[a-zA-Z]+")
    private String rol;

    private List<String> knowledge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<String> getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(List<String> knowledge) {
        this.knowledge = knowledge;
    }
}
