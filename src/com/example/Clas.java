package com.example;

import java.util.List;

/**
 * Created by LunaFlores on 1/6/17.
 */
public class Clas {
    private int clas_id;
    private String name;
    private List<Student> listStudent;

    public int getClas_id() {
        return clas_id;
    }

    public void setClas_id(int clas_id) {
        this.clas_id = clas_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<Student> listStudent) {
        this.listStudent = listStudent;
    }

    @Override
    public String toString() {
        return "Clas{" +
                "clas_id=" + clas_id +
                ", name='" + name + '\'' +
                ", listStudent=" + listStudent +
                '}';
    }
}
