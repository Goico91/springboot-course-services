package com.course.springboot.services.controllers.dto;

import java.io.Serializable;
import java.util.List;

public class ListEmployeesDTO implements Serializable {
    private int idEmployee;

    private String name;

    private String surname;

    private List<String> knowledge;

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

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

    public List<String> getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(List<String> knowledge) {
        this.knowledge = knowledge;
    }
}
