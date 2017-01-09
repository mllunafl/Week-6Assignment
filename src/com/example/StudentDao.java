package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LunaFlores on 1/6/17.
 */
public class StudentDao {

    public static int areThereStudets (int student_id, Connection conn) {
        int areThere = 0;
        try {
            String students = "select * from student_clas where student_id = ?";
            PreparedStatement pStmnt = conn.prepareStatement(students);
            pStmnt.setInt(1,student_id);
            ResultSet rsult = pStmnt.executeQuery();
            if (!rsult.isBeforeFirst()){
                areThere = 1;
            }
            return areThere;
        } catch (SQLException e){
            DatabaseUtils.printSQLException(e);
        }
        return areThere;
    }

    public static void deleteStudent(int student_id, Connection conn) {
        try {
            String deleteStu = "DELETE from student WHERE student_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(deleteStu);
            pStmt.setInt(1,student_id);
            pStmt.execute();
        } catch (SQLException e) {
            DatabaseUtils.printSQLException(e);
        }
    }

    public static List<Student> students (Connection conn) {
        List<Student> students = new ArrayList<>();
        try {
            String getAllstudents = "select * from student";
            PreparedStatement pStmt = conn.prepareStatement(getAllstudents);
            pStmt.executeQuery();
            ResultSet rsult = pStmt.executeQuery();
            while (rsult.next()){
                Student student = new Student();
                student.setName(rsult.getString("name"));
                student.setStudent_Id(rsult.getInt("student_id"));
                students.add(student);
            }
            return students;
        } catch (SQLException e){
            DatabaseUtils.printSQLException(e);
        }
        return students;
    }


    public static void createStudent(String name, Connection conn){

        try {
            String createStu = "INSERT into student (name) VALUES (?)";
            PreparedStatement pStmt = conn.prepareStatement(createStu);
            pStmt.setString(1,name);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            DatabaseUtils.printSQLException(e);
        }
    }

    public static String getStudentName (int student_id, Connection conn) {
        String studentName = null;
        try {
            String getStudentname = "select name from student WHERE student_id = ?";
            PreparedStatement pStment = conn.prepareStatement(getStudentname);
            pStment.setInt(1,student_id);
            ResultSet rs = pStment.executeQuery();
            if (rs.next()){
                studentName = rs.getString("name");
                return studentName;
            }
        } catch (SQLException e){
            DatabaseUtils.printSQLException(e);
        }
        return studentName;
    }

    public static void main(String[] args) {

    }
}
