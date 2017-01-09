package com.example;

import java.util.List;

/**
 * Created by LunaFlores on 1/6/17.
 */
public class Student {
    private int student_Id;
    private String name;
    private List<Clas> listClass;

    public int getStudent_Id() {
        return student_Id;
    }

    public void setStudent_Id(int student_Id) {
        this.student_Id = student_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Clas> getListClass() {
        return listClass;
    }

    public void setListClass(List<Clas> listClass) {
        this.listClass = listClass;
    }

    @Override
    public String toString() {
        return "Clas{" +
                "student_Id=" + student_Id +
                ", name='" + name + '\'' +
                ", listClass=" + listClass +
                '}';
    }
}
